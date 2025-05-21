# Используем официальный образ JDK 17
FROM eclipse-temurin:17-jdk-alpine

#
# Указываем рабочую директорию в контейнере
WORKDIR /app

# Копируем JAR файл в контейнер
COPY target/client-contact-service-0.0.1-SNAPSHOT.jar app.jar

# Указываем команду запуска
ENTRYPOINT ["java", "-jar", "app.jar"]
