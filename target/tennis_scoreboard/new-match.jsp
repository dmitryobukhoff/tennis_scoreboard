<%--
  Created by IntelliJ IDEA.
  User: dmitryobukhoff
  Date: 28.08.2023
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Новый матч</title>
    <style>
        <%@ include file="/style.css" %>
    </style>
</head>
<body>
<main>
    <section>
        <div class="header">
            <h1 class="header_title">Создание нового матча</h1>
        </div>
    </section>
    <section>
        <h2 class="paragraph">Ввод игроков</h2>
        <form action="/new-match" method="post">
            <input type="text" id="player1Name" name="player1Name" class="searcher" placeholder="Имя первого игрока"required>
            <br>
            <input type="text" id="player2Name" name="player2Name" class="searcher" placeholder="Имя второго игрока"required>
            <button>Начать</button>
        </form>
    </section>
</main>
<footer>
</footer>
</body>
</html>
