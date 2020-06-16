package CSCI5308.GroupFormationTool.QuestionTest;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Questions.MultipleChoiceOption;
import CSCI5308.GroupFormationTool.Questions.Question;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@SuppressWarnings("deprecation")
public class QuestionTest {
    @Test
    public void getIdTest() {
        Question question = new Question();
        question.setId(123);
        assertEquals(question.getId(), 123);
    }

    @Test
    public void setIdTest(long id) {
        Question question = new Question();
        question.setId(1234);
        assertEquals(question.getId(), 1234);
    }

    @Test
    public void getTitleTest() {
        Question question = new Question();
        question.setTitle("Question title getter test");
        assertEquals(question.getTitle(), "Question title getter test");
    }

    @Test
    public void setTitleTest(String title) {
        Question question = new Question();
        question.setType("Question title setter test");
        assertEquals(question.getType(), "Question title setter test");
    }

    @Test
    public void getTextTest() {
        Question question = new Question();
        question.setText("Question text getter test");
        assertEquals(question.getText(), "Question text getter test");
    }

    @Test
    public void setTextTest(String text) {
        Question question = new Question();
        question.setText("Question text setter test");
        assertEquals(question.getText(), "Question text setter test");
    }

    @Test
    public void getTypeTest() {
        Question question = new Question();
        question.setType("Question type getter test");
        assertEquals(question.getType(), "Question type getter test");
    }

    @Test
    public void setTypeTest(String type) {
        Question question = new Question();
        question.setType("Question type setter test");
        assertEquals(question.getType(), "Question type setter test");
    }

    @Test
    public void getInstructorTest() {
        Question question = new Question();
        User u = new User();
        u.setId(1234);
        question.setInstructor(u);
        assertEquals(question.getInstructor().getId(), 1234);
    }

    @Test
    public void setInstructorTest(User instructor) {
        Question question = new Question();
        User u = new User();
        u.setId(100);
        question.setInstructor(u);
        assertEquals(question.getInstructor().getId(), 100);
    }

    //public void getCreatedOnTest() {

    //}

    //public void setCreatedOnTest(Date createdOn) {

    //}

    @Test
    public void getMultipleChoiceOptionTest() {
        Question question = new Question();
        ArrayList<MultipleChoiceOption> choices = new ArrayList<MultipleChoiceOption>();

        MultipleChoiceOption choiceQue1 = new MultipleChoiceOption();
        MultipleChoiceOption choiceQue2 = new MultipleChoiceOption();
        MultipleChoiceOption choiceQue3 = new MultipleChoiceOption();

        choiceQue1.setDisplayText("Option 1");
        choiceQue1.setOptionNumber(1);
        choices.add(choiceQue1);

        choiceQue2.setDisplayText("Option 2");
        choiceQue2.setOptionNumber(2);
        choices.add(choiceQue2);

        choiceQue3.setDisplayText("Option 3");
        choiceQue3.setOptionNumber(3);
        choices.add(choiceQue3);

        question.setMultipleChoiceOption(choices);
        assertEquals(question.getMultipleChoiceOption(), choices);
    }

    @Test
    public void setMultipleChoiceOptionTest(ArrayList<MultipleChoiceOption> multipleChoiceOption) {
        Question question = new Question();
        ArrayList<MultipleChoiceOption> choices = new ArrayList<MultipleChoiceOption>();

        MultipleChoiceOption choiceQue1 = new MultipleChoiceOption();
        MultipleChoiceOption choiceQue2 = new MultipleChoiceOption();
        MultipleChoiceOption choiceQue3 = new MultipleChoiceOption();

        choiceQue1.setDisplayText("Option 1");
        choiceQue1.setOptionNumber(1);
        choices.add(choiceQue1);

        choiceQue2.setDisplayText("Option 2");
        choiceQue2.setOptionNumber(2);
        choices.add(choiceQue2);

        choiceQue3.setDisplayText("Option 3");
        choiceQue3.setOptionNumber(3);
        choices.add(choiceQue3);

        question.setMultipleChoiceOption(choices);
        assertEquals(question.getMultipleChoiceOption(), choices);
    }
}
