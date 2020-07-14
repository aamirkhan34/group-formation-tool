package CSCI5308.GroupFormationTool.passwordConstraint;

public class PasswordRegChecker implements IPasswordChecker {
    private String reg;
    private String info;

    public PasswordRegChecker(String reg, String info) {
        this.reg = reg;
        this.info = info;
    }

    @Override
    public boolean check(String password, StringBuffer sb) {
        String temp = password.replaceAll(reg," ");
        if (temp.equals(password)){
            return true;
        }else {
            sb.append("Format: ");
            sb.append(info);
            try{
                sb.append(reg.replace("|",","));
            }catch (NullPointerException e){
                //TODO add logging
                e.printStackTrace();
            }
            sb.append(".<br/> \n");
            return false;
        }
    }
}
