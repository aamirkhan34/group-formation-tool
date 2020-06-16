package CSCI5308.GroupFormationTool.Questions;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class QuestionController {

    @RequestMapping("/question/questionmanager")
    public String createQuestion(Model model)
    {
        model.addAttribute("question", new Question());
        return "/question/questionmanager";
    }

    @RequestMapping( value = "/question/questionanswer", method = RequestMethod.POST)
    public String createAnswer(Model model, Question question, RedirectAttributes attr){
        attr.addFlashAttribute("question", question);
        return "redirect:/question/questionoptions";
    }

    @RequestMapping(value = "/question/questionoptions", method = RequestMethod.GET)
    public String displayQuestion(Question question){
        return "/question/questionanswer";
    }

}
