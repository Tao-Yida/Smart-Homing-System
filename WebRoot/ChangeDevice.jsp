<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html lang="en">
    <head>
        <base href="<%=basePath%>">

        <title>Change outcome!</title>

        <meta content="no-cache" http-equiv="pragma">
        <meta content="no-cache" http-equiv="cache-control">
        <meta content="0" http-equiv="expires">
        <meta content="keyword1,keyword2,keyword3" http-equiv="keywords">
        <meta content="This is my page" http-equiv="description">
        <link href="loginStyle.css" rel="stylesheet" type="text/css">

    </head>

    <body class="img_1">
        <div class="warningPanel">
        <%String id=(String)session.getAttribute("d_name");int flag = (Integer)session.getAttribute("flag");%>
        <%if (flag==1) { %>
            <h1 class="warningMessage">Your device (name is <%=id %>) is changed successfully!</h1><br>
            <a class="warningButton" href="./welcome.jsp">Back</a>
        <%} %>
        <%if (flag==0) { %>
        	<h1 class="warningMessage">Your device (name is <%=id %>) is changed unsuccessfully!</h1><br>
        	<h1 class="warningMessage">Please fill in a correct state of the device.</h1><br>
            <a class="warningButton" href="./welcome.jsp">Return</a>
        <%} %>
        </div>
    </body>
</html>