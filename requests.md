# Перевірка API магазину ігор

У корені проєкту запустіть сервер командою `mvn spring-boot:run` з Java 21.
В IntelliJ IDEA можна запустити клас `GameStoreApplication`.

У другому вікні PowerShell виконайте запити.

```powershell
curl.exe -i http://localhost:8080/api/games
curl.exe -i http://localhost:8080/api/games/1
curl.exe -i http://localhost:8080/api/games/999
```

Перший запит повертає `200 OK` і масив із п’яти карток ігор.
Другий повертає `200 OK` і деталі гри Hollow Knight.
Третій повертає `404 Not Found`.

У Bash використовуйте `curl` замість `curl.exe`. Ціни наведено в гривнях.
