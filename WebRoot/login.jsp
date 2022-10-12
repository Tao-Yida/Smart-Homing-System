<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">

        <title>Login page</title>

        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <link rel="stylesheet" type="text/css" href="loginStyle.css">

    </head>

    <body class="img_1">
        <div class="panel">
            <div id="title">
                <h1>Welcome to Smart Homing System!</h1>
                <h2 style="text-align: center;margin-bottom: 0">Please Log in</h2></div>
            <form method="post" action="./login">

                <%
                    String lastusername = "";
                    String message = "";
                %>
                <%
                    if (session.getAttribute("flag") != null)
                        lastusername = (String) session.getAttribute("lastusername");
                        System.out.println(session.getAttribute("flag"));
                %>
                <%
                    if (session.getAttribute("flag") != null)
                    {
                        String flag = (String) session.getAttribute("flag");
                        if (flag.equals("1")) message = "Wrong password!";
                        else if (flag.equals("2"))
                        {
                            message = "Invalid username!";
                            lastusername = "";
                        }
                        else if (flag.equals("3"))
                        {
                            message = "Change password successfully!";
                        }
                        else if (flag.equals("4"))
                        {
                            message = "Change password unsucessfully!";
                        }
	        else if (flag.equals("6")) {
                        	message = "Register sucessfully!";
                        	lastusername = "";
                        }
                        else if (flag.equals("7")) {
	                        message = "Register unsucessfully!";
	                        lastusername = "";
                        }
                    }
                %>
                <div class="login">
                    <label>
                        <input type="text" name="username" class="input" placeholder="Username" value="<%=lastusername %>" />
                    </label><br/>
                    <label>
                        <input type="text" name="password" class="input" placeholder="Password"/>
                    </label><br/>
                    <span style="color: red;font-size: small"><%=message %>
                        </span>
                    <input type="SUBMIT" name="submit" value="Login" class="button">
                </div>
            </form>
            <div class="changeAndReg">
                <a href="./changepassword.jsp" style="text-decoration: none">Change password.</a>
                <br>
                <a href="./signIn.jsp" style="text-decoration: none">Don't have an account? Sign in.</a>
            </div>
        </div>
    </body>
</html>
