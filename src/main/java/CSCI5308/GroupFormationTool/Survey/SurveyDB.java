package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Questions.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SurveyDB implements ISurveyPersistence {
    @Override
    public boolean createSurvey(Survey survey) {
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spCreateSurvey(?, ?)");
            proc.setParameter(1, survey.getCourseID());
            proc.setParameter(2, survey.getInstructor().getID());
            ResultSet resultSet = proc.executeWithResults();
            int surveyIdfromDB = 0;
            while (resultSet.next()) {
                surveyIdfromDB = resultSet.getInt(1);
            }
            createSurveyQuestions(surveyIdfromDB, survey.getSurveyQuestionList());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    private boolean createSurveyQuestions(int surveyIdfromDB, ArrayList<Question> surveyQuestionList) {
        CallStoredProcedure proc = null;
        try {
            for (int i = 0; i < surveyQuestionList.size(); i++) {
                proc = new CallStoredProcedure("spCreateSurveyQuestions(?,?)");
                proc.setParameter(1, surveyIdfromDB);
                proc.setParameter(2, surveyQuestionList.get(i).getId());
                proc.execute();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }
}
