<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<div>
    <c:if test="${not empty sessionScope.user}">
        <div id="logout">
            <span>
                    ${sessionScope.user.firstName} ${sessionScope.user.surname}
            </span>
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit">Выйти</button>
            </form>
        </div>
    </c:if>
    <c:if test="${empty sessionScope.user}">
        <div id="login">
            <form action="${pageContext.request.contextPath}/login" method="post">
                <a href="${pageContext.request.contextPath}/login">
                    <button type="button">Логин</button>
                </a>
                <a href="${pageContext.request.contextPath}/registration">
                    <button type="button">Регистрация</button>
                </a>
                <c:if test="${param.error != null}">
                    <div style="color: red">
                        <span>Неверный логин или пароль</span>
                    </div>
                </c:if>
            </form>
        </div>
    </c:if>
</div>
</html>
