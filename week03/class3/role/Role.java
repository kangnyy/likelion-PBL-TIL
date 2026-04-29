package likelion_PBL_TIL.week03.class3.role;

import likelion_PBL_TIL.week03.class3.policy.SubmissionPolicy;

public abstract class Role {
    private String name;
    private String major;
    private int generation;
    private String part;

    public Role(String name, String major, int generation, String part) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
    }

    public String getCommonInfo() {
        return String.format("👤 이름: %s | 🎓 전공: %s | 📌 기수: %d | 💻 파트: %s", name, major, generation, part);
    }

    public abstract String getRoleName();
    public abstract String getRoleDetails();
    protected abstract SubmissionPolicy getPolicy();

    public boolean checkSubmission(){
        return getPolicy().canSubmit();
    }





}

