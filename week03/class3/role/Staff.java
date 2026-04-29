package likelion_PBL_TIL.week03.class3.role;

import likelion_PBL_TIL.week03.class3.policy.SubmissionPolicy;
import likelion_PBL_TIL.week03.class3.policy.StaffSubmissionPolicy;

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
