package CSCI5308.GroupFormationTool.passwordConstraint;

import CSCI5308.GroupFormationTool.SystemConfig;

public class PasswordBannedChecker  implements IPasswordBannedChecker {
    private String bannedRe;
    public PasswordBannedChecker(){
        IPasswordConstraintConfiguration config = SystemConfig.instance().getPasswordConstraintConfiguration();
        bannedRe = config.getBannedRe();
    }
    @Override
    public boolean checkSubstring(String password,StringBuffer sb) {
        String temp = password.replaceAll(bannedRe," ");
        if (temp.equals(password)){
            return true;
        }else {
            sb.append("The password should not have some characters like ");
            sb.append(bannedRe.replace("|",","));
            sb.append(".<br/> \n");
            return false;
        }
    }
}
