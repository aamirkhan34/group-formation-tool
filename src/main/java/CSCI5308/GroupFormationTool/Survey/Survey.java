package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.Question;

import java.util.ArrayList;

public class Survey {
    private long id;
    private long courseID;
    private User instructor;
    private ArrayList<Question> surveyQuestionList;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourseID() {
        return courseID;
    }

    public void setCourseID(long courseID) {
        this.courseID = courseID;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public ArrayList<Question> getSurveyQuestionList() {
        return surveyQuestionList;
    }

    public void setSurveyQuestionList(ArrayList<Question> surveyQuestionList) {
        this.surveyQuestionList = surveyQuestionList;
    }

    public boolean createSurvey(ISurveyPersistence surveyDB) {
        return surveyDB.createSurvey(this);
    }
}
