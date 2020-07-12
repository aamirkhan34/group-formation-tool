package CSCI5308.GroupFormationTool.PasswordConstraint;

import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordBannedCheckerTest {
    @Test
    public void testCheckSubstring(){
        IPasswordChecker checker = new PasswordCheckerMock();
        StringBuffer sb = new StringBuffer("");
        assertTrue(checker.check("fabdce",sb));
        assertFalse(checker.check("fabce",sb));
        assertFalse(checker.check("fbcde",sb));
    }
}
