package com.hufslikelion.pbl07.controller;

import com.hufslikelion.pbl07.domain.role.Lion;
import com.hufslikelion.pbl07.domain.role.Role;
import com.hufslikelion.pbl07.domain.role.Staff;
import com.hufslikelion.pbl07.dto.LionCreateRequest;
import com.hufslikelion.pbl07.dto.LionResponse;
import com.hufslikelion.pbl07.dto.LionUpdateRequest;
import com.hufslikelion.pbl07.dto.StaffCreateRequest;
import com.hufslikelion.pbl07.dto.StaffResponse;
import com.hufslikelion.pbl07.dto.StaffUpdateRequest;
import com.hufslikelion.pbl07.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/lions")
    public ResponseEntity<LionResponse> createLion(@RequestBody LionCreateRequest request) {
        Lion lion = memberService.createLion(request);
        if (lion == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(LionResponse.from(lion));
    }

    @PostMapping("/staffs")
    public ResponseEntity<StaffResponse> createStaff(@RequestBody StaffCreateRequest request) {
        Staff staff = memberService.createStaff(request);
        if (staff == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(StaffResponse.from(staff));
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> findMember(@PathVariable String name) {
        Role member = memberService.searchByName(name);
        if (member == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(toResponse(member));
    }

    @PutMapping("/lions/{name}")
    public ResponseEntity<LionResponse> updateLion(
            @PathVariable String name,
            @RequestBody LionUpdateRequest request
    ) {
        Lion lion = memberService.updateLion(name, request);
        if (lion == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(LionResponse.from(lion));
    }

    @PutMapping("/staffs/{name}")
    public ResponseEntity<StaffResponse> updateStaff(
            @PathVariable String name,
            @RequestBody StaffUpdateRequest request
    ) {
        Staff staff = memberService.updateStaff(name, request);
        if (staff == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(StaffResponse.from(staff));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteMember(@PathVariable String name) {
        boolean deleted = memberService.deleteMember(name);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    private Object toResponse(Role member) {
        if (member instanceof Lion lion) {
            return LionResponse.from(lion);
        }

        Staff staff = (Staff) member;
        return StaffResponse.from(staff);
    }
}
