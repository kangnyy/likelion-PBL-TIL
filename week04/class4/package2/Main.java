package likelion_PBL_TIL.week04.class4.package2;

import likelion_PBL_TIL.week04.class4.role.Lion;
import likelion_PBL_TIL.week04.class4.role.Role;
import likelion_PBL_TIL.week04.class4.role.Staff;

import java.util.*;

public class Main {

    private static List<Role> memberList = new ArrayList<>();
    private static Map<String, List<Role>> partMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n======= 🦁 멤버 관리 시스템 =======");
            System.out.println("1. 멤버 등록");
            System.out.println("2. 전체 멤버 조회");
            System.out.println("3. 이름으로 검색");
            System.out.println("4. 파트별 조회");
            System.out.println("5. 종료");
            System.out.print("선택: ");

            int menuChoice = Integer.parseInt(scanner.nextLine());

            switch (menuChoice) {
                case 1:
                    registerMember(scanner);
                    break;
                case 2:
                    displayAllMembers();
                    break;
                case 3:
                    findMemberByName(scanner);
                    break;
                case 4:
                    findMembersByPart(scanner);
                    break;
                case 5:
                    System.out.println("종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력값입니다. 다시 입력해주세요.");
                    break;
            }
        }
    }


    public static void registerMember(Scanner scanner){
        System.out.println("\n--- 📝 멤버 등록 ---");
        System.out.print("역할 선택 (1: 아기사자, 2: 운영진): ");
        int roleChoice = Integer.parseInt(scanner.nextLine());

        System.out.print("👤 이름: ");
        String name = scanner.nextLine();
        for (Role member : memberList) {
            if (member != null && member.getName().equals(name)) {
                System.out.println("❌ 등록 실패: 이미 존재하는 이름입니다.");
                return;
            }
        }

        System.out.print("🎓 전공: ");
        String major = scanner.nextLine();
        System.out.print("📌 기수: ");
        int generation = Integer.parseInt(scanner.nextLine());
        System.out.print("💻 파트 (백엔드/프론트엔드/기획/디자인): ");
        String part = scanner.nextLine();


        Role newMember ;

        if (roleChoice == 1) {
            System.out.print("🆔 학번: ");
            String studentId = scanner.nextLine();
            newMember = new Lion(name, major, generation, part, studentId);
        } else if (roleChoice == 2) {
            System.out.print("⭐ 직책 (대표/부대표/파트장/멘토): ");
            String position = scanner.nextLine();
            newMember = new Staff(name, major, generation, part, position);
        } else {
            System.out.println("1 또는 2를 입력하세요.");
            return;
        }

        memberList.add(newMember);

        if (!partMap.containsKey(part)){
            partMap.put(part, new ArrayList<>());
        }
        partMap.get(part).add(newMember);

        System.out.println("✅ 등록 완료: " + name);
    }


    public static void displayAllMembers(){
        System.out.println("\n--- 📝 전체 멤버 목록 ---");
        if (memberList.isEmpty()) {
            System.out.println("등록된 멤버가 없습니다.");
        }

        int i = 1;
        for (Role member : memberList) {
            System.out.printf("%d. [%s] %s - %d기\n",
                    i++, member.getRoleName(), member.getName(), member.getGeneration());
        }
        System.out.println("📊 총 " + memberList.size() + "명");
    }

    public static void findMemberByName(Scanner scanner){
        System.out.println("\n--- 🔎 이름으로 검색 ---");
        System.out.print("검색할 이름: ");
        String searchName = scanner.nextLine();

        boolean found = false;
        for (Role member : memberList) {
            if (member.getName().equals(searchName)) {
                System.out.println("\n✨ [검색 결과]");
                System.out.println("🎭 역할: " + member.getRoleName());
                System.out.println(member.getCommonInfo());
                System.out.println(member.getRoleDetails());
                System.out.println("📝 과제 제출 가능 여부: " + (member.checkSubmission() ? "✅ 가능" : "❌ 불가능"));
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("❌ '" + searchName + "' 멤버를 찾을 수 없습니다.");
        }
    }

    public static void findMembersByPart(Scanner scanner) {
        System.out.println("\n--- 🔎 파트별 조회 ---");

        if (partMap.isEmpty()) {
            System.out.println("등록된 멤버가 없습니다.");
            return;
        }

        System.out.println("등록된 파트: " + partMap.keySet());

        System.out.print("조회할 파트: ");
        String searchPart = scanner.nextLine();

        List<Role> partMembers = partMap.get(searchPart);

        if (partMembers == null || partMembers.isEmpty()){
            System.out.println("❌ [" + searchPart + "] 파트에 등록된 멤버가 없습니다.");
        } else {
            System.out.printf("\n✨ [%s 파트 멤버]\n", searchPart);
            int i = 1;
            for (Role member: partMembers) {
                System.out.printf("%d. %s (%s) - %d기\n", i++, member.getName(), member.getRoleName(), member.getGeneration());
            }
        }
    }


}



