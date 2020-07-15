package CSCI5308.GroupFormationTool.Logger;

public interface ILogDB {
    public boolean createRecord(ILog log , String className, String methodName,String createTime, String msg, String possibleSolution);
}
