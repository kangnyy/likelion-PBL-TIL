package likelion_PBL_TIL.week02.package2;

import likelion_PBL_TIL.week02.package1.Lion;

public class step3 {
    public static void main(String[] args) {

        System.out.println("🦁 아기사자 객체를 생성합니다.");
        Lion lion = new Lion("김멋대", "컴퓨터공학과", 14);
        lion.printInfo();

        // public 필드 접근
        System.out.println("\n📌 Step 3-1. public 필드 접근을 시도합니다.");
        System.out.println("👉 name 필드 값을 변경합니다.");

        lion.name = "홍길동";

        System.out.println("✅ public 필드 접근 성공");
        lion.printInfo();

        /*
        // default 필드 접근
        System.out.println("\n📌Step 3-2. default 필드 접근을 시도합니다.");
        System.out.println("👉 major 필드 값을 변경합니다.");

        lion.major = "환경학과";
        lion.printInfo();


        // private 필드 접근
        System.out.println("\nStep 3-3. private 필드 접근을 시도합니다.");
        System.out.println("👉 generation 필드 값을 변경합니다.");

        lion.generation = 11;
        lion.printInfo();

         */

    }
}
