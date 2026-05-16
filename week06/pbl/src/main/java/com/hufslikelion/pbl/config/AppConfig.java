package com.hufslikelion.pbl.config;

import com.hufslikelion.pbl.repository.MemberRepository;
import com.hufslikelion.pbl.repository.MemoryMemberRepository;
import com.hufslikelion.pbl.service.MemberService;
import org.springframework.context.annotation.Bean;

// @Configuration  // 자동 주입으로 전환
public class AppConfig {

    @Bean // 스프링 컨테이너에 MemberRepository를 빈으로 등록
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean // 스프링 컨테이너에 MemberService를 빈으로 등록
    public MemberService memberService() {

        // 생성자 주입: 위에서 만든 memberRepository() 빈을 주입
        return new MemberService(memberRepository());
    }
}