package CSCI5308.GroupFormationTool.passwordConstraint;

public interface IPasswordTypeLengthChecker {
    public boolean checkLowerLength(String password,StringBuffer sb);
    public boolean checkUpperLength(String password,StringBuffer sb);
    public boolean checkSymbolLength(String password,StringBuffer sb);
}
