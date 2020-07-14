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
        assertTrue(checker.check("A@bcqrew",sb));
        assertFalse(checker.check("a@bcqrew",sb));
        assertFalse(checker.check("A@BCQWEG",sb));
        assertFalse(checker.check("a@bc",sb));
        assertFalse(checker.check("A@abcdfef",sb));
        assertFalse(checker.check("A@BCjoifreqoj94787943saf",sb));
    }
}