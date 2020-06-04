package com.group1.SDCapplication.courseadministration.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.multipart.MultipartFile;

public class StudentFileDataValidation implements UserFileDataValidation {

	private MultipartFile fileData;

	public StudentFileDataValidation(MultipartFile fileData) {
		super();
		this.fileData = fileData;
	}

	@Override
	public Boolean validateFileType() {
		String fileName = fileData.getOriginalFilename();

		if (fileName.endsWith(".csv") || fileName.endsWith(".CSV"))
			return true;

		return false;
	}

	@Override
	public Boolean validateFields() {
		try {
			String[] requiredColumns = { "banner_id", "last_name", "first_name", "email" };
			String content = new String(fileData.getBytes());
			String firstRow = content.split("\n")[0];
			String[] firstRowDataList = firstRow.split(",");

			for (String requiredColumn : requiredColumns) {
				if (Arrays.stream(firstRowDataList).anyMatch(requiredColumn::equals) == false)
					return false;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	public Boolean validateEmail(String email) {

		if (email.isEmpty()) {
			return false;
		}

		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Boolean match = email.matches(regex);

		return match;
	}

	public Boolean validateBannerId(String bannerId) {
		if (bannerId.isEmpty()) {
			return false;
		}

		if (!bannerId.startsWith("B") || bannerId.length() > 9 || bannerId.length() < 6) {
			return false;
		}

		Boolean match = bannerId.matches("^[a-zA-Z0-9]*$");

		return match;
	}

	@Override
	public Boolean validateData() {
		try {
			
			String content = new String(fileData.getBytes());
			String[] rows = content.split("\n");
			String[] firstRowDataList = content.split("\n")[0].split(",");
			
			int emailColumnIndex = 0;
			int bannerIdColumnIndex = 0;
			
			// Find email and bannerId indexes
			for (int i = 0; i < firstRowDataList.length; i++) {
				if (firstRowDataList[i].trim().equals("email"))
					emailColumnIndex = i;
				else if(firstRowDataList[i].trim().equals("banner_id"))
					bannerIdColumnIndex = i;
			}
			
			for (int j = 1; j < rows.length; j++) {
				String[] studentData = rows[j].split(",");

				if (!validateEmail(studentData[emailColumnIndex]))
					return false;
				
				if (!validateBannerId(studentData[bannerIdColumnIndex]))
					return false;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}

}
