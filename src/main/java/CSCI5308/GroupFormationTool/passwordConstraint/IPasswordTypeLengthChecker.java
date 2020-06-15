package CSCI5308.GroupFormationTool.passwordConstraint;

public interface IPasswordTypeLengthChecker {
    public boolean checkLowerLength(String password);
    public boolean checkUpperLength(String password);
    public boolean checkSymbolLength(String password);
}
