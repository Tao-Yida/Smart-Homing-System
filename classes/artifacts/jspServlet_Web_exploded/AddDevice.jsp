<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
    <head>
        <base href="<%=basePath%>">

        <title>Add devices page</title>

        <meta content="no-cache" http-equiv="pragma">
        <meta content="no-cache" http-equiv="cache-control">
        <meta content="0" http-equiv="expires">
        <meta content="keyword1,keyword2,keyword3" http-equiv="keywords">
        <meta content="This is my page" http-equiv="description">
        <link href="loginStyle.css" rel="stylesheet" type="text/css">

    </head>

    <body class="img_3">
        <div class="panel">
            <h1 id="title" style="font-size: 50px">Please Add a Device</h1>
            <form action="./add" method="post">
                <div class="mainFrame">
                    <div class="selectType">
                        <label>
                            <input name="type" type="radio" value="Light"> Light
                        </label>
                        <label>
                            <input name="type" type="radio" value="Window"> Window
                        </label>
                        <label>
                            <input name="type" type="radio" value="Door"> Door
                        </label>
                    </div>
                    <div class="addText">
                        <div>
                            <label>
                                Name:
                                <input class="modifyInput" name="newname" style="margin-top: 10px;margin-left:1em"
                                       type="text">
                            </label>
                        </div>
                        <div>
                            <label>
                                Information:
                                <input class="modifyInput" name="information" style="margin-top: 10px;margin-left:1em"
                                       type="text">
                            </label>
                        </div>
                        <div>
                            <label>
                                Manufacturer:
                                <input class="modifyInput" name="Manufacturer"
                                       style="margin-top: 10px;margin-left:1em"
                                       type="text">
                            </label>
                        </div>
                        <div>
                            <label>
                                Family:
                                <input class="modifyInput" name="Family" style="margin-top: 10px;margin-left:1em"
                                       type="text">
                            </label>
                        </div>
                    </div>
                </div>
                <input class="button" name="submit" type="SUBMIT" value="Add">
            </form>
        </div>
    </body>
</html>
