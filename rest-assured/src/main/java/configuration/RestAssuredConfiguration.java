package configuration;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class RestAssuredConfiguration {

    public static final String HTTPS = "https://";
    public static String BASE_URI = "www.bankier.pl/";

    private static RestAssuredConfiguration instance;

    public static RestAssuredConfiguration getInstance() {
        if (instance == null) {
            instance = new RestAssuredConfiguration();
        }
        return instance;
    }

    public RequestSpecification getRequestSpecification() {
        return given().log().all()
                .baseUri(HTTPS + BASE_URI);
    }
}
