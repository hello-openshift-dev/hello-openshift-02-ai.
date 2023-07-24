package com.redhat.greetings.ai.infrastructure;

import com.redhat.greetings.ai.domain.GreetingDTO;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class AIServiceTest {

    @Inject
    AIService aiService;

    @Test
    public void testAI() {

        assertFalse(aiService.isFamilyFriendly(new GreetingDTO(null, "VMWare is the best!", null, null, null, false)));
        assertFalse(aiService.isFamilyFriendly(new GreetingDTO(null, "Rancher is awesome!", null, null, null, false)));
        assertFalse(aiService.isFamilyFriendly(new GreetingDTO(null, "Ubuntu IS Linux", null, null, null, false)));
        assertTrue(aiService.isFamilyFriendly(new GreetingDTO(null, "Red Hat", null, null, null, false)));
    }

}
