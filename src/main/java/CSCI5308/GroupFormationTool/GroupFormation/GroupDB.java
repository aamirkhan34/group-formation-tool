package CSCI5308.GroupFormationTool.GroupFormation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class GroupDB implements IGroupPersistence {

	@Override
	public boolean createGroups(List<Group> group) {
		CallStoredProcedure proc = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			for (Group g : group) {
				proc = new CallStoredProcedure("spCreateGroup(?, ?, ?, ?)");
				proc.setParameter(1, g.getCourse().getId());
				proc.setParameter(2, sdf.format(g.getCreatedOn()));
				proc.setParameter(3, sdf.format(g.getCreatedOn()));
				proc.registerOutputParameterLong(4);
				proc.execute();
				
				Long groupID = proc.getLongParameters(4);
				proc = new CallStoredProcedure("spCreateGroupMembers(?, ?, ?)");
				for (int i = 0; i < g.getStudents().size(); i++) {
					proc.setParameter(1, groupID);
					proc.setParameter(2, g.getStudents().get(i).getId());
					proc.registerOutputParameterLong(3);
					proc.execute();
				}
			}
		} catch (SQLException e) {
			// Logging needed
			System.out.println(e);
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}

	@Override
	public List<Group> loadGroupByCourse(Course course) {
		CallStoredProcedure proc = null;
		List<Group> groups = new ArrayList<>();
		try {
			proc = new CallStoredProcedure("spGroupIDByCourseID(?)");
			proc.setParameter(1, course.getId());
			ResultSet resultSet = proc.executeWithResults();
			while (resultSet.next()) {
				ArrayList<User> users = new ArrayList<>();
				int groupID = resultSet.getInt(1);
				Date createOn = resultSet.getDate(2);
				proc = new CallStoredProcedure("spUserIDByGroupID(?)");
				proc.setParameter(1, groupID);
				ResultSet resultSet1 = proc.executeWithResults();
				while (resultSet1.next()) {
					User user = new User();
					int eachUserID = resultSet1.getInt(1);
					proc = new CallStoredProcedure("spLoadUser(?)");
					proc.setParameter(1, eachUserID);
					ResultSet results = proc.executeWithResults();
					if (null != results) {
						while (results.next()) {
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
				Group group = new Group(groupID, course, createOn, users);
				groups.add(group);
			}
			return groups;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return null;
	}

}
