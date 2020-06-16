package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

import java.sql.SQLException;
import java.time.LocalDate;
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
}
