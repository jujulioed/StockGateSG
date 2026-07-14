# Etapa 1: Compilação da aplicação
FROM maven:3.9-eclipse-temurin-21-alpine AS build

WORKDIR /app

# Copia o pom.xml
COPY pom.xml .

RUN mvn dependency:go-offline -B

# Copia código fonte
COPY src ./src

# Gera JAR
RUN mvn clean package

# Etapa 2: executa a aplicação
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Cria um usuario não-root para executar a aplicação
RUN addgroup -S spring && adduser -S spring -G spring

# Copia o Jar gerado na etapa anterior
COPY --from=build --chown=spring:spring /app/target/*.jar app.jar

USER spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]