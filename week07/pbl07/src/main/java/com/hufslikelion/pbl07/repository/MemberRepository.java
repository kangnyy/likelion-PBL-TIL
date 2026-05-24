package com.hufslikelion.pbl07.repository;

import com.hufslikelion.pbl07.domain.role.Role;

import java.util.List;

public interface MemberRepository {
    void save(Role member);

    Role findByName(String name);

    List<Role> findAll();

    void updateByName(String name, Role member);

    boolean deleteByName(String name);

    boolean existsByName(String name);
}
