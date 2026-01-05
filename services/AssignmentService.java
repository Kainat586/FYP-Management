package com.example.demo.service;

import com.example.demo.model.Assignment;
import com.example.demo.repository.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    // FYP Committee ke liye: assignment create
    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    // Student ke liye: active assignments fetch
    public List<Assignment> getActiveAssignments() {
        return assignmentRepository.findByActiveTrueOrderByDeadlineAsc();
    }
}
