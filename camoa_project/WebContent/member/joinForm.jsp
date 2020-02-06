<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" ;  charset="utf-8">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">

<!-- Website CSS style -->
<link rel="stylesheet" type="text/css" href="assets/css/main.css">

<!-- Website Font style -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

<!-- Google Fonts -->
<link href='https://fonts.googleapis.com/css?family=Passion+One'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oxygen'
	rel='stylesheet' type='text/css'>


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="../js/check.js"></script>
<link rel="stylesheet" type="text/css" href="../css/camoaCategory.css">

<title>회원가입</title>
</head>
<body>
	<style type="text/css">
body, html {
	height: 100%;
	background-repeat: no-repeat;
	/* background-color: #F2F2F2; */
	font-family: 'Oxygen', sans-serif;
}

.main {
	/* margin-top: 70px; */
	
}

h1.title {
	font-size: 50px;
	font-family: 'Passion One', cursive;
	font-weight: 400;
}

hr {
	width: 10%;
	color: #fff;
}

.form-group {
	margin-bottom: 15px;
}

label {
	margin-bottom: 15px;
}

input, input::-webkit-input-placeholder {
	font-size: 11px;
	padding-top: 3px;
}

.main-login {
	background-color: #fff;
	/* shadows and rounded borders */
	-moz-border-radius: 2px;
	-webkit-border-radius: 2px;
	border-radius: 2px;
	-moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	-webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.main-center {
	margin-top: 30px;
	margin: 0 auto;
	max-width: 330px;
	padding: 40px 40px;
}

.login-button {
	margin-top: 5px;
}

.login-register {
	font-size: 11px;
	text-align: center;
}

.redText {
	display: block;
	color: red;
	margin-left: 10px;
}

.greenText {
	display: block;
	color: green;
	margin-left: 10px;
}
</style>

	<div class="container">
		<div class="row main">
			<div class="panel-heading">
				<div class="panel-title text-center">
					<h1 class="title">Join</h1>
					<hr />
				</div>
			</div>
			<div class="main-login main-center">
				<form class="form-horizontal" method="post"
					action="./../userRegister">

					<div class="form-group">
						<label for="name" class="cols-sm-2 control-label">Your ID
							<button class="cols-sm-10" onclick="registerCheckFunction();"
								type="button">Duplicate check</button>
						</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user fa"
									aria-hidden="true"></i></span> <input type="text" class="form-control"
									name="userId" id="userId" maxlength="20"
									placeholder="Enter your ID" />
							</div>
							<span id="idSpan" class="redText"> </span>
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="cols-sm-2 control-label">Password</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-lock fa-lg" aria-hidden="true"></i></span> <input
									type="password" class="form-control" name="userPwd1"
									id="userPwd1" maxlength="20" placeholder="Enter your Password" />
							</div>
							<span id="pwdSpan" class="redText"> </span>
						</div>
					</div>

					<div class="form-group">
						<label for="confirm" class="cols-sm-2 control-label">Confirm
							Password</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-lock fa-lg" aria-hidden="true"></i></span> <input
									type="password" class="form-control" name="userPwd2"
									id="userPwd2" maxlength="20"
									placeholder="Confirm your Password" />
							</div>
							<span id="pwdSpan2" class="redText"> </span>
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="cols-sm-2 control-label">Your
							Email</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-envelope fa" aria-hidden="true"></i></span> <input
									type="email" class="form-control" name="email" maxlength="40"
									placeholder="Enter your Email" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="username" class="cols-sm-2 control-label">Name</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-users fa"
									aria-hidden="true"></i></span> <input type="text" class="form-control"
									name="userName" maxlength="20" placeholder="Enter your name" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="username" class="cols-sm-2 control-label">PhoneNum</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-users fa"
									aria-hidden="true"></i></span> <input type="text" class="form-control"
									id="userNum" name="userNum" maxlength="20"
									placeholder="Enter your PhoneNum" />
							</div>
							<span id="numSpan" class="redText"> </span>
						</div>

						<input type="submit" class="btn btn-primary form-control"
							id="submit" value="Register">
				</form>
			</div>
		</div>
	</div>
	<%
		String messageContent = null;
		String messageType = null;
		if (session.getAttribute("messageContent") != null) {
			messageContent = (String) session.getAttribute("messageContent");
		}
		if (session.getAttribute("messageType") != null) {
			messageType = (String) session.getAttribute("messageType");
		}
		if (messageContent != null) {
	%>
	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div
					class="modal-content
					<%if (messageType.equals("오류메세지"))
					out.println("panel-warning");
				else
					out.println("panal-success");%> ">
					<div class="modal-header panal-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">
							<%=messageType%></h4>
					</div>
					<div class="modal-body">
						<%=messageContent%>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('#messageModal').modal("show");
	</script>
	<%
		session.removeAttribute("messageContent");
			session.removeAttribute("messageType");
		}
	%>
	<div class="modal fade" id="checkModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div class="modal-content panel-info">
					<div id="checkType" class="modal-header panal-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">확인메세지</h4>
					</div>
					<div class="modal-body" id="checkMessage"></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>