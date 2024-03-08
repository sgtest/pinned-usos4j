package dev.wms.usos4j.client;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth10aService;
import dev.wms.usos4j.api.apiref.UsosApiRefAPI;
import dev.wms.usos4j.api.apisrv.UsosApiSrvAPI;
import dev.wms.usos4j.api.calendar.UsosCalendarAPI;
import dev.wms.usos4j.api.cards.UsosCardsAPI;
import dev.wms.usos4j.api.courses.UsosCoursesAPI;
import dev.wms.usos4j.api.credits.UsosCreditsAPI;
import dev.wms.usos4j.api.fac.UsosFacultiesServerAPI;
import dev.wms.usos4j.api.feedback.UsosFeedbackAPI;
import dev.wms.usos4j.api.groups.UsosGroupsAPI;
import dev.wms.usos4j.api.mailing.UsosMailingAPI;
import dev.wms.usos4j.api.mailing.UsosMailingServerAPI;
import dev.wms.usos4j.api.generic.UsosGenericServerAPI;
import dev.wms.usos4j.api.generic.UsosGenericUserAPI;
import dev.wms.usos4j.api.news.UsosNewsAPI;
import dev.wms.usos4j.api.payments.UsosPaymentsAPI;
import dev.wms.usos4j.api.phones.UsosPhonesAPI;
import dev.wms.usos4j.api.photos.UsosPhotosAPI;
import dev.wms.usos4j.api.plctests.UsosPlacementTestsAPI;
import dev.wms.usos4j.api.registrations.UsosRegistrationsAPI;
import dev.wms.usos4j.api.terms.UsosTermsAPI;
import dev.wms.usos4j.api.theses.UsosThesesAPI;
import dev.wms.usos4j.api.timetable.UsosTimeTableAPI;
import dev.wms.usos4j.model.auth.UsosAccessToken;
import dev.wms.usos4j.model.auth.UsosRequestToken;
import dev.wms.usos4j.model.auth.UsosScope;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.SneakyThrows;

import java.util.Collections;
import java.util.Set;

public class Usos {

    @Getter
    private final OAuth10aService oAuthService;
    private final String baseUrl;

    @Builder
    public Usos(String baseUrl, String callbackUrl, String consumerKey, String consumerSecret, boolean debug, @Singular Set<UsosScope> scopes) {
        var serviceBuilder = new ServiceBuilder(consumerKey)
                .apiSecret(consumerSecret);
        if (callbackUrlGiven(callbackUrl)) {
            serviceBuilder.callback(callbackUrl);
        }

        if (debug) serviceBuilder.debug();
        this.oAuthService = serviceBuilder.build(new ScribeBasedUsosAPI(baseUrl, scopes));
        this.baseUrl = baseUrl;
    }

    private boolean callbackUrlGiven(String callbackUrl) {
        return callbackUrl != null && !callbackUrl.isEmpty();
    }

    public static Usos serverOnly(String baseUrl, String consumerKey, String consumerSecret) {
        return new Usos(baseUrl, "", consumerKey, consumerSecret,false, Collections.emptySet());
    }

    @SneakyThrows
    public UsosRequestToken getRequestToken() {
        return UsosRequestToken.from(oAuthService.getRequestToken());
    }

    @SneakyThrows
    public UsosAccessToken getAccessToken(UsosRequestToken requestToken, String verifier) {
        return UsosAccessToken.from(oAuthService.getAccessToken(requestToken.toFrameworkToken(), verifier));
    }

    public String getAuthorizationUrl(UsosRequestToken requestToken) {
        return oAuthService.getAuthorizationUrl(requestToken.toFrameworkToken());
    }

    public UsosUserAPI getUserApi(UsosAccessToken accessToken) {
        var requestFactory = new ScribeOAuthRequestFactory(baseUrl);
        return new UsosUserAPI(new UsosUsersAPI(oAuthService, requestFactory, accessToken),
                new UsosCoursesAPI(oAuthService, requestFactory, accessToken),
                new UsosCalendarAPI(oAuthService, requestFactory, accessToken),
                new UsosCardsAPI(oAuthService, requestFactory, accessToken),
                new UsosPhonesAPI(oAuthService, requestFactory, accessToken),
                new UsosTimeTableAPI(oAuthService, requestFactory, accessToken),
                new UsosPhotosAPI(oAuthService, requestFactory, accessToken),
                new UsosNewsAPI(oAuthService, requestFactory, accessToken),
                new UsosCreditsAPI(oAuthService, requestFactory, accessToken),
                new UsosFeedbackAPI(oAuthService, requestFactory, accessToken),
                new UsosMailingAPI(oAuthService, requestFactory, accessToken),
                new UsosThesesAPI(oAuthService, requestFactory, accessToken),
                new UsosGenericUserAPI(oAuthService, requestFactory, accessToken),
                new UsosGroupsAPI(oAuthService, requestFactory, accessToken),
                new UsosPaymentsAPI(oAuthService, requestFactory, accessToken),
                new UsosPlacementTestsAPI(oAuthService, requestFactory, accessToken),
                new UsosRegistrationsAPI(oAuthService, requestFactory, accessToken));
    }

    public UsosServerAPI getServerApi() {
        var requestFactory = new ScribeOAuthRequestFactory(baseUrl);
        return new UsosServerAPI(new UsosApiSrvAPI(oAuthService, requestFactory),
                new UsosApiRefAPI(oAuthService, requestFactory),
                new UsosPhonesAPI(oAuthService, requestFactory, null),
                new UsosTimeTableAPI(oAuthService, requestFactory, null),
                new UsosFeedbackAPI(oAuthService, requestFactory, null),
                new UsosMailingServerAPI(oAuthService, requestFactory),
                new UsosGenericServerAPI(oAuthService, requestFactory),
                new UsosTermsAPI(oAuthService, requestFactory),
                new UsosFacultiesServerAPI(oAuthService, requestFactory),
                new UsosGroupsAPI(oAuthService, requestFactory, null),
                new UsosPlacementTestsAPI(oAuthService, requestFactory, null));
    }

}
