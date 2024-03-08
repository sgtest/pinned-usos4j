package dev.wms.usos4j.model.payments;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosInstallmentParams(String installmentPlanId, int number) implements UsosParams {

    public static UsosInstallmentParamsBuilder builder(String installmentPlanId, int number) {
        return new UsosInstallmentParamsBuilder().installmentPlanId(installmentPlanId).number(number);
    }

}
