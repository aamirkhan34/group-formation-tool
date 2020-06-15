package CSCI5308.GroupFormationTool.PasswordConstraint;

import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordLengthChecker;

public class PasswordLengthCheckerMock implements IPasswordLengthChecker {
    @Override
    public boolean checkMaxLength(String password) {
        return password.length()<=18;
    }

    @Override
    public boolean checkMinLength(String password) {
        return password.length()>=6;
    }
}
