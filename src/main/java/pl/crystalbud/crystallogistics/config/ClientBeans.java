package pl.crystalbud.crystallogistics.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import pl.crystalbud.crystallogistics.client.RestClientTablesRestClient;

@Configuration
public class ClientBeans {

    @Bean
    public RestClientTablesRestClient tablesRestClient(
            @Value("${crystal_logistics.service.catalogue.uri:http://localhost:8081}") String catalogueBaseUri){
        return new RestClientTablesRestClient(RestClient.builder()
                .baseUrl(catalogueBaseUri)
                .build());
    }
}
