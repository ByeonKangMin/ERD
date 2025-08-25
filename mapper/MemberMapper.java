package com.dw.ERD.mapper;

import com.dw.ERD.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    void insertMember(MemberDto memberDto);
    int countByMemberEmail(String email);

    List<MemberDto> getMembers(@Param("offset") int offset, @Param("limit") int limit);
    int countAllMembers();
    MemberDto getMemberById(@Param("memberId") long memberId);
    void modifyMemberById(MemberDto memberDto);
}
