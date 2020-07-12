package CSCI5308.GroupFormationTool.passwordConstraint;

import CSCI5308.GroupFormationTool.SystemConfig;

public class PasswordLowerLengthChecker implements IPasswordLengthChecker {
    private Integer minLowerLength;

    public PasswordLowerLengthChecker() {
        minLowerLength = Integer.valueOf(SystemConfig.instance().getPasswordConstraintConfiguration().getPasswordLowerMin());
    }

    @Override
    public boolean checkMaxLength(String password, StringBuffer sb) {
        return false;
    }

    @Override
    public boolean checkMinLength(String password, StringBuffer sb) {
        Integer lowerSize = password.chars().filter(value -> (value>='a'&&value<='z')).toArray().length;
        if (lowerSize>=minLowerLength){
            return true;
        }else {
            sb.append("The number of lower case characters should not be fewer than ");
            sb.append(minLowerLength);
            sb.append(".");
            sb.append("<br/> \n");
            return false;
        }
    }
}
