<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="firstName">Имя:
        <input type="text" name="firstName" id="firstName" required>
    </label><br>
    <label for="surname">Фамилия:
        <input type="text" name="surname" id="surname" required>
    </label><br>
    <label for="birthday">День рождения:
        <input type="date" name="birthday" id="birthday" required>
    </label><br>
    <label for="telephone">Телефон:
        <input type="text" name="telephone" id="telephone" required>
    </label><br>
    <label for="passport">Номер паспорта:
        <input type="text" name="passport" id="passport" required>
    </label><br>
    <label for="emailId">Email:
        <input type="text" name="email" id="emailId" required>
    </label><br>
    <label for="passwordId">Пароль:
        <input type="password" name="password" id="passwordId" required>
    </label><br>
    <button type="submit">Зарегистрироваться</button>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span><br>
            </c:forEach>
        </div>
    </c:if>
</form>
</body>
</html>
