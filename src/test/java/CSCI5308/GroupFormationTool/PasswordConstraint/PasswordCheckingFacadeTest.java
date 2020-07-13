package CSCI5308.GroupFormationTool.PasswordConstraint;

import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordChecker;
import CSCI5308.GroupFormationTool.passwordConstraint.PasswordCheckingFacade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordCheckingFacadeTest {

    @Test
    void check() {
        IPasswordChecker checker = new PasswordCheckingFacade();
        StringBuffer sb = new StringBuffer("");
        assertTrue(checker.check("A@bc",sb));
        assertFalse(checker.check("a@bc",sb));
        assertFalse(checker.check("A@BC",sb));
        assertFalse(checker.check("a@",sb));
        assertFalse(checker.check("A@abc",sb));
    }
}