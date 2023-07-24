package com.redhat.greetings.ai.infrastructure;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;

import java.util.HashMap;
import java.util.Map;

public class AIKafkaEndpointTestResource implements QuarkusTestResourceLifecycleManager {

    @Override
    public Map<String, String> start() {
        Map<String, String> env = new HashMap<>();
        Map<String, String> props1 = InMemoryConnector.switchIncomingChannelsToInMemory("greetings-verify");
        Map<String, String> props2 = InMemoryConnector.switchOutgoingChannelsToInMemory("greetings-valid");
        Map<String, String> props3 = InMemoryConnector.switchOutgoingChannelsToInMemory("greetings-invalid");

        env.putAll(props1);
        env.putAll(props2);
        env.putAll(props3);
        return env;
    }

    @Override
    public void stop() {
        InMemoryConnector.clear();
    }
}
