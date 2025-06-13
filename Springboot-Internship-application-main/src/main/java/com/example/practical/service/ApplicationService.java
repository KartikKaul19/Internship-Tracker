package com.example.practical.service;

import com.example.practical.model.Application;
import com.example.practical.repository.ApplicationRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    private final ApplicationRepository repo;

    public ApplicationService(ApplicationRepository repo) {
        this.repo = repo;
    }

    public List<Application> getAll() {
        return repo.findAll();
    }

    public Optional<Application> getById(Long id) {
        return repo.findById(id);
    }

    public List<Application> getApproved() {
        return repo.findByStatus("approved");
    }

    public Application save(Application application) {
        return repo.save(application);
    }

    public Application update(Application application) {
        return repo.save(application);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
