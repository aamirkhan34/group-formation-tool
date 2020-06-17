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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class QuestionController {
    @RequestMapping("/question/questionmanager")
    public String createQuestion(Model model) {
        IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
        List<Question> questionTypeList = questionDB.loadAllQuestionTypes();
        model.addAttribute("questionTypeList", questionTypeList);
        model.addAttribute("question", new Question());
        return "/question/questionmanager";
    }

    @RequestMapping(path = "/question/createQuestion", method = RequestMethod.POST)
    public String createQuestion(@ModelAttribute Question question, Model model,
                                 RedirectAttributes attr,
                                 @RequestParam(name = "displayText", required = false) ArrayList<String> displaytext,
                                 @RequestParam(name = "optionNumber", required = false) ArrayList<Integer> optionNumber) {
        IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
        User user = CurrentUser.instance().getCurrentAuthenticatedUser();
        Question q = new Question();
        q.setTitle(question.getTitle());
        q.setText(question.getText());
        q.setTypeID(question.getTypeID());
        q.setInstructor(user);
        //q.setMultipleChoice(que.getMultipleChoiceOption());
        if (q.getTypeID() == 1 || q.getTypeID() == 4) {
            q.createQuestion(questionDB);
            return "redirect:/question/questionmanager";
        } else {
            if (optionNumber.isEmpty() || displaytext.isEmpty()) {
                attr.addFlashAttribute("question", question);
                return "redirect:/question/questionoptions";
            } else {
                ArrayList<MultipleChoiceOption> multipleChoices = new ArrayList<MultipleChoiceOption>();
                for (int i = 0; i < displaytext.size(); i++) {
                    if (optionNumber.get(i) != null && !displaytext.get(i).isEmpty()) {
                        MultipleChoiceOption choice = new MultipleChoiceOption();
                        choice.setDisplayText(displaytext.get(i));
                        choice.setOptionNumber(optionNumber.get(i));
                        multipleChoices.add(choice);
                    }
                }
                q.setMultipleChoiceOption(multipleChoices);
                q.createQuestion(questionDB);
                return "redirect:/question/questionmanager";
            }
        }
    }

    @RequestMapping(value = "/question/questionoptions", method = RequestMethod.GET)
    public String displayQuestion(Question question, Model model) {
        List<MultipleChoiceOption> multipleChoiceOptionList = new ArrayList<>();
        MultipleChoiceOption multipleChoiceOption = new MultipleChoiceOption();
        model.addAttribute("multipleChoiceOption", multipleChoiceOption);
        model.addAttribute("multipleChoiceOptionList", multipleChoiceOptionList);
        return "/question/questionswithoptions";
    }

    /*
    @RequestMapping(value = "/question/createquestion", method = RequestMethod.POST)
    public String saveQuestion(Question question, Model model) {
        MultipleChoiceOption multipleChoiceOption = new MultipleChoiceOption();
        List<MultipleChoiceOption> multipleChoiceOptionList = new ArrayList<>();
        //System.out.println(displaytext);
        model.addAttribute("multipleChoiceOption", multipleChoiceOption);
        model.addAttribute("multipleChoiceOptionList", multipleChoiceOptionList);
        return "/course/instructoradmin";
    }*/
}
