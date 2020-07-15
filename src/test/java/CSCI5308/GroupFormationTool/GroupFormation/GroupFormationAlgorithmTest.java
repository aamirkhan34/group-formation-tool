package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Questions.Question;

class GroupFormationAlgorithmTest {
	long id = 122334;
	Course course = new Course();
	Date createdOn = new Date();
	int groupSize = 3;
	ArrayList<Boolean> comparisonChoices = new ArrayList<Boolean>(Arrays.asList(true));
	ArrayList<Question> questions = new ArrayList<Question>(Arrays.asList(new Question()));
	ArrayList<Double> weights = new ArrayList<Double>(Arrays.asList(1.0));

	GroupFormationAlgorithm gfa = new GroupFormationAlgorithm(id, course, createdOn, comparisonChoices, questions,
			weights, groupSize);
	
	@Test
	void testConstructor() {
			
		Assert.isTrue(gfa.getId() == id);
		
		Assert.isTrue(gfa.getCourse() == course);
		
		Assert.isTrue(gfa.getCreatedOn() == createdOn);
		
		Assert.isTrue(gfa.getGroupSize() == groupSize);
		
		Assert.isTrue(gfa.getComparisonChoices().equals(comparisonChoices));
		
		Assert.isTrue(gfa.getQuestions().equals(questions));
		
		Assert.isTrue(gfa.getWeights().equals(weights));
	}
	
	@Test
	void testGetId() {
		Assert.isTrue(gfa.getId() == id);
	}

	@Test
	void testGetCourse() {
		Assert.isTrue(gfa.getCourse() == course);
	}

	@Test
	void testGetCreatedOn() {
		Assert.isTrue(gfa.getCreatedOn() == createdOn);
	}

	@Test
	void testGetComparisonChoices() {		
		Assert.isTrue(gfa.getComparisonChoices().equals(comparisonChoices));
	}

	@Test
	void testGetQuestions() {
		Assert.isTrue(gfa.getQuestions().equals(questions));
	}

	@Test
	void testGetWeights() {
		Assert.isTrue(gfa.getWeights().equals(weights));
	}

	@Test
	void testGetGroupSize() {
		Assert.isTrue(gfa.getGroupSize() == groupSize);
	}

}
