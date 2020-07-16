package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Courses.Course;

public interface IGroupPersistence
{
	public boolean createGroup(Group group);
	public List<Group> loadGroupByCourse(Course course);
}
