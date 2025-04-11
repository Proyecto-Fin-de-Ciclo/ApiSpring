FROM amazoncorretto:17


ADD target/apilibreria-0.0.1-SNAPSHOT.jar apilibreria.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "apilibreria.jar"]
