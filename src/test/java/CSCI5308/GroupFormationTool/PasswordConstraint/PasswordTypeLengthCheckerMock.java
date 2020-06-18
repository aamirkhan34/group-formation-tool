package CSCI5308.GroupFormationTool.PasswordConstraint;

import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordTypeLengthChecker;
import io.jsonwebtoken.lang.Collections;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class PasswordTypeLengthCheckerMock implements IPasswordTypeLengthChecker {


    @Override
    public boolean checkLowerLength(String password,StringBuffer sb) {
        Integer lowerSize = password.chars().filter(value -> (value>'a'&&value<'z')).toArray().length;
        return lowerSize>=1;
    }

    @Override
    public boolean checkUpperLength(String password,StringBuffer sb) {
        Integer upperSize = password.chars().filter(value -> (value>'A'&&value<'Z')).toArray().length;
        return upperSize>=1;
    }

    @Override
    public boolean checkSymbolLength(String password,StringBuffer sb) {
        password = password.replaceAll("[a-zA-Z0-9\\s+]","");
        return password.length()>=1;
    }
}
