package com.teamlinks.teamlinks_api.repository;

import com.teamlinks.teamlinks_api.entity.Link;
import com.teamlinks.teamlinks_api.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

    Page<Link> findAllByProjectId(Long projectId, Pageable pageable);

    List<Link> findAllByTagsContaining(Tag tag);

    Optional<Link> findByShortCode(String shortCode);

    boolean existsByShortCode(String shortCode);
}
