<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/AuthFilterServlet" method="post">
    Login: <input type="text" name="login"><br/>
    Password: <input type="password" name="password"><br/>
    <input type="submit">
</form>

<form action="${pageContext.servletContext.contextPath}/AuthFilterServlet" method="post">
    <p>
        <button name="enterHowGuest" type="hidden" value="${user.id}">Enter how guest</button>
    </p>
</form>
</body>
</html>
