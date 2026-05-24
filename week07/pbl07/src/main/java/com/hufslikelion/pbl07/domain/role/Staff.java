package com.hufslikelion.pbl07.domain.role;

import com.hufslikelion.pbl07.policy.StaffSubmissionPolicy;
import com.hufslikelion.pbl07.policy.SubmissionPolicy;

public class Staff extends Role {
    private final String position;

    public Staff(String name, String major, int generation, String part, String position) {
        super(name, major, generation, part);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public SubmissionPolicy submissionPolicy() {
        return new StaffSubmissionPolicy();
    }

    @Override
    public String roleName() {
        return "운영진";
    }

    @Override
    public String getInfo() {
        return "이름: " + getName()
                + " | 전공: " + getMajor()
                + " | 기수: " + getGeneration()
                + " | 파트: " + getPart()
                + " | 직책: " + position;
    }
}
