package com.hufslikelion.pbl;

import com.hufslikelion.pbl.service.MemberService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PblApplication {

	public static void main(String[] args) {
		// SpringApplication.run은 스프링 컨테이너(ApplicationContext)를 반환
		ApplicationContext context = SpringApplication.run(PblApplication.class, args);

		MemberService memberService = context.getBean(MemberService.class);

		System.out.println("---------------------------------------");
		System.out.println("memberService = " + memberService);
		System.out.println("---------------------------------------");
	}
}