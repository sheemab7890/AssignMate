package com.sheemab.Assignment.Management.System.repository;




import com.sheemab.Assignment.Management.System.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByCode(String code);
}

