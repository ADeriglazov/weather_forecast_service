### Тестовое задание в "Школу будущих CTO". Стэк разработки
#### Умный сервис прогноза погоды. Средний уровень сложности

##### Проектирование сервиса
Решение представляет собой **RESTful Web-сервис**, написанный на **Spring**. 
Интерфейсом взаимодействия с пользователем является **сайт**, способный принимать и отвечать на запросы пользователей о погоде в каком-либо городе.
После ввода названия города, на страницу пользователю выводится ответ с информацией о прогнозе погоды на сегодняшний день.

##### Описание процесса работы сервиса
1) После запуска программы, на порту 8080 поднимается сервер. При обращении на http://localhost:8080 пользователь попадает на домашнюю страницу сайта, где можно найти поле ввода названия города.
2) После заполнения формы, формируется POST запрос к серверу, после обработки которого строится строка запроса к https://openweathermap.org.
3) Происходит инициализация пользователя, сохраняется ID сессии для хранения истории операций. На данный момент т.н. cookie хранятся в виртуальной памяти сервера *(прим. этот момент можно улучшить, если сохранять активность пользователя в бд. Это позволит серверу держать большую нагрузку и сохранять свое состояние на случай падения)*.
4) Далее делается запрос к сервису поставщику прогноза погоды, обрабатывается ответ, и результат выводится на страницу пользователя.
