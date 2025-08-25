package com.dw.ERD.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberType {
    STUDENT("학생"),
    TEACHER("선생님"),
    GENERAL("일반인");

    private final String memberType;
}
