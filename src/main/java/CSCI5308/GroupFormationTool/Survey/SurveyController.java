package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SurveyController {
    private static final String ID = "id";

    @GetMapping("/survey/createsurvey")
    public String surveyQuestions(Model model,@RequestParam(name = ID) long courseID)
    {
        IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
        User user = CurrentUser.instance().getCurrentAuthenticatedUser();
        Question q = new Question();
        q.setInstructor(user);
        List<Question> questionList = q.loadAllQuestionsByInstructor(questionDB);
        model.addAttribute("questionlist", questionList);
        return "createsurvey";
    }
}
