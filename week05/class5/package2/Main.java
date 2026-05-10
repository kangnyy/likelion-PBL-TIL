package likelion_PBL_TIL.week05.class5.package2;

import likelion_PBL_TIL.week05.class5.role.Lion;
import likelion_PBL_TIL.week05.class5.role.Role;
import likelion_PBL_TIL.week05.class5.role.Staff;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🔧 저장소를 선택하세요:");
        System.out.println("1. MemoryMemberRepository (실제 저장)");
        System.out.println("2. MockMemberRepository (더미 데이터)");
        System.out.print("선택: ");
        int repoChoice = Integer.parseInt(scanner.nextLine());


        MemberRepository repository;
        if (repoChoice == 2) {
            repository = new MockMemberRepository();
        } else {
            repository = new MemoryMemberRepository();
        }

        MemberService service = new MemberService(repository);

        while (true) {
            System.out.println("\n🦁 ===== 멋사 멤버 관리 시스템 (Step 2: DI 적용) ===== 🦁");
            System.out.println("1. ➕ 멤버 등록");
            System.out.println("2. 📋 전체 멤버 조회");
            System.out.println("3. 🔍 이름으로 검색");
            System.out.println("4. 🚪 종료");
            System.out.print("선택: ");

            int choice = Integer.parseInt(scanner.nextLine());


            if (choice == 1) {
                System.out.println("\n👤 역할 선택 (1: 아기사자, 2: 운영진): ");
                int roleType = Integer.parseInt(scanner.nextLine());

                System.out.println("\n📝 정보 입력");
                System.out.print("이름: ");
                String name = scanner.nextLine();
                System.out.print("전공: ");
                String major = scanner.nextLine();
                System.out.print("기수: ");
                int generation = Integer.parseInt(scanner.nextLine());
                System.out.print("파트: ");
                String part = scanner.nextLine();

                Role member;
                if (roleType == 1) {
                    System.out.print("학번: ");
                    String studentId = scanner.nextLine();
                    member = new Lion(name, major, generation, part, studentId);
                } else {
                    System.out.print("직책: ");
                    String position = scanner.nextLine();
                    member = new Staff(name, major, generation, part, position);
                }

                if (service.registerMember(member)) {
                    System.out.println("\n✅ 등록 완료: " + name);
                } else {
                    System.out.println("\n❌ 실패: 이미 존재하는 이름입니다.");
                }

            } else if (choice == 2) {
                List<Role> members = service.getAllMembers();
                System.out.println("\n📋 ===== 전체 멤버 목록 =====");
                if (members.isEmpty()) {
                    System.out.println("등록된 멤버가 없습니다.");
                } else {
                    for (Role m : members) {
                        printMemberInfo(m);
                        System.out.println("---------------------------------");
                    }
                }

            } else if (choice == 3) {
                System.out.print("\n🔍 검색할 이름: ");
                String name = scanner.nextLine();
                Role member = service.findMember(name);

                if (member != null) {
                    System.out.println("\n🎯 ===== 검색 결과 =====");
                    printMemberInfo(member);
                } else {
                    System.out.println("\n❌ 해당 이름의 멤버를 찾을 수 없습니다.");
                }

            } else if (choice == 4) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
        }
        scanner.close();
    }

    private static void printMemberInfo(Role member) {
        System.out.println("👤 역할: " + member.getRoleName());
        System.out.printf("📌 이름: %s | 🎓 전공: %s | 🔢 기수: %d | 💻 파트: %s\n",
                member.getName(), member.getMajor(), member.getGeneration(), member.getPart());
        System.out.println(member.getRoleDetails());
        System.out.println("📝 과제 제출 가능: " + (member.checkSubmission() ? "✅ 가능" : "❌ 불가능"));
    }
}