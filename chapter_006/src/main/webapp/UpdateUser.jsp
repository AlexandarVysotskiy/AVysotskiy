<%@ page import="crud.UserUpdateServlet" %>
<%@ page import="crud.User" %>
<%@ page import="crud.Validate" %>
<%@ page import="crud.ValidateService" %><%--
  Created by IntelliJ IDEA.
  User: Sanek
  Date: 18.12.2018
  Time: 0:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%final Validate storage = ValidateService.getInstance();%>
<table>
        <%int id = Integer.valueOf(request.getParameter("id"));%>
        <%User user = storage.findById(id);%>
    <tr>
        <td>Parameter's user: <%=user.getLogin()%>
            <form action="<%=request.getContextPath()%>/UserUpdateServlet" method="post">
            Name : <input type = "text" value=" <%=user.getName()%> " name="name"/>
            Login : <input type = "text" value=" <%=user.getLogin()%>" name="login"/>
            Email : <input type = "text" value=" <%=user.getEmail()%>" name="email"/>
                    <input type = "hidden" value="<%=id%>" name="id"/>
            <input type = "submit">
            </form>
        </td></tr>
        <a href="<%=request.getContextPath()%>/index.jsp">Return to list of user</a>
</body>
</html>
