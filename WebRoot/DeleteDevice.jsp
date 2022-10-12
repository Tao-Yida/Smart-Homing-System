<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html lang="en">
    <head>
        <base href="<%=basePath%>">

        <title>Successfully!</title>

        <meta content="no-cache" http-equiv="pragma">
        <meta content="no-cache" http-equiv="cache-control">
        <meta content="0" http-equiv="expires">
        <meta content="keyword1,keyword2,keyword3" http-equiv="keywords">
        <meta content="This is my page" http-equiv="description">
        <link href="loginStyle.css" rel="stylesheet" type="text/css">

    </head>

    <body class="img_1">
        <div class="warningPanel">
        <%String id=(String)session.getAttribute("d_name"); %>
            <h1 class="warningMessage">Your device (name is <%=id %>) is deleted successfully!</h1><br>
            <a class="warningButton" href="./welcome.jsp">Back</a>
        </div>

    </body>
</html>