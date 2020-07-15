package CSCI5308.GroupFormationTool.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AbstractLogger {
    private final static SimpleDateFormat sdf = new SimpleDateFormat("");
    public String getFormattedTime(){
        return sdf.format(new Date());
    }
    public StringBuffer buildHeading(LogType level, String className, String methodName,String msg){
        StringBuffer sb = new StringBuffer("");
        sb.append("[");
        sb.append(level.getTypeName());
        sb.append("], at class '");
        sb.append(className);
        sb.append("' at method '");
        sb.append(methodName);
        sb.append("' ");
        sb.append(msg);
        return sb;
    }
}
