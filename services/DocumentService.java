package com.example.demo.service;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.example.demo.model.Document;
import com.example.demo.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    // ✅ Save document to database
    public Document save(Document document) {
        return documentRepository.save(document);
    }

    public String storeFile(MultipartFile file) throws IOException {
        String uploadDir = "C:/Users/DUA AYAT/Desktop/demo/uploads";
        Path uploadPath = Paths.get(uploadDir);

        // Agar directory exist nahi karti to create karo
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // File ka path resolve karo
        Path filePath = uploadPath.resolve(file.getOriginalFilename());

        // File save karo
        file.transferTo(filePath.toFile());

        return filePath.toString(); // agar path chahiye
    }
    // ✅ Get documents by student
    public List<Document> getDocumentsByStudentId(Long studentId) {
        return documentRepository.findByStudentId(studentId);
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    // ✅ Get documents by supervisor
    public List<Document> getDocumentsBySupervisorId(Long supervisorId) {
        return documentRepository.findBySupervisorId(supervisorId);
    }

    // ✅ Get documents by status (for committee)
    public List<Document> getDocumentsByStatus(String status) {
        return documentRepository.findByStatus(status);
    }




    // 🔹 Calculate total marks
    public int calculateTotalMarks(Document doc) {
        return doc.getContentQuality()
                + doc.getFormattingPresentation()
                + doc.getOriginality()
                + doc.getTimeliness();
    }

    // 🔹 Convert marks to grade
    public String calculateGrade(int total) {
        if (total >= 85) return "A";
        else if (total >= 70) return "B";
        else if (total >= 55) return "C";
        else return "F";
    }

    // 🔹 Finalize Result
    public void finalizeResult(Long documentId) {
        Document doc = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        int total = calculateTotalMarks(doc);
        String grade = calculateGrade(total);

        doc.setGrade(grade);
        doc.setStatus("FINALIZED");

        documentRepository.save(doc);
    }

    public void releaseFinalResult(Long documentId) {
        Document doc = getDocumentById(documentId);
        doc.setStatus("FINALIZED");
        documentRepository.save(doc);
    }

    // ✅ Get document by id
    public Document getDocumentById(Long documentId) {
        return documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
    }
    public void updateDocumentStatus(Long documentId, String status, String feedback) {
        Document doc = documentRepository.findById(documentId).orElse(null);
        if (doc != null) {
            doc.setStatus(status);

            if (feedback != null && !feedback.isEmpty()) {
                doc.setFeedback(feedback); // feedback save
            }

            documentRepository.save(doc); // save important
        }
    }

    public void gradeDocument(Long documentId, String grade, String feedback) {
        Document doc = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        doc.setGrade(grade);
        doc.setFeedback(feedback);
        doc.setStatus("GRADED");

        documentRepository.save(doc);
    }
    public void gradeDocumentRubric(Long id, Integer contentQuality, Integer formattingPresentation,
                                    Integer originality, Integer timeliness, String grade, String feedback) {
        Document doc = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        doc.setContentQuality(contentQuality);
        doc.setFormattingPresentation(formattingPresentation);
        doc.setOriginality(originality);
        doc.setTimeliness(timeliness);

        doc.setGrade(grade);
        doc.setFeedback(feedback);
        doc.setStatus("GRADED");

        documentRepository.save(doc);
    }


}
