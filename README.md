Gatling plugin for Maven - Java demo project
============================================

A simple showcase of a Maven project using the Gatling plugin for Maven. Refer to the plugin documentation
[on the Gatling website](https://docs.gatling.io/reference/integrations/build-tools/maven-plugin/) for usage.

This project is written in Java, others are available for [Kotlin](https://github.com/gatling/gatling-maven-plugin-demo-kotlin)
and [Scala](https://github.com/gatling/gatling-maven-plugin-demo-scala).

It includes:

* [Maven Wrapper](https://maven.apache.org/wrapper/), so that you can immediately run Maven with `./mvnw` without having
  to install it on your computer
* minimal `pom.xml`
* latest version of `io.gatling:gatling-maven-plugin` applied
* sample [Simulation](https://docs.gatling.io/reference/glossary/#simulation) class,
  demonstrating sufficient Gatling functionality
* proper source file layout

  
Modo de ejecución:
============================================
Se debe ejecutar desde linea de comandos maven (mvn) lado derecho del intellij con la siguiente casúistica:

./mvnw clean gatling:test -Dgatling.simulationClass=example.RestApiSimulationAll

./mvnw clean gatling:test -Dgatling.simulationClass=example.PostProductosSimulation

./mvnw clean gatling:test -Dgatling.simulationClass=example.GetSimulation

./mvnw clean gatling:test -Dgatling.simulationClass=example.PutSimulation

./mvnw clean gatling:test -Dgatling.simulationClass=example.DeleteSimulation


Aclaración:
============================================
El método PUT desde la clase individual (PostProductosSimulation) hace uso de un feeder




