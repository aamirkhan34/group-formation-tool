package CSCI5308.GroupFormationTool.passwordConstraint;

public class DefaultPasswordHistoryConstraint implements IPasswordHistoryConstraint{
    private final static Integer HISTORY_PASSWORD_MAX = Integer.valueOf(System.getenv("HISTORY_PASSWORD_MAX"));
    @Override
    public Integer getHistoryPasswordMaximum() {
        return HISTORY_PASSWORD_MAX;
    }
}
