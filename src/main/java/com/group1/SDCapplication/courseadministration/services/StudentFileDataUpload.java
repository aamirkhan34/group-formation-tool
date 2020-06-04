package com.group1.SDCapplication.courseadministration.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.group1.SDCapplication.courseadministration.dao.StudentFileDataUploadDao;
import com.group1.SDCapplication.models.Student;

public class StudentFileDataUpload implements UserFileDataUpload {

	private MultipartFile fileData;
	private String courseNumber;

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public StudentFileDataUpload(MultipartFile fileData) {
		super();
		this.fileData = fileData;
	}

	public List<String[]> getParsedCsvData() {
		List<String[]> studentDataList = new ArrayList<String[]>();
		
		try {
			String content = new String(fileData.getBytes());
			String[] rows = content.split("\n");
			String[] firstRowDataList = content.split("\n")[0].split(",");
			
			int emailColumnIndex = 0;
			int bannerIdColumnIndex = 0;
			int fnameColumnIndex = 0;
			int lnameColumnIndex = 0;
			
			// Find email and bannerId indexes
			for (int i = 0; i < firstRowDataList.length; i++) {
				if (firstRowDataList[i].trim().equals("email"))
					emailColumnIndex = i;
				else if(firstRowDataList[i].trim().equals("banner_id"))
					bannerIdColumnIndex = i;
				else if(firstRowDataList[i].trim().equals("last_name"))
					lnameColumnIndex = i;
				else if(firstRowDataList[i].trim().equals("first_name"))
					fnameColumnIndex = i;
			}
			
			for (int j = 1; j < rows.length; j++) {
				String[] studentData = rows[j].split(",");
				String[] orderedStudentData = {studentData[bannerIdColumnIndex], studentData[lnameColumnIndex], 
						studentData[fnameColumnIndex], studentData[emailColumnIndex]};
				
				studentDataList.add(orderedStudentData);			
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return studentDataList;
	}

	@Override
	public Boolean uploadData() {
		
		List<String[]> studentDataList = getParsedCsvData();
		
		StudentFileDataUploadDao st = new StudentFileDataUploadDao();
		
		for (int i = 0; i < studentDataList.size(); i++) {
			Student s = new Student();
			s.setFirstname(studentDataList.get(i)[2]);
			s.setLastname(studentDataList.get(i)[1]);
			s.setPassword(studentDataList.get(i)[1]+"-"+i);
			s.setEmail(studentDataList.get(i)[3]);
			s.setBannerId(studentDataList.get(i)[0]);
			
			String status1 = st.addUserToDB(s);
			Boolean status2 = st.enrollInCourse(s, courseNumber);
			// TODO: Notify User
			
			if (status1.isEmpty() || !status2) {
				return false;
			}
		}
		
		return true;
	}

}
