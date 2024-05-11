package pl.crystalbud.crystallogistics.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestClient;
import pl.crystalbud.crystallogistics.client.RestClientTablesRestClient;

@Configuration
public class ClientBeans {

    @Bean
    public RestClientTablesRestClient tablesRestClient(
            @Value("${crystal_logistics.services.catalogue.uri:http://localhost:8081}") String catalogueBaseUri,
            @Value("${crystal_logistics.services.catalogue.username:}") String catalogueUsername,
            @Value("${crystal_logistics.services.catalogue.password:}") String cataloguePassword){
        return new RestClientTablesRestClient(RestClient.builder()
                .baseUrl(catalogueBaseUri)
                .requestInterceptor(
                        new BasicAuthenticationInterceptor(catalogueUsername, cataloguePassword))
                .build());
    }
}
