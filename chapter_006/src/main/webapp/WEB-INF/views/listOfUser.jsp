<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of users</title>
</head>
<p>
    <table>
        <c:forEach items="${users}" var="user">
        <tr>
            <td>User login: ${user.login}
                    ${user.role}
                <c:if test="${role != 'guest'}">
                <c:if test="${role == 'admin' || user.login == login}">
            <td>
                <form action="${pageContext.servletContext.contextPath}/UsersController" method="post">
<p>
    <button name="id" type="hidden" value="${user.id}">Delete</button>
</p>
</form>
</td>
</c:if>
<c:if test="${role == 'admin' || user.login == login}">
<td>
    <form action="${pageContext.servletContext.contextPath}/UserUpdateServlet" method="get">
        <p>
            <button name="id" type="hidden" value="${user.id}">Update</button>
        </p>
    </form>
    </c:if>
    </c:if>
</td>

</tr>
</c:forEach>
</table>
<c:if test="${users == null}">
    <p>User list is empty</p>
</c:if>
<c:if test="${role != 'guest'}">
    <a href="${pageContext.servletContext.contextPath}/UserCreateServlet">Create a new user</a>
    <form action="${pageContext.servletContext.contextPath}/UsersController" method="post">
        <p>
            <button name="exist" type="hidden" value="${user.id}">Exist</button>
        </p>
    </form>
</c:if>
<c:if test="${role == 'guest'}">
    <a href="${pageContext.servletContext.contextPath}/UserCreateServlet">Create a new user</a>
    <form action="${pageContext.servletContext.contextPath}/UsersController" method="post">
        <p>
            <button name="exist" type="hidden" value="${user.id}">Sign in</button>
        </p>
    </form>
</c:if>
</body>
</html>