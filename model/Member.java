package com.dw.ERD.model;

import com.dw.ERD.dto.MemberDto;
import com.dw.ERD.enums.MemberType;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Member {
    private long memberId;              //회원ID
    private String email;                 //이메일
    private String password;             //비밀번호
    private String name;                 //이름
    private String phone;                //전화번호
    private String address;              //주소
    private MemberType memberType;       //회원유형
    private LocalDateTime createdAt;    //생성일시
    private LocalDateTime updatedAt;//수정일시

    public MemberDto toDto(){
        return new MemberDto(
        this.memberId,
        this.email,
        this.password,
        this.name,
        this.phone,
        this.address,
        this.memberType
        );
    }
}
