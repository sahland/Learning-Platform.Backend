package com.knitwit.service;

import com.knitwit.model.CourseSection;
import com.knitwit.model.MediaFile;
import com.knitwit.repository.CourseSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseSectionService {
    private final CourseSectionRepository courseSectionRepository;

    @Autowired
    public CourseSectionService(CourseSectionRepository courseSectionRepository) {
        this.courseSectionRepository = courseSectionRepository;
    }

    //создание раздела курса
    @Transactional
    public CourseSection createCourseSection(CourseSection courseSection) {
        return courseSectionRepository.save(courseSection);
    }

    //удаление раздела курса
    @Transactional
    public void deleteCourseSection(CourseSection courseSection) {
        courseSectionRepository.delete(courseSection);
    }

    //редактирование раздела курса
    @Transactional
    public CourseSection updateCourseSection(int sectionId, CourseSection updateCourseSection) {
        Optional<CourseSection> optionalCourseSection = courseSectionRepository.findById(sectionId);
        if (optionalCourseSection.isPresent()) {
            updateCourseSection.setSectionId(sectionId);
            return courseSectionRepository.save(updateCourseSection);
        } else {
            throw new IllegalArgumentException("Course section not found with id: " + sectionId);
        }
    }

    //получение списка разделов курса
    public List<CourseSection> getAllCourseSections() {
        return courseSectionRepository.findAll();
    }

    //получение списка раздела курса по id
    public CourseSection getCourseSectionById(int sectionId) {
        return courseSectionRepository.findById(sectionId)
                .orElseThrow(() -> new IllegalArgumentException("Course section not found with id: " + sectionId));
    }

    // Метод для добавления медиа-файла в раздел курса
    public void addMediaFileToCourseSection(MediaFile mediaFile, CourseSection section) {
        section.getMediaFiles().add(mediaFile);
        courseSectionRepository.save(section);
    }
}