package CSCI5308.GroupFormationTool.passwordConstraint;

public class DefaultPasswordHistoryConstraintConfiguration implements IPasswordHistoryConstraintConfiguration {
    private final static Integer HISTORY_PASSWORD_MAX = Integer.valueOf(System.getenv("HISTORY_PASSWORD_MAX"));
    @Override
    public Integer getHistoryPasswordMaximum() {
        return HISTORY_PASSWORD_MAX;
    }
}
