package CSCI5308.GroupFormationTool.QuestionTest;

import CSCI5308.GroupFormationTool.Questions.MultipleChoiceOption;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MultipleChoiceOptionTest {
    @Test
    public void getDisplayTextTest() {
        MultipleChoiceOption multipleChoiceOption = new MultipleChoiceOption();
        assertEquals(multipleChoiceOption.getDisplayText(), "");
        multipleChoiceOption.setDisplayText("Question text");
        assertEquals(multipleChoiceOption.getDisplayText(), "Question text");
    }

    @Test
    public void setDisplayTextTest(String displayText) {
        MultipleChoiceOption multipleChoiceOption = new MultipleChoiceOption();
        multipleChoiceOption.setDisplayText("Display text test");
        assertEquals(multipleChoiceOption.getDisplayText(), "Display text test");
    }

    @Test
    public void getOptionNumberTest() {
        MultipleChoiceOption multipleChoiceOption = new MultipleChoiceOption();
        multipleChoiceOption.setOptionNumber(1000);
        assertEquals(multipleChoiceOption.getOptionNumber(), 1000);
    }

    @Test
    public void setOptionNumberTest(int optionNumber) {
        MultipleChoiceOption multipleChoiceOption = new MultipleChoiceOption();
        multipleChoiceOption.setOptionNumber(1234);
        assertEquals(multipleChoiceOption.getOptionNumber(), 1234);
    }
}
