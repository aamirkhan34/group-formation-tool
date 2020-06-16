package CSCI5308.GroupFormationTool.Questions;

import java.util.List;

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
        return false;
    }

    @Override
    public boolean deleteQuestionById(long questionId) {
        return false;
    }
}
