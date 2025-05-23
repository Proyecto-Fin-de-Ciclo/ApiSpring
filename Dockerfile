FROM amazoncorretto:17
ARG JAR_FILE=target/apitaller-0.0.1-SNAPSHOT.jar
COPY target/apitaller-0.0.1-SNAPSHOT.jar apitaller.jar


ENTRYPOINT ["java","-jar","/apitaller.jar"]

