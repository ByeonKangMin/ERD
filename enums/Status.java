package com.dw.ERD.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    ACTIVE("대여중"),
    RETURNED("반납"),
    OVERDUE("연체");
    private final  String status;

}
