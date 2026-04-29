package likelion_PBL_TIL.week03.class3.role;

import likelion_PBL_TIL.week03.class3.policy.SubmissionPolicy;
import likelion_PBL_TIL.week03.class3.policy.LionSubmissionPolicy;

public class Lion extends Role {
    private String studentId;

    public Lion(String name, String major, int generation, String part, String studentId) {
        super(name, major, generation, part);
        this.studentId = studentId;
    }

    @Override
    public String getRoleName() {
        return "아기사자";
    }


    @Override
    public String getRoleDetails() {
        return "🆔 학번: " + studentId;
    }

    @Override
    protected SubmissionPolicy getPolicy() {
        return new LionSubmissionPolicy();
    }
}
