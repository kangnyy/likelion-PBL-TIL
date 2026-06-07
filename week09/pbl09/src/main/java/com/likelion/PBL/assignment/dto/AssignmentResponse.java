package com.likelion.PBL.assignment.dto;

import com.likelion.PBL.assignment.domain.Assignment;
import com.likelion.PBL.member.domain.Member;

public class AssignmentResponse {
    private final Long id;
    private final String title;
    private final String description;
    private final Long memberId;
    private final String memberName;

    private AssignmentResponse(
            Long id,
            String title,
            String description,
            Long memberId,
            String memberName
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.memberId = memberId;
        this.memberName = memberName;
    }

    public static AssignmentResponse from(Assignment assignment) {
        Member member = assignment.getMember();
        return new AssignmentResponse(
                assignment.getId(),
                assignment.getTitle(),
                assignment.getDescription(),
                member.getId(),
                member.getName()
        );
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }
}
