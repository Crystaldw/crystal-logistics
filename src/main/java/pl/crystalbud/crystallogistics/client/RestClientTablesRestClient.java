package pl.crystalbud.crystallogistics.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import pl.crystalbud.crystallogistics.controller.payload.payload.NewTablePayload;
import pl.crystalbud.crystallogistics.controller.payload.payload.UpdateTablePayload;
import pl.crystalbud.crystallogistics.entity.Table;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
public class RestClientTablesRestClient implements TablesRestClient {

    private static final ParameterizedTypeReference<List<Table>> TABLES_TYPE_REFERENCE =
            new ParameterizedTypeReference<>() {
            };

    private final RestClient restClient;

    @Override
    public List<Table> findAllTables() {
        return this.restClient
                .get()
                .uri("/catalogue-api/tables")
                .retrieve()
                .body(TABLES_TYPE_REFERENCE);
    }

    @Override
    public Table createTable(String title, String details) {
        try {
            return this.restClient
                    .post()
                    .uri("/catalogue-api/tables")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new NewTablePayload(title, details))
                    .retrieve()
                    .body(Table.class);
        } catch (HttpClientErrorException.BadRequest exception) {
            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

    @Override
    public Optional<Table> findTable(int tableId) {
        try {
            return Optional.ofNullable(this.restClient.get()
                    .uri("/catalogue-api/tables{tableId}", tableId)
                    .retrieve()
                    .body(Table.class);
        } catch (HttpClientErrorException.NotFound exception) {
            return Optional.empty();
        }
    }

    @Override
    public void updateTable(int tableId, String title, String details) {
        try {
            this.restClient
                    .patch()
                    .uri("/catalogue-api/tables/{tableId}", tableId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new UpdateTablePayload(title, details))
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.BadRequest exception) {
            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

    @Override
    public void deleteTable(int tableId) {
        try {
            this.restClient
                    .delete()
                    .uri("/catalogue-api/tables{tableId}", tableId)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NoSuchElementException(exception);
        }
    }
}
