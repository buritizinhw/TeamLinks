package com.teamlinks.teamlinks_api.repository;

import com.teamlinks.teamlinks_api.entity.Link;
import com.teamlinks.teamlinks_api.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

    Optional<Link> findByName(String name);

    boolean existsByUrl(String url);

    Page<Link> findByProjectId(Long projectId, Pageable pageable);

    Page<Link> findByTagsName(String tagName, Pageable pageable);
    
    long countByTagsId(Long tagId);
}
