package com.luv2code.crudDemo.dao;

import com.luv2code.crudDemo.entity.Course;
import com.luv2code.crudDemo.entity.Instructor;
import com.luv2code.crudDemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {
    private final EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor Instructor) {
        entityManager.persist(Instructor);
    }

    @Override
    public Instructor findById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Instructor instructor = findById(id);
        if (instructor == null) {
            System.out.println("Instructor not found");
            return;
        }
        List<Course> courses = findCourseByInstructorId(id);
        courses.forEach(course -> course.setInstructor(null));
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorByDetailId(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = findInstructorByDetailId(id);
        if (instructorDetail == null) {
            System.out.println("Instructor detail not found");
            return;
        }
        instructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCourseByInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c where c.instructor.id = :id", Course.class);
        query.setParameter("id", id);
        try {
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery("" +
                "select i from Instructor i " +
                "join fetch i.courses " +
                "where i.id = :id", Instructor.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void updateInstructorDetail(InstructorDetail instructorDetail) {
        entityManager.merge(instructorDetail);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteCourse(int id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void saveCourse(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }


    @Override
    public List<Course> findAllCourses() {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c", Course.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<Instructor> findAllInstructors() {
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i", Instructor.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<InstructorDetail> findAllInstructorDetails() {
        TypedQuery<InstructorDetail> query = entityManager.createQuery("select i from InstructorDetail i", InstructorDetail.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    @Transactional
    public void deleteInstructor(Instructor instructor) {
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructorDetail(InstructorDetail instructorDetail) {
        entityManager.remove(instructorDetail);
    }

    @Override
    @Transactional
    public void saveInstructorDetail(InstructorDetail instructorDetail) {
        entityManager.persist(instructorDetail);
    }

    @Override
    @Transactional
    public void saveInstructorWithCourses(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    @Transactional
    public void saveInstructorWithCourses(Instructor instructor, List<Course> courses) {
        entityManager.persist(instructor);
        courses.forEach(entityManager::persist);
    }

    @Override
    @Transactional
    public void deleteInstructorWithCourses(Instructor instructor) {
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructorWithCourses(Instructor instructor, List<Course> courses) {
        courses.forEach(entityManager::remove);
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructorWithCourses(int id) {
        Instructor instructor = findById(id);
        if (instructor == null) {
            System.out.println("Instructor not found");
            return;
        }
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructorWithCourses(int id, List<Course> courses) {
        Instructor instructor = findById(id);
        if (instructor == null) {
            System.out.println("Instructor not found");
            return;
        }
        courses.forEach(entityManager::remove);
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructorWithCoursesJoinFetch(int id) {
        Instructor instructor = findInstructorByIdJoinFetch(id);
        if (instructor == null) {
            System.out.println("Instructor not found");
            return;
        }
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructorWithCoursesJoinFetch(Instructor instructor) {
        Instructor instructor1 = findInstructorByIdJoinFetch(instructor.getId());
        if (instructor1 == null) {
            System.out.println("Instructor not found");
            return;
        }
        entityManager.remove(instructor1);
    }

    @Override
    @Transactional
    public void deleteInstructorWithCoursesJoinFetch(Instructor instructor, List<Course> courses) {
        Instructor instructor1 = findInstructorByIdJoinFetch(instructor.getId());
        if (instructor1 == null) {
            System.out.println("Instructor not found");
            return;
        }
        courses.forEach(entityManager::remove);
        entityManager.remove(instructor1);
    }

    @Override
    @Transactional
    public void deleteInstructorWithCoursesJoinFetch(int id, List<Course> courses) {
        Instructor instructor = findInstructorByIdJoinFetch(id);
        if (instructor == null) {
            System.out.println("Instructor not found");
            return;
        }
        courses.forEach(entityManager::remove);
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void saveInstructorWithCoursesJoinFetch(Instructor instructor) {
        Instructor instructor1 = findInstructorByIdJoinFetch(instructor.getId());
        if (instructor1 == null) {
            entityManager.persist(instructor);
        }

    }

    @Override
    public void save(Course course) {
        entityManager.persist(course);

    }

    @Override
    public Course findCourseAndReviewsById(int id) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c join fetch c.reviews where c.id = :id", Course.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
