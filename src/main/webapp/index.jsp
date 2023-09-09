<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Теннисный счёт</title>
    <style>
        <%@ include file="/style.css" %>
    </style>
</head>
<body>
<main>
    <section>
        <div class="header">
            <h1 class="header_title">Теннисный счёт</h1>
        </div>
    </section>
    <section>
        <h2 class="paragraph">Искать матчи по имени игрока</h2>
        <div>
            <form action="/matches" method="post">
                <input id="name" name="filter_by_player_name" type="text" placeholder="Имя игрока" class="searcher" required>
                <button type="submit">Искать</button>
            </form>
        </div>
    </section>
    <section>
        <h2 class="paragraph">Действия с матчами</h2>
        <form action="/new-match" method="get">
            <button>Создать новый матч</button>
        </form>
        <form action="/matches" method="get">
            <button>Просмотреть все матчи</button>
        </form>
    </section>
</main>
<footer>
</footer>
</body>
</html>
