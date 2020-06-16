DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateUser $$

CREATE PROCEDURE spCreateQuestion (
	IN owner_id BIGINT,
    IN type_id INT,
    IN title VARCHAR(75),
    IN context VARCHAR(100),
    IN public TINYINT,
    OUT id BIGINT
)
BEGIN
	INSERT INTO Question(owner_id, type_id,title,context,public)
    VALUES (owner_id, type_id,title,context,public);
	SET @questionID = LAST_INSERT_ID();
END $$

DELIMITER ;