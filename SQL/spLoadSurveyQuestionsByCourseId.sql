DELIMITER $$

DROP procedure IF EXISTS spLoadSurveyQuestionsByCourseId;


CREATE PROCEDURE spLoadSurveyQuestionsByCourseId (
IN id BIGINT
)
BEGIN
	select SurveyQuestions.questionID,Survey.isPublished from Survey
join SurveyQuestions on Survey.id=SurveyQuestions.surveyID
    WHERE Survey.courseID = id;
END $$

DELIMITER ;

