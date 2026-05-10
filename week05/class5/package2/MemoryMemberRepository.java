package likelion_PBL_TIL.week05.class5.package2;

import likelion_PBL_TIL.week05.class5.role.Role;
import java.util.ArrayList;
import java.util.List;

public class MemoryMemberRepository implements MemberRepository {

    private final List<Role> members = new ArrayList<>();

    @Override
    public void save(Role member) { members.add(member); }

    @Override
    public Role findByName(String name) {
        return members.stream()
                .filter(m -> m.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Role> findAll() { return new ArrayList<>(members); }

    @Override
    public boolean isDuplicate(String name) { return findByName(name) != null; }
}