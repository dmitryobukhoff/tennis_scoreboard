<%@ page import="ru.dmitryobukhoff.models.Match" %><%--
  Created by IntelliJ IDEA.
  User: dmitryobukhoff
  Date: 29.08.2023
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Подсчет очков</title>
    <style>
        <%@ include file="/style.css" %>
    </style>
</head>
<body>
<main>
    <section>
        <div class="header">
            <h1 class="header_title">Подсчёт очков</h1>
        </div>
    </section>
    <section>
        <h2 class="paragraph">Таблица матча</h2>
        <%
            Match match = (Match) request.getAttribute("match");
            String matchId = request.getParameter("uuid");
        %>
        <table>
            <tr>
                <th></th>
                <th><%= match.getPlayer1().getName() %></th>
                <th><%= match.getPlayer2().getName() %></th>
            </tr>
            <tr>
                <td>Очки</td>
                <td><%= match.getScore().getPersonalScore1().getScore()%></td>
                <td><%= match.getScore().getPersonalScore2().getScore()%></td>
            </tr>
            <tr>
                <td>Игры</td>
                <td><%= match.getScore().getPersonalScore1().getGame()%></td>
                <td><%= match.getScore().getPersonalScore2().getGame()%></td>
            </tr>
            <tr>
                <td>Сеты</td>
                <td><%= match.getScore().getPersonalScore1().getSet()%></td>
                <td><%= match.getScore().getPersonalScore2().getSet()%></td>
            </tr>
            <%
                if(!match.getScore().isGameEnd()){
            %>
            <tr>
                <td class="buttons"></td>
                <td class="buttons">
                    <form action="/match-score?uuid=<%=matchId%>" method="post">
                        <button name="playerWon" value="PLAYER1">Выиграл первый</button>
                    </form>
                </td>
                <td class="buttons">
                    <form action="/match-score?uuid=<%=matchId%>" method="post">
                        <button name="playerWon" name="PLAYER2">Выиграл второй</button>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>
        <%
            if(match.getScore().isGameEnd()){
        %>
        <form action="/matches" method="get">
            <button>Просмотреть матчи</button>
        </form>
        <%
            }
        %>
    </section>
</main>
</body>
</html>
