package likelion_PBL_TIL.week04.class4.role;

import likelion_PBL_TIL.week04.class4.policy.StaffSubmissionPolicy;
import likelion_PBL_TIL.week04.class4.policy.SubmissionPolicy;

public class Staff extends Role {
    private String position;

    public Staff(String name, String major, int generation, String part, String position) {
        super(name, major, generation, part);
        this.position = position;
    }

    @Override
    public String getRoleName() {
        return "운영진";
    }

    @Override
    public String getRoleDetails() {
        return "⭐ 직책: " + position;
    }

    @Override
    protected SubmissionPolicy getPolicy() {
        return new StaffSubmissionPolicy();
    }
}
