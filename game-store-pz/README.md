# Game Store

Каталог ігор на Spring Boot. Потрібна Java 21. Дані зберігаються в пам’яті.

Запуск із папки `game-store-pz` у PowerShell.

```powershell
.\mvnw.cmd spring-boot:run
```

API доступне за адресою `http://localhost:8080/api/games`. У [requests.md](requests.md) є запити для перегляду, фільтрації, додавання, редагування й видалення ігор.

Щоб запустити тести сервісу й контролера, виконайте `.\mvnw.cmd test`.
