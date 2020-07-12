package CSCI5308.GroupFormationTool.passwordConstraint;

public class PasswordUpperLengthCheckerFactory extends PasswordLengthCheckerFactory {
    @Override
    public IPasswordLengthChecker produceLengthChecker() {
        return new PasswordUpperLengthChecker();
    }
}
