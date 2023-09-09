<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c' %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <style>
        <%@ include file="/style.css" %>
    </style>
</head>
<body>
    <main>
        <section>
            <div class="header">
                <h1 class="paragraph">Сыгранные матчи</h1>
            </div>
        </section>
        <section>
            <c:if test="${not empty matches}">
                <ul>
                <c:forEach var="match" items="${matches}">
                    <li>
                        <div class="item">
                            <p>Первый игрок: <b>${match.getPlayer1().getName()}</b> Второй игрок:
                                <b>${match.getPlayer2().getName()}</b> Победитель: <b>${match.getWinner().getName()}</b></p>
                        </div>
                    </li>
                </c:forEach>
                </ul>
            </c:if>
        </section>
    </main>
    <c:if test="${not empty matches}">
        <div style="display: flex; justify-content: center; align-items: center;">
            <c:if test="${page > 1}">
                <a href="/matches?page=${page - 1}">< Предыдущая</a>
            </c:if>
            <a href="/matches?page=${page + 1}">Следующая ></a>
        </div>
    </c:if>

    <c:if test="${empty matches}">
        <c:if test="${page > 1}">
            <a href="/matches?page=${page - 1}">< Предыдущая</a>
        </c:if>
    </c:if>
</body>
</html>
