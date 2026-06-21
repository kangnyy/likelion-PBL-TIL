package com.likelion.PBL.member.service;

import com.likelion.PBL.global.exception.DuplicateMemberException;
import com.likelion.PBL.global.exception.MemberNotFoundException;
import com.likelion.PBL.member.domain.Member;
import com.likelion.PBL.member.domain.RoleType;
import com.likelion.PBL.member.dto.LionCreateRequest;
import com.likelion.PBL.member.dto.LionUpdateRequest;
import com.likelion.PBL.member.dto.StaffCreateRequest;
import com.likelion.PBL.member.dto.StaffUpdateRequest;
import com.likelion.PBL.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Member createLion(LionCreateRequest request) {
        if (repository.existsByName(request.getName())) {
            throw new DuplicateMemberException("이미 존재하는 이름입니다. name: " + request.getName());
        }

        Member member = new Member(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                RoleType.LION,
                request.getStudentId(),
                null
        );
        return repository.save(member);
    }

    @Transactional
    public Member createStaff(StaffCreateRequest request) {
        if (repository.existsByName(request.getName())) {
            throw new DuplicateMemberException("이미 존재하는 이름입니다. name: " + request.getName());
        }

        Member member = new Member(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                RoleType.STAFF,
                null,
                request.getPosition()
        );
        return repository.save(member);
    }

    public Member findById(Long id) {
        return findMember(id);
    }

    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    public List<Member> getMembersByPart(String part) {
        return repository.findByPart(part);
    }

    @Transactional
    public Member updateLion(Long id, LionUpdateRequest request) {
        Member member = findMember(id);

        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updateStudentId(request.getStudentId());
        return repository.save(member);
    }

    @Transactional
    public Member updateStaff(Long id, StaffUpdateRequest request) {
        Member member = findMember(id);

        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updatePosition(request.getPosition());
        return repository.save(member);
    }

    @Transactional
    public void deleteMember(Long id) {
        Member member = findMember(id);
        repository.delete(member);
    }

    public boolean isEmpty() {
        return repository.findAll().isEmpty();
    }

    private Member findMember(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("멤버를 찾을 수 없습니다. id:" + id));
    }
}
