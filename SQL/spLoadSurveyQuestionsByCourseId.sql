DELIMITER $$

DROP procedure IF EXISTS spLoadSurveyQuestionsByCourseId;


CREATE PROCEDURE spLoadSurveyQuestionsByCourseId (
IN id BIGINT
)
BEGIN
	SELECT SurveyQuestions.questionID
    FROM SurveyQuestions
    WHERE SurveyQuestions.surveyID = id;
END $$

DELIMITER ;

