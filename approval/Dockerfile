FROM maven:3.8.6-jdk-11
RUN apt-get update
ENV JDBC_CONNECTION_STRING jdbc:h2:mem:demodb
WORKDIR /approval
COPY pom.xml .
RUN mvn dependency:resolve
RUN mvn dependency:resolve-plugins
COPY src ./src
COPY scripts ./scripts
RUN mvn package

# Start Approval service
# ######################
ENTRYPOINT exec java $JAVA_OPTS -jar target/approval.jar
# CMD ["java", "-jar", "target/api.jar"]
