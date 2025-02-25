package com.doctolib.doctobootplayground;

import static com.doctolib.doctoboot.authentication.DoctoAuthenticationConstants.X_INTERSERVICE_AUTHORIZATION_HEADER;
import static com.doctolib.doctoboot.context.ContextHeaderConstants.X_ACT_AS_HEADER;
import static com.doctolib.doctoboot.context.ContextHeaderConstants.X_CORRELATION_ID_HEADER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.doctolib.doctoboot.autoconfigure.ApplicationContextAutoconfiguration;
import com.doctolib.doctoboot.autoconfigure.AuthenticationAutoconfiguration;
import com.doctolib.doctoboot.autoconfigure.InterserviceAuthenticationAutoconfiguration;
import com.doctolib.doctoboot.interservices.InterserviceAutoconfiguration;
import com.doctolib.doctoboot.jwt.DoctobootJwtTestSupport;
import com.doctolib.doctobootplayground.configuration.SecurityFilterConfiguration;
import com.doctolib.doctobootplayground.controller.SimpleInterserviceController;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@ContextConfiguration(
        classes = {
            ApplicationContextAutoconfiguration.class,
            InterserviceAutoconfiguration.class,
            AuthenticationAutoconfiguration.class,
            InterserviceAuthenticationAutoconfiguration.class,
            SecurityFilterConfiguration.class,
            SimpleInterserviceController.class
        })
@ActiveProfiles({"test", "dev"})
class InterserviceIntegrationTest extends DoctobootJwtTestSupport {

    @Autowired
    private MockMvc mockMvc;

    @Value("${doctoboot.authentication.trusted-issuers[0].issuer}")
    private String issuer;

    @Value("${doctoboot.authentication.trusted-issuers[0].jwks-uri}")
    private String jkwsUri;

    @MockitoBean
    private MeterRegistry micrometerRegistry;

    @Test
    void testIfInterserviceCallIsUnauthorizedWithInvalidIssuerJwt() throws Exception {
        initJwksExpectation(extractPathFromUri(jkwsUri));
        var interserviceToken = generateInterserviceJwtToken("some-subject");
        var secureTokenInvalid = generateInvalidJwtInvalidIssuer().getTokenValue();
        mockMvc.perform(get("/__internal__/doctoboot-playground/v1/hello")
                        .header(X_INTERSERVICE_AUTHORIZATION_HEADER, "Bearer %s".formatted(interserviceToken))
                        .header(AUTHORIZATION, "Bearer %s".formatted(secureTokenInvalid))
                        .header(X_ACT_AS_HEADER, "drn:hcp:account:f47ac10b-58cc-4372-a567-0e02b2c3d489")
                        .header(X_CORRELATION_ID_HEADER, "123"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testIfInterserviceCallIsAuthorizedWithTwoJwt() throws Exception {
        initJwksExpectation(extractPathFromUri(jkwsUri));
        var interserviceToken = generateInterserviceJwtToken("some-subject");
        var secureToken = generateValidJwtWithIssuer(issuer).getTokenValue();
        var result = mockMvc.perform(get("/__internal__/doctoboot-playground/v1/hello")
                        .header(X_INTERSERVICE_AUTHORIZATION_HEADER, "Bearer %s".formatted(interserviceToken))
                        .header(AUTHORIZATION, "Bearer %s".formatted(secureToken))
                        .header(X_ACT_AS_HEADER, "drn:hcp:account:f47ac10b-58cc-4372-a567-0e02b2c3d489")
                        .header(X_CORRELATION_ID_HEADER, "123"))
                .andExpect(status().isOk())
                .andReturn();

        var response = result.getResponse().getContentAsString();
        assertThat(response).isEqualTo("Hello World");
    }
}
