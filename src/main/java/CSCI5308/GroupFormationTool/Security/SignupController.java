package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.passwordConstraint.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

import javax.mail.MessagingException;

@Controller
public class SignupController
{
	private final String USERNAME = "username";
	private final String PASSWORD = "password";
	private final String PASSWORD_CONFIRMATION = "passwordConfirmation";
	private final String FIRST_NAME = "firstName";
	private final String LAST_NAME = "lastName";
	private final String EMAIL = "email";
	private final String ERROR = "errorMessage";
	
	@GetMapping("/signup")
	public String displaySignup(Model model)
	{
		IPasswordConstraintConfiguration config = new DefaultPasswordConstraintConfiguration();
		IPasswordLengthChecker lengthChecker = new PasswordLengthChecker();
		IPasswordTypeLengthChecker typeLengthChecker = new PasswordTypeLengthChecker();
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST) 
   public ModelAndView processSignup(
   	@RequestParam(name = USERNAME) String bannerID,
   	@RequestParam(name = PASSWORD) String password,
   	@RequestParam(name = PASSWORD_CONFIRMATION) String passwordConfirm,
   	@RequestParam(name = FIRST_NAME) String firstName,
   	@RequestParam(name = LAST_NAME) String lastName,
   	@RequestParam(name = EMAIL) String email) throws MessagingException {

		StringBuffer errorInformation = new StringBuffer("");
		boolean passwordFormat = true;
		IPasswordConstraintConfiguration config = new DefaultPasswordConstraintConfiguration();
		IPasswordLengthChecker lengthChecker = new PasswordLengthChecker();
		IPasswordTypeLengthChecker typeLengthChecker = new PasswordTypeLengthChecker();
		if (!lengthChecker.checkMinLength(password)){
			passwordFormat = false;
			errorInformation.append("The password should not be shorter than ");
			errorInformation.append(config.getPasswordMin());
			errorInformation.append("<br/> \n");
		} else if (!lengthChecker.checkMaxLength(password)){
			passwordFormat = false;
			errorInformation.append("The password should not be longer than than ");
			errorInformation.append(config.getPasswordMin());
			errorInformation.append("<br/> \n");
		}
		if (!typeLengthChecker.checkLowerLength(password)){
			passwordFormat = false;
			errorInformation.append("The number of lower case characters should not be fewer than than ");
			errorInformation.append(config.getPasswordLowerMin());
			errorInformation.append("<br/> \n");
		}
		if (!typeLengthChecker.checkUpperLength(password)){
			passwordFormat = false;
			errorInformation.append("The number of upper case characters should not be fewer than than ");
			errorInformation.append(config.getPasswordUpperMin());
			errorInformation.append("<br/> \n");
		}
		if (!typeLengthChecker.checkSymbolLength(password)){
			passwordFormat = false;
			errorInformation.append("The number of special characters should not be fewer than than ");
			errorInformation.append(config.getPasswordUpperMin());
			errorInformation.append("<br/> \n");
		}


		boolean success = false;
		if (User.isBannerIDValid(bannerID) &&
			 User.isEmailValid(email) &&
			 User.isFirstNameValid(firstName) &&
			 User.isLastNameValid(lastName) &&
			 password.equals(passwordConfirm)&&
			passwordFormat)
		{
			User u = new User();
			u.setBannerID(bannerID);
			u.setPassword(password);
			u.setFirstName(firstName);
			u.setLastName(lastName);
			u.setEmail(email);
			IUserPersistence userDB = SystemConfig.instance().getUserDB();
			IPasswordEncryption passwordEncryption = SystemConfig.instance().getPasswordEncryption();
			success = u.createUser(userDB, passwordEncryption, new UserNotification());
		}
		ModelAndView m;
		if (success)
		{
			// This is lame, I will improve this with auto-signin for M2.
			m = new ModelAndView("login");
		}
		else
		{
			// Something wrong with the input data.
			m = new ModelAndView("signup");
			errorInformation.append("Invalid data, please check your values.");
			m.addObject(ERROR, errorInformation.toString());
		}
		return m;
	}
}