package dev.wms.usos4j.model.terms;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosTermsParams(String termIds) implements UsosParams {

    public static UsosTermsParamsBuilder builder(String... termIds) {
        return new UsosTermsParamsBuilder().termIds(String.join("|", termIds));
    }

}