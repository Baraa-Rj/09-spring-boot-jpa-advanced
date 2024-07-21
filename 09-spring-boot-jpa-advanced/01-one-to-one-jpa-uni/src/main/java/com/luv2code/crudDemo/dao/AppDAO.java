package com.luv2code.crudDemo.dao;

import com.luv2code.crudDemo.entity.Course;
import com.luv2code.crudDemo.entity.Instructor;
import com.luv2code.crudDemo.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {
    void save(Instructor Instructor);
    Instructor findById(int id);
    void deleteById(int id);
    InstructorDetail findInstructorByDetailId(int id);
    void deleteInstructorDetailById(int id);
    List<Course> findCourseByInstructorId(int id);
    Instructor findInstructorByIdJoinFetch(int id);
    void updateInstructor(Instructor instructor);
    void updateInstructorDetail(InstructorDetail instructorDetail);
    void updateCourse(Course course);
    void deleteCourse(int id);
    void saveCourse(Course course);
    Course findCourseById(int id);
    List<Course> findAllCourses();
    List<Instructor> findAllInstructors();
    List<InstructorDetail> findAllInstructorDetails();
    void deleteInstructor(Instructor instructor);
    void deleteInstructorDetail(InstructorDetail instructorDetail);
    void saveInstructorDetail(InstructorDetail instructorDetail);
    void saveInstructorWithCourses(Instructor instructor);
    void saveInstructorWithCourses(Instructor instructor, List<Course> courses);
    void deleteInstructorWithCourses(Instructor instructor);
    void deleteInstructorWithCourses(Instructor instructor, List<Course> courses);
    void deleteInstructorWithCourses(int id);
    void deleteInstructorWithCourses(int id, List<Course> courses);
    void deleteInstructorWithCoursesJoinFetch(int id);
    void deleteInstructorWithCoursesJoinFetch(Instructor instructor);
    void deleteInstructorWithCoursesJoinFetch(Instructor instructor, List<Course> courses);
    void deleteInstructorWithCoursesJoinFetch(int id, List<Course> courses);
    void saveInstructorWithCoursesJoinFetch(Instructor instructor);
    void save(Course course);
    Course findCourseAndReviewsById(int id);
}
