package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Questions.Question;

public interface ISurveyPersistence {
    public boolean createSurvey(Survey survey);
    public boolean publishSurvey(Survey survey);
}
