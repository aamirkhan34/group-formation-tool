DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateQuestion $$

CREATE PROCEDURE spCreateQuestion (
	IN title VARCHAR(200),
    IN questionText VARCHAR(500),
    IN instructorID bigInt(20),
    IN type_id int(11),
    OUT id BIGINT
)
BEGIN
	INSERT INTO Question(instructorID,type_id,title,questionText,createdON)
    VALUES (instructorID,type_id,title,questionText,CURRENT_DATE);
	SET id = LAST_INSERT_ID();
END $$

DELIMITER ;