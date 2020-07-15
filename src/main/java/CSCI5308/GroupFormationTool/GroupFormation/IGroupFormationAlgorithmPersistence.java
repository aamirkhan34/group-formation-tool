package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Courses.Course;

public interface IGroupFormationAlgorithmPersistence
{
	public boolean createAlgorithm(GroupFormationAlgorithm algorithm);
	public GroupFormationAlgorithm loadAlgorithmByCourse(Course course);
}
