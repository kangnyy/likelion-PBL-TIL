package likelion_PBL_TIL.week03.class3.role;

import likelion_PBL_TIL.week03.class3.policy.AssignmentPolicy;

public abstract class Member {
    private String name;
    private String major;
    private int generation;
    private String part;

    public Member(String name, String major, int generation, String part) {
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
    protected abstract AssignmentPolicy getPolicy();

    public boolean checkSubmission(){
        return getPolicy().canSubmit();
    }





}

