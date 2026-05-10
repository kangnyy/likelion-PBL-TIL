package likelion_PBL_TIL.week05.class5.package1;

import likelion_PBL_TIL.week05.class5.role.Role;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    private final List<Role> members = new ArrayList<>();

    public void save(Role member) {
        members.add(member);
    }

    public Role findByName(String name) {
        return members.stream()
                .filter(m -> m.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<Role> findAll() {
        return new ArrayList<>(members);
    }

    public boolean isDuplicate(String name) {
        return findByName(name) != null;
    }
}