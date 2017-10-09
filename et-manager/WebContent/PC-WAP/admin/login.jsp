<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>后台管理系统</title>

<!-- Bootstrap -->
<link href="/PC-WAP/admin/css/bootstrap.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body style="background-image: url('/PC-WAP/img/zhuoku050.jpg')">
	<div style="height: 200px"></div>
	<div class="container-fluid">
		<div class="row" style="background-image: /PC-WAP/img/zhuoku050.jpg">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<form id="loginForm" action="/admin.html" method="post"
					accept-charset="utf-8" enctype="application/x-www-form-urlencoded">
					<div class="form-group">
						<label for="exampleInputEmail1">邮箱地址</label> <input type="email"
							class="form-control" name="email" id="exampleInputEmail1"
							placeholder="请输入邮箱">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">密码</label> <input
							type="password" class="form-control" name="password"
							id="exampleInputPassword1" placeholder="请输入密码">
					</div>
					<div class="checkbox">
						<label> <input type="checkbox"> 记住密码
						</label>
					</div>
					<button type="submit" class="btn btn-default">登录</button>
					<button type="button" class="btn btn-default" data-toggle="modal"
						data-target="#myModal">注册</button>
				</form>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>
	<!-- 注册用户信息 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">请输入注册信息</h4>
				</div>
				<div class="modal-body">
					<form  action="addEmailUser.html" method="post"
						accept-charset="utf-8" enctype="application/x-www-form-urlencoded">
						<div class="form-group">
							<label for="exampleInputEmail1">邮箱地址</label> <input type="email"
								class="form-control" name="email" id="exampleInputEmail1"
								placeholder="请输入邮箱">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">密码</label> <input
								type="password" class="form-control" name="password"
								id="exampleInputPassword1" placeholder="请输入密码">
						</div>
						<div class="checkbox">
							<label> <input type="checkbox"> 记住密码
							</label>
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-default">注册</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<!-- <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script> -->
	<script src="/PC-WAP/admin/js/jquery-1.10.2.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="/PC-WAP/admin/js/bootstrap.js"></script>
	<script type="text/javascript">

		/* 		$(function() {
		 $("#addEmail").click(function(msg) {
		 $("#addEmailUser").submit();
		 });
		 }); */
	</script>
</body>
</html>