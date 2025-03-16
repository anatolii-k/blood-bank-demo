package integration_test.utilities.compatibility;

import anatolii.k.bloodbank.BloodBankSystemApplication;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
                classes = BloodBankSystemApplication.class)
public class CompatibilityCheckTest {

    @Autowired
    private TestRestTemplate rest;
    private final String BASE_URL = "/api/utilities/compatibility";

    @Test
    void whenRequestWithCompatibleRecipientAndDonor_ThenResponseOkAndCompatibleIsTrue(){

        // Blood Types in ABO format
        {
            ResponseEntity<String> response = rest.getForEntity(BASE_URL + "?recipientType=AB-&donorType=A-", String.class );

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

            DocumentContext responseJSON = JsonPath.parse(response.getBody());
            Boolean isOk = responseJSON.read("$.ok");
            Boolean isCompatible = responseJSON.read("$.compatible");
            JSONArray errors = responseJSON.read("$.errors");

            assertThat(isOk.booleanValue()).isTrue();
            assertThat(isCompatible.booleanValue()).isTrue();
            assertThat(errors.isEmpty()).isTrue();
        }
        // Blood Types in ABO Numeric format
        {
            ResponseEntity<String> response = rest.getForEntity(BASE_URL + "?recipientType=IV-&donorType=II-", String.class );

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

            DocumentContext responseJSON = JsonPath.parse(response.getBody());
            Boolean isOk = responseJSON.read("$.ok");
            Boolean isCompatible = responseJSON.read("$.compatible");
            JSONArray errors = responseJSON.read("$.errors");

            assertThat(isOk.booleanValue()).isTrue();
            assertThat(isCompatible.booleanValue()).isTrue();
            assertThat(errors.isEmpty()).isTrue();

        }
        // Blood Types in ABO Mixed format
        {
            ResponseEntity<String> response = rest.getForEntity(BASE_URL + "?recipientType=AB(IV)-&donorType=A(II)-", String.class );

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

            DocumentContext responseJSON = JsonPath.parse(response.getBody());
            Boolean isOk = responseJSON.read("$.ok");
            Boolean isCompatible = responseJSON.read("$.compatible");
            JSONArray errors = responseJSON.read("$.errors");

            assertThat(isOk.booleanValue()).isTrue();
            assertThat(isCompatible.booleanValue()).isTrue();
            assertThat(errors.isEmpty()).isTrue();
        }
    }

    @Test
    void whenRequestWithNotCompatibleRecipientAndDonor_ThenResponseOkAndCompatibleIsFalse(){

        ResponseEntity<String> response = rest.getForEntity(BASE_URL + "?recipientType=B-&donorType=A-", String.class );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext responseJSON = JsonPath.parse(response.getBody());
        Boolean isOk = responseJSON.read("$.ok");
        Boolean isCompatible = responseJSON.read("$.compatible");
        JSONArray errors = responseJSON.read("$.errors");

        assertThat(isOk.booleanValue()).isTrue();
        assertThat(isCompatible.booleanValue()).isFalse();
        assertThat(errors.isEmpty()).isTrue();
    }

    @Test
    void whenRequestWithoutRecipient_ThenResponseStatusIsBadRequest(){

        ResponseEntity<String> response = rest.getForEntity(BASE_URL + "?donorType=A-", String.class );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void whenRequestWithoutDonor_ThenResponseStatusIsBadRequest(){

        ResponseEntity<String> response = rest.getForEntity(BASE_URL + "?recipientType=B-", String.class );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void whenRequestWithInvalidRecipient_ThenResponseOkIsFalse(){

        ResponseEntity<String> response = rest.getForEntity(BASE_URL + "?recipientType=BBB&donorType=A-", String.class );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext responseJSON = JsonPath.parse(response.getBody());
        Boolean isOk = responseJSON.read("$.ok");
        Boolean isCompatible = responseJSON.read("$.compatible");
        JSONArray errors = responseJSON.read("$.errors");

        assertThat(isOk.booleanValue()).isFalse();
        assertThat(isCompatible.booleanValue()).isFalse();
        assertThat(errors.size()).isEqualTo(1);
    }

    @Test
    void whenRequestWithInvalidDonor_ThenResponseOkIsFalse(){

        ResponseEntity<String> response = rest.getForEntity(BASE_URL + "?recipientType=B-&donorType=AAA", String.class );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext responseJSON = JsonPath.parse(response.getBody());
        Boolean isOk = responseJSON.read("$.ok");
        Boolean isCompatible = responseJSON.read("$.compatible");
        JSONArray errors = responseJSON.read("$.errors");

        assertThat(isOk.booleanValue()).isFalse();
        assertThat(isCompatible.booleanValue()).isFalse();
        assertThat(errors.size()).isEqualTo(1);
    }
}
