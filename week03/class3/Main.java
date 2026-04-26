package likelion_PBL_TIL.week03.class3;

import likelion_PBL_TIL.week03.class3.role.Lion;
import likelion_PBL_TIL.week03.class3.role.Member;
import likelion_PBL_TIL.week03.class3.role.Staff;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====== 🦁 아기사자 정보 입력 ======");
        System.out.print("👤 이름: ");
        String lionName = scanner.nextLine();
        System.out.print("🎓 전공: ");
        String lionMajor = scanner.nextLine();
        System.out.print("📌 기수: ");
        int lionGen = Integer.parseInt(scanner.nextLine());
        System.out.print("💻 파트(백엔드/프론트엔드/기획/디자인): ");
        String lionPart = scanner.nextLine();
        System.out.print("🆔 학번: ");
        String lionId = scanner.nextLine();
        System.out.println();

        Member lion = new Lion(lionName, lionMajor, lionGen, lionPart, lionId);

        System.out.println("====== 👩‍💻 운영진 정보 입력 ======");
        System.out.print("👤 이름: ");
        String staffName = scanner.nextLine();
        System.out.print("🎓 전공: ");
        String staffMajor = scanner.nextLine();
        System.out.print("📌 기수: ");
        int staffGen = Integer.parseInt(scanner.nextLine());
        System.out.print("💻 파트 (백엔드/프론트엔드/기획/디자인): ");
        String staffPart = scanner.nextLine();
        System.out.print("⭐ 직책 (대표/부대표/파트장/멘토): ");
        String staffPosition = scanner.nextLine();
        System.out.println();

        Member staff = new Staff(staffName, staffMajor, staffGen, staffPart, staffPosition);

        System.out.println("====== 📋결과 출력 ======");
        System.out.println();
        printMemberInfo(lion);
        printMemberInfo(staff);

        scanner.close();

    }

    private static void printMemberInfo(Member member) {
        System.out.println("🎭 역할: " + member.getRoleName());
        System.out.println(member.getCommonInfo());
        System.out.println(member.getRoleDetails());

        String submissionStatus = member.checkSubmission() ? "✅ 가능" : "❌ 불가능";
        System.out.println("📝 과제 제출 가능 여부: " + submissionStatus);
        System.out.println("-------------------------------");

    }


}
