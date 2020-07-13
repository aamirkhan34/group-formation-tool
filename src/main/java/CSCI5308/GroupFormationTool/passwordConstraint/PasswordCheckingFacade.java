package CSCI5308.GroupFormationTool.passwordConstraint;

import CSCI5308.GroupFormationTool.SystemConfig;

public class PasswordCheckingFacade implements IPasswordChecker{



    @Override
    public boolean check(String password, StringBuffer sb) {
        boolean result = true;
        IPasswordConstraintConfiguration config = SystemConfig.instance().getPasswordConstraintConfiguration();
        IPasswordRegLengthCheckerBuilder lengthBuilder = new PasswordRegRegLengthCheckerBuilder();
        IPasswordRegCheckerBuilder regBuilder = new PasswordRegCheckerBuilder();
        IPasswordChecker checker;
        lengthBuilder.setMaxLength(config.getPasswordMax());
        lengthBuilder.setMiniLength(config.getPasswordLowerMin());
        lengthBuilder.setReg(PasswordReg.NONE);
        checker = lengthBuilder.getResult();
        result  = result && checker.check(password,sb);
        lengthBuilder.reset();
        lengthBuilder.setReg(PasswordReg.LOWER);
        lengthBuilder.setMiniLength(config.getPasswordLowerMin());
        checker = lengthBuilder.getResult();
        result  = result && checker.check(password,sb);
        lengthBuilder.reset();
        lengthBuilder.setReg(PasswordReg.UPPER);
        lengthBuilder.setMiniLength(config.getPasswordUpperMin());
        checker = lengthBuilder.getResult();
        result  = result && checker.check(password,sb);
        lengthBuilder.reset();
        lengthBuilder.setReg(PasswordReg.SPECIAL);
        lengthBuilder.setMiniLength(config.getPasswordSymbolMin());
        checker = lengthBuilder.getResult();
        result  = result && checker.check(password,sb);
        regBuilder.setReg(PasswordReg.BANNED);
        checker = regBuilder.getResult();
        result  = result && checker.check(password,sb);

        return result;
    }
}
