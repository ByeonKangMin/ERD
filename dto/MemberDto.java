package com.dw.ERD.dto;

import com.dw.ERD.enums.MemberType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class MemberDto {
    private long memberId;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String address;
    private MemberType memberType;
}
