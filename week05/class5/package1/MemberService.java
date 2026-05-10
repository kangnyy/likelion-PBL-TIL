package likelion_PBL_TIL.week05.class5.package1;

import likelion_PBL_TIL.week05.class5.role.Role;
import java.util.List;

public class MemberService {

    private final MemberRepository repository = new MemberRepository();

    public boolean registerMember(Role member) {
        if (repository.isDuplicate(member.getName())) {
            return false;
        }
        repository.save(member);
        return true;
    }

    public Role findMember(String name) {
        return repository.findByName(name);
    }

    public List<Role> getAllMembers() {
        return repository.findAll();
    }
}