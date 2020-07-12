package CSCI5308.GroupFormationTool.passwordConstraint;

import CSCI5308.GroupFormationTool.SystemConfig;

public class PasswordUpperLengthChecker implements IPasswordLengthChecker{
    private Integer minUpperLength;

    public PasswordUpperLengthChecker() {
        minUpperLength = Integer.valueOf(SystemConfig.instance().getPasswordConstraintConfiguration().getPasswordUpperMin());
    }

    @Override
    public boolean checkMaxLength(String password, StringBuffer sb) {
        return false;
    }

    @Override
    public boolean checkMinLength(String password, StringBuffer sb) {
        Integer upperSize = password.chars().filter(value -> (value>='A'&&value<='Z')).toArray().length;
        if (upperSize>=minUpperLength){
            return true;
        }else {
            sb.append("The number of upper case characters should not be fewer than ");
            sb.append(minUpperLength);
            sb.append(".");
            sb.append("<br/> \n");
            return false;
        }
    }
}
