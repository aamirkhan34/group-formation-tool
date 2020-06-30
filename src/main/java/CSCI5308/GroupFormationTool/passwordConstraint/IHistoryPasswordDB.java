package CSCI5308.GroupFormationTool.passwordConstraint;

import CSCI5308.GroupFormationTool.AccessControl.User;

import java.util.List;

public interface IHistoryPasswordDB {
    public void loadHistoryPasswordWithLimit(User user, List<String > passwords,Integer length);
    public boolean checkDuplicatedPassword(User user , String password);
    public void addNewHistoryPassword(User user);
}
