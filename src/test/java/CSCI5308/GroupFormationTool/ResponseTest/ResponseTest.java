package CSCI5308.GroupFormationTool.ResponseTest;

import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.Response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@SuppressWarnings("deprecation")
public class ResponseTest {

    @Test
    public void getQuestionTest()
    {
        Response response = new Response();
        Question question = new Question();
        question.setId(1);
        response.setQuestion(question);
        assertEquals(1, response.getQuestion().getId());
    }

    @Test
    public void getSingleResponseTest()
    {
        Response response = new Response();
        response.setSingleresponse("test");
        assertEquals("test", response.getSingleresponse());
    }

    @Test
    public void getResponse()
    {
        ArrayList<String> responses = new ArrayList<>();
        responses.add("test");
        Response response = new Response();
        response.setResponse(responses);
        assertNotNull(response.getResponse());
    }

}
