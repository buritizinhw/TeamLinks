package com.teamlinks.teamlinks_api.repository;

import com.teamlinks.teamlinks_api.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByName(String name);

    boolean existsByName(String name);

    Page<Project> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
