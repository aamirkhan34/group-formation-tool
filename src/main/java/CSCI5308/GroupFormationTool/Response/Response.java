package CSCI5308.GroupFormationTool.Response;

import CSCI5308.GroupFormationTool.Questions.Question;

import java.util.ArrayList;

public class Response {

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    private Question question;
    private String singleresponse;
    private ArrayList<String> response;

    public String getSingleresponse() {
        return singleresponse;
    }

    public void setSingleresponse(String singleresponse) {
        this.singleresponse = singleresponse;
    }


    public ArrayList<String> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<String> response) {
        this.response = response;
    }

}
