FROM maven:3.8.6-jdk-11
RUN apt-get update

WORKDIR /fraud

COPY pom.xml .
RUN mvn dependency:resolve
RUN mvn dependency:resolve-plugins
#COPY scripts ./scripts
COPY src ./src
RUN mvn package

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://hippodb:5432/hippotech
ENV SPRING_DATASOURCE_USERNAME=hippotech
ENV SPRING_DATASOURCE_PASSWORD=hippotech
ENV SPRING_JPA_HIBERNATE_DDL-AUTO=update
ENV SPRING_JPA_SHOW-SQL=true
ENV SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL=true
ENV SPRING_JPA_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQL81Dialect

# Start Approval service
# ######################
ENTRYPOINT exec java $JAVA_OPTS -jar target/fraud.jar
# CMD ["java", "-jar", "target/api.jar"]
