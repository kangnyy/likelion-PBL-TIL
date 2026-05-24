package com.hufslikelion.pbl07.service;

import com.hufslikelion.pbl07.domain.role.Lion;
import com.hufslikelion.pbl07.domain.role.Role;
import com.hufslikelion.pbl07.domain.role.Staff;
import com.hufslikelion.pbl07.dto.LionCreateRequest;
import com.hufslikelion.pbl07.dto.LionUpdateRequest;
import com.hufslikelion.pbl07.dto.StaffCreateRequest;
import com.hufslikelion.pbl07.dto.StaffUpdateRequest;
import com.hufslikelion.pbl07.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Lion createLion(LionCreateRequest request) {
        if (repository.existsByName(request.getName())) {
            return null;
        }

        Lion lion = new Lion(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getStudentId()
        );
        repository.save(lion);
        return lion;
    }

    public Staff createStaff(StaffCreateRequest request) {
        if (repository.existsByName(request.getName())) {
            return null;
        }

        Staff staff = new Staff(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getPosition()
        );
        repository.save(staff);
        return staff;
    }

    public Role searchByName(String name) {
        return repository.findByName(name);
    }

    public List<Role> getAllMembers() {
        return repository.findAll();
    }

    public Lion updateLion(String name, LionUpdateRequest request) {
        if (!repository.existsByName(name)) {
            return null;
        }

        Lion lion = new Lion(
                name,
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getStudentId()
        );
        repository.updateByName(name, lion);
        return lion;
    }

    public Staff updateStaff(String name, StaffUpdateRequest request) {
        if (!repository.existsByName(name)) {
            return null;
        }

        Staff staff = new Staff(
                name,
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getPosition()
        );
        repository.updateByName(name, staff);
        return staff;
    }

    public boolean deleteMember(String name) {
        return repository.deleteByName(name);
    }

    public boolean isEmpty() {
        return repository.findAll().isEmpty();
    }
}
