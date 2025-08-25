package com.dw.ERD.service;

import com.dw.ERD.dto.MemberDto;
import com.dw.ERD.enums.MemberType;
import com.dw.ERD.mapper.MemberMapper;
import com.dw.ERD.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    MemberMapper memberMapper;

    @Transactional
    public MemberDto insertMember (MemberDto memberDto) {
        if (memberMapper.countByMemberEmail(memberDto.getEmail()) > 0) {
            throw new IllegalArgumentException("이메일이 중복되었습니다.");
        }
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        if (memberDto.getMemberType() == null) {
            memberDto.setMemberType(MemberType.GENERAL);
        }
        memberMapper.insertMember(memberDto);
        return memberDto;
    }

    @Transactional
    public List<MemberDto> getMembers(int offset, int limit) {
        return memberMapper.getMembers(offset, limit);
    }
    public int getTotalCount() {
        return memberMapper.countAllMembers();
    }

    @Transactional
    public MemberDto getMemberById(long memberId) {
        MemberDto member = memberMapper.getMemberById(memberId);
        if (member == null) {
            throw new IllegalArgumentException("회원이 존재하지 않습니다. memberId" + memberId);
        }
        return member;
    }

    public MemberDto modifyMemberById(long memberId, MemberDto memberDto) {
        MemberDto existingMember = memberMapper.getMemberById(memberId);
        if (existingMember == null) {
            throw new IllegalArgumentException("회원이 존재하지 않습니다.");
        }

        memberDto.setEmail(existingMember.getEmail());

        if (memberDto.getPassword() != null && !memberDto.getPassword().isEmpty()) {
            memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        } else {
            memberDto.setPassword(existingMember.getPassword());
        }

        memberDto.setMemberId(memberId);

        memberMapper.modifyMemberById(memberDto);

        return memberMapper.getMemberById(memberId);
    }
}
