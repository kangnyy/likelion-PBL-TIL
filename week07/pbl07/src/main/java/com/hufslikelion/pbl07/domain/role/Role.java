package com.hufslikelion.pbl07.domain.role;

import com.hufslikelion.pbl07.policy.SubmissionPolicy;

public abstract class Role {
    private final String name;
    private final String major;
    private final int generation;
    private final String part;

    protected Role(String name, String major, int generation, String part) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
    }

    public final String getName() {
        return name;
    }

    public final String getMajor() {
        return major;
    }

    public final int getGeneration() {
        return generation;
    }

    public final String getPart() {
        return part;
    }

    public abstract SubmissionPolicy submissionPolicy();

    public boolean canSubmitAssignment() {
        return submissionPolicy().canSubmit();
    }

    public abstract String roleName();

    public abstract String getInfo();
}
