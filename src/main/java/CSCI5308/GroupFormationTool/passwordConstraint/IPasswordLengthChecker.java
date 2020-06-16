package CSCI5308.GroupFormationTool.passwordConstraint;

public interface IPasswordLengthChecker {
    public boolean checkMaxLength(String password,StringBuffer sb);
    public boolean checkMinLength(String password,StringBuffer sb);
}
