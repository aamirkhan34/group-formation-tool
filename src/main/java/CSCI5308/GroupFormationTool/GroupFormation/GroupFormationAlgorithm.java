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
	private ArrayList<Boolean> similarityChoices;

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

	@Override
	public String toString() {
		return "GroupFormationAlgorithm [id=" + id + ", course=" + course + ", createdOn=" + createdOn + ", groupSize="
				+ groupSize + ", comparisonChoices=" + comparisonChoices + ", questions=" + questions + ", weights="
				+ weights + "]";
	}

}
