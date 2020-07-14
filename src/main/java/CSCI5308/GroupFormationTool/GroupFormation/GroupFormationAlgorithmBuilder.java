package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;
import java.util.Date;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Questions.Question;

public class GroupFormationAlgorithmBuilder {
	private long id;
	private Course course;
	private Date createdOn;
	private boolean isChoiceSimilarity;
	private ArrayList<Question> questions;
	private ArrayList<Double> weights;

	public GroupFormationAlgorithmBuilder setId(long id) {
		this.id = id;
		return this;
	}

	public GroupFormationAlgorithmBuilder setCourse(Course course) {
		this.course = course;
		return this;
	}

	public GroupFormationAlgorithmBuilder setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
		return this;
	}

	public GroupFormationAlgorithmBuilder setChoiceSimilarity(boolean isChoiceSimilarity) {
		this.isChoiceSimilarity = isChoiceSimilarity;
		return this;
	}

	public GroupFormationAlgorithmBuilder setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
		return this;
	}

	public GroupFormationAlgorithmBuilder setWeights(ArrayList<Double> weights) {
		this.weights = weights;
		return this;
	}

	public GroupFormationAlgorithm getGroupFormationAlgorithm() {
		return new GroupFormationAlgorithm(id, course, createdOn, isChoiceSimilarity, questions, weights);
	}
}
