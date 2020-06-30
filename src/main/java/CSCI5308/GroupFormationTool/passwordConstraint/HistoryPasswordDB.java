package CSCI5308.GroupFormationTool.passwordConstraint;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HistoryPasswordDB implements IHistoryPasswordDB {
    @Override
    public void loadHistoryPasswordWithLimit(User user, List<String > passwords,Integer length) {
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spLoadPasswords(?,?)");
            proc.setParameter(1, user.getID());
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    long userID = results.getLong(1);
                    String bannerID = results.getString(2);
                    String password = results.getString(3);
                    String firstName = results.getString(4);
                    String lastName = results.getString(5);
                    String email = results.getString(6);
                    user.setID(userID);
                    user.setBannerID(bannerID);
                    user.setPassword(password);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                }
            }
        }
        catch (SQLException e)
        {
            // Logging needed.
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
        return false;
    }

    @Override
    public void addNewHistoryPassword(User user) {

    }
}
