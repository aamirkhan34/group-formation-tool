package CSCI5308.GroupFormationTool.PasswordConstraint;

import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordLengthChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordLengthCheckerTest {
    @Test
    public void testCheckLength(){
        IPasswordLengthChecker checker = new PasswordLengthCheckerMock();
        StringBuffer sb = new StringBuffer("");
        assertTrue(checker.checkMinLength("1234567",sb));
        assertTrue(checker.checkMaxLength("1234567",sb));
    }
}
