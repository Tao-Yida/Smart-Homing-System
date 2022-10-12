<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
    <head>
        <base href="<%=basePath%>">

        <title>Warning!</title>

        <meta content="no-cache" http-equiv="pragma">
        <meta content="no-cache" http-equiv="cache-control">
        <meta content="0" http-equiv="expires">
        <meta content="keyword1,keyword2,keyword3" http-equiv="keywords">
        <meta content="This is my page" http-equiv="description">
        <link href="loginStyle.css" rel="stylesheet" type="text/css">

    </head>

    <body class="img_1">
        <div class="warningPanel">
        <%String id=(String)session.getAttribute("Warn"); %>
            <h1 class="warningMessage">Your door (name is <%=id %>) is not at the correct state!</h1><br>
            <a class="warningButton" href="./welcome.jsp">Back</a>
        </div>

    </body>
</html>
