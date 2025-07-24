package example;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class PostProductosSimulation extends Simulation {

    // Feeder dinámico
    FeederBuilder<String> productFeeder = csv("post_productos.csv").circular();

    HttpProtocolBuilder httpProtocol = http.baseUrl("https://fakestoreapi.com")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    ScenarioBuilder postScenario = scenario("POST Creación de Productos desde feeder")
            .feed(productFeeder)
            .exec(
                    http("POST new product from CSV")
                            .post("/products")
                            .body(StringBody(session -> String.format("""
                                {
                                  "title": "%s",
                                  "price": %s,
                                  "description": "%s",
                                  "image": "%s",
                                  "category": "%s"
                                }
                                """,
                                    session.getString("title"),
                                    session.getString("price"),
                                    session.getString("description"),
                                    session.getString("image"),
                                    session.getString("category")
                            )))
                            .asJson()
                            .check(status().is(200))
                            .check(jsonPath("$.id").exists())
            );

    {
        setUp(
                postScenario.injectOpen(rampUsers(5).during(10))
        ).protocols(httpProtocol);
    }
}