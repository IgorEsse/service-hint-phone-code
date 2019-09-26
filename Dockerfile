FROM openjdk

EXPOSE 8080:8080

ADD target/service-hint-phone-code-0.1.0.jar service-hint-phone-code-0.1.0.jar
ENTRYPOINT ["java", "-jar", "service-hint-phone-code-0.1.0.jar"]