package likelion_PBL_TIL.week04.class4.policy;

public class StaffSubmissionPolicy implements SubmissionPolicy {
    @Override
    public boolean canSubmit() {
        return false;
    }
}
