package example;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class PutSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http.baseUrl("https://fakestoreapi.com")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");
    // Scenario 3: PUT update product
    ScenarioBuilder putScenario = scenario("PUT Update Product")
            .exec(
                    http("PUT update product")
                            .put("/products/1")
                            .body(StringBody("""
                                {
                                  "title": "Updated Shirt",
                                  "price": 25.00,
                                  "description": "Updated via Gatling",
"image": "https://i.pravatar.cc",
                                  "category": "clothing"
                                }
                                """)).asJson()
                            .check(status().is(200))
                            .check(jsonPath("$.title").is("Updated Shirt"))
            );
    {
        setUp(
                putScenario.injectOpen(rampUsers(5).during(30))
        ).protocols(httpProtocol);
    }
}