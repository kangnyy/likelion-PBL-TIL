package likelion_PBL_TIL.week01;
import java.util.Scanner;

public class PBL_01 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int num;

        while (true) {

            System.out.println("🦁 저장할 아기사자 수를 5 이상 입력해주세요.");
            num = sc.nextInt();

            if (num >= 5) {
                break;
            } else {
                System.out.println("❗ [오류] 5 이상 입력해주세요.");
            }
        }

        String[] members = new String[num];
        System.out.println("✏️ 아기사자 이름을 입력해주세요.");
        sc.nextLine();

        for (int i = 0; i < num; i++) {
            members[i] = sc.nextLine();
        }

        System.out.println("📋 아기사자 명단을 최종적으로 출력합니다.");
        for (int i = 0; i < num; i++) {
            System.out.println("🦁 "+(i+1)+". "+ members[i]);
        }
        sc.close();

    }
}
