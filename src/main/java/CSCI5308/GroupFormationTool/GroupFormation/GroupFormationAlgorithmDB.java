package CSCI5308.GroupFormationTool.GroupFormation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Questions.Question;

public class GroupFormationAlgorithmDB implements IGroupFormationAlgorithmPersistence {

	@Override
	public boolean createAlgorithm(GroupFormationAlgorithm algorithm) {
		CallStoredProcedure proc = null;
		try {
			proc = new CallStoredProcedure("spCreateAlgorithm(?, ?, ?, ?, ?)");
			proc.setParameter(1, algorithm.getCourse().getId());
			proc.setParameter(2, algorithm.getCreatedOn().toString());
			proc.setParameter(3, algorithm.getCreatedOn().toString());
			proc.setParameter(4, algorithm.getGroupSize());
			proc.registerOutputParameterLong(5);
			proc.execute();
			Long algorithmID = proc.getLongParameters(5);
			proc = new CallStoredProcedure("spCreateAlgorithmDefinition(?, ?, ?, ?, ?)");
			for (int i = 0; i <algorithm.getQuestions().size() ; i++) {
				proc.setParameter(1, algorithmID);
				proc.setParameter(2, algorithm.getQuestions().get(i).getId());
				proc.setParameter(3, algorithm.getWeights().get(i));
				proc.setParameter(4, algorithm.getComparisonChoices().get(i));
				proc.registerOutputParameterLong(5);
				proc.execute();
			}
		}
		catch (SQLException e) {
			// Logging needed
			return false;
		}
		finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}

	@Override
	public GroupFormationAlgorithm loadAlgorithmByCourse(Course course) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Question> questions = new ArrayList<>();
		List<Double> weights = new ArrayList<>();
		List<Boolean> comparisonChoices = new ArrayList<>();
		CallStoredProcedure proc = null;
		Long algorithmID = null;
		Date createOn = null;
		Date updateOn = null;
		Integer groupSize = null;
		GroupFormationAlgorithm algorithm = null;
		try {
			proc = new CallStoredProcedure("spLoadAlgorithmByCourseId(?)");
			proc.setParameter(1,course.getId());
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					algorithmID = results.getLong(1);
					createOn = sdf.parse(results.getString(3));
					updateOn = sdf.parse(results.getString(4));
					groupSize= results.getInt(1);
				}
			}
			proc = new CallStoredProcedure("spLoadAlgorithmDefinitionByAlgorithmId(?)");
			proc.setParameter(1,algorithmID);
			results = proc.executeWithResults();
			if (null != results) {
				Question question;
				while (results.next()) {
					question = new Question();
					question.setId(results.getLong(3));
					weights.add(results.getDouble(4));
					comparisonChoices.add(results.getBoolean(5));
					question.setType(results.getString(6));
				}
			}
			algorithm = new GroupFormationAlgorithm(algorithmID,course,createOn,comparisonChoices,questions,weights,groupSize);
		}
		catch (SQLException e) {
			// Logging needed.
		}
		catch (ParseException e){
			e.printStackTrace();
		}
		finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return algorithm;
	}

}
