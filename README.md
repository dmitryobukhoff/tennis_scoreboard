
# Проект "Табло теннисного матча"
"Табло теннисного матча" - ученбный проект, созданный с целью получения практики использования Hibernate и H2-in-memory database. Практика архитектуры MVC(S) и написания юнит тестов, с помощью библиотеки JUnit5.




## Описание функционала
Приложение написано по техническому заданию, доступному по ссылке: [Техническое задание](https://github.com/zhukovsd/java-backend-learning-course/blob/main/Projects/TennisScoreboard/index.md)

### Функционал GET запросов
* Создание нового матча, по GET запросу **/new-match**
* Открытие страницы счёта матча по GET запросу **/match-score?uuid={uuid}** при это запрос принимает параметр UUID
* Страница сыгранных матче по GET запросу **/matches?page=$page_number filter_by_player_name=$player_name**

### Функционал POST запросов
В данном приложении POST запросы реализуют функцию полуения очков одним из учасников теннисного матча
* Запрос отправляется по запросу **/match-score?uuid={uuid}**, где также с формы передаётся значение **playerWon**, отвечающее за опредление игрока, выигравшего очко



