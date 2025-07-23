package example;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class RestApiSimulation extends Simulation {

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

    // Scenario 2: POST create product
    ScenarioBuilder postScenario = scenario("POST Create Product")
            .exec(
                    http("POST new product")
                            .post("/products")
                            .body(StringBody("""
                                {
                                  "title": "Gatling Shirt",
                                  "price": 19.99,
                                  "description": "Load tested shirt",
"image": "https://i.pravatar.cc",
                                  "category": "clothing"
                                }
                                """)).asJson()
                            .check(status().is(200))
                            .check(jsonPath("$.id").exists())
            );

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

    // Scenario 4: DELETE product
    ScenarioBuilder deleteScenario = scenario("DELETE Product")
            .exec(
                    http("DELETE product")
                            .delete("/products/1")
                            .check(status().is(200))
            );

    // Scenario 5: GET specific product
    ScenarioBuilder getByIdScenario = scenario("GET Product by ID")
            .exec(
                    http("GET product by ID")
                            .get("/products/2")
                            .check(status().is(200))
                            .check(jsonPath("$.id").is("2"))
            );

    {
        setUp(
                getScenario.injectOpen(rampUsers(1).during(30))
    //            postScenario.injectOpen(rampUsers(4).during(10)),
    //            putScenario.injectOpen(rampUsers(6).during(10)),
    //            deleteScenario.injectOpen(rampUsers(8).during(10)),
    //            getByIdScenario.injectOpen(rampUsers(10).during(10))
        ).protocols(httpProtocol);
    }
}