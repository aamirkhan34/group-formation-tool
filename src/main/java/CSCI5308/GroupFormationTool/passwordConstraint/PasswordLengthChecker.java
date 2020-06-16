package CSCI5308.GroupFormationTool.passwordConstraint;

import CSCI5308.GroupFormationTool.SystemConfig;

public class PasswordLengthChecker implements IPasswordLengthChecker{
    private Integer maxLength;
    private Integer minLength;
    public PasswordLengthChecker(){
        IPasswordConstraintConfiguration config = SystemConfig.instance().getPasswordConstraintConfiguration();
        maxLength = Integer.valueOf(config.getPasswordMax());
        minLength = Integer.valueOf(config.getPasswordMin());

    }
    @Override
    public boolean checkMaxLength(String password,StringBuffer sb) {
        if (maxLength> password.length()){
            return true ;
        }else {
            sb.append("The password should not be longer than than ");
            sb.append(maxLength);
            sb.append("<br/> \n");
            return false;
        }
    }

    @Override
    public boolean checkMinLength(String password,StringBuffer sb) {
        if (minLength<password.length()){
            return true;
        }else {
            sb.append("The password should not be shorter than ");
            sb.append(minLength);
            sb.append("<br/> \n");
            return false;
        }
    }
}
