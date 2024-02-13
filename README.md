# My-first-app-in-jetpack-compose

### Этот проект состоит из следующих мини-приложений:
1. [Программа решения неравенства вида ax + b < 0](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/tree/main/app/src/main/java/com/fred_projects/education/solving_the_inequality).
2. [Та же программа, только с использованием MVVM](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/tree/main/app/src/main/java/com/fred_projects/education/solving_the_inequality/mvvm).![image](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/assets/152185797/40d5e757-eaa0-44ef-900b-f5b1a236f7ef)
3. [В данной работе необходимо осуществить вычисление 3^fn со всеми десятичными знаками, где n in 1..45, fn - числа фибоначчи. Это вычисление должно осуществляться внутри Service в отдельном потоке. После вычисления результаты должны появиться в Activity, а если он неактивен, то должно появиться оповещение, кликнув по которому, будет осуществлен переход на Activity с ответом. Также должна быть возможность остановить вычисление по желанию пользователя](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/tree/main/app/src/main/java/com/fred_projects/education/service_assignment).![image](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/assets/152185797/69fdc78f-67c2-4c9c-988b-e4ce69a93cc9)![image](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/assets/152185797/e937fdfa-06d2-40f4-89fd-ba17ffd529b2)![image](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/assets/152185797/62ef577b-e360-4bcc-9fac-06251759bfe5)
4. [CRUD приложение](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/tree/main/app/src/main/java/com/fred_projects/education/main) с использованием room, sqlite, dagger-hilt, а также внедрение возможности осуществлять прикрепление фотографии к хранимым сущностям.

![image](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/assets/152185797/7569f6f4-9d96-49f7-ad2d-a0bd38d579ed)![image](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/assets/152185797/6e646f52-1f49-42d5-bc19-799245d6afd4)![image](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/assets/152185797/4d62d5ac-e27e-4f55-a380-4a74d2df2cdb)![image](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/assets/152185797/1060a7c8-7caa-4745-aa62-9c565bf89ac9)
![image](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/assets/152185797/9829a5d6-96a1-4826-9c7f-eb49a2a4665d)

5. [SecondLW](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/blob/main/app/src/main/java/com/fred_projects/education/SecondLW.kt) предназначено для учёта количества прыжков через скакалку с использованием сенсора акселерометр. Телефон в кармане брюк, т.е. оно должно быть жестко зафиксировано. Информация о активностях должна сохраняться в базе данных. [Пользователь должен иметь возможность корректировать результаты неточных измерений](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/blob/main/app/src/main/java/com/fred_projects/education/jumping_rope/SensorAndAnimation.kt).![image](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/assets/152185797/e8b56f14-2eeb-4b13-8b34-3006a5e36c4e)![image](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/assets/152185797/584f8c03-5f22-4441-89fe-f7abf2cc88b2)![image](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/assets/152185797/a8c525c7-a0ee-4e7e-a908-d0dac51b47e5)

6. Доработка приложения, выполненного в предыдущей практической, чтобы оно в виде [анимации](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/blob/main/app/src/main/java/com/fred_projects/education/jumping_rope/view/StickMan.kt) показывало активности пользователя.

![image](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/assets/152185797/518009a2-0fe3-4c7a-934c-0408864a120e)

7. [Работа](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/tree/main/app/src/main/java/com/fred_projects/education/rest_api) с минимум одним rest-api запросом, который должен выполняется в фоне, имеющим аргумент. Также присутствует реализация возможности просмотра загруженной информации при отсуствии Интернета, т.е. с сохранением загруженной информации на мобильном устройстве и вывод его на экран в случае отсутствия Интернета. Реализуйте автоматическое [UNIT](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/tree/main/app/src/test/java/com/fred_projects/test_astronomy_service) и [UI-тестирование](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/tree/main/app/src/androidTest/java/com/fred_projects/test_astronomy_service).

#### В пункте 7 я использовал следующие API:
1. [Возвращает объекты на расстоянии r от заданного набора координат ra и dec. Обратите внимание, что это отключает точные совпадения для ra и dec.](https://github.com/astrocatalogs/OACAPI)
2. [По данному аниме выведите цитаты из него](https://github.com/RocktimSaikia/anime-chan)

![image](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/assets/152185797/3647eb9a-4210-4bee-a75c-995767ec1529)

3. [По данному выражению найдите его упрощенный вариант](https://newton.now.sh/).

![image](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/assets/152185797/bc24eb3a-ad3f-4343-820d-0a868c968637)


Все эти мини-проекты реализованы с использованием MVVM и SOLID, кроме 1, 3 и 6 соответственно. Возможно, что использовал Clean Architecture в некоторых работах.

### Как запустить этот проект?
1. Создать проект с Jetpack Compose.
2. Скопировать id("com.google.dagger.hilt.android") version "current version" apply false и вставить в build.gradle.kts (Project).
3. Открыть [build.gradle.kts](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/blob/main/app/build.gradle.kts) и перенести необходимые plugins и отсутствующие dependencies.
4. Открыть [strings.xml](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/blob/main/app/src/main/res/values/strings.xml), скопировать данные из данного файла и вставить в свой.
5. Открыть [src](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/tree/main/app/src/main/java/com/fred_projects) перенести все, кроме [этого](https://github.com/FredNekrasov/My-first-app-in-jetpack-compose/tree/main/app/src/main/java/com/fred_projects/ui/theme).
6. И заняться небольшой отладкой, связанной с ui в activity, наверное.
