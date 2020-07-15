package CSCI5308.GroupFormationTool.GroupFormation;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.Survey;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GroupFormationController {
    private static final String ID = "id";

    @GetMapping("/groupformation/algorithm")
    public String surveyQuestions(Model model, @RequestParam(name = ID) long courseID)
    {
        IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
        ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
        User user = CurrentUser.instance().getCurrentAuthenticatedUser();
        Question q = new Question();
        q.setInstructor(user);
        List<Question> questionList = q.loadAllQuestionsByInstructor(questionDB);
        List<Question> questionsAddedToSurvey = surveyDB.loadSurveyQuestionsByCourseId(courseID);

        Survey s = new Survey();
        List<Question> surveyQuestions = s.getAllSurveyQuestions(questionList,questionsAddedToSurvey);
        int isPublished = surveyDB.isSurveyPublished(courseID);
        model.addAttribute("questionlist", surveyQuestions);
        model.addAttribute("courseid", courseID);
        return "defineformula";
    }
}
