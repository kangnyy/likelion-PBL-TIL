package com.likelion.PBL.assignment.controller;

import com.likelion.PBL.assignment.domain.Assignment;
import com.likelion.PBL.assignment.dto.AssignmentCreateRequest;
import com.likelion.PBL.assignment.dto.AssignmentResponse;
import com.likelion.PBL.assignment.dto.AssignmentUpdateRequest;
import com.likelion.PBL.assignment.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AssignmentController {
    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/members/{memberId}/assignments")
    public ResponseEntity<AssignmentResponse> createAssignment(
            @PathVariable Long memberId,
            @RequestBody AssignmentCreateRequest request
    ) {
        Assignment assignment = assignmentService.createAssignment(memberId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(AssignmentResponse.from(assignment));
    }

    @GetMapping("/assignments")
    public ResponseEntity<List<AssignmentResponse>> findAllAssignments() {
        List<AssignmentResponse> responses = assignmentService.findAll()
                .stream()
                .map(AssignmentResponse::from)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/members/{memberId}/assignments")
    public ResponseEntity<List<AssignmentResponse>> findAssignmentsByMember(@PathVariable Long memberId) {
        List<AssignmentResponse> responses = assignmentService.findByMemberId(memberId)
                .stream()
                .map(AssignmentResponse::from)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/assignments/search")
    public ResponseEntity<List<AssignmentResponse>> searchAssignmentsByTitle(
            @RequestParam String keyword
    ) {
        List<AssignmentResponse> responses = assignmentService.searchByTitle(keyword)
                .stream()
                .map(AssignmentResponse::from)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> findAssignment(@PathVariable Long id) {
        Assignment assignment = assignmentService.findById(id);
        return ResponseEntity.ok(AssignmentResponse.from(assignment));
    }

    @PutMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> updateAssignment(
            @PathVariable Long id,
            @RequestBody AssignmentUpdateRequest request
    ) {
        Assignment assignment = assignmentService.updateAssignment(id, request);
        return ResponseEntity.ok(AssignmentResponse.from(assignment));
    }

    @DeleteMapping("/assignments/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
