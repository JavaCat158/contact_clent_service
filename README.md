# Client Contact Service

📬 RESTful приложение для управления клиентами и их контактной информацией.

---

## 🚀 Функциональность

- Создание, обновление и удаление клиентов
- Хранение контактной информации (телефон, email)
- Один клиент — один контакт
- Валидация входных данных (DTO)
- Документация через Swagger/OpenAPI
- Тесты: unit + интеграционные (TestContainers)

---

## 🛠 Используемые технологии

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **PostgreSQL**
- **Docker + Docker Compose**
- **Swagger (springdoc-openapi)**
- **JUnit 5 + Mockito**
- **TestContainers**

---

## ⚙️ Запуск проекта

### 🔧 1. Клонировать репозиторий

```bash
git clone https://github.com/JavaCat158/contact_clent_service.git
cd contact_clent_service
```

### 🐳 2. Запуск с Docker Compose

```bash
mvn clean package                   # необходимо скачать maven локально или использовать wrapper
        или
./mvnw clean package                # wrapper
docker-compose up --build
```

> Используется `.env` файл для настройки доступа к базе данных.  
> Приложение будет доступно на `http://localhost:8080`

### 🔧 Локальный запуск (из IDE)

Для запуска без Docker в `application.properties` используется:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/clientsdb
spring.datasource.username=admin
spring.datasource.password=admin123
```

---

## 🔎 Swagger UI

После запуска доступен по адресу:

```
http://localhost:8080/swagger-ui.html
```

Позволяет тестировать все эндпоинты в интерактивном интерфейсе.

---

## 📁 Структура проекта

```
src/
├── controller       # REST API
├── service          # Бизнес-логика
├── entity           # JPA-сущности
├── dto              # DTO-объекты с валидацией
├── mapper           # Мапперы DTO <-> Entity
├── repository       # Spring Data репозитории
├── config           # Swagger и др.
└── exception        # Глобальный обработчик ошибок
```

---

## ✅ Пример запроса

**POST /api/clients**

```json
{
  "name": "Иван",
  "lastName": "Иванов",
  "contact": {
    "phone": "89001234567",
    "email": "ivan@example.com"
  }
}
```

---

## 🔒 Валидация и обработка ошибок

- Все входные данные валидируются с помощью `@NotBlank`, `@Email`, `@NotNull`
- Ошибки обрабатываются через `@ControllerAdvice`
- Ответы содержат подробную информацию об ошибке

Пример ответа при ошибке:

```json
{
  "timestamp": "2025-05-21T16:50:00",
  "status": 400,
  "message": "Ошибка валидации!",
  "errors": [
    "email: Некорректный Email",
    "phone: Поле не должно быть пустым"
  ]
}
```

---

## 🧪 Тестирование

Проект покрыт юнит- и интеграционными тестами:

### ✅ Unit-тесты (с использованием Mockito)

- `ClientServiceTest` — бизнес-логика клиента
- `ContactServiceTest` — логика управления контактами

### ✅ Интеграционные тесты (с TestContainers)

- `ClientRepositoryIntegrationTest` — проверка сохранения клиента с каскадным сохранением контакта
- `ContactRepositoryIntegrationTest` — сохранение и извлечение контактов

Используется TestContainers + PostgreSQL:
- `jdbc:tc:postgresql:15:///clientsdb` автоматически запускает контейнер

### 🚀 Запуск всех тестов

```bash
mvn clean test
```

---

## ⚙️ Настройка .env

Создайте файл `.env` в корне проекта на основе `.env.example`:
Прежде не забудьте ввести корректные данные от вашей бд пример в файле example.
```bash
cp .env.example .env

## 📝 Автор

**Ильдар Ибрагимов**  
Java-разработчик  
📧 [ibragimovildar229@gmail.com](mailto:ibragimovildar229@gmail.com)

---

## 📦 Лицензия

Проект предоставляется "как есть" для демонстрации навыков backend-разработки.