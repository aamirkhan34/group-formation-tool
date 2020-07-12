package CSCI5308.GroupFormationTool.PasswordConstraint;

import CSCI5308.GroupFormationTool.passwordConstraint.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordTypeLengthCheckerTest {
    private IPasswordRegLengthCheckerBuilder builder;
    private IPasswordChecker checker;
    StringBuffer sb = new StringBuffer("");
    @BeforeEach
    public void initialize(){
        builder = new PasswordRegRegLengthCheckerBuilder();
    }
    @Test
    public void testCheckLowerLength(){
        builder.reset();
        builder.setReg(PasswordReg.LOWER);
        builder.setMiniLength(1);
        checker = builder.getResult();
        System.out.println("---------------");
        assertTrue(checker.check("abc",sb));
        assertFalse(checker.check("ABC",sb));
    }
    @Test
    public void testCheckUpperLength(){
        builder.reset();
        builder.setReg(PasswordReg.UPPER);
        builder.setMiniLength(1);
        checker = builder.getResult();
        System.out.println("---------------");
        assertFalse(checker.check("abc",sb));
        assertTrue(checker.check("ABC",sb));
    }
    @Test
    public void testCheckSymbolLength(){
        builder.reset();
        builder.setReg(PasswordReg.SPECIAL);
        builder.setMiniLength(1);
        checker = builder.getResult();
        System.out.println("---------------");
        assertTrue(checker.check("a@bc",sb));
        assertFalse(checker.check("ABC",sb));
    }
}
