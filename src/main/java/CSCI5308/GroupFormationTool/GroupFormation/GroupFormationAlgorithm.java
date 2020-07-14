package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;
import java.util.Date;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Questions.Question;

public class GroupFormationAlgorithm {
	private long id;
	private Course course;
	private Date createdOn;
	private boolean isChoiceSimilarity;
	private ArrayList<Question> questions;
	private ArrayList<Double> weights;

	public GroupFormationAlgorithm(long id, Course course, Date createdOn, boolean isChoiceSimilarity,
			ArrayList<Question> questions, ArrayList<Double> weights) {
		super();
		this.id = id;
		this.course = course;
		this.createdOn = createdOn;
		this.isChoiceSimilarity = isChoiceSimilarity;
		this.questions = questions;
		this.weights = weights;
	}

	@Override
	public String toString() {
		return "GroupFormationAlgorithm [id=" + id + ", course=" + course + ", createdOn=" + createdOn
				+ ", isChoiceSimilarity=" + isChoiceSimilarity + ", questions=" + questions + ", weights=" + weights
				+ "]";
	}
	
	

}
