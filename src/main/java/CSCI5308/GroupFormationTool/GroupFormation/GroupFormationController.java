package CSCI5308.GroupFormationTool.GroupFormation;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.Question;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.Survey;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GroupFormationController {
    private static final String ID = "id";
    private static final String QUESTIONID ="questionid";

    @GetMapping("/groupformation/algorithm")
    public String surveyQuestions(Model model, @RequestParam(name = ID) long courseID)
    {
        ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
        Survey s = new Survey();
        List<Question> questionsAddedToSurvey = surveyDB.loadSurveyQuestionsByCourseId(courseID);

        model.addAttribute("questionlist", questionsAddedToSurvey);
        model.addAttribute("courseid", courseID);
        return "defineformula";
    }

    @RequestMapping(value = "/groupformation/algorithm", method = RequestMethod.POST)
    public String generateAlgo(Model model,@RequestParam(name = ID) long courseID,
                               @RequestParam(name="noOfQuestions") int noOfStudents,
                               @RequestParam(name = QUESTIONID, required = false) ArrayList<Integer> questionIDs,
                               @RequestParam(name = "similarity", required = false) ArrayList<Integer> similarityChoices,
                               @RequestParam(name = "weight", required = false) ArrayList<Integer> weights) {

        return "index";
    }
}
