package com.doctolib.doctobootplayground;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles({"test", "dev"})
class ApplicationIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @SuppressWarnings("squid:S1186")
    void contextLoads() { // NOSONAR
        // assertion is useless: just interested in context loading
    }

    @Test
    void actuatorIsEnabled() {
        var response = restTemplate.getForEntity("/actuator", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("/health");
    }
}
