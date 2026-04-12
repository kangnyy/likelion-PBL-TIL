package likelion_PBL_TIL.week02.package1;

import java.util.Scanner;

public class step1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("🦁 아기사자 이름을 입력해주세요.\n");
        String name = sc.nextLine();

        System.out.print("🎓 전공을 입력해주세요.\n");
        String major = sc.nextLine();

        System.out.print("📌 기수를 입력해주세요.\n");
        int generation = sc.nextInt();

        System.out.println("📌 입력값 검증을 진행합니다.");
        if (name == null || name.isEmpty()) {
            System.out.println("❌ 이름은 비어 있을 수 없습니다.");
            return;
        }
        if (major == null || major.isEmpty()) {
            System.out.println("❌ 전공은 비어 있을 수 없습니다.");
            return;
        }
        if (generation < 1) {
            System.out.println("❌ 기수는 1미만일 수 없습니다.");
            return;
        }

        System.out.println("⏩ 입력값 검증을 통과하여 아기사자 객체 생성을 진행합니다.");

        Lion lion = new Lion(name, major, generation);
        System.out.println("✅ 아기사자 객체를 성공적으로 생성하였습니다.");


        lion.printInfo();
    }
}
