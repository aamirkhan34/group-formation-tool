package com.group1.SDCapplication.courseadministration.services;

import org.springframework.web.multipart.MultipartFile;

public class StudentFileDataValidation implements UserFileDataValidation {
	
	private MultipartFile fileData;
	
	public StudentFileDataValidation(MultipartFile fileData) {
		super();
		this.fileData = fileData;
	}
	
	@Override
	public Boolean validateFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validateFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validateData() {
		// TODO Auto-generated method stub
		return null;
	}

}
