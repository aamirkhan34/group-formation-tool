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
    public boolean checkLowerLength(String password) {
        Integer lowerSize = password.chars().filter(value -> (value>'a'&&value<'z')).toArray().length;
        return lowerSize>=minLowerLength;
    }

    @Override
    public boolean checkUpperLength(String password) {
        Integer upperSize = password.chars().filter(value -> (value>'A'&&value<'Z')).toArray().length;
        return upperSize>=minUpperLength;
    }

    @Override
    public boolean checkSymbolLength(String password) {
        password = password.replaceAll("[a-zA-Z0-9\\s+]","");
        return password.length()>=minSymbolLength;
    }
}
