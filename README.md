# My-first-app-in-jetpack-compose

### Этот проект состоит из следующих мини-приложений:
1. [Программа решения неравенства вида ax + b < 0](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/tree/main/app/src/main/java/com/fred_projects/education/solving_the_inequality).
2. Та же программа, только с использованием [MVVM](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/tree/main/app/src/main/java/com/fred_projects/education/solving_the_inequality/mvvm).
3. [В данной работе необходимо осуществить вычисление 3^fn со всеми десятичными знаками, где n in 1..45, fn - числа фибоначчи. Это вычисление должно осуществляться внутри Service в отдельном потоке. После вычисления результаты должны появиться в Activity, а если он неактивен, то должно появиться оповещение, кликнув по которому, будет осуществлен переход на Activity с ответом. Также должна быть возможность остановить вычисление по желанию пользователя](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/tree/main/app/src/main/java/com/fred_projects/education/service_assignment).
4. [CRUD приложение](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/tree/main/app/src/main/java/com/fred_projects/education/main) с использованием room, sqlite, dagger-hilt, а также внедрение возможности осуществлять прикрепление фотографии к хранимым сущностям.
5. [SecondLW](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/blob/main/app/src/main/java/com/fred_projects/education/SecondLW.kt) предназначено для учёта количества прыжков через скакалку с использованием сенсора акселерометр. Телефон в кармане брюк, т.е. оно должно быть жестко зафиксировано. Информация об активностях должна сохраняться в базе данных. [Пользователь должен иметь возможность корректировать результаты неточных измерений](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/blob/main/app/src/main/java/com/fred_projects/education/jumping_rope/SensorAndAnimation.kt).
6. Доработка приложения, выполненного в предыдущей практической, чтобы оно в виде [анимации](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/blob/main/app/src/main/java/com/fred_projects/education/jumping_rope/view/StickMan.kt) показывало активности пользователя.
7. [Работа](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/tree/main/app/src/main/java/com/fred_projects/education/rest_api) с минимум одним rest-api запросом, который должен выполняется в фоне, имеющим аргумент. Также присутствует реализация возможности просмотра загруженной информации при отсуствии Интернета, т.е. с сохранением загруженной информации на мобильном устройстве и вывод его на экран в случае отсутствия Интернета. Реализуйте автоматическое [UNIT](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/tree/main/app/src/test/java/com/fred_projects/test_astronomy_service) и [UI-тестирование](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/tree/main/app/src/androidTest/java/com/fred_projects/test_astronomy_service).

#### В пункте 7 я использовал следующие API:
1. [Возвращает объекты на расстоянии r от заданного набора координат ra и dec. Обратите внимание, что это отключает точные совпадения для ra и dec.](https://github.com/astrocatalogs/OACAPI)
2. [По данному аниме выведите цитаты из него](https://github.com/RocktimSaikia/anime-chan)
3. [По данному выражению найдите его упрощенный вариант](https://newton.now.sh/).
Эти проекты реализованы с использованием MVVM и SOLID, кроме 1, 3 и 6 соответственно.

### Как запустить этот проект?
1. Создать проект с Jetpack Compose.
2. Скопировать id("com.google.dagger.hilt.android") version "current version" apply false и вставить в build.gradle.kts (Project).
3. Открыть [build.gradle.kts](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/blob/main/app/build.gradle.kts) и перенести необходимые plugins и отсутствующие dependencies.
4. Открыть [strings.xml](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/blob/main/app/src/main/res/values/strings.xml), скопировать данные из данного файла и вставить в свой.
5. Открыть [src](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/tree/main/app/src/main/java/com/fred_projects) перенести все, кроме [этого](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/tree/main/app/src/main/java/com/fred_projects/ui/theme).
6. И заняться небольшой отладкой, наверное.

**Или клонировать репозиторий**
