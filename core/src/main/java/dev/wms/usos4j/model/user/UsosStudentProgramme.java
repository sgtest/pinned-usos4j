package dev.wms.usos4j.model.user;

import dev.wms.usos4j.model.common.UsosLocalizedString;

public record UsosStudentProgramme(String id, UsosStudentProgramme.Programme programme) {

    public record Programme(String id, UsosLocalizedString description) {
    }
}