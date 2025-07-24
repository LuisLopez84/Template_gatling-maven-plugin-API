package example;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class DeleteSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http.baseUrl("https://fakestoreapi.com")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    // Scenario 4: DELETE product
    ScenarioBuilder deleteScenario = scenario("DELETE Product")
            .exec(
                    http("DELETE product")
                            .delete("/products/1")
                            .check(status().is(200))
            );
    {
        setUp(
               deleteScenario.injectOpen(rampUsers(5).during(30))
        ).protocols(httpProtocol);
    }
}
