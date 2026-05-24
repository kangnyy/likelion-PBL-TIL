package com.hufslikelion.pbl07.dto;

import com.hufslikelion.pbl07.domain.role.Lion;

public class LionResponse {
    private final String name;
    private final String major;
    private final int generation;
    private final String part;
    private final String roleName;
    private final String studentId;

    private LionResponse(String name, String major, int generation, String part, String roleName, String studentId) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.roleName = roleName;
        this.studentId = studentId;
    }

    public static LionResponse from(Lion lion) {
        return new LionResponse(
                lion.getName(),
                lion.getMajor(),
                lion.getGeneration(),
                lion.getPart(),
                lion.roleName(),
                lion.getStudentId()
        );
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public int getGeneration() {
        return generation;
    }

    public String getPart() {
        return part;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getStudentId() {
        return studentId;
    }
}
