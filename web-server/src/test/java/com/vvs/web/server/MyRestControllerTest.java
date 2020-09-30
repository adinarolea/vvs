package com.vvs.web.server;

import com.vvs.web.server.data.MyResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class MyRestControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int localPort;

    private String serverUrl;

    @BeforeEach
    public void initServerUrl() {
        this.serverUrl = "http://localhost:" + localPort;
    }

    /**
     * Happy flow
     * Expected :
     * - 200 status code
     * - body contains input param
     * - headers contains content-type
     */
    @DisplayName("Test with param -> return param in the response")
    @ParameterizedTest
    @ValueSource(strings = {"haha", "null", "1234", "0", "-1"})
    public void whenGetTestEndpoint_thenReturnResponseWithInput(String input) {
        ResponseEntity<MyResponse> response = getMyResponseResponseEntity(input);
        assertBodyContainsParam(input, response);
    }

    /**
     * Negative case
     * Expected :
     * - 400 status code
     */
    @Test
    @DisplayName("Test with bad input -> expected status 400")
    public void whenGetWithBadInput_theReturnBadRequest() {
        ResponseEntity<MyResponse> response = getMyResponseResponseEntity("error");
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    /**
     * Negative case
     * Expected :
     * - 400 status code
     */
    @Test
    @DisplayName("Test missing input -> expected body null")
    public void whenMissingInput_thenResponseContainsNull() {
        String endpointUrl = UriComponentsBuilder.fromHttpUrl(serverUrl)
                .path("test")
                .toUriString();
        ResponseEntity<MyResponse> response = testRestTemplate.getForEntity(endpointUrl, MyResponse.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getValue()).isNull();
    }


    private void assertBodyContainsParam(String param, ResponseEntity<MyResponse> response) {
        assertThat(response).isNotNull();
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getValue()).isEqualTo(param);
    }

    private ResponseEntity<MyResponse> getMyResponseResponseEntity(String param) {
        String endpointUrl = UriComponentsBuilder.fromHttpUrl(serverUrl)
                .path("test")
                .queryParam("var", param)
                .toUriString();
        return testRestTemplate.getForEntity(endpointUrl, MyResponse.class);
    }

}
