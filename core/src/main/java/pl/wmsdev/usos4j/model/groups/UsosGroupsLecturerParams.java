package pl.wmsdev.usos4j.model.groups;

import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosGroupsLecturerParams(String userId, UsosLanguage lang, Boolean activeTerms) implements UsosParams {

    public static UsosGroupsLecturerParamsBuilder builder(String userId) {
        return new UsosGroupsLecturerParamsBuilder().userId(userId);
    }

}