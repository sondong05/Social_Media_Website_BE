# Sử dụng image có Maven và JDK
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Copy toàn bộ project vào container build
WORKDIR /app
COPY . .

# Build project và tạo file jar
RUN mvn clean package -DskipTests

# Stage 2: Image chạy app
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy file jar từ stage build
COPY --from=build /app/target/*.jar app.jar

# Cổng mà app sẽ chạy
EXPOSE 8080

# Lệnh chạy app
ENTRYPOINT ["java", "-jar", "app.jar"]
