package CSCI5308.GroupFormationTool.GroupFormation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Logger.ErrorLoggerFactory;
import CSCI5308.GroupFormationTool.Logger.ILogger;
import CSCI5308.GroupFormationTool.Logger.ILoggerFactory;
import CSCI5308.GroupFormationTool.Response.Response;
import CSCI5308.GroupFormationTool.SystemConfig;

import javax.xml.transform.Result;

public class GroupDB implements IGroupPersistence {

	private static final int NUMERIC_TYPE_ID = 1;
	private static final int MULTI_CHOICE_MULTI_ONE_TYPE_ID = 2;
	private static final int MULTI_CHOICE_MULTI_MULTI_TYPE_ID = 3;
	private static final int FREE_TEXT_TYPE_ID = 4;

	@Override
	public boolean createGroups(List<Group> group) {
		CallStoredProcedure proc = null;
		return false;
	}

	@Override
	public List<Group> loadGroupByCourse(Course course) {
		CallStoredProcedure proc = null;
		ILoggerFactory loggerFactory = new ErrorLoggerFactory();
		ILogger logger = loggerFactory.createLogger();
		List<Group> groups = new ArrayList<>();
		try {
			proc = new CallStoredProcedure("spGroupIDByCourseID(?)");
			proc.setParameter(1,course.getId());
			ResultSet resultSet = proc.executeWithResults();
			while (resultSet.next()){
				ArrayList<User> users = new ArrayList<>();
				int groupID = resultSet.getInt(1);
				Date createOn = resultSet.getDate(2);
				proc = new CallStoredProcedure("spUserIDByGroupID(?)");
				proc.setParameter(1,groupID);
				ResultSet resultSet1 = proc.executeWithResults();
				while (resultSet1.next()){
					User user = new User();
					int eachUserID = resultSet1.getInt(1);
					proc = new CallStoredProcedure("spLoadUser(?)");
					proc.setParameter(1, eachUserID);
					ResultSet results = proc.executeWithResults();
					if (null != results)
					{
						while (results.next())
						{
							long userID = results.getLong(1);
							String bannerID = results.getString(2);
							String firstName = results.getString(4);
							String lastName = results.getString(5);
							String email = results.getString(6);
							user.setID(userID);
							user.setBannerID(bannerID);
							user.setFirstName(firstName);
							user.setLastName(lastName);
							user.setEmail(email);
						}
						users.add(user);
					}
				}
				Group group = new Group(groupID,course,createOn,users);
				groups.add(group);
			}
			return groups;
		}
		catch (SQLException e) {
			logger.logMessage(e.getMessage(),"Error in loadGroupByCourse method", SystemConfig.instance().getLogDB());
			e.printStackTrace();
		}
		finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return null;
	}

	@Override
	public LinkedHashMap<User, List<Response>> loadUsersResponsesByCourseID(Long courseID){
		LinkedHashMap<User, List<Response>> responses = new LinkedHashMap<>();
		CallStoredProcedure proc = null;
		ILoggerFactory loggerFactory = new ErrorLoggerFactory();
		ILogger logger = loggerFactory.createLogger();
		try {
			proc = new CallStoredProcedure("spLoadSurveyIdByCourseId(?)");
			proc.setParameter(1,courseID);
			ResultSet resultSet = proc.executeWithResults();
			while (resultSet.next()){
				User user = new User();
				List<Response> userResponses = new ArrayList<>();
				int surveyId = resultSet.getInt(1);
				proc = new CallStoredProcedure("spUsersBySurveyId(?)");
				proc.setParameter(1,surveyId);
				ResultSet resultSet1 = proc.executeWithResults();
				while (resultSet.next()){
					int userID = resultSet1.getInt(1);
					proc = new CallStoredProcedure("spLoadUser(?)");
					proc.setParameter(1,userID);
					ResultSet userDetails = proc.executeWithResults();
					while (userDetails.next()){
						long userId = userDetails.getLong(1);
						String bannerID = userDetails.getString(2);
						String firstName = userDetails.getString(4);
						String lastName = userDetails.getString(5);
						String email = userDetails.getString(6);
						user.setID(userId);
						user.setBannerID(bannerID);
						user.setFirstName(firstName);
						user.setLastName(lastName);
						user.setEmail(email);
					}
					proc = new CallStoredProcedure("spResponseIdByUserIDSurveyId(?,?)");
					proc.setParameter(1,surveyId);
					proc.setParameter(2,userID);
					ResultSet responseIDANDQuestionsID = proc.executeWithResults();
					while (responseIDANDQuestionsID.next()){
						int questionId = responseIDANDQuestionsID.getInt(2);
						int responseID = responseIDANDQuestionsID.getInt(1);
						proc = new CallStoredProcedure("spLoadQuestionById(?)");
						proc.setParameter(1,questionId);
						ResultSet questionType = proc.executeWithResults();
						while (questionType.next()){
							Response response = new Response();
							int eachQuestionType = questionType.getInt(3);
							if (eachQuestionType == MULTI_CHOICE_MULTI_ONE_TYPE_ID){
								proc = new CallStoredProcedure("spLoadMultipleOptionSingleResponse(?)");
								proc.setParameter(1,responseID);
								ResultSet eachQuestionResponse = proc.executeWithResults();
								while (eachQuestionResponse.next()){
									String responseEach =  eachQuestionResponse.getString(1);
									response.setSingleresponse(responseEach);
								}
							}
							if (eachQuestionType == FREE_TEXT_TYPE_ID){
								proc = new CallStoredProcedure("spLoadFreeTextResponse(?)");
								proc.setParameter(1,responseID);
								ResultSet eachQuestionResponse = proc.executeWithResults();
								while (eachQuestionResponse.next()){
									String responseEach =  eachQuestionResponse.getString(1);
									response.setSingleresponse(responseEach);
								}
							}
							if (eachQuestionType == NUMERIC_TYPE_ID){
								proc = new CallStoredProcedure("spLoadNumericResponse(?)");
								proc.setParameter(1,responseID);
								ResultSet eachQuestionResponse = proc.executeWithResults();
								while (eachQuestionResponse.next()){
									String responseEach =  eachQuestionResponse.getString(1);
									response.setSingleresponse(responseEach);
								}
							}
							if (eachQuestionType == MULTI_CHOICE_MULTI_MULTI_TYPE_ID){
								proc = new CallStoredProcedure("spLoadMultipleOptionMultipleResponse(?)");
								proc.setParameter(1,responseID);
								ResultSet eachQuestionResponse = proc.executeWithResults();
								ArrayList<String> eachResponses = new ArrayList<>();
								while (eachQuestionResponse.next()){
									String responseEach =  eachQuestionResponse.getString(1);
									eachResponses.add(responseEach);
								}
								response.setResponse(eachResponses);
							}
							userResponses.add(response);
						}
					}
					responses.put(user,userResponses);
				}
				return responses;
			}
		}
		catch (SQLException e) {
			logger.logMessage(e.getMessage(),"Error in loadUsersResponsesByCourseID method", SystemConfig.instance().getLogDB());

			e.printStackTrace();
		}
		finally {
			if (null != proc) {
				proc.cleanup();
			}
		}

		return  responses;
	}

}
