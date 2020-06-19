package CSCI5308.GroupFormationTool.passwordConstraint;

public interface IPasswordBannedChecker {
    public boolean checkSubstring(String password,StringBuffer sb);
}
