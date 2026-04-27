package com.teamlinks.teamlinks_api.config;

import com.teamlinks.teamlinks_api.entity.Link;
import com.teamlinks.teamlinks_api.repository.LinkRepository;
import com.teamlinks.teamlinks_api.util.ShortCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Order(1)
@RequiredArgsConstructor
public class ShortCodeBackfill implements CommandLineRunner {

    private final LinkRepository linkRepository;
    private final ShortCodeGenerator shortCodeGenerator;

    @Override
    @Transactional
    public void run(String... args) {
        for (Link link : linkRepository.findAll()) {
            if (link.getShortCode() == null || link.getShortCode().isBlank()) {
                link.setShortCode(shortCodeGenerator.nextUnique());
                linkRepository.save(link);
            }
        }
    }
}
