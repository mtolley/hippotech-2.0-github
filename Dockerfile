FROM maven:3.8.6-jdk-11

# Step 1: Build the React frontend
# ################################
ENV NVM_DIR /usr/local/nvm
ENV NODE_VERSION 16.14.0
RUN mkdir -p $NVM_DIR
WORKDIR /hippotech-react

# Dependencies required for Cypress
RUN apt update
RUN apt-get install -y libgtk2.0-0 libgtk-3-0 libgbm-dev libnotify-dev libgconf-2-4 libnss3 libxss1 libasound2 libxtst6 xauth xvfb

RUN curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash
ENV NODE_PATH $NVM_DIR/v$NODE_VERSION/lib/node_modules
ENV PATH $NVM_DIR/v$NODE_VERSION/bin:$PATH
RUN . $NVM_DIR/nvm.sh \
    && nvm install v$NODE_VERSION \
    && nvm alias default $NODE_VERSION \
    && nvm use default 

COPY hippotech-react/cypress.config.js .
COPY hippotech-react/package-lock.json .
COPY hippotech-react/package.json .
COPY hippotech-react/cypress ./cypress
COPY hippotech-react/public ./public
COPY hippotech-react/src ./src

RUN . $NVM_DIR/nvm.sh && npm install 
RUN . $NVM_DIR/nvm.sh && npm run build
RUN . $NVM_DIR/nvm.sh && npx cypress install

# Step 2: Build the Java server application hosting the API and the frontend from step 1.
# #######################################################################################
WORKDIR /javaapi
COPY java-api/pom.xml .
RUN mvn dependency:resolve
RUN mvn dependency:resolve-plugins
COPY java-api/src ./src
COPY java-api/start.sh .
RUN mvn package -DskipTests

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://hippodb:5432/hippotech
ENV SPRING_DATASOURCE_USERNAME=hippotech
ENV SPRING_DATASOURCE_PASSWORD=hippotech
ENV SPRING_JPA_HIBERNATE_DDL-AUTO=update
ENV SPRING_JPA_SHOW-SQL=true
ENV SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL=true
ENV SPRING_JPA_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQL81Dialect

# Start HippoTech
# ###############
CMD ["java", "-jar", "target/api.jar"]