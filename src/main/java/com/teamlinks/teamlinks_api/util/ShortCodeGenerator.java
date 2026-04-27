package com.teamlinks.teamlinks_api.util;

import com.teamlinks.teamlinks_api.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@RequiredArgsConstructor
public class ShortCodeGenerator {

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int LENGTH = 8;

    private final SecureRandom random = new SecureRandom();
    private final LinkRepository linkRepository;

    public String nextUnique() {
        for (int attempt = 0; attempt < 64; attempt++) {
            StringBuilder sb = new StringBuilder(LENGTH);
            for (int i = 0; i < LENGTH; i++) {
                sb.append(ALPHABET.charAt(random.nextInt(62)));
            }
            String code = sb.toString();
            if (!linkRepository.existsByShortCode(code)) {
                return code;
            }
        }
        throw new IllegalStateException("Não foi possível gerar código Base62 único.");
    }
}
