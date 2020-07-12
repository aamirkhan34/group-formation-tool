package CSCI5308.GroupFormationTool.passwordConstraint;

public class PasswordRegLengthChecker implements IPasswordChecker{
    private Integer maxLength;
    private Integer miniLength;
    private String reg;
    private String info;

    public PasswordRegLengthChecker(Integer maxLength, Integer miniLength, String reg, String info) {
        this.maxLength = maxLength;
        this.miniLength = miniLength;
        this.reg = reg;
        this.info = info;
    }

    @Override
    public boolean check(String password, StringBuffer sb) {
        boolean result = true;
        if (isReg()){
            sb.append("For ");
            sb.append(info);
            sb.append(":");
            password = password.replaceAll(reg,"");
            System.out.println("==============");
            System.out.println(password);
            System.out.println(reg);
        }
        if (isMin()&&(password.length()<miniLength)){
            result = false;
            sb.append("The password should have more than ");
            sb.append(miniLength);
            sb.append(" characters.");
            sb.append("<br/> \n");
        }
//        System.out.println(maxLength);
//        System.out.println(password.length());
        if (isMax()&&(password.length()>maxLength)){
            result = false;
            sb.append("The password should have fewer than ");
            sb.append(maxLength);
            sb.append(" characters.");
            sb.append("<br/> \n");
        }
        return result;
    }
    public boolean isReg(){
        return null!=reg;
    }
    public boolean isMin(){
        return null!=miniLength;
    }
    public boolean isMax(){
        return null!=maxLength;
    }
}
