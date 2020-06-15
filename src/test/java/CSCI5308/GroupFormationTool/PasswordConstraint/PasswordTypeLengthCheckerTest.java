package CSCI5308.GroupFormationTool.PasswordConstraint;

import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordTypeLengthChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordTypeLengthCheckerTest {
    @Test
    public void testCheckLowerLength(){
        IPasswordTypeLengthChecker checker = new PasswordTypeLengthCheckerMock();
        assertTrue(checker.checkLowerLength("abc"));
        assertFalse(checker.checkLowerLength("ABC"));
    }
    @Test
    public void testCheckUpperLength(){
        IPasswordTypeLengthChecker checker = new PasswordTypeLengthCheckerMock();
        assertFalse(checker.checkUpperLength("abc"));
        assertTrue(checker.checkUpperLength("ABC"));
    }
    @Test
    public void testCheckSymbolLength(){
        IPasswordTypeLengthChecker checker = new PasswordTypeLengthCheckerMock();
        assertTrue(checker.checkSymbolLength("a@bc"));
        assertFalse(checker.checkSymbolLength("ABC"));
    }
}
