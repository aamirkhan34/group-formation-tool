package CSCI5308.GroupFormationTool.Logger;

public class InfoLogger extends AbstractLogger implements ILogger{
    private LogType level;
    private String className;
    private String methodName;

    public InfoLogger(String className, String methodName) {
        level = LogType.INFO;
        this.className = className;
        this.methodName = methodName;
    }

    @Override
    public boolean checkLogValid(String msg,String possibleSolution) {
        // Do things different for different types logger
        switch (this.level){
            case ERROR:{
                return (null != possibleSolution);
            }
            default:{
                return null !=msg;
            }
        }
    }

    @Override
    public void logMessage( String msg,String possibleSolution) {
        StringBuffer sb = buildHeading(this.level,this.className, this.methodName,msg);
        if (null != possibleSolution){
            sb.append("\n Suggested Action");
            sb.append(possibleSolution);
            System.err.println(sb.toString());
        }
    }

}
