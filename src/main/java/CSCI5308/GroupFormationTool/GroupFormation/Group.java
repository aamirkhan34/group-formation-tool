package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;

public class Group {
	private long id;
	private Course course;
	private Date createdOn;
	private ArrayList<User> students;

	public Group()
	{

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public ArrayList<User> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<User> students) {
		this.students = students;
	}

	public Group(long id, Course course, Date createdOn, ArrayList<User> students) {
		super();
		this.id = id;
		this.course = course;
		this.createdOn = createdOn;
		this.students = students;
	}
	public List<Group> loadGroupByCourse(IGroupPersistence groupDB, Course course){
		return groupDB.loadGroupByCourse(course);
	}

}
