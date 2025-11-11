# ---------- Build stage ----------
    FROM maven:3.9.9-eclipse-temurin-8 AS build
    WORKDIR /app
    COPY pom.xml .
    RUN mvn -B -ntp -q dependency:go-offline
    COPY src ./src
    RUN mvn -B -ntp -DskipTests package
    
    # ---------- Runtime stage ----------
    FROM eclipse-temurin:8-jre
    WORKDIR /app
    # copy the fat jar (adjust name if you don’t use spring-boot repackage)
    COPY --from=build /app/target/*.jar /app/app.jar
    EXPOSE 8080
    ENTRYPOINT ["java","-jar","/app/app.jar"]
    