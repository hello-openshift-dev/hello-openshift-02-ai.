package com.redhat.greetings.ai.infrastructure;

import com.redhat.greetings.ai.domain.GreetingDTO;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AIService {

    static final Logger LOGGER = LoggerFactory.getLogger(AIService.class);
    static List<String> unFriendlyWords = new ArrayList<>(){{
        add("VMWare");
        add("Rancher");
        add("Ubuntu");
        add("SUSE");
    }};
    public boolean isFamilyFriendly(GreetingDTO greetingDTO) {
        LOGGER.debug("verifying: {}", greetingDTO);
        boolean isFamilyFriendly;
        for (String word : unFriendlyWords) {
            if (greetingDTO.text().contains(word)) {
                LOGGER.debug("{} not verified", greetingDTO);
                return false;
            }
        }
        LOGGER.debug("{} verified", greetingDTO);
        return true;
    }
}
