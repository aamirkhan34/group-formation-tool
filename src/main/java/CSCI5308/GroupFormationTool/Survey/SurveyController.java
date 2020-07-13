package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.MultipleChoiceOption;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SurveyController {
    private static final String ID = "id";
    private static final String QUESTIONID ="questionid";

    @GetMapping("/survey/createsurvey")
    public String surveyQuestions(Model model,@RequestParam(name = ID) long courseID)
    {
        IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
        User user = CurrentUser.instance().getCurrentAuthenticatedUser();
        Question q = new Question();
        q.setInstructor(user);
        List<Question> questionList = q.loadAllQuestionsByInstructor(questionDB);
        model.addAttribute("questionlist", questionList);
        model.addAttribute("courseid", courseID);
        return "createsurvey";
    }

    @RequestMapping(value = "/survey/createsurvey", method = RequestMethod.POST)
    public String createSurvey(@RequestParam(name = ID) long courseID,
                                         @RequestParam(name = QUESTIONID, required = false) ArrayList<Integer> questionIDs) {
        ISurveyPersistence questionDB = SystemConfig.instance().getSurveyDB();
        User user = CurrentUser.instance().getCurrentAuthenticatedUser();
        Survey s = new Survey();
        s.setCourseID(courseID);
        s.setInstructor(user);
        ArrayList<Question> surveyQuestionList = new ArrayList<Question>();
        for (int i = 0; i < questionIDs.size(); i++) {
            if (questionIDs.get(i) != null && questionIDs.get(i) != 0) {
                Question q = new Question();
                q.setId(questionIDs.get(i));
                surveyQuestionList.add(q);
            }
        }
        s.setSurveyQuestionList(surveyQuestionList);
        s.createSurvey(questionDB);
        return "createsurvey";
    }

    @GetMapping("/survey/publishsurvey")
    public String publishSurvey(@RequestParam(name = ID) long courseID) {
        ISurveyPersistence questionDB = SystemConfig.instance().getSurveyDB();
        User user = CurrentUser.instance().getCurrentAuthenticatedUser();
        Survey s = new Survey();
        s.setCourseID(courseID);
        s.setPublished(true);
        s.publishSurvey(questionDB);
        return "createsurvey";
    }


}
