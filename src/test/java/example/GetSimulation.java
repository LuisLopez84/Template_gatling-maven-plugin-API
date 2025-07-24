package example;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class GetSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http.baseUrl("https://fakestoreapi.com")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    // Scenario 1: GET all products
    ScenarioBuilder getScenario = scenario("GET Products")
            .exec(
                    http("GET all products")
                            .get("/products")
                            .check(status().is(200))
            );
    {
        setUp(
                getScenario.injectOpen(rampUsers(5).during(30))
        ).protocols(httpProtocol);
    }
}