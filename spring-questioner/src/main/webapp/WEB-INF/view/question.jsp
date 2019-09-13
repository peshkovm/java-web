<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <title>Questioner</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <h2>Вопрос</h2>
    <p>${question.text}</p>
    <form role="form" action="questioner" method="post">
        <input type="hidden" name="numOfQ" value="${question.num}"/>
        <c:forEach var="answer" items="${question.answers}">
        <div class="radio">
            <label><input type="radio" name="answer" value="${answer}">${answer}</label>
        </div>
        </c:forEach>
        <button type="submit" class="btn btn-default">Отправить</button>
    </form>
</div>

</body>
</html>