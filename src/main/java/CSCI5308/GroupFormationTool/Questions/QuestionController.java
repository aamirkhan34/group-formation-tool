package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionController {

    @GetMapping("/question/questionmanager")
    public String createQuestion(Model model)
    {
        model.addAttribute("question", new Question());
        return "question/questionmanager";
    }

    @GetMapping("/question/questionanswer")
    public String createAnswer(Model model){
        Question question = new Question();
        return "question/questionanswer";
    }

}
