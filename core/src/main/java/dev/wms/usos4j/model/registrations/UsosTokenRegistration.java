package dev.wms.usos4j.model.registrations;

import dev.wms.usos4j.model.common.UsosLocalizedString;
import dev.wms.usos4j.model.common.UsosObject;
import dev.wms.usos4j.model.fac.UsosFaculty;

public record UsosTokenRegistration (String id, UsosLocalizedString description, UsosLocalizedString message,
                                     String status, UsosFaculty faculty, Boolean isLinkageRequired,
                                     String shortDescription) implements UsosObject {
}
