package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Questions.Question;

public class GroupFormationAlgorithm {
	private long id;
	private Course course;
	private Date createdOn;
	private int groupSize;
	private List<Boolean> comparisonChoices;
	private List<Question> questions;
	private List<Double> weights;

	public GroupFormationAlgorithm(long id, Course course, Date createdOn, List<Boolean> comparisonChoices,
								   List<Question> questions, List<Double> weights, int groupSize) {
		super();
		this.id = id;
		this.course = course;
		this.createdOn = createdOn;
		this.groupSize = groupSize;
		this.comparisonChoices = comparisonChoices;
		this.questions = questions;
		this.weights = weights;
	}

	public long getId() {
		return id;
	}

	public Course getCourse() {
		return course;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public int getGroupSize() {
		return groupSize;
	}

	public List<Boolean> getComparisonChoices() {
		return comparisonChoices;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public List<Double> getWeights() {
		return weights;
	}

	@Override
	public String toString() {
		return "GroupFormationAlgorithm [id=" + id + ", course=" + course + ", createdOn=" + createdOn + ", groupSize="
				+ groupSize + ", comparisonChoices=" + comparisonChoices + ", questions=" + questions + ", weights="
				+ weights + "]";
	}

	public boolean createAlgorithm(GroupFormationAlgorithm algorithm, IGroupFormationAlgorithmPersistence algorithmDB) {
		boolean status = algorithmDB.createAlgorithm(algorithm);
		return status;
	}

	public GroupFormationAlgorithm loadAlgorithmByCourse(Course course,
			IGroupFormationAlgorithmPersistence algorithmDB) {
		GroupFormationAlgorithm algorithm = algorithmDB.loadAlgorithmByCourse(course);
		return algorithm;
	}

}