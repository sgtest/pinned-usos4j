package dev.wms;

import dev.wms.usos4j.model.groups.*;
import org.junit.jupiter.api.Test;

public class UsosGroupsAPITest extends UsosAPIBaseTest {


    @Test
    void commonGroups() {
        log(userApi.groups().commonGroups(UsosCommonGroupParams.builder("622").build()));
    }

    @Test
    void isLecturer() {
        log(userApi.groups().isLecturer(UsosGroupsIsLecturerParams.builder(53875, 1, "622").build()));
    }

    @Test
    void isLecturerWithToken() {
        log(userApi.groups().isLecturer(UsosGroupsIsLecturerWithTokenParams.builder(53875, 1).build()));
    }

    @Test
    void isParticipant() {
        log(userApi.groups().isParticipant(UsosGroupsIsParticipantParams.builder(53875, 1).build()));
    }

    @Test
    void lecturer() {
        log(userApi.groups().lecturer(UsosGroupsLecturerParams.builder("622").build()));
    }

    @Test
    void lecturerWithToken() {
        log(userApi.groups().lecturer());
    }

    @Test
    void participant() {
        log(userApi.groups().participant(UsosGroupsParticipantParams.builder().build()));
    }

    @Test
    void user() {
        log(userApi.groups().user(UsosGroupsUserParams.builder().build()));
    }

    @Test
    void groups() {
        log(userApi.groups().groups(UsosGroupsParams.helper().addGroup(53875, 1).build()));
    }

    @Test
    void classGroup() {
        log(userApi.groups().classGroup(UsosClassGroupParams.builder(53875, 1).build()));
    }

    @Test
    void classGroupWithToken() {
        log(userApi.groups().classGroup(UsosClassGroupParamsWithToken.builder(53875, 1).build()));
    }

}
