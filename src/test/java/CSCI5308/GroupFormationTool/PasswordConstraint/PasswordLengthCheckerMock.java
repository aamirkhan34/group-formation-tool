package CSCI5308.GroupFormationTool.PasswordConstraint;

import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordLengthChecker;

public class PasswordLengthCheckerMock implements IPasswordLengthChecker {
    @Override
    public boolean checkMaxLength(String password) {
        return true;
    }

    @Override
    public boolean checkMinLength(String password) {
        return true;
    }
}
