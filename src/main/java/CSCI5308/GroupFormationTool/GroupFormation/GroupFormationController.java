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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class GroupFormationController {
	private static final String ID = "id";
	private static final String QUESTIONID = "questionid";

	@GetMapping("/groupformation/algorithm")
	public String surveyQuestions(Model model, @RequestParam(name = ID) long courseID) {
		ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
		Survey s = new Survey();
		List<Question> questionsAddedToSurvey = surveyDB.loadSurveyQuestionsByCourseId(courseID);

		model.addAttribute("questionlist", questionsAddedToSurvey);
		model.addAttribute("courseid", courseID);
		return "definealgorithm";
	}
	
	public List<Integer> parseComparisonChoices(String requestBody) {
		List<Integer> allMatches = new ArrayList<Integer>();
		Matcher m = Pattern.compile("comparisonChoice[0-9]+=[0-9]{1}")
		     .matcher(requestBody);
		
		while (m.find()) {
			System.out.println(m.group().split("=")[1]);
			int choice = Integer.parseInt(m.group().split("=")[1].trim());
			allMatches.add(choice);
		}
		
		return allMatches;
	}

	@RequestMapping(value = "/groupformation/algorithm", method = RequestMethod.POST)
	public String generateAlgo(Model model, @RequestParam(name = ID) long courseID,
			@RequestBody String body,
			@RequestParam(name = "noOfQuestions") int noOfQuestions,
			@RequestParam(name = QUESTIONID, required = false) ArrayList<Integer> questionIDs,
			@RequestParam(name = "weight", required = false) ArrayList<Integer> weights) {
		
		Course course = new Course();
		course.setId(courseID);
		List<Integer> comparisonChoices = parseComparisonChoices(body);
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		List<Course> allCourses = courseDB.loadAllCourses();
		model.addAttribute("courses", allCourses);
		return "index";
	}

	@RequestMapping(value = "/groupformation/groups", method = RequestMethod.GET)
	public String displayGroups(Model model, @RequestParam(name = ID) long courseID){
		Course course = new Course();
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		courseDB.loadCourseByID(courseID, course);
		model.addAttribute("course", course);
		model.addAttribute("courseid", courseID);
		Group group = new Group();
		IGroupPersistence groupDB = SystemConfig.instance().getGroupDB();
		List<Group> groups =  group.loadGroupByCourse(groupDB, course);
		model.addAttribute("groups", groups);
		return "displaygroups";
	}
}
