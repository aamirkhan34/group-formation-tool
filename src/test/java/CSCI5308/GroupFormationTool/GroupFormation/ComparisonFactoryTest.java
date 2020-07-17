package CSCI5308.GroupFormationTool.GroupFormation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class ComparisonFactoryTest {

	@Test
	void testGetComparisonFactory() {
		boolean isChoiceSimilarity1 = true;
		boolean isChoiceSimilarity2 = false;

		// Test 1: Returned value must be an instance of type SimilarComparisonFactory
		Assert.isTrue(ComparisonFactory.getComparisonFactory(isChoiceSimilarity1) instanceof SimilarComparisonFactory);

		// Test 2: Returned value must be an instance of type DissimilarComparisonFactory
		Assert.isTrue(
				ComparisonFactory.getComparisonFactory(isChoiceSimilarity2) instanceof DissimilarComparisonFactory);
	}

}
