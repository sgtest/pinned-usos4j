package dev.wms.usos4j.model.registrations;

import dev.wms.usos4j.model.common.UsosParams;
import lombok.Builder;

@Builder
public record UsosFacultyRegistrationsParams(String facultyId, Boolean activeOnly, Boolean userRelated) implements UsosParams {

    public static UsosFacultyRegistrationsParamsBuilder builder(String facultyId) {
        return new UsosFacultyRegistrationsParamsBuilder().facultyId(facultyId);
    }

}
