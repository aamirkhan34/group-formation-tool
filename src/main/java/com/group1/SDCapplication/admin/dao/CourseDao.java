package com.group1.SDCapplication.admin.dao;

import com.group1.SDCapplication.datasource.DevDatabase;
import com.group1.SDCapplication.models.Courses;
import com.group1.SDCapplication.models.Instructor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDao implements IAdminRepository {
    DevDatabase devDatabase = new DevDatabase();

    @Override
    public List<Courses> loadAllCourses() {
        List<Courses> allCourses = new ArrayList<>();
        Connection devConnection;
        {
            try {
                String SELECT_COURSE_QUERY = "SELECT course_number, course_name,instructor.instructor_number,CONCAT(user.first_name,\" \",user.last_name) as instuctor_name\n" +
                        " from course\n" +
                        " inner join instructor on instructor.instructor_number = course.instructor_number\n" +
                        " inner join user on user.UID=instructor.UID where course.delete_flag=0;";
                devConnection = devDatabase.getConnection();
                Statement stmt = devConnection.createStatement();
                ResultSet rs = stmt.executeQuery(SELECT_COURSE_QUERY);
                while (rs.next()) {
                    Courses course = new Courses();
                    String courseNumber = rs.getString("course_number");
                    course.setCourseID(courseNumber);
                    String courseName = rs.getString("course_name");
                    course.setCourseName(courseName);
                    String instructor_name = rs.getString("instuctor_name");
                    Long instructor_number = rs.getLong("instructor_number");
                    course.setInstructor_name(instructor_name);
                    allCourses.add(course);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return allCourses;
    }

    @Override
    public boolean createCourse(Courses course) {
        return false;
    }

    @Override
    public List<Courses> findById(String id) {
        DevDatabase devDatabase = new DevDatabase();
        List<Courses> allCourses = new ArrayList<>();
        Connection devConnection;
        {
            try {
                String SELECT_COURSE_QUERY = "SELECT course_number, course_name,CONCAT(user.first_name,\" \",user.last_name) as instuctor_name, course.instructor_number\n" +
                        " from course\n" +
                        " inner join instructor on instructor.instructor_number = course.instructor_number\n" +
                        " inner join user on user.UID=instructor.UID\n" +
                        " where course.course_number='" + id + "';";
                devConnection = devDatabase.getConnection();
                Statement stmt = devConnection.createStatement();
                ResultSet rs = stmt.executeQuery(SELECT_COURSE_QUERY);
                while (rs.next()) {
                    Courses course = new Courses();
                    String courseNumber = rs.getString("course_number");
                    course.setCourseID(courseNumber);
                    String courseName = rs.getString("course_name");
                    course.setCourseName(courseName);
                    String instructor_name = rs.getString("instuctor_name");
                    Long instructor_number = rs.getLong("instructor_number");
                    course.setIntsructor_number(instructor_number);
                    allCourses.add(course);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return allCourses;
    }

    public boolean checkCourseExist(String courseName) {
        String courseExists = "select * from course where course_number='" + courseName + "';";
        ResultSet resultSet = null;
        try {
            Connection devConnection = devDatabase.getConnection();
            Statement stmt = devConnection.createStatement();
            resultSet = stmt.executeQuery(courseExists);
            int size = 0;
            while(resultSet.next()){
                size++;
            }
            return size != 0 ? true : false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Instructor> gretalInstructors() {
        DevDatabase devDatabase = new DevDatabase();
        List<Instructor> allInstructors = new ArrayList<>();
        Connection devConnection;
        {
            try {
                String SELECT_COURSE_QUERY = "SELECT CONCAT(user.first_name,\" \",user.last_name) as instuctor_name, instructor.instructor_number\n" +
                        " from  instructor\n" +
                        " inner join user on user.UID=instructor.UID;";
                devConnection = devDatabase.getConnection();
                Statement stmt = devConnection.createStatement();
                ResultSet rs = stmt.executeQuery(SELECT_COURSE_QUERY);
                while (rs.next()) {
                    String instructor_name = rs.getString("instuctor_name");
                    Long instructor_number = rs.getLong("instructor_number");
                    Instructor instuctor = new Instructor(instructor_number, instructor_name);
                    allInstructors.add(instuctor);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return allInstructors;
    }

    @Override
    public String createOrUpdateCourse(Courses course) {
        if (!checkCourseExist(course.getCourseID())) {
            String USER_INSERT_QUERY = "insert into course(course_number,course_name,instructor_number) " +
                    "values('" + course.getCourseID() + "','" + course.getCourseName() + "','" + course.getIntsructor_number() + ';';
            try {
                int res;
                Connection devConnection = devDatabase.getConnection();
                Statement stmt = devConnection.createStatement();
                res = stmt.executeUpdate(USER_INSERT_QUERY);
                return "Course inserted";
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            String update_query = "update course set course_name='" + course.getCourseName() + "' " +
                    ", instructor_number = '" + course.getIntsructor_number() + "' where course_number='" + course.getCourseID() + "';";
            try {
                int res;
                Connection devConnection = devDatabase.getConnection();
                Statement stmt = devConnection.createStatement();
                res = stmt.executeUpdate(update_query);
                return "Course Updated";
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return "";
    }

    @Override
    public String deleteCourseByCourseNumber(String courseNumber) {
        if (checkCourseExist(courseNumber)) {
            String delete_query = "update course set delete_flag=1 where course_number='"+courseNumber+"';";
            try {
                int res;
                Connection devConnection = devDatabase.getConnection();
                Statement stmt = devConnection.createStatement();
                res = stmt.executeUpdate(delete_query);
                return "Course deleted";
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
            return "Error while deleting";
    }
}
