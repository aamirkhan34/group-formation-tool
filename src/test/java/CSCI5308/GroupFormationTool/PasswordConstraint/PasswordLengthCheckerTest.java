package CSCI5308.GroupFormationTool.PasswordConstraint;

import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordLengthChecker;
import CSCI5308.GroupFormationTool.passwordConstraint.PasswordLengthChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordLengthCheckerTest {
    @Test
    public void testCheckLength(){
        IPasswordLengthChecker checker = new PasswordLengthCheckerMock();
        assertTrue(checker.checkMinLength("1234567"));
        assertTrue(checker.checkMaxLength("1234567"));
    }
}