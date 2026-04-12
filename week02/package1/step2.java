package likelion_PBL_TIL.week02.package1;

import java.util.Scanner;

public class step2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("🦁 아기사자 이름을 입력해주세요.\n");
        String name = sc.nextLine();

        System.out.print("🎓 전공을 입력해주세요.\n");
        String major = sc.nextLine();

        System.out.print("📌 기수를 입력해주세요.\n");
        int generation = sc.nextInt();

        Lion lion = new Lion(name, major, generation);
        System.out.println("⏩ 객체 생성이 완료되었습니다. 아기사자 객체의 상태를 확인합니다.");

        if (!lion.isValid()) {
            System.out.println("❌ 잘못된 아기사자 정보입니다.");
            return;
        }
        System.out.println("✅ 아기사자 객체가 자신의 상태를 정상으로 판단했습니다.");

        lion.printInfo();
    }
}
