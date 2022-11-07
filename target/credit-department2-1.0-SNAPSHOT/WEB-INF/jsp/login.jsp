<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="email">Email:
        <input type="text" name="email" id="email" value="${param.email}" required>
    </label><br>
    <label for="password">Пароль:
        <input type="password" name="password" id="password" required>
    </label><br>
    <button type="submit">Логин</button>
    <a href="${pageContext.request.contextPath}/registration">
        <button type="button">Регистрация</button>
    </a>
    <c:if test="${param.error != null}">
        <div style="color: red">
            <span>Неверный логин или пароль</span>
        </div>
    </c:if>
</form>
</body>
</html>
