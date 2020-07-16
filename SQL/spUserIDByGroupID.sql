USE `CSCI5308_1_DEVINT`;
DROP procedure IF EXISTS `spUserIDByGroupID`;

DELIMITER $$
USE `CSCI5308_1_DEVINT`$$
CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `spUserIDByGroupID`(
	IN gID VARCHAR(20)
)
BEGIN
	SELECT userID
    FROM GroupMember
    WHERE GroupMember.groupID = gID;
END$$

DELIMITER ;

