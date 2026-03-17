# Etapa 1: build com Maven
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .

# Forçar UTF-8 durante o build do Maven
ENV MAVEN_OPTS="-Dfile.encoding=UTF-8"

RUN mvn clean package -DskipTests

# Etapa 2: runtime com JRE
FROM eclipse-temurin:17-jre

WORKDIR /app
COPY --from=build /app/target/Backend-hotels-0.0.1-SNAPSHOT.jar app.jar

# Forçar Java UTF-8 na execução
ENV JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF-8"

EXPOSE 8080

CMD ["java","-jar","app.jar"]