package CSCI5308.GroupFormationTool.QuestionTest;

import CSCI5308.GroupFormationTool.AccessControl.User;
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
        User user = new User();
        user.setID(1);
        user.setBannerID("B00847415");
        user.setPassword("Pass@123");
        user.setFirstName("Pratz");
        user.setLastName("B");
        user.setEmail("pr676280@dal.ca");

        question.setId(0);
        question.setTypeID(1);
        question.setInstructor(user);
        question.setTitle("Test Question");
        question.setText("How many credits you have taken?");
        return true;
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
