## Параметры запуска для командной строки

Требуются JDK Java 11 и Gradle 6.8.3

запуск с параметрами, пример:

`gradle clean test -Durl=https://app.maxibooking.ru/ -Dbrowser=chrome`

браузеры: `chrome`, `safari`, `firefox`, `legacy_firefox`, `ie`, `opera`, `edge`

после окончания тестов
`gradle allureServe` для отчетов