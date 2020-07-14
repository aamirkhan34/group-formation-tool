package CSCI5308.GroupFormationTool.passwordConstraint;

public class DefaultPasswordConstraintConfiguration implements IPasswordConstraintConfiguration{
    private final static String PASS_MIN = System.getenv("PASS_MIN");
    private final static String PASS_MAX = System.getenv("PASS_MAX");
    private final static String PASS_UPPER_MIN = System.getenv("PASS_UPPER_MIN");
    private final static String PASS_LOWER_MIN = System.getenv("PASS_LOWER_MIN");
    private final static String PASS_SYMBOL_MIN = System.getenv("PASS_SYMBOL_MIN");
    private final static String BANNED_RE = System.getenv("BANNED_RE");
    private final static String DEFAULT_BANNED_RE = "abc|cde";


    @Override
    public Integer getPasswordMin() {
        return covertAndCatchingException(PASS_MIN);
    }

    @Override
    public Integer getPasswordMax() {
        return covertAndCatchingException(PASS_MAX);
    }

    @Override
    public Integer getPasswordUpperMin() {
        return covertAndCatchingException(PASS_UPPER_MIN);
    }

    @Override
    public Integer getPasswordLowerMin() {
        return covertAndCatchingException(PASS_LOWER_MIN);
    }

    @Override
    public Integer getPasswordSymbolMin() {
        return covertAndCatchingException(PASS_SYMBOL_MIN);
    }

    @Override
    public String getBannedRe() {
        if (null == BANNED_RE){
            return  DEFAULT_BANNED_RE;
        }
        return BANNED_RE;
    }

    private Integer covertAndCatchingException(String numberConfig){
        Integer config = null;
        try{
            config = Integer.valueOf(numberConfig);
        }catch (NumberFormatException e){
            //TODO LOGGER NEEDED
            e.printStackTrace();
        }
        return config;
    }
}
