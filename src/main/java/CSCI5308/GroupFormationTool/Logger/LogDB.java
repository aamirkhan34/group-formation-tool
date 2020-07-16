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
    public boolean createRecord(LogDAO log)
    {
        CallStoredProcedure proc = null;
        try
        {
            System.out.println("---------------");
            proc = new CallStoredProcedure("spCreateLog(?,?,?,?,?,?,?)");
            proc.setParameter(1, log.getClassName());
            proc.setParameter(2, log.getMethodName());
            proc.setParameter(3, log.getCreateTime());
            System.out.println(log.getCreateTime());
            proc.setParameter(4, log.getLevel().getTypeName());
            proc.setParameter(5, log.getMessage());
            proc.setParameter(6, log.getPossibleSolution());
            proc.registerOutputParameterLong(7);
            proc.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
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
