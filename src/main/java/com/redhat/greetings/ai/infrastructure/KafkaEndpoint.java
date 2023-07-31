package com.redhat.greetings.ai.infrastructure;

import com.redhat.greetings.ai.domain.GreetingDTO;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class KafkaEndpoint {

    static final Logger LOGGER = LoggerFactory.getLogger(KafkaEndpoint.class);

    @Inject
    AIService aiService;

    @Incoming("greeting-submissions")
    @Outgoing("greetings-verified")
    public Uni<GreetingDTO> onGreeting(final GreetingDTO greetingDTOToVerify) {

        LOGGER.debug("Greeting received: {}", greetingDTOToVerify);
        boolean isValidGreeting = aiService.isFamilyFriendly(greetingDTOToVerify);
        return Uni.createFrom().item(new GreetingDTO(
                greetingDTOToVerify.text(),
                greetingDTOToVerify.author(),
                greetingDTOToVerify.sourceSystem(),
                greetingDTOToVerify.createdAt(),
                greetingDTOToVerify.isVerifiedFamilyFriendly()
        ));
    }
}
