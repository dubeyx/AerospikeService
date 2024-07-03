package com.example.AeroSpikeUploader.config;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.policy.ClientPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AerospikeConfig {

    private final AerospikeConfigurationProperties properties;

    public AerospikeConfig(AerospikeConfigurationProperties properties) {
        this.properties = properties;
    }

    @Bean
    public AerospikeClient aerospikeClient() {
        ClientPolicy policy = new ClientPolicy();
        return new AerospikeClient(policy, properties.getHost(), properties.getPort());
    }
}
