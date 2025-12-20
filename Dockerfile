# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jdk
WORKDIR /app

# --- THAY ĐỔI QUAN TRỌNG TẠI ĐÂY ---
# Chỉ định rõ tên file để tránh lỗi copy nhầm file rác
COPY --from=build /app/target/vinbook-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]