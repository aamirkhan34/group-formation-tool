package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;
import java.util.Date;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Questions.Question;

public class GroupFormationAlgorithm {
	private long id;
	private Course course;
	private Date createdOn;
	private int groupSize;
	private ArrayList<Boolean> comparisonChoices;
	private ArrayList<Question> questions;
	private ArrayList<Double> weights;

	public GroupFormationAlgorithm(long id, Course course, Date createdOn, ArrayList<Boolean> comparisonChoices,
			ArrayList<Question> questions, ArrayList<Double> weights, int groupSize) {
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

	public ArrayList<Boolean> getComparisonChoices() {
		return comparisonChoices;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public ArrayList<Double> getWeights() {
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