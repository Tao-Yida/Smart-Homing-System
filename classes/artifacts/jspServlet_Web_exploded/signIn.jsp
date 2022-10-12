<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">

        <title>My JSP 'signIn.jsp' starting page</title>

        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <link rel="stylesheet" type="text/css" href="loginStyle.css">

    </head>

    <body class="img_3">
        <div class="panel">
            <div id="title">
                <h1>Welcome to Smart Homing System!</h1>
                <h2 style="text-align: center;margin-bottom: 0">Please Create a new account</h2></div>
            <form method="post" action="./signIn">
                <div class="register">
                    <div>
                        <label>
                            <input type="text" name="u_name" class="input" placeholder="Username">
                        </label>
                        <label>
                            <input type="text" name="password" class="input" placeholder="Password">
                        </label><br/>
                    </div>
                    <div>
                        <label>
                            <input type="text" name="phone" class="input" placeholder="Phone Number">
                        </label>
                        <label>
                            <input type="text" name="email" class="input" placeholder="Email">
                        </label><br/>
                    </div>
                    <div>
                    	<label>
                            <input type="text" name="familyname" class="input" placeholder="Family Name">
                        </label>
                    </div>
                    <input type="SUBMIT" name="submit" value="Register" class="button">
                </div>
            </form>
        </div>
    </body>
</html>
