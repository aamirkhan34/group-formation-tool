package CSCI5308.GroupFormationTool.passwordConstraint;

public class DefaultPasswordConstraintConfiguration implements IPasswordConstraintConfiguration{
    private final static String PASS_MIN = System.getenv("PASS_MIN");
    private final static String PASS_MAX = System.getenv("PASS_MAX");
    private final static String PASS_UPPER_MIN = System.getenv("PASS_UPPER_MIN");
    private final static String PASS_LOWER_MIN = System.getenv("PASS_LOWER_MIN");
    private final static String PASS_SYMBOL_MIN = System.getenv("PASS_SYMBOL_MIN");

    @Override
    public String getPasswordMin() {
        return PASS_MIN;
    }

    @Override
    public String getPasswordMax() {
        return PASS_MAX;
    }

    @Override
    public String getPasswordUpperMin() {
        return PASS_UPPER_MIN;
    }

    @Override
    public String getPasswordLowerMin() {
        return PASS_LOWER_MIN;
    }

    @Override
    public String getPasswordSymbolMin() {
        return PASS_SYMBOL_MIN;
    }
}
