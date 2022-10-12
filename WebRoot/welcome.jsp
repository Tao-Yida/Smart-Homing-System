<%@ page language="java" import="java.util.*,jspservlet.vo.*"
	pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<base href="<%=basePath%>">

<title>Main page</title>

<meta content="no-cache" http-equiv="pragma">
<meta content="no-cache" http-equiv="cache-control">
<meta content="0" http-equiv="expires">
<meta content="keyword1,keyword2,keyword3" http-equiv="keywords">
<meta content="This is my page" http-equiv="description">
<META HTTP-EQUIV=“Refresh” CONTENT=“120;URL=http://www.baidu.com”>
<link href="loginStyle.css" rel="stylesheet" type="text/css">

</head>

<body class="img_1">
	<div class="userPanel">
		<%
			String username = (String) session.getAttribute("username");
		%>
		<h1 class="userTitle">
			Welcome
			<%=username%></h1>
		<h2 class="userSubTitle">~Home page of smart home system~</h2>
		<form action="./showequ" method="post">
			<input id="refresh" name="submit" type="SUBMIT" value="Refresh">
		</form>
	</div>
	<div class="mainFrame">
		<form id="show" method="post" action="./showequ">
			<script language="JavaScript">
				setInterval("show.submit();", 60000);
			</script>
		</form>
		<h3 class="userSubTitle"
			style="font-family:courier,serif; color: black">
			<a href="./AddDevice.jsp" style="text-decoration: none;color:black">Add a new
				device!</a>
		</h3>
		<%
			ArrayList<Equipment> list = (ArrayList<Equipment>) session.getAttribute("equList");
		%>
		<%
			Window[] list_window = new Window[100];
			for (int j = 0; j < 100; j++)
				list_window[j] = new Window();
		%>
		<%
			Door[] list_door = new Door[100];
			for (int j = 0; j < 100; j++)
				list_door[j] = new Door();
		%>
		<%
			int[] check = new int[1000];
		%>
		<%
			if (session.getAttribute("last_d_state") != null) {
				list_door = (Door[]) session.getAttribute("last_d_state");
			}
		%>
		<%
			if (session.getAttribute("last_w_state") != null) {
				list_window = (Window[]) session.getAttribute("last_w_state");
			}
		%>
		<%
			if (session.getAttribute("check") != null) {
				check = (int[]) session.getAttribute("check");
				for (int j = 0; j < 8; j++)
					System.out.println(">" + check[j] + "<");
			}
		%>

		<%
			if (list != null) {
		%>

		<%
			int size = list.size();
		%>
		<br>
		<h3 class="userSubTitle">
			Total amount of devices :
			<%=size%>.
		</h3>
		<br>
		<div style="display:flex; width:auto; margin-left:auto">
			<div style="margin-left:auto;width:50%">
				<h3 class="userSubTtile">
					<form method="post" action="./search">
						<input class="input"
							style="margin-top:0; height:50px; margin-bottom:20px; margin-left:20%"
							name="d_name" type="text"> <input name="flag"
							type="hidden" value="0"> <input style="width:200px"
							class="search" name="submit" type="SUBMIT" value="Search by name">
					</form>
				</h3>
			</div>
			<div style="margin-right:auto; width:50%">
				<h3 class="userSubTitle">
					<form method="post" action="./search">
						<input type="radio" name="type" value="Light"> Light
						&nbsp; <input type="radio" name="type" value="Door"> Door
						&nbsp; <input type="radio" name="type" value="Window">
						Window &nbsp; <input name="flag" type="hidden" value="1">
						<input style="margin-top:40px; width:200px" class="search"
							name="submit" type="SUBMIT" value="Search by type">
					</form>
				</h3>
			</div>
		</div>
		<%
			int i;
				String f_name;
				int hu;
				int tm;
				int[] family = new int[1000];
				for (int j = 0; j < 999; j++)
					family[j] = 0;
		%>
		<%
			String information;
				int bright;
				String l_state;
				String d_state;
				String w_state;
				String b_state;
				String s_state;
				String d_name;
		%>
		<%
			for (i = 0; i < list.size(); i++) {
		%>

		<%
			if (family[list.get(i).getF_ID()] == 0) {
						family[list.get(i).getF_ID()] = 1;
						hu = list.get(i).getHumidity();
						tm = list.get(i).getTemperature();
						f_name = list.get(i).getF_Name();
		%>
		<div class="family">


			<h3 style="margin-top:20px;font-size: 30px;text-align: center">
				Family :
				<%=f_name%></h3>
			<div class="status">
				<div class="column1">
					<span>Humidity : <%=hu%></span>
				</div>
				<div class="column2">
					<span>Temperature : <%=tm%></span>
				</div>
			</div>
		</div>
		<%
			}
		%>

		<%
			if (list.get(i).getType().equals("Light")) {
						Light L = (Light) list.get(i);
						int ID = L.getD_ID();
						d_name = L.getD_Name();
						bright = L.getBright();
						l_state = L.getL_state();
						information = L.getInformation();
		%>
		<div class="device">
			<div class="information">
				<div class="userSubTitle">
					<span><img src="./1.jpg" /></span><br>
				</div>
				<div class="column1">
					<span>&nbsp; Name : <%=d_name%>
					</span><br> <br> <span>&nbsp; Bright : <%=bright%>
					</span>
				</div>
				<div class="column2">
					<span> Information : <%=information%></span><br> <br> <span>
						ON/OFF : <%=l_state%>
					</span>
				</div>
			</div>


			<form action="./light" method="post" style="margin-bottom: 15px">
				<div class="modification">
					<div class="column1">
						<label> &nbsp;Set bright : <input class="modifyInput"
							name="bright" placeholder="brightness" type="text">
						</label>
					</div>
					<div class="column2">
						<label> ON/OFF : <label style="margin-right: 0"> <input
								name="l_state" type="radio" value="on">
						</label> / <label style="margin-right: 0"> <input name="l_state"
								type="radio" value="off">
						</label> <input name="ID" type="hidden" value="<%=ID%>"> <input name="d_name" type="hidden" value="<%=d_name%>">
						</label>
					</div>
				</div>
				<input class="change" name="submit" type="SUBMIT" value="Change">
			</form>
			<form action="./delete" method="post" style="margin-bottom: 15px">
				<input name="ID" type="hidden" value="<%=ID%>"> <input name="d_name" type="hidden" value="<%=d_name%>"> <input
					class="delete" name="submit" type="SUBMIT" value="Delete">
			</form>
			<br />
		</div>
		<%
			}
		%>

		<%
			if (list.get(i).getType().equals("Door")) {
						Door D = (Door) list.get(i);
						int ID = D.getD_ID();
						d_name = D.getD_Name();
						d_state = D.getD_state();
						information = D.getInformation();
						System.out.println(check[ID]);
						list_door[ID].setD_state(d_state);
		%>
		<div class="device">
			<div class="information">
				<div class="userSubTitle">
					<span><img src="./2.jpg" /></span><br>
				</div>
				<div class="column1">
					<span>&nbsp; Name : <%=d_name%>
					</span><br> <br> <span>&nbsp; Information : <%=information%>
					</span>
				</div>
				<div class="column2">
					<span> OPEN/CLOSE : <%=d_state%>
					</span><br> <br>
				</div>
			</div>
			<form action="./door" method="post" style="margin-bottom: 15px">
				<div class="modification">
					<label> &nbsp;OPEN/CLOSE : <label style="margin-right: 0">
							<input name="d_state" type="radio" value="open">
					</label> / <label style="margin-right: 0"> <input name="d_state"
							type="radio" value="close">
					</label>
					</label> <input name="ID" type="hidden" value="<%=ID%>"> <input name="d_name" type="hidden" value="<%=d_name%>">
				</div>
				<input class="change" name="submit" type="SUBMIT" value="Change">
			</form>

			<form action="./delete" method="post" style="margin-bottom: 15px">
				<input name="ID" type="hidden" value="<%=ID%>"> <input name="d_name" type="hidden" value="<%=d_name%>"> <input
					class="delete" name="submit" type="SUBMIT" value="Delete">
			</form>
			<br />
		</div>
		<%
			}
		%>

		<%
			if (list.get(i).getType().equals("Window")) {
						Window W = (Window) list.get(i);
						int ID = W.getD_ID();
						d_name = W.getD_Name();
						w_state = W.getW_state();
						information = W.getInformation();
						
		%>
		<div class="device">
			<div class="information">
				<div class="userSubTitle">
					<span><img src="./3.jpg" /></span><br>
				</div>
				<div class="column1">
					<span>&nbsp; Name : <%=d_name%>
					</span><br> <br> <span>&nbsp; Information : <%=information%>
					</span>
				</div>
				<div class="column2">
					<span> OPEN/CLOSE : <%=w_state%>
					</span><br> <br>
				</div>
			</div>
			<form action="./window" method="post" style="margin-bottom: 15px">
				<div class="modification">
					<label> &nbsp;OPEN/CLOSE : <label style="margin-right: 0">
							<input name="w_state" type="radio" value="open">
					</label> / <label style="margin-right: 0"> <input name="w_state"
							type="radio" value="close">
					</label>
					</label> <input name="ID" type="hidden" value="<%=ID%>"> <input name="d_name" type="hidden" value="<%=d_name%>">
				</div>
				<input class="change" name="submit" type="SUBMIT" value="Change">
			</form>

			<form action="./delete" method="post" style="margin-bottom: 15px">
				<input name="ID" type="hidden" value="<%=ID%>"> <input name="d_name" type="hidden" value="<%=d_name%>"> <input
					class="delete" name="submit" type="SUBMIT" value="Delete">
			</form>
			<br />
		</div>
		<%
			}
			
			if (list.get(i).getType().equals("Buzzer")) {
						Buzzer B = (Buzzer) list.get(i);
						int ID = B.getD_ID();
						d_name = B.getD_Name();
						b_state = B.getB_state();
						information = B.getInformation();
						
		%>
		<div class="device">
			<div class="information">
				<div class="userSubTitle">
					<span><img src="./3.jpg" /></span><br>
				</div>
				<div class="column1">
					<span>&nbsp; Name : <%=d_name%>
					</span><br> <br> <span>&nbsp; Information : <%=information%>
					</span>
				</div>
				<div class="column2">
					<span> ON/OFF : <%=b_state%>
					</span><br> <br>
				</div>
			</div>
			<form action="./window" method="post" style="margin-bottom: 15px">
				<div class="modification">
					<label> &nbsp;ON/OFF : <label style="margin-right: 0">
							<input name="w_state" type="radio" value="open">
					</label> / <label style="margin-right: 0"> <input name="w_state"
							type="radio" value="close">
					</label>
					</label> <input name="ID" type="hidden" value="<%=ID%>"> <input name="d_name" type="hidden" value="<%=d_name%>">
				</div>
				<input class="change" name="submit" type="SUBMIT" value="Change">
			</form>

			<form action="./delete" method="post" style="margin-bottom: 15px">
				<input name="ID" type="hidden" value="<%=ID%>"> <input name="d_name" type="hidden" value="<%=d_name%>"> <input
					class="delete" name="submit" type="SUBMIT" value="Delete">
			</form>
			<br />
		</div>
		<%
			}

		if (list.get(i).getType().equals("Smoke")) {
						Smoke S = (Smoke) list.get(i);
						int ID = S.getD_ID();
						d_name = S.getD_Name();
						s_state = S.getS_state();
						information = S.getInformation();
						list_window[ID].setW_state(s_state);
		%>
		<div class="device">
			<div class="information">
				<div class="userSubTitle">
					<span><img src="./3.jpg" /></span><br>
				</div>
				<div class="column1">
					<span>&nbsp; Name : <%=d_name%>
					</span><br> <br> <span>&nbsp; Information : <%=information%>
					</span>
				</div>
				<div class="column2">
					<span> ON/OFF : <%=s_state%>
					</span><br> <br>
				</div>
			</div>
			<form action="./window" method="post" style="margin-bottom: 15px">
				<div class="modification">
					<label> &nbsp;ON/OFF : <label style="margin-right: 0">
							<input name="w_state" type="radio" value="open">
					</label> / <label style="margin-right: 0"> <input name="w_state"
							type="radio" value="close">
					</label>
					</label> <input name="ID" type="hidden" value="<%=ID%>"> <input name="d_name" type="hidden" value="<%=d_name%>">
				</div>
				<input class="change" name="submit" type="SUBMIT" value="Change">
			</form>

			<form action="./delete" method="post" style="margin-bottom: 15px">
				<input name="ID" type="hidden" value="<%=ID%>"> <input name="d_name" type="hidden" value="<%=d_name%>"> <input
					class="delete" name="submit" type="SUBMIT" value="Delete">
			</form>
			<br />
		</div>
		<%
			}

		}
				session.setAttribute("check", check);
				session.setAttribute("last_w_state", list_window);
				session.setAttribute("last_d_state", list_door);
			}
		%>

	</div>
</body>
</html>
