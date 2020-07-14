package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Courses.Course;

public interface IGroupPersistence
{
	public boolean createGroup(Group group);
	public ArrayList<Group> loadGroupByCourse(Course course);
}
