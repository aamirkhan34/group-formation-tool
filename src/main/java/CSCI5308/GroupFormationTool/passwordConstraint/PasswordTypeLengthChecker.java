package CSCI5308.GroupFormationTool.passwordConstraint;

import CSCI5308.GroupFormationTool.SystemConfig;

public class PasswordTypeLengthChecker implements IPasswordTypeLengthChecker {
    private Integer minUpperLength;
    private Integer minLowerLength;
    private Integer minSymbolLength;
    public PasswordTypeLengthChecker(){
        IPasswordConstraintConfiguration config = SystemConfig.instance().getPasswordConstraintConfiguration();
        minUpperLength = Integer.valueOf(config.getPasswordUpperMin());
        minLowerLength = Integer.valueOf(config.getPasswordLowerMin());
        minSymbolLength = Integer.valueOf(config.getPasswordSymbolMin());
    }
    @Override
    public boolean checkLowerLength(String password,StringBuffer sb) {
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

    @Override
    public boolean checkUpperLength(String password,StringBuffer sb) {
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

    @Override
    public boolean checkSymbolLength(String password,StringBuffer sb) {
        password = password.replaceAll("[a-zA-Z0-9\\s+]","");
        if (password.length()>=minSymbolLength){
            return true;
        }else {
            sb.append("The number of special characters should not be fewer than ");
            sb.append(minSymbolLength);
            sb.append(".");
            sb.append("<br/> \n");
            return false;
        }
    }
}
