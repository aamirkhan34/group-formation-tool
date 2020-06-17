package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionController {
    @RequestMapping("/question/questionmanager")
    public String createQuestion(Model model) {
        IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
        List<Question> questionTypeList = questionDB.loadAllQuestionTypes();
        model.addAttribute("questionTypeList", questionTypeList);
        model.addAttribute("question", new Question());
        return "question/createquestion";
    }

    @RequestMapping(path = "/question/createQuestion", method = RequestMethod.POST)
    public String createQuestion(@ModelAttribute Question question, Model model,
                                 RedirectAttributes attr,
                                 @RequestParam(name = "displayText", required = false) ArrayList<String> displaytext,
                                 @RequestParam(name = "optionNumber", required = false) ArrayList<Integer> optionNumber) {
        IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
        User user = CurrentUser.instance().getCurrentAuthenticatedUser();
        model.addAttribute("question", question);
        Question q = new Question();
        q.setTitle(question.getTitle());
        q.setText(question.getText());
        q.setTypeID(question.getTypeID());
        q.setInstructor(user);
        if (q.getTypeID() == 1 || q.getTypeID() == 4) {
            q.createQuestion(questionDB);
            return "redirect:/question/questionmanager";
        } else {
            if (optionNumber == null || displaytext ==null) {
                attr.addFlashAttribute("question", question);
                model.addAttribute("question", question);
                return "redirect:/question/questionoptions";
            } else {
                return "redirect:/question/questionmanager";
            }
        }
    }

    @RequestMapping(value = "/question/questionoptions", method = RequestMethod.GET)
    public String displayQuestion(@ModelAttribute Question question, Model model, RedirectAttributes attr) {
        List<MultipleChoiceOption> multipleChoiceOptionList = new ArrayList<>();
        attr.addFlashAttribute("question", question);
        MultipleChoiceOption multipleChoiceOption = new MultipleChoiceOption();
        model.addAttribute("multipleChoiceOption", multipleChoiceOption);
        model.addAttribute("multipleChoiceOptionList", multipleChoiceOptionList);
        return "/question/questionswithoptions";
    }

    @RequestMapping(value = "/question/createQuestionmultiple", method = RequestMethod.POST)
    public String createQuestionmultiple(@ModelAttribute Question question, Model model,
                                         RedirectAttributes attr,
                                         @RequestParam(name = "questiontitle", required = false) String questiontitle,
                                         @RequestParam(name = "questiontypeid", required = false) int questiontypeid,
                                         @RequestParam(name = "questiontext", required = false) String questiontext,
                                         @RequestParam(name = "displayText", required = false) ArrayList<String> displaytext,
                                         @RequestParam(name = "optionNumber", required = false) ArrayList<Integer> optionNumber){

        IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
        User user = CurrentUser.instance().getCurrentAuthenticatedUser();
        Question q = new Question();
        q.setTitle(questiontitle);
        q.setText(questiontext);
        q.setTypeID(questiontypeid);
        q.setInstructor(user);
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
    @GetMapping("/question/questionmanagement")
    public String questionManagement()
    {
        IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
        User user = CurrentUser.instance().getCurrentAuthenticatedUser();
        Question q = new Question();
        q.loadQuestionById(questionDB);
        return "/question/questionmanagement";
    }
}
