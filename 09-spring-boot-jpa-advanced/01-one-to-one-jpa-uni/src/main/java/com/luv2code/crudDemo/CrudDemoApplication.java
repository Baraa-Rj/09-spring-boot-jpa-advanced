package com.luv2code.crudDemo;

import com.luv2code.crudDemo.dao.AppDAO;
import com.luv2code.crudDemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            //createInstructor(appDAO);
            //findInstructor(appDAO);
            // deleteInstructor(appDAO);
            //findInstructorDetail(appDAO);
            // deleteInstructorDetail(appDAO);
            //createInstructorWithCourses(appDAO);
            //findInstructorWithCourses(appDAO);
            //findCoursesForInstructor(appDAO);
            //findInstructorWithCoursesJoinFetch(appDAO);
            // updateInstructor(appDAO);
            // updateCourse(appDAO);
            //deleteInstructor(appDAO);
            //deleteCourse(appDAO);
            //  createCourseAndReviews(appDAO);
            // retrieveCourseAndReviews(appDAO);
           // deleteCourseAndReviews(appDAO);
            createCourseAndStudents(appDAO);
        };
    }

    private void createCourseAndStudents(AppDAO appDAO) {
        Course course = new Course("Java");
        course.addStudent(new Student("Baraa", "RJ", "gas"));
        course.addStudent(new Student("Baraa", "RJ", "gas"));
        course.addStudent(new Student("Baraa", "RJ", "gas"));
        System.out.println("Saving course: " + course);
        appDAO.saveCourse(course);
        System.out.println("Course saved: " + course);
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {
        int id = 10;
        Course course = appDAO.findCourseById(id);
        if (course == null) {
            System.out.println("Course not found");
            return;
        }
        System.out.println("Deleting course with id: " + id);
        appDAO.deleteCourse(id);
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {
        int id = 10;
        Course course = appDAO.findCourseAndReviewsById(id);
        if (course == null) {
            System.out.println("Course not found");
            return;
        }
        System.out.println("Course found: " + course);
        System.out.println("Course reviews: " + course.getReviews());
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        Course course = new Course("Java");
        course.add(new Review("Great course"));
        course.add(new Review("Awesome course"));
        course.add(new Review("Bad course"));
        System.out.println("Saving course: " + course);
        appDAO.saveCourse(course);
        System.out.println("Course saved: " + course);
    }

    private void deleteCourse(AppDAO appDAO) {
        int id = 10;
        System.out.println("Deleting course with id: " + id);
        appDAO.deleteCourse(id);
    }

    private void updateCourse(AppDAO appDAO) {
        int id = 10;
        Course course = appDAO.findCourseById(id);
        if (course == null) {
            System.out.println("Course not found");
            return;
        }
        System.out.println("Updating course: " + course);
        course.setTitle("C#");
        appDAO.updateCourse(course);
        System.out.println("Course updated: " + course);


    }


    private void updateInstructor(AppDAO appDAO) {
        int id = 1;
        Instructor instructor = appDAO.findById(id);
        if (instructor == null) {
            System.out.println("Instructor not found");
            return;
        }
        System.out.println("Updating instructor: " + instructor);
        instructor.setLastName("RJ");
        appDAO.updateInstructor(instructor);
        System.out.println("Instructor updated: " + instructor);
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
        int id = 1;
        System.out.println("Finding instructor with courses with id: " + id);
        Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);
        if (instructor == null) {
            System.out.println("Instructor not found");
            return;
        }
        System.out.println("Instructor found: " + instructor);
        System.out.println("Instructor courses: " + instructor.getCourses());
    }

    private void findCoursesForInstructor(AppDAO appDAO) {
        int id = 1;
        System.out.println("Finding courses for instructor with id: " + id);
        try {
            Instructor instructor = appDAO.findById(id);
            if (instructor == null) {
                System.out.println("Instructor not found");
                return;
            }
            List<Course> courses = appDAO.findCourseByInstructorId(id);
            if (courses == null) {
                System.out.println("Courses not found");
                return;
            }
            instructor.setCourses(courses);
            System.out.println("Instructor found: " + instructor);
            System.out.println("Instructor courses: " + instructor.getCourses());
        } catch (Exception e) {
            System.err.println("Error finding courses for instructor: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void findInstructorWithCourses(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor with courses with id: " + theId);
        Instructor instructor = appDAO.findById(theId);
        if (instructor == null) {
            System.out.println("Instructor not found");
            return;
        }
        System.out.println("Instructor found: " + instructor);
        System.out.println("Instructor courses: " + instructor.getCourses());
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        Instructor instructor = new Instructor("Susan", "Public", "susan.public@luv2code.com");
        InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com", "Video Games");
        instructor.setInstructorDetail(instructorDetail);
        instructor.add(new Course("Java"));
        instructor.add(new Course("C++"));
        instructor.add(new Course("Python"));
        System.out.println("Saving instructor: " + instructor);
        appDAO.save(instructor);
        System.out.println("Instructor saved: " + instructor);

    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int id = 4;
        System.out.println("Deleting instructor detail with id: " + id);
        appDAO.deleteInstructorDetailById(id);
    }

    private void findInstructorDetail(AppDAO appDAO) {
        int id = 3;
        System.out.println("Finding instructor with detail id: " + id);
        InstructorDetail instructorDetail = appDAO.findInstructorByDetailId(id);
        if (instructorDetail == null) {
            System.out.println("Instructor not found");
            return;
        }
        System.out.println("Instructor found: " + instructorDetail.getInstructor());
        System.out.println("Instructor Detail: " + instructorDetail);
    }

    private void deleteInstructor(AppDAO appDAO) {
        int id = 1;
        System.out.println("Deleting instructor with id: " + id);
        appDAO.deleteById(id);
    }

    private void findInstructor(AppDAO appDAO) {
        Instructor instructor = appDAO.findById(1);
        System.out.println("Instructor found: " + instructor);
        System.out.println("Instructor details: " + instructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {
        Instructor instructor = new Instructor("Baraa", "RJ", "baraarjoob17@gmail.com");
        InstructorDetail instructorDetails = new InstructorDetail("https://www.luv2code.com/youtube", "Coding");
        instructor.setInstructorDetail(instructorDetails);
        System.out.println("Saving instructor: " + instructor);
        appDAO.save(instructor);
        System.out.println("Instructor saved: " + instructor);
    }
}
