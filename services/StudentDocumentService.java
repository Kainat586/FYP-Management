package com.example.demo.service;

import com.example.demo.model.StudentDocument;
import com.example.demo.repository.StudentDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class StudentDocumentService {

    private final StudentDocumentRepository repository;

    @Autowired
    public StudentDocumentService(StudentDocumentRepository repository) {
        this.repository = repository;
    }

    public List<StudentDocument> getByStudentId(Long studentId) {
        return repository.findByStudentId(studentId);
    }

    public StudentDocument getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void save(StudentDocument doc) {
        repository.save(doc);
    }

    public String storeFile(MultipartFile file) {
        // TODO: Implement file saving logic
        return file.getOriginalFilename();
    }
}
