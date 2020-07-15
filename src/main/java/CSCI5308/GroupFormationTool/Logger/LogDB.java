package CSCI5308.GroupFormationTool.Logger;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

import java.sql.SQLException;

public class LogDB implements ILogDB
{
    private static ILogDB instance;
    public static ILogDB getInstance(){
        if (null == instance){
            instance = new LogDB();
        }
        return instance;
    }
    @Override
    public boolean createRecord(ILog log, String className , String methodName,String createTime, String msg, String possibleSolution)
    {
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spCreateLog(?,?,?,?,?,?,?)");
            proc.setParameter(1, className);
            proc.setParameter(2, methodName);
            proc.setParameter(3, methodName);
            proc.setParameter(4, log.getLevel().getTypeName());
            proc.setParameter(5, msg);
            proc.setParameter(6, possibleSolution);
            proc.registerOutputParameterLong(7);
            proc.execute();
        }
        catch (SQLException e)
        {
            // TODO Logging needed
            return false;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return true;
    }
}
