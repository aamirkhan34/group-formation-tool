package CSCI5308.GroupFormationTool.passwordConstraint;

import CSCI5308.GroupFormationTool.SystemConfig;

public class PasswordLengthChecker implements IPasswordLengthChecker{
    private Integer maxLength;
    private Integer minLength;
    public PasswordLengthChecker(){
        IPasswordConstraintConfiguration config = SystemConfig.instance().getPasswordConstraintConfiguration();
        maxLength = Integer.valueOf(config.getPasswordMax());
        minLength = Integer.valueOf(config.getPasswordMin());
        System.out.println(maxLength);
        System.out.println(minLength);

    }
    @Override
    public boolean checkMaxLength(String password) {

        return maxLength> password.length();
    }

    @Override
    public boolean checkMinLength(String password) {
        return minLength < password.length();
    }
}
