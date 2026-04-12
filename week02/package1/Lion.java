package likelion_PBL_TIL.week02.package1;

public class Lion {
    public String name;
    String major;
    private int generation;

    public Lion(String name, String major, int generation) {
        this.name = name;
        this.major = major;
        this.generation = generation;
    }

    public boolean isValid() {
        if (name == null || name.isEmpty()) {
            System.out.println("❌ 이름이 비어 있습니다.");
            return false;
        }
        if (major == null || major.isEmpty()) {
            System.out.println("❌ 전공이 비어 있습니다.");
            return false;
        }
        if (generation < 1) {
            System.out.println("❌ 기수가 1미만입니다.");
            return false;
        }
        return true;
    }

    public void printInfo() {
        System.out.println("🦁 아기사자 정보를 출력합니다.");
        System.out.println("👤 이름: " + name + " | 🎓 전공: " + major + " | 📌 기수: " + generation);
    }
}
