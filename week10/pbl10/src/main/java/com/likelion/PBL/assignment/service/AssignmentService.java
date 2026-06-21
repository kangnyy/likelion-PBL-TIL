package com.likelion.PBL.assignment.service;

import com.likelion.PBL.assignment.domain.Assignment;
import com.likelion.PBL.assignment.dto.AssignmentCreateRequest;
import com.likelion.PBL.assignment.dto.AssignmentUpdateRequest;
import com.likelion.PBL.assignment.repository.AssignmentRepository;
import com.likelion.PBL.global.exception.AssignmentNotFoundException;
import com.likelion.PBL.global.exception.MemberNotFoundException;
import com.likelion.PBL.member.domain.Member;
import com.likelion.PBL.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final MemberRepository memberRepository;

    public AssignmentService(
            AssignmentRepository assignmentRepository,
            MemberRepository memberRepository
    ) {
        this.assignmentRepository = assignmentRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Assignment createAssignment(Long memberId, AssignmentCreateRequest request) {
        Member member = findMember(memberId);

        Assignment assignment = new Assignment(request.getTitle(), request.getDescription(), member);
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> findAll() {
        return assignmentRepository.findAll();
    }

    public List<Assignment> findByMemberId(Long memberId) {
        findMember(memberId);
        return assignmentRepository.findByMemberId(memberId);
    }

    public Assignment findById(Long id) {
        return findAssignment(id);
    }

    public List<Assignment> searchByTitle(String keyword) {
        return assignmentRepository.findByTitleContaining(keyword);
    }

    @Transactional
    public Assignment updateAssignment(Long id, AssignmentUpdateRequest request) {
        Assignment assignment = findAssignment(id);

        assignment.updateInfo(request.getTitle(), request.getDescription());
        return assignmentRepository.save(assignment);
    }

    @Transactional
    public void deleteAssignment(Long id) {
        Assignment assignment = findAssignment(id);
        assignmentRepository.delete(assignment);
    }

    private Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("멤버를 찾을 수 없습니다. id:" + memberId));
    }

    private Assignment findAssignment(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException("과제를 찾을 수 없습니다. id:" + id));
    }
}
