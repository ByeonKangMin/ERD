package com.dw.ERD.controller;

import com.dw.ERD.dto.MemberDto;
import com.dw.ERD.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping("/post/members")
    public ResponseEntity<MemberDto> insertMember (@RequestBody MemberDto memberDto) {
        return new ResponseEntity<>(memberService.insertMember(memberDto),HttpStatus.CREATED);
    }

    @GetMapping("/get/members")
    public ResponseEntity<Map<String, Object>> getMembers(@RequestParam(defaultValue = "1") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        int offset = (page - 1) * size;
        List<MemberDto> members = memberService.getMembers(offset, size);
        int total = memberService.getTotalCount();

        Map<String, Object> response = new HashMap<>();
        response.put("members", members);
        response.put("currentPage", page);
        response.put("pageSize", size);
        response.put("totalItems", total);
        response.put("totalPages", (int) Math.ceil((double) total / size));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/members/{memberId}")
    public ResponseEntity<MemberDto> getMemberById (@PathVariable long memberId) {
        return new ResponseEntity<>(memberService.getMemberById(memberId),HttpStatus.OK);
    }

    @PutMapping("/put/members/{memberId}")
    public ResponseEntity<MemberDto> modifyMemberById(@PathVariable long memberId, @RequestBody MemberDto memberDto) {
        return new ResponseEntity<>(memberService.modifyMemberById(memberId, memberDto), HttpStatus.OK);
    }
}
