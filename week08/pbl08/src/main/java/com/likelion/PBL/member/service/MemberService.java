package com.likelion.PBL.member.service;

import com.likelion.PBL.member.domain.Member;
import com.likelion.PBL.member.domain.RoleType;
import com.likelion.PBL.member.dto.LionCreateRequest;
import com.likelion.PBL.member.dto.LionUpdateRequest;
import com.likelion.PBL.member.dto.StaffCreateRequest;
import com.likelion.PBL.member.dto.StaffUpdateRequest;
import com.likelion.PBL.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Member createLion(LionCreateRequest request) {
        if (repository.existsByName(request.getName())) {
            return null;
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

    public Member createStaff(StaffCreateRequest request) {
        if (repository.existsByName(request.getName())) {
            return null;
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
        return repository.findById(id).orElse(null);
    }

    public Member searchByName(String name) {
        return repository.findByName(name);
    }

    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    public Member updateLion(Long id, LionUpdateRequest request) {
        Member member = repository.findById(id).orElse(null);
        if (member == null) {
            return null;
        }

        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updateStudentId(request.getStudentId());
        return repository.save(member);
    }

    public Member updateStaff(Long id, StaffUpdateRequest request) {
        Member member = repository.findById(id).orElse(null);
        if (member == null) {
            return null;
        }

        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updatePosition(request.getPosition());
        return repository.save(member);
    }

    public boolean deleteMember(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }

    public boolean isEmpty() {
        return repository.findAll().isEmpty();
    }
}
