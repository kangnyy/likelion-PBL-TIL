package likelion_PBL_TIL.week05.class5.package2;

import likelion_PBL_TIL.week05.class5.role.Role;
import java.util.List;

public interface MemberRepository {
    void save(Role member);
    Role findByName(String name);
    List<Role> findAll();
    boolean isDuplicate(String name);
}