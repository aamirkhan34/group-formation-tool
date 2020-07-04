package CSCI5308.GroupFormationTool.passwordConstraint;

public class DefaultPasswordHistoryConstraintConfiguration implements IPasswordHistoryConstraintConfiguration {
    private final static Integer  DEFAULT_HISTORY_PASSWORD_MAX = 3;
    private final static String  HISTORY_PASSWORD_MAX = System.getenv("HISTORY_PASSWORD_MAX");
    @Override
    public Integer getHistoryPasswordMaximum() {
        if (null == HISTORY_PASSWORD_MAX){
            return DEFAULT_HISTORY_PASSWORD_MAX;
        }else {
            return Integer.valueOf(HISTORY_PASSWORD_MAX);
        }
    }
}
