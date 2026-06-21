package com.likelion.PBL.member.dto;

import com.likelion.PBL.member.domain.Member;

public class MemberResponse {
    private final Long id;
    private final String name;
    private final String major;
    private final int generation;
    private final String part;
    private final String roleName;
    private final String studentId;
    private final String position;

    private MemberResponse(
            Long id,
            String name,
            String major,
            int generation,
            String part,
            String roleName,
            String studentId,
            String position
    ) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.roleName = roleName;
        this.studentId = studentId;
        this.position = position;
    }

    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getMajor(),
                member.getGeneration(),
                member.getPart(),
                member.getRoleType().getDisplayName(),
                member.getStudentId(),
                member.getPosition()
        );
    }

    public Long getId() {
        return id;
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

    public String getPosition() {
        return position;
    }
}
