package CSCI5308.GroupFormationTool.QuestionTest;

import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.Question;

import java.util.List;

public class QuestionDBMock implements IQuestionPersistence {
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

    @Override
    public List<Question> loadAllQuestionTypes() {
        return null;
    }
}
