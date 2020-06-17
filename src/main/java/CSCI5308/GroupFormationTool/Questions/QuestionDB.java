package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class QuestionDB implements IQuestionPersistence{
    @Override
    public List<Question> loadAllQuestionsByInstructor(long instructorId) {
        return null;
    }

    @Override
    public void loadQuestionById(long questionId) {

    }

    @Override
    public boolean createQuestion(Question question) {

        LocalDate date = java.time.LocalDate.now();
        CallStoredProcedure proc = null;
        if(question.getType().equals('4')){
            try {

                proc = new CallStoredProcedure("spCreateUser(?, ?, ?, ?, ?, ?)");
                proc.setParameter(1, "");
                proc.setParameter(2, question.getInstructor().getID());
                proc.setParameter(3, question.getType());
                proc.setParameter(4, question.getTitle());
                proc.setParameter(5, question.getText());
                proc.setParameter(6, question.getText());
                //                proc.setParameter(6, date);
                proc.execute();
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
        return false;

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
            if(null != results){
                while (results.next()){
                    int id = results.getInt(1);
                    String questionType =  results.getString(2);
                    Question question = new Question();
                    question.setTypeID(id);
                    question.setType(questionType);
                    questions.add(question);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return questions;
    }
}
