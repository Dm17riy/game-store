# Перевірка API магазину ігор

У корені проєкту запустіть сервер командою `mvn spring-boot:run` з Java 21.
В IntelliJ IDEA можна запустити клас `GameStoreApplication`.

У другому вікні PowerShell виконайте запити.

```powershell
curl.exe -i http://localhost:8080/api/games
curl.exe -i http://localhost:8080/api/games/1
curl.exe -i http://localhost:8080/api/games/999
```

Перший запит повертає `200 OK` і п’ять стартових ігор, другий — `200 OK`
і деталі Hollow Knight, третій — `404 Not Found`.

## Фільтр за жанром

```powershell
curl.exe -i "http://localhost:8080/api/games?genre=%D1%80%D0%BE%D0%B3%D0%BB%D0%B0%D0%B9%D1%82"
```

Запит надсилає жанр «роглайт» і повертає Hades. Регістр літер у фільтрі не має значення.

## Фільтр за платформою (ПЗ 1.3)

```powershell
curl.exe -i 'http://localhost:8080/api/games?platform=PC'
curl.exe -i 'http://localhost:8080/api/games?platform=Nintendo%20Switch'
```

Фільтр ігнорує регістр і може працювати разом із `genre`. Поле `platform`
необов’язкове у тілі запиту та повертається в деталях гри.

## Створення, оновлення і видалення

Виконайте блок у PowerShell. ID береться з відповіді на створення.

```powershell
$base = 'http://localhost:8080/api/games'
$game = @{
    title = 'Dead Cells'
    studio = 'Motion Twin'
    genre = 'Roguelite'
    price = 429.00
    releaseYear = 2018
    platform = 'PC'
    description = 'Explore a cursed island.'
}
# POST: 201 Created. Статус виводиться в консоль, тіло містить створений ID.
$response = $game | ConvertTo-Json -Compress | curl.exe -sS --fail -w '%{stderr}HTTP %{http_code}\n' -H 'Content-Type: application/json' --data-binary '@-' $base
if ($LASTEXITCODE -ne 0) { throw 'Не вдалося створити гру.' }
$id = ($response | ConvertFrom-Json).id
# PUT: 200 OK і оновлені деталі.
$game.price = 399.00
$game.description = 'Exploration and combat on a cursed island.'
$game | ConvertTo-Json -Compress | curl.exe -i -X PUT -H 'Content-Type: application/json' --data-binary '@-' "$base/$id"

# POST: 400 Bad Request через порожню назву і від’ємну ціну.
$invalid = $game.Clone()
$invalid.title = ''
$invalid.price = -1
$invalid | ConvertTo-Json -Compress | curl.exe -i -H 'Content-Type: application/json' --data-binary '@-' $base

# DELETE: 204 No Content. Повторний DELETE: 404 Not Found.
curl.exe -i -X DELETE "$base/$id"
curl.exe -i -X DELETE "$base/$id"
```

У Bash використовуйте `curl` замість `curl.exe`. Ціни наведено в гривнях.
