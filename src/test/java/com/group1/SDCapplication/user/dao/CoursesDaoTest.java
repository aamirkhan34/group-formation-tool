package com.group1.SDCapplication.user.dao;

import com.group1.SDCapplication.models.CoursesTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class CoursesDaoTest implements CourseTest{

    CoursesTest coursesTest = new CoursesTest();

    @Override
    public List<CoursesTest> getCoursesTest() {
        List<CoursesTest> coursesTests = new ArrayList<>();
        coursesTests.add(coursesTest);
        return coursesTests;
    }

}
