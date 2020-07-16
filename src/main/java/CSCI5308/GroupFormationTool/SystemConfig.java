package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.Email.DefaultEmailConfiguration;
import CSCI5308.GroupFormationTool.Email.IEmailConfiguration;
import CSCI5308.GroupFormationTool.Questions.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Questions.QuestionDB;
import CSCI5308.GroupFormationTool.Security.*;
import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Database.*;
import CSCI5308.GroupFormationTool.Courses.*;
import CSCI5308.GroupFormationTool.passwordConstraint.DefaultPasswordConstraintConfiguration;
import CSCI5308.GroupFormationTool.passwordConstraint.DefaultPasswordHistoryConstraintConfiguration;
import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordConstraintConfiguration;
import CSCI5308.GroupFormationTool.passwordConstraint.IPasswordHistoryConstraintConfiguration;

/*
 * This is a singleton, we will learn about these when we learn design patterns.
 * 
 * The single responsibility of this singleton is to store concrete classes
 * selected by the system for use in the rest of the system. This will allow
 * a form of dependency injection in places where we cannot use normal
 * dependency injection (for example classes that override or extend existing
 * library classes in the framework).
 */
public class SystemConfig
{
	private static SystemConfig uniqueInstance = null;

	private IPasswordEncryption passwordEncryption;
	private IPasswordConstraintConfiguration passwordConstraintConfiguration;
	private IUserPersistence userDB;
	private IDatabaseConfiguration databaseConfiguration;
	private ICoursePersistence courseDB;
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;
	private IEmailConfiguration emailConfiguration;
	private IQuestionPersistence questionDB;
	private IPasswordHistoryConstraintConfiguration passwordHistoryConstraintConfiguration;
	// This private constructor ensures that no class other than System can allocate
	// the System object. The compiler would prevent it.

	private SystemConfig()
	{
		passwordConstraintConfiguration =  DefaultPasswordConstraintConfiguration.getInstance();
		passwordEncryption = new BCryptPasswordEncryption();
		userDB = UserDB.getInstance();
		databaseConfiguration = DefaultDatabaseConfiguration.getInstance();
		courseDB = CourseDB.getInstance();
		courseUserRelationshipDB = CourseUserRelationshipDB.getInstance();
		emailConfiguration = DefaultEmailConfiguration.getInstance();
		questionDB = QuestionDB.getInstance();
		passwordHistoryConstraintConfiguration = DefaultPasswordHistoryConstraintConfiguration.getInstance();
	}
	public static SystemConfig instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new SystemConfig();
		}
		return uniqueInstance;
	}

	public IPasswordHistoryConstraintConfiguration getPasswordHistoryConstraintConfiguration()
	{
		return passwordHistoryConstraintConfiguration;
	}

	public void setPasswordHistoryConstraintConfiguration(IPasswordHistoryConstraintConfiguration passwordHistoryConstraintConfiguration)
	{
		this.passwordHistoryConstraintConfiguration = passwordHistoryConstraintConfiguration;
	}
	public IPasswordEncryption getPasswordEncryption()
	{
		return passwordEncryption;
	}

	public void setPasswordEncryption(IPasswordEncryption passwordEncryption)
	{
		this.passwordEncryption = passwordEncryption;
	}

	public IPasswordConstraintConfiguration getPasswordConstraintConfiguration()
	{
		return passwordConstraintConfiguration;
	}

	public void setPasswordConstraintConfiguration(IPasswordConstraintConfiguration passwordConstraintConfiguration)
	{
		this.passwordConstraintConfiguration = passwordConstraintConfiguration;
	}

	public IUserPersistence getUserDB()
	{
		return userDB;
	}

	public void setUserDB(IUserPersistence userDB)
	{
		this.userDB = userDB;
	}

	public IDatabaseConfiguration getDatabaseConfiguration()
	{
		return databaseConfiguration;
	}

	public void setDatabaseConfiguration(IDatabaseConfiguration databaseConfiguration)
	{
		this.databaseConfiguration = databaseConfiguration;
	}

	public IEmailConfiguration getEmailConfiguration()
	{
		return emailConfiguration;
	}

	public void setEmailConfiguration(IEmailConfiguration emailConfiguration)
	{
		this.emailConfiguration = emailConfiguration;
	}

	public void setCourseDB(ICoursePersistence courseDB)
	{
		this.courseDB = courseDB;
	}

	public ICoursePersistence getCourseDB()
	{
		return courseDB;
	}
	
	public void setCourseUserRelationshipDB(ICourseUserRelationshipPersistence courseUserRelationshipDB)
	{
		this.courseUserRelationshipDB = courseUserRelationshipDB;
	}
	
	public ICourseUserRelationshipPersistence getCourseUserRelationshipDB()
	{
		return courseUserRelationshipDB;
	}

	public IQuestionPersistence getQuestionDB()
	{
		return questionDB;
	}

	public void setQuestionDB(IQuestionPersistence questionDB)
	{
		this.questionDB = questionDB;
	}
}
