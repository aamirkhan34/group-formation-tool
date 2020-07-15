package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.Questions.QuestionDB;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public boolean publishSurvey(Survey survey) {
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spPublishSurvey(?)");
            proc.setParameter(1, survey.getCourseID());
            ResultSet resultSet = proc.executeWithResults();
            int surveyIdfromDB = 0;
            while (resultSet.next()) {
                surveyIdfromDB = resultSet.getInt(1);
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

    @Override
    public List<Question> loadSurveyQuestions(Long courseId) {
        CallStoredProcedure proc = null;
            try {
                if(isSurveyPublished(courseId) != 0) {
                    List<Question> surveyQuestions = new ArrayList<Question>();
                    List<Long> questionIDs = null;
                    IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
                    int surveyID = isSurveyPublished(courseId);
                    proc = new CallStoredProcedure("spLoadSurveyQuestionsBySurveyId(?)");
                    proc.setParameter(1, surveyID);
                    ResultSet resultSet = proc.executeWithResults();
                    while (resultSet.next()) {
                        int questionID = resultSet.getInt(1);
                        Question question = new Question();
                        question = questionDB.loadQuestionById(questionID);
                        surveyQuestions.add(question);
                    }
                    return surveyQuestions;
                }
                else {
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (null != proc) {
                    proc.cleanup();
                }
            }
        return null;
    }

    @Override
    public int isSurveyPublished(Long courseID){
        CallStoredProcedure proc = null;
        int surveyID = 0;
        try {
            proc = new CallStoredProcedure("spIsSurveyPublished(?)");
            proc.setParameter(1, courseID);
            ResultSet resultSet = proc.executeWithResults();
            while (resultSet.next()) {
                surveyID = resultSet.getInt(1);
            }
            return surveyID;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return surveyID;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    @Override
    public List<Question> loadSurveyQuestionsByCourseId(Long courseID) {
        CallStoredProcedure proc = null;
        List<Question> questionList = new ArrayList<Question>();
        try {
            proc = new CallStoredProcedure("spLoadSurveyQuestionsByCourseId(?)");
            proc.setParameter(1, courseID);
            ResultSet results = proc.executeWithResults();
            if (null != results) {
                while (results.next()) {
                    Question question = new Question();
                    question.setId(results.getInt(1));
                    questionList.add(question);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return questionList;
    }

    @Override
    public User findInstructorOfTA(Long courseID) {
        CallStoredProcedure proc = null;
        int instructorId =0;
        try {
            proc = new CallStoredProcedure("spFindInstructorOfTA(?)");
            proc.setParameter(1, courseID);
            ResultSet results = proc.executeWithResults();
            if (null != results) {
                while (results.next()) {
                    instructorId=results.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        User u = new User();
        u.setID(instructorId);
        return u;
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
