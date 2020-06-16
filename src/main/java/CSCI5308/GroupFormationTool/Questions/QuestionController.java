package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionController {

    @RequestMapping("/question/questionmanager")
    public String createQuestion(Model model)
    {
        model.addAttribute("question", new Question());
        return "/question/questionmanager";
    }

    @RequestMapping(path = "/question/createQuestion", method = RequestMethod.POST)
    public String createQuestion(@ModelAttribute Question question, Model model, RedirectAttributes attr) {
        IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
        User u = CurrentUser.instance().getCurrentAuthenticatedUser();
        Question q = new Question();
        q.setTitle(question.getTitle());
        q.setText(question.getText());
        q.setType(question.getType());
        q.setInstructor(question.getInstructor());
        //q.setMultipleChoice(que.getMultipleChoiceOption());
        q.createQuestion(questionDB);
        attr.addFlashAttribute("question", question);
        return "redirect:/question/questionoptions";
       // return "question/questionanswer";
    }
    @RequestMapping(value = "/question/questionoptions", method = RequestMethod.GET)
    public String displayQuestion(Question question){
        return "/question/questionanswer";
    }
}
