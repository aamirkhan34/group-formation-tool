package CSCI5308.GroupFormationTool.GroupFormation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class GroupFormationControllerTest {

	@Test
	void testSurveyQuestions() {
		fail("Not yet implemented");
	}

	@Test
	void testParseComparisonChoices() {
		String body = "id=5&noOfQuestions=3&questionid=77&questionid=78&comparisonChoice77=1&weight=10&weight=50&comparisonChoice78=0";
		
		List<Integer> output = new ArrayList<Integer>(Arrays.asList(1, 0)); 
				
		GroupFormationController gfc = new GroupFormationController();
		
		Assert.isTrue(gfc.parseComparisonChoices(body).equals(output));
	}

	@Test
	void testGenerateAlgo() {
		fail("Not yet implemented");
	}

}
