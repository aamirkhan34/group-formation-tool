package CSCI5308.GroupFormationTool.Logger;

import java.util.ArrayList;
import java.util.List;

public class LogDBMock implements ILogDB {
    private static LogDBMock instance;
    public List<LogDAO> logs;

    public LogDBMock() {
        logs = new ArrayList<>();
    }

    @Override
    public boolean createRecord(LogDAO log) {
        logs.add(log);
        return false;
    }
    public static ILogDB getInstance(){
        if (null == instance){
            instance = new LogDBMock();
        }
        return instance;
    }

    @Override
    public String toString() {
        return ""+logs.size();
    }
}
