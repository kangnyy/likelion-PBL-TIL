package likelion_PBL_TIL.week03.class3.policy;

public class StaffPolicy implements AssignmentPolicy {
    @Override
    public boolean canSubmit() {
        return false;
    }
}
