<%@ page import="crud.User" %>
<%@ page import="crud.ValidateService" %>
<%@ page import="crud.UserError" %>
<%@ page import="crud.Validate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of users</title>
</head>
<body>
<%final Validate storage = ValidateService.getInstance();%>
<table>
    <%try {%>
    <%for (User user : storage.findAll()) {%>

    <tr>
        <td>User login: <%=user.getLogin()%>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/UserServlet" method="post">
                <p>
                    <button name="id" type="hidden" value="<%=storage.getId(user)%>">Delete</button>
                </p>
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/UpdateUser.jsp?id=<%=storage.getId(user)%>">
                <a href="<%=request.getContextPath()%>/UpdateUser.jsp?id=<%=storage.getId(user)%>">Update</a>
            </form>
        </td>

    </tr>
    <%}%>
</table>
<%} catch (UserError u) {%>
<br>User list is empty</br>
<%
        u.printStackTrace();
    }
%>
<a href="<%=request.getContextPath()%>/CreateNewUser.jsp">Create a new user</a>
</body>
</html>