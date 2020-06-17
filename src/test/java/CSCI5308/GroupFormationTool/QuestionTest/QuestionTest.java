package CSCI5308.GroupFormationTool.QuestionTest;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Questions.MultipleChoiceOption;
import CSCI5308.GroupFormationTool.Questions.Question;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SuppressWarnings("deprecation")
public class QuestionTest {
    @Test
    public void getIdTest() {
        Question question = new Question();
        question.setId(123);
        assertEquals(123, question.getId());
    }

    @Test
    public void setIdTest() {
        Question question = new Question();
        question.setId(1234);
        assertEquals(question.getId(), 1234);
    }

    @Test
    public void getTitleTest() {
        Question question = new Question();
        question.setTitle("Question title getter test");
        assertEquals("Question title getter test", question.getTitle());
    }

    @Test
    public void setTitleTest() {
        Question question = new Question();
        question.setType("Question title setter test");
        assertEquals("Question title setter test",question.getType());
    }

    @Test
    public void getTextTest() {
        Question question = new Question();
        question.setText("Question text getter test");
        assertEquals("Question text getter test", question.getText());
    }

    @Test
    public void setTextTest() {
        Question question = new Question();
        question.setText("Question text setter test");
        assertEquals("Question text setter test", question.getText());
    }

    @Test
    public void getTypeTest() {
        Question question = new Question();
        question.setType("Question type getter test");
        assertEquals("Question type getter test", question.getType());
    }

    @Test
    public void setTypeTest() {
        Question question = new Question();
        question.setType("Question type setter test");
        assertEquals("Question type setter test",question.getType());
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
    public void setInstructorTest() {
        Question question = new Question();
        User u = new User();
        u.setId(100);
        question.setInstructor(u);
        assertEquals(100, question.getInstructor().getId());
    }

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
        assertEquals(choices, question.getMultipleChoiceOption());
    }

    @Test
    public void setMultipleChoiceOptionTest() {
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
        assertEquals(choices, question.getMultipleChoiceOption());
    }
}
