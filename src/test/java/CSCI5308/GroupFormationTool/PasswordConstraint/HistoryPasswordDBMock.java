package CSCI5308.GroupFormationTool.PasswordConstraint;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.passwordConstraint.IHistoryPasswordDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryPasswordDBMock implements IHistoryPasswordDB {
    private Map<Long, List<String >> mockDB = null;
    private final static Integer SAMPLE_PASSWORD_LIMIT = 3;



    @Override
    public void loadHistoryPasswordWithLimit(User user, List<String > passwords, Integer length) {
        mockDB = new HashMap<>();
        passwords = new ArrayList<>();
        mockDB.put(user.getID(),passwords);
    }

    @Override
    public boolean checkDuplicatedPassword(User user , String password) {
        if (!mockDB.containsKey(user.getID())){
            loadHistoryPasswordWithLimit(user, new ArrayList<>(),SAMPLE_PASSWORD_LIMIT);
        }
        return mockDB.get(user.getID()).contains(password);
    }

    @Override
    public void addNewHistoryPassword(User user) {
        if (mockDB.get(user.getID()).size()>=SAMPLE_PASSWORD_LIMIT){
            mockDB.get(user.getID()).remove(0);
        }
        mockDB.get(user.getID()).add(user.getPassword());
    }
}
