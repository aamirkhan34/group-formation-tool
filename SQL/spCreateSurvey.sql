DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateSurvey $$

CREATE PROCEDURE spCreateSurvey (
	IN courseID bigint,
    IN instructorID bigint  
)
BEGIN
	DECLARE sid bigint(11);
	INSERT INTO Survey(courseID,instructorID)
    VALUES (courseID,instructorID);
	SET sid = LAST_INSERT_ID();
    SELECT S.id from Survey S WHERE S.id = sid;
END $$

DELIMITER ;