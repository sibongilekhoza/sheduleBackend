package com.sheduleBackend.BusinessServices.impl;

import com.sheduleBackend.Api.Response.CourseDetails;
import com.sheduleBackend.Api.Response.CourseResponse;
import com.sheduleBackend.Models.Subject;
import com.sheduleBackend.Repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sheduleBackend.BusinessServices.Interface.CourseService;
import com.sheduleBackend.Exceptions.CourseException;
import com.sheduleBackend.Models.Course;
import com.sheduleBackend.Models.Dto.requests.CourseRequestDto;
import com.sheduleBackend.Repositories.CourseRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private ModelMapper courseMapper;

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Course createCourse(CourseRequestDto courseRequestDto) {
        Optional<Course> existsByName = courseRepository.findByCourseName(courseRequestDto.getCourseName());
        Optional<Course> existsByCode = courseRepository
                .findByCourseCode(courseRequestDto.getCourseCode());

        if (existsByName.isEmpty() || existsByCode.isEmpty() )
        {
            Course mappedCourse = courseMapper.map(courseRequestDto , Course.class);
            return courseRepository.save(mappedCourse);
        }
      throw  new CourseException("Course  Name or Course Code Exist");
    }

    @Override
    public List<CourseResponse> getAllCourse() {

        List<Course> courseList = courseRepository.findAll();

        return courseList.stream()
                .map(course -> courseMapper.map(course, CourseResponse.class))
                .collect(Collectors.toList());
    }
    @Override
    public Course updateCourse(long courseId, CourseRequestDto courseRequestDto) {
        Optional<Course> existingCourseOptional = courseRepository.findById(courseId);
        if (existingCourseOptional.isPresent()) {
            Course existingCourse = existingCourseOptional.get();
            if (courseRequestDto.getCourseLevel() != null) {
                existingCourse.setCourseLevel(courseRequestDto.getCourseLevel());
            }
            if (courseRequestDto.getCourseDescription() != null) {
                existingCourse.setCourseDescription(courseRequestDto.getCourseDescription());
            }
            return courseRepository.save(existingCourse);
        } else {
            throw new CourseException("Course not found with ID: " + courseId);
        }
    }

    @Override
    public CourseDetails getCourseById(Long id) {
        CourseDetails courseDetails= new CourseDetails();
        Course course = courseRepository.findByCourseId(id);
        if (course!=null){
            courseDetails.setCourseId(course.getCourseId());
            courseDetails.setCourseCode(course.getCourseCode());
            courseDetails.setCourseLevel(course.getCourseLevel());
            courseDetails.setCourseName(course.getCourseName());
            courseDetails.setCourseDescription(course.getCourseDescription());
            List<Subject> subjectList = subjectRepository.findByCourse(course);
            courseDetails.setSubjectList(subjectList);
        }
        return courseDetails;
    }


    @Override
    public void deleteCourse(long courseId) {
        Optional<Course> existingCourse = courseRepository.findById(courseId);
        if (existingCourse.isPresent()) {
            courseRepository.deleteById(courseId);

        } else {
            throw new IllegalStateException("Course not found with ID: " + courseId);
        }
    }



}
