package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Response.Response;

public interface IGroupPersistence
{
	public boolean createGroups(List<Group> group);
	public List<Group> loadGroupByCourse(Course course);
}
