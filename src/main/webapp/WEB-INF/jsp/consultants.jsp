<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${not empty requestScope.consultants}">
    <h1>Консультанты</h1>
    <ul>
        <c:forEach var="consultant" items="${requestScope.consultants}">
            <li>
                    ${consultant.firstName} ${consultant.surname}<br>
            </li>
        </c:forEach>
    </ul>
</c:if>
</body>
</html>
