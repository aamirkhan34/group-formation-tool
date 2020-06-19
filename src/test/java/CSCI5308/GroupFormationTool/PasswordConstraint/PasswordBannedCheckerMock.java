package CSCI5308.GroupFormationTool.PasswordConstraint;

import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordBannedChecker;

public class PasswordBannedCheckerMock implements IPasswordBannedChecker {
    @Override
    public boolean checkSubstring(String password,StringBuffer sb) {
        String temp = password.replaceAll("abc|bcd"," ");
        return temp.equals(password);
    }
}
