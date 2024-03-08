package dev.wms.usos4j.model.user;

import dev.wms.usos4j.model.common.UsosCommonObject;

public record UsosUserShort(Integer id, String firstName, String studentNumber, String middleNames, String lastName,
                            Integer studentStatus, Integer staffStatus) implements UsosCommonObject {
}
