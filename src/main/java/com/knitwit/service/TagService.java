package com.knitwit.service;

import com.knitwit.model.Course;
import com.knitwit.model.Tag;
import com.knitwit.repository.TagRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Schema(description = "Сервис для работы с тегами")
@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    @Transactional
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    public Tag updateTag(int tagId, Tag updatedTag) {
        Optional<Tag> optionalTag = tagRepository.findById(tagId);
        if (optionalTag.isPresent()) {
            Tag tag = optionalTag.get();
            tag.setTagName(updatedTag.getTagName());
            return tagRepository.save(tag);
        } else {
            throw new IllegalArgumentException("Тег не найден по id: " + tagId);
        }
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagByName(String tagName) {
        List<Tag> tags = tagRepository.findByTagName(tagName);
        if (tags.isEmpty()) {
            throw new IllegalArgumentException("Тег " + tagName + " не найден");
        }
        return tags.get(0);
    }

    public Tag getTagById(int tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(() -> new IllegalArgumentException("Тег не найден по id: " + tagId));
    }

    @Transactional
    public void deleteTag(int tagId) {
        Optional<Tag> optionalTag = tagRepository.findById(tagId);
        if (optionalTag.isPresent()) {
            Tag tag = optionalTag.get();
            for (Course course : tag.getCourses()) {
                course.getTags().remove(tag);
            }
            tag.getCourses().clear();
            tagRepository.delete(tag);
        } else {
            throw new IllegalArgumentException("Тег не найден по id: " + tagId);
        }
    }
}
