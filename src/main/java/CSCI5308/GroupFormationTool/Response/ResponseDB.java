package CSCI5308.GroupFormationTool.Response;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Questions.MultipleChoiceOption;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResponseDB implements IResponsePersistence {

    private static final int NUMERIC_TYPE_ID = 1;
    private static final int MULTI_CHOICE_MULTI_ONE_TYPE_ID = 2;
    private static final int MULTI_CHOICE_MULTI_MULTI_TYPE_ID = 3;
    private static final int FREE_TEXT_TYPE_ID = 4;

    @Override
    public boolean saveResponse(ArrayList<Response> responses, int surveyID) {
        CallStoredProcedure proc = null;
        try {
            for (Response r : responses) {
                proc = new CallStoredProcedure("spSaveSurveyResponse(?,?,?)");
                proc.setParameter(1,  surveyID);
                proc.setParameter(2, r.getQuestion().getId());
                proc.setParameter(3, r.getQuestion().getInstructor().getID());
                ResultSet resultSet = proc.executeWithResults();
                if(r.getQuestion().getTypeID() == MULTI_CHOICE_MULTI_MULTI_TYPE_ID){
                    while (resultSet.next()){
                        Long responseIdFromDB = resultSet.getLong(1);
                        ArrayList<String> choiceResponses = r.getResponse();
                        for (String choice: choiceResponses
                        ) {
                            proc = new CallStoredProcedure("spSaveSurveyResponseMultipleChoiceMultiple(?,?,?)");
                            proc.setParameter(1, responseIdFromDB);
                            proc.setParameter(2, choice);
                            proc.setParameter(3,r.getQuestion().getTypeID());
                            proc.execute();
                        }
                    }
                }
                if(r.getQuestion().getTypeID() == FREE_TEXT_TYPE_ID) {
                    while (resultSet.next()){
                        Long responseIdFromDB = resultSet.getLong(1);
                        proc = new CallStoredProcedure("spSaveSurveyResponseFreeText(?,?,?)");
                        proc.setParameter(1, responseIdFromDB);
                        proc.setParameter(2,r.getSingleresponse());
                        proc.setParameter(3,r.getQuestion().getTypeID());
                        proc.execute();
                    }
                }
                if(r.getQuestion().getTypeID() == MULTI_CHOICE_MULTI_ONE_TYPE_ID) {
                    while (resultSet.next()){
                        Long responseIdFromDB = resultSet.getLong(1);
                        proc = new CallStoredProcedure("spSaveSurveyResponseMultipleChoiceSingle(?,?,?)");
                        proc.setParameter(1, responseIdFromDB);
                        proc.setParameter(2, r.getSingleresponse());
                        proc.setParameter(3, r.getQuestion().getTypeID());
                        proc.execute();
                    }
                }
                if(r.getQuestion().getTypeID() == NUMERIC_TYPE_ID) {
                    while (resultSet.next()){
                        Long responseIdFromDB = resultSet.getLong(1);
                        proc = new CallStoredProcedure("spSaveSurveyResponseNumeric(?,?,?)");
                        proc.setParameter(1, responseIdFromDB);
                        proc.setParameter(2, r.getSingleresponse());
                        proc.setParameter(3, r.getQuestion().getTypeID());
                        proc.execute();
                    }
                }
            }
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
    }

    @Override
    public boolean saveResponseAnswers(Response response) {
        CallStoredProcedure proc = null;
        try {
            ArrayList<String> choices = response.getResponse();
            proc = new CallStoredProcedure("spLoadQuestionOptionsById(?)");

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return false;
    }
}
