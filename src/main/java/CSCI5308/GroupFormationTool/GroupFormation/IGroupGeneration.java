package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.List;

public interface IGroupGeneration {
	List<Group> generateGroups(List<List<Double>> matchMatrix);
}
