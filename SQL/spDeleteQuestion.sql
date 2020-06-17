USE `CSCI5308_1_DEVINT`;
DROP procedure IF EXISTS `spDeleteQuestion`;

DELIMITER $$
USE `CSCI5308_1_DEVINT`$$
CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `spDeleteQuestion`(
	IN id BIGINT
)
BEGIN
	DELETE FROM QuestionOptions
    WHERE QuestionOptions.questionID = id;

	DELETE FROM Question
    WHERE Question.id = id;
END$$

DELIMITER ;