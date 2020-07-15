package CSCI5308.GroupFormationTool.GroupFormation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class DissimilarComparisonFactoryTest {

	@Test
	void testGetNumericComparer() {
		ComparisonFactory dcf = new DissimilarComparisonFactory();

		// Test 1: Returned value must be an instance of type
		// DistantNumericDissimilarity
		Assert.isTrue(dcf.getNumericComparer(ComparisonMethod.DISTANT) instanceof DistantNumericDissimilarity);

		// Test 2: Returned value must be null
		Assert.isNull(dcf.getNumericComparer(ComparisonMethod.CLOSE));
	}

	@Test
	void testGetSingleChoiceComparer() {
		ComparisonFactory dcf = new DissimilarComparisonFactory();

		// Test 1: Returned value must be an instance of type
		// EqualsSingleChoiceDissimilarity
		Assert.isTrue(dcf.getSingleChoiceComparer(ComparisonMethod.UNEQUAL) instanceof EqualsSingleChoiceDissimilarity);

		// Test 2: Returned value must be null
		Assert.isNull(dcf.getSingleChoiceComparer(ComparisonMethod.EQUAL));
	}

	@Test
	void testGetMultipleChoiceComparer() {
		ComparisonFactory dcf = new DissimilarComparisonFactory();

		// Test 1: Returned value must be an instance of type
		// AverageEqualsMultipleChoiceDissimilarity
		Assert.isTrue(dcf.getMultipleChoiceComparer(
				ComparisonMethod.AVERAGEDIFFERENCE) instanceof AverageEqualsMultipleChoiceDissimilarity);

		// Test 2: Returned value must be null
		Assert.isNull(dcf.getMultipleChoiceComparer(ComparisonMethod.AVERAGEMATCH));
	}

	@Test
	void testGetTextComparer() {
		ComparisonFactory dcf = new DissimilarComparisonFactory();

		// Test 1: Returned value must be an instance of type
		// EqualsTextDissimilarity
		Assert.isTrue(dcf.getTextComparer(ComparisonMethod.UNEQUAL) instanceof EqualsTextDissimilarity);

		// Test 2: Returned value must be null
		Assert.isNull(dcf.getTextComparer(ComparisonMethod.EQUAL));
	}

}
