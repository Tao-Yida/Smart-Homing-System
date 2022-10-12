<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
    <head>
        <base href="<%=basePath%>">

        <title>Return page</title>

        <meta content="no-cache" http-equiv="pragma">
        <meta content="no-cache" http-equiv="cache-control">
        <meta content="0" http-equiv="expires">
        <meta content="keyword1,keyword2,keyword3" http-equiv="keywords">
        <meta content="This is my page" http-equiv="description">
        <link href="loginStyle.css" rel="stylesheet" type="text/css">

    </head>

    <body class="img_1">
        <div class="warningPanel">
            <div>
                
                <%if(session.getAttribute("flag").equals("8")) { %>
                
                <h1 class="warningMessage">Add Devices successfully!</h1><br><br><br><br>

                <%} else { %>
                

                <h1 class="warningMessage"> Add Devices Unsuccessfully! <br></h1>
                <h1 class="warningMessage">Please Return back to Try Again! <br><br></h1>
                
                <%} %>
                
                <div>
                    <a class="warningButton" href="./welcome.jsp">Back</a>
                </div>
            </div>
        </div>
    </body>
</html>