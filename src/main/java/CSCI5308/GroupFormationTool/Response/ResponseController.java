package CSCI5308.GroupFormationTool.Response;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.Role;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ResponseController {
    private static final String ID = "id";

    @RequestMapping(value = "/survey/submitresponse", method = RequestMethod.POST)
    public String createSurvey(Model model, @RequestParam(name = ID) long courseID,
                               @ModelAttribute Response response,
                               @RequestParam(name = "questionID", required = false) ArrayList<Integer> questionIDs,
                               @RequestParam(name = "responses", required = false) ArrayList<Response> responses) {
        ISurveyPersistence questionDB = SystemConfig.instance().getSurveyDB();
        ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
        User user = CurrentUser.instance().getCurrentAuthenticatedUser();
//        for (Response res: responses) {
//            System.out.println(res.getQuestion().getId());
//        }
//        for (int value: questionIDs) {
//            System.out.println(value);
//        }
        Course course = new Course();
        courseDB.loadCourseByID(courseID, course);
        model.addAttribute("course", course);
        List<Role> userRoles = course.getAllRolesForCurrentUserInCourse();
        if (null == userRoles)
        {
            model.addAttribute("instructor", false);
            model.addAttribute("ta", false);
            model.addAttribute("student", false);
            model.addAttribute("guest", true);
        }
        else
        {
            model.addAttribute("instructor", userRoles.contains(Role.INSTRUCTOR));
            model.addAttribute("ta", userRoles.contains(Role.TA));
            model.addAttribute("student", userRoles.contains(Role.STUDENT));
            model.addAttribute("guest", userRoles.isEmpty());
        }
        return "index";
    }

}
