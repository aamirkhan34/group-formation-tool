package CSCI5308.GroupFormationTool.PasswordConstraint;

import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordBannedChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordBannedCheckerTest {
    @Test
    public void testCheckSubstring(){
        IPasswordBannedChecker checker = new PasswordBannedCheckerMock();
        StringBuffer sb = new StringBuffer("");
        assertTrue(checker.checkSubstring("fabdce",sb));
        assertFalse(checker.checkSubstring("fabce",sb));
        assertFalse(checker.checkSubstring("fbcde",sb));
    }
}
