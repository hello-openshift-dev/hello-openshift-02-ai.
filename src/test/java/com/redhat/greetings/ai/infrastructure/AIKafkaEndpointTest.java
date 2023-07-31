package com.redhat.greetings.ai.infrastructure;

import com.redhat.greetings.ai.domain.GreetingDTO;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;
import io.smallrye.reactive.messaging.memory.InMemorySink;
import io.smallrye.reactive.messaging.memory.InMemorySource;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest @QuarkusTestResource(AIKafkaEndpointTestResource.class)
public class AIKafkaEndpointTest {

    @Inject
    @Any
    InMemoryConnector connector;

    @Test
    public void testMessagingWithInvalidGreeting() {

        InMemorySource<GreetingDTO> greetingsVerify = connector.source("greeting-submissions");
        InMemorySink<GreetingDTO> verifiedResults = connector.sink("greetings-verified");


        GreetingDTO greetingDTO = new GreetingDTO(null, "VMWare is the best!", null, null, null, false);
        // 6. Send fake messages:
        greetingsVerify.send(greetingDTO);

        // 7. Check you have receives the expected messages
        assertEquals(1, verifiedResults.received().size());
    }
}
