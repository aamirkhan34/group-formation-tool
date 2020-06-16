package CSCI5308.GroupFormationTool.PasswordConstraint;

import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordTypeLengthChecker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordTypeLengthCheckerTest {
    private IPasswordTypeLengthChecker checker;
    StringBuffer sb = new StringBuffer("");
    @BeforeEach
    public void initialize(){
        checker = new PasswordTypeLengthCheckerMock();
    }
    @Test
    public void testCheckLowerLength(){
        assertTrue(checker.checkLowerLength("abc",sb));
        assertFalse(checker.checkLowerLength("ABC",sb));
    }
    @Test
    public void testCheckUpperLength(){
        assertFalse(checker.checkUpperLength("abc",sb));
        assertTrue(checker.checkUpperLength("ABC",sb));
    }
    @Test
    public void testCheckSymbolLength(){
        assertTrue(checker.checkSymbolLength("a@bc",sb));
        assertFalse(checker.checkSymbolLength("ABC",sb));
    }
}
