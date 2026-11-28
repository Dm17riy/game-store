# Game Store

REST API каталогу відеоігор. Для запуску потрібна Java 21.

## Тести ГЗ 1.4

- `GameServiceImplTest` перевіряє пошук гри та виняток для відсутнього ID. Репозиторій підмінено за допомогою Mockito.
- `GameControllerTest` перевіряє список ігор та JSON-відповіді `404` і `400` через `@WebMvcTest` і MockMvc.

Запуск із кореня проєкту.

```powershell
.\mvnw.cmd test
```
