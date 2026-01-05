package com.example.demo.service;

import com.example.demo.model.Deadline;
import com.example.demo.repository.DeadlineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeadlineService {

    private final DeadlineRepository repository;

    public DeadlineService(DeadlineRepository repository) {
        this.repository = repository;
    }

    public Deadline createDeadline(Deadline deadline) {
        return repository.save(deadline);
    }

    public List<Deadline> getActiveDeadlines() {
        return repository.findByIsActiveTrueOrderByDueDateAsc();
    }
}
