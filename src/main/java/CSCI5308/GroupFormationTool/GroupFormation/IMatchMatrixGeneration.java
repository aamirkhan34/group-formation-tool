package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Response.Response;

public interface IMatchMatrixGeneration {
	List<List<Double>> generateMatchMatrix(GroupFormationAlgorithm gFormationAlgorithm, List<Response> responses,
			List<User> students);
}
