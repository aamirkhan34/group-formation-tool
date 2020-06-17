package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.AccessControl.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Question {
    private long id;
    private String title;
    private String text;
    private int typeID;
    private String type;
    private User instructor;
    private Date createdOn;
    private ArrayList<MultipleChoiceOption> multipleChoiceOption;

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public ArrayList<MultipleChoiceOption> getMultipleChoiceOption() {
        return multipleChoiceOption;
    }

    public void setMultipleChoiceOption(ArrayList<MultipleChoiceOption> multipleChoiceOption) {
        this.multipleChoiceOption = multipleChoiceOption;
    }

    public boolean createQuestion(IQuestionPersistence questionDB) {
        return questionDB.createQuestion(this);
    }
    public boolean deleteQuestion(IQuestionPersistence questionDB) {

        return questionDB.deleteQuestionById(this.id);
    }
    public List<Question> getAllQuestionTypes(IQuestionPersistence questionDB){
        return  questionDB.loadAllQuestionTypes();
    }

    public List<Question> loadQuestionById(IQuestionPersistence questionDB){
        return  questionDB.loadAllQuestionsByInstructor(2);
    }
}
