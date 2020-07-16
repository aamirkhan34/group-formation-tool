package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.LinkedHashMap;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;

public interface IGroupGeneration {
	List<Group> generateGroups(LinkedHashMap<List<User>, Double> matchMatrix, int groupSize, List<User> students);
}
