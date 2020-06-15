package CSCI5308.GroupFormationTool.PasswordConstraint;

import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordTypeLengthChecker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordTypeLengthCheckerTest {
    private IPasswordTypeLengthChecker checker;
    @BeforeEach
    public void initialize(){
        checker = new PasswordTypeLengthCheckerMock();
    }
    @Test
    public void testCheckLowerLength(){
        assertTrue(checker.checkLowerLength("abc"));
        assertFalse(checker.checkLowerLength("ABC"));
    }
    @Test
    public void testCheckUpperLength(){
        assertFalse(checker.checkUpperLength("abc"));
        assertTrue(checker.checkUpperLength("ABC"));
    }
    @Test
    public void testCheckSymbolLength(){
        assertTrue(checker.checkSymbolLength("a@bc"));
        assertFalse(checker.checkSymbolLength("ABC"));
    }
}
