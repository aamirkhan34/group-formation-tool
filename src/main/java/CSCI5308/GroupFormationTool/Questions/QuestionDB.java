package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class QuestionDB implements IQuestionPersistence {
    @Override
    public List<Question> loadAllQuestionsByInstructor(long instructorId) {
        return null;
    }

    @Override
    public void loadQuestionById(long questionId) {

    }

    @Override
    public boolean createQuestion(Question question) {
        CallStoredProcedure proc = null;
        try {
            if (question.getTypeID() == 1 || question.getTypeID() == 4) {
                proc = new CallStoredProcedure("spCreateQuestion(?, ?, ?, ?,?)");
                proc.setParameter(1, question.getTitle());
                proc.setParameter(2, question.getText());
                proc.setParameter(3, question.getInstructor().getID());
                proc.setParameter(4, question.getTypeID());
                proc.registerOutputParameterLong(5);
                proc.execute();
                return true;
            } else if (question.getTypeID() == 2 || question.getTypeID() == 3) {
                proc = new CallStoredProcedure("spCreateQuestionWithOptions(?, ?, ?, ?,?)");
                proc.setParameter(1, question.getTitle());
                proc.setParameter(2, question.getText());
                proc.setParameter(3, question.getInstructor().getID());
                proc.setParameter(4, question.getTypeID());
                proc.registerOutputParameterLong(5);
                ResultSet results = proc.executeWithResults();
                long id = -1;
                while (results.next()) {
                    id = results.getLong(1);
                }
                createMultipleQuestionOptions(id, question.getMultipleChoiceOption());
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return false;
    }

    private boolean createMultipleQuestionOptions(long id, ArrayList<MultipleChoiceOption> options) {
        CallStoredProcedure proc = null;
        try {
            for(int i = 0; i< options.size();i++) {
                proc = new CallStoredProcedure("spCreateQuestionWithOptions(?, ?, ?)");
                proc.setParameter(1, id);
                proc.setParameter(2, options.get(i).getDisplayText());
                proc.setParameter(3, options.get(i).getOptionNumber());
                proc.execute();
            }
            return true;
        }catch (SQLException e)
        {
            e.printStackTrace();
            return false;
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
    public boolean deleteQuestionById(long questionId) {
        return false;
    }

    @Override
    public List<Question> loadAllQuestionTypes() {
        List<Question> questions = new ArrayList<Question>();
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spLoadAllQuestoinTypes()");
            ResultSet results = proc.executeWithResults();
            if (null != results) {
                while (results.next()) {
                    int id = results.getInt(1);
                    String questionType = results.getString(2);
                    Question question = new Question();
                    question.setTypeID(id);
                    question.setType(questionType);
                    questions.add(question);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return questions;
    }
}
