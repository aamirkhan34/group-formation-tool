package CSCI5308.GroupFormationTool.QuestionTest;

import CSCI5308.GroupFormationTool.Questions.MultipleChoiceOption;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultipleChoiceOptionTest {

    @Test
    public void getDisplayTextTest() {
        MultipleChoiceOption multipleChoiceOption = new MultipleChoiceOption();
        assertTrue(multipleChoiceOption.getDisplayText().isEmpty());
        multipleChoiceOption.setDisplayText("test");
        assertTrue(multipleChoiceOption.getDisplayText().equals("test"));
    }

    @Test
    public void setDisplayTextTest() {
        MultipleChoiceOption multipleChoiceOption = new MultipleChoiceOption();
        multipleChoiceOption.setDisplayText("Display text");
        assertTrue(multipleChoiceOption.getDisplayText().equals("Display text"));
    }

    @Test
    public void getOptionNumberTest() {
        MultipleChoiceOption multipleChoiceOption = new MultipleChoiceOption();
        multipleChoiceOption.setOptionNumber(1000);
        assertEquals(1000, multipleChoiceOption.getOptionNumber());
    }

    @Test
    public void setOptionNumberTest() {
        MultipleChoiceOption multipleChoiceOption = new MultipleChoiceOption();
        multipleChoiceOption.setOptionNumber(1234);
        assertEquals(1234, multipleChoiceOption.getOptionNumber());
    }
}
