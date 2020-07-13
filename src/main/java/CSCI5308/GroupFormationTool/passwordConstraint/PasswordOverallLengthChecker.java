package CSCI5308.GroupFormationTool.passwordConstraint;

import CSCI5308.GroupFormationTool.SystemConfig;

public class PasswordOverallLengthChecker implements IPasswordLengthChecker{
    private Integer maxLength;
    private Integer minLength;
    public PasswordOverallLengthChecker(){
        IPasswordConstraintConfiguration config = SystemConfig.instance().getPasswordConstraintConfiguration();
        try{
            maxLength = config.getPasswordMax();
            minLength = config.getPasswordMin();
        }catch (NumberFormatException e){
            // TODO Add Fix Logger
            e.printStackTrace();
        }
    }
    @Override
    public boolean checkMaxLength(String password,StringBuffer sb) {
        if (maxLength>= password.length()){
            return true ;
        }else {
            sb.append("The password should not be longer than ");
            sb.append(maxLength);
            sb.append(" characters.");
            sb.append("<br/> \n");
            return false;
        }
    }

    @Override
    public boolean checkMinLength(String password,StringBuffer sb) {
        if (minLength<=password.length()){
            return true;
        }else {
            sb.append("The password should not be shorter than ");
            sb.append(minLength);
            sb.append(" characters.");
            sb.append("<br/> \n");
            return false;
        }
    }
}
