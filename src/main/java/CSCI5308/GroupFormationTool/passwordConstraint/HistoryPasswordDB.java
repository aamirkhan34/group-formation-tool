package CSCI5308.GroupFormationTool.passwordConstraint;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryPasswordDB implements IHistoryPasswordDB {
    @Override
    public void loadHistoryPasswordWithLimit(User user, List<String > passwords,Integer length) {
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spLoadPasswords(?,?)");
            proc.setParameter(1, user.getID());
            proc.setParameter(2, length);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    long id = results.getLong(1);
                    long userID = results.getLong(2);
                    String password = results.getString(3);
                    String modifyDate = results.getString(4);
                    passwords.add(password);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }

    }

    @Override
    public boolean checkDuplicatedPassword(User user, String password) {
        boolean duplicated = false;
        List<String > passwords = new ArrayList<>();
        loadHistoryPasswordWithLimit(user,passwords,SystemConfig.instance().getPasswordHistoryConstraintConfiguration().getHistoryPasswordMaximum());
        for (String historyPassword:passwords
             ) {
            duplicated = duplicated || SystemConfig.instance().getPasswordEncryption().matches(password,historyPassword);
        }
        return duplicated;
    }

    @Override
    public void addNewHistoryPassword(User user) {

    }
}
