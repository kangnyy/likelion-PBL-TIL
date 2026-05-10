package likelion_PBL_TIL.week05.class5.package2;

import likelion_PBL_TIL.week05.class5.role.Lion;
import likelion_PBL_TIL.week05.class5.role.Role;
import likelion_PBL_TIL.week05.class5.role.Staff;
import java.util.Arrays;
import java.util.List;

public class MockMemberRepository implements MemberRepository {

    private final List<Role> dummyData;

    public MockMemberRepository() {
        this.dummyData = Arrays.asList(
                new Lion("김사자", "화학과", 14, "백엔드", "202200100"),
                new Staff("최운영", "컴퓨터공학과", 13, "프론트엔드", "파트장")
        );
    }

    @Override
    public void save(Role member) {
    }

    @Override
    public Role findByName(String name) {
        return dummyData.stream()
                .filter(m -> m.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Role> findAll() { return dummyData; }

    @Override
    public boolean isDuplicate(String name) { return findByName(name) != null; }
}