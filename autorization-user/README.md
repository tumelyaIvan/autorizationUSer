# User Management System with Spring Boot

## Описание проекта

Этот проект реализует систему управления пользователями на основе Spring Boot. Приложение поддерживает следующие функции:

- Создание пользователя
- Удаление пользователя
- Редактирование пользователя
- Вход в систему под созданным пользователем
- Выход из системы
- Получение списка пользователей
- WebSocket для STOMP обмена сообщениями
- Запланированное завершение работы приложения

## Требования

- Java 17+
- PostgreSQL
- Gradle
- Spring Boot

## Установка

1. Склонируйте репозиторий:

    ```bash
    git clone <URL вашего репозитория>
    cd <имя директории>
    ```

2. Настройте базу данных PostgreSQL:

   Создайте базу данных и пользователя в PostgreSQL. Затем обновите `application.yml` с вашими учетными данными.

    ```yaml
    spring:
      datasource:
        url: jdbc:postgresql://localhost:5432/<ваша_база_данных>
        username: <ваш_пользователь>
        password: <ваш_пароль>
        driver-class-name: org.postgresql.Driver
      jpa:
        hibernate:
          ddl-auto: update
        show-sql: true
    ```

3. Соберите и запустите приложение:

   Выполните следующую команду:

    ```bash
    ./gradlew bootRun
    ```

## Использование

### API Эндпоинты

- **Создание пользователя:**

    ```http
    POST /users/create
    Content-Type: application/json
    ```

  Пример запроса:

    ```json
    {
        "login": "newUser",
        "password": "Pass@123",
        "fullName": "New User",
        "gender": {
            "id": 1
        }
    }
    ```

- **Удаление пользователя:**

    ```http
    DELETE /users/delete/{id}
    ```

- **Редактирование пользователя:**

    ```http
    PUT /users/update/{id}
    Content-Type: application/json
    ```

  Пример запроса:

    ```json
    {
        "login": "updatedUser",
        "password": "NewPass@123",
        "fullName": "Updated User",
        "gender": {
            "id": 2
        }
    }
    ```

- **Получение списка пользователей:**

    ```http
    GET /users
    ```

- **Вход в систему:**

    ```http
    POST /users/login
    Content-Type: application/json
    ```

  Пример запроса:

    ```json
    {
        "login": "existingUser",
        "password": "existingPassword"
    }
    ```

- **Выход из системы:**

    ```http
    POST /users/logout
    ```

### WebSocket для STOMP

- Подключение к WebSocket: `ws://localhost:8080/ws`
- Подписка на топик `/topic/users` для получения уведомлений о действиях пользователей.

### Scheduled Shutdown

Приложение автоматически завершит работу, если текущее время превысит значение, указанное в `application.yml`:

```yaml
scheduled:
  shutdown-time: 2024-12-31T23:59:59