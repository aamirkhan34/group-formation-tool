package CSCI5308.GroupFormationTool.passwordConstraint;

public interface IPasswordLengthChecker {
    public boolean checkMaxLength(String password);
    public boolean checkMinLength(String password);
}
