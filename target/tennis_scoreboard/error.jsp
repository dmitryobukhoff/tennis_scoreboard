<%--
  Created by IntelliJ IDEA.
  User: dmitryobukhoff
  Date: 04.09.2023
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ошибка</title>
    <style>
        <%@ include file="/style.css" %>
    </style>
</head>
<body>
    <main>
        <section>
            <div class="header">
                <h1 class="header_title">Информация об ошибке</h1>
            </div>
        </section>
        <section>
            <h2 class="paragraph">Сообщеие</h2>
            <p style="font-size: 18px">${message}</p>
        </section>
    </main>
</body>
</html>
