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
<body>
	<!-- 导航条 -->
	<div class="container">
		<div class="page-header">
			<div class="container">
				<h1>
					欢迎使用去向网后台管理网站 <small>后台管理</small>
				</h1>
			</div>
		</div>
		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/PC-WAP/admin/admin.jsp"><b>去向</b></a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a data-toggle="modal" data-target="#insertUsers">添加用户</a></li>
						<li><a data-toggle="modal" data-target="#insetMemory">添加回忆</a></li>
						<li><a data-toggle="modal" id="deleteMemoryByID">删除回忆</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">数据操作<span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#">添加</a></li>
								<li><a href="#">删除</a></li>
								<li><a href="#">修改</a></li>
								<li class="divider"></li>
								<li><a href="#">其他</a></li>
								<li class="divider"></li>
							</ul></li>
					</ul>
					<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<input type="text" id="placeHolder" class="form-control"
								placeholder="请填入名字">
						</div>
						<button type="button" class="btn btn-default"
							onclick="googleName()">GO!</button>
					</form>

					<div id="tbPageNumDIV" class="nav navbar-nav navbar-right">
						<nav>
							<ul class="pagination" id="tbPageNum">
							</ul>
						</nav>
					</div>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
		<div class="row" style="height: 400px">
			<table
				class="table table-condensed,table,table-striped,table table-hover">
				<thead>
					<tr>
						<td class="col-md-2">序号</td>
						<td class="col-md-2">参与人</td>
						<td class="col-md-2">参与地点</td>
						<td class="col-md-2">简单描述</td>
						<td class="col-md-2">时间</td>
						<td class="col-md-2">也许会用到(在某天)</td>
					</tr>
				</thead>
				<tbody id="tbodyMemory">
				</tbody>
			</table>
		</div>
	</div>
	<!-- 导航条结束 -->
	<!-- 表格 -->


	<!-- 表格 -->
	<!-- 添加摸态框 用户-->
	<div class="modal fade" id="insertUsers" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Modal title</h4>
				</div>
				<div class="modal-body">
					<!-- form 表单 -->

					<form role="form" id="myForm">
						<div class="form-group">
							<label for="exampleInputEmail1">Email address</label> <input
								type="email" class="form-control" id="exampleInputEmail1"
								placeholder="Enter email">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Password</label> <input
								type="password" class="form-control" id="exampleInputPassword1"
								placeholder="Password">
						</div>
						<div class="form-group">
							<label for="exampleInputFile">File input</label> <input
								type="file" id="exampleInputFile">
							<p class="help-block">Example block-level help text here.</p>
						</div>
						<div class="checkbox">
							<label> <input type="checkbox"> Check me out
							</label>
						</div>
						<button type="submit" id="submitB" class="btn btn-default">Submit</button>
					</form>

					<!-- form 表单 -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 添加摸态框结束 -->
	<!-- 添加摸态框 用户-->
	<div class="modal fade" id="insetMemory" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加回忆</h4>
				</div>
				<div class="modal-body">
					<!-- form 表单 -->

					<form role="form" id="memoryForm" method="post"
						action="/insertMemory" enctype="multipart/form-data">
						<div class="form-group">
							<label for="NAME_MENORY">参与人</label> <input type="text"
								class="form-control" name="NAME" id="NAME_MENORY"
								placeholder="谁去了呢" />
						</div>
						<div class="form-group">
							<label for="ADRESS">参与地点</label> <input type="text"
								class="form-control" name="ADRESS" id="ADRESS"
								placeholder="去哪了呢" />
						</div>
						<div class="form-group">
							<label for="DESCPTION">简单描述</label> <input type="text"
								class="form-control" name="DESCPTION" id="DESCPTION"
								placeholder="有什么想说的" />
						</div>
						<div class="form-group">
							<label for="exampleInputFile">印象派</label> <input type="file"
								id="exampleInputFile" name="fileCover" />
							<p class="help-block">保存一张设为封面！</p>
						</div>
						<div class="checkbox">
							<label> <input type="checkbox" /> 选择我
							</label>
						</div>
						<!-- <button type="submit" id="submitB" class="btn btn-default">Submit</button> -->
					</form>
					<!-- form 表单 -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">我要独享</button>
					<button type="button" id="submitMemory" class="btn btn-primary">分享我的回忆</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 添加摸态框结束 -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<!-- <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script> -->
	<script src="/PC-WAP/admin/js/jquery-1.10.2.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="/PC-WAP/admin/js/bootstrap.js"></script>
	<script type="text/javascript">
		$(function() {
			//初始化
			runOnload(null, 1);
			//初始化获得page数量
			SelectPageNum(null);
			//添加css 特性
			addCssAll();
		});

		//能够提交form
		$("button#submitMemory").click(function() {
			$("#memoryForm").submit();
		});

		//初始化页面    拼接出来table
		function runOnload(goName, page) {
			//alert("onload__" + "goName:" + goName + "page:" + page);
			$.post("/seletAllMemoryWork", {
				name : goName,
				pageNum : page,
				address : "",
				desc : ""
			}, function(date) {
				$("#tbodyMemory").empty();
				//console.log(date);
				$(date).each(
						function(i, m) {
							var dual = "<tr class = 'active' onclick= 'getID("
									+ m.id
									+ ", this )'>"
									+ "<td class='col-md-2'>"
									+ (parseInt(12)
											* (parseInt(parseInt(page)
													- parseInt(1)))
											+ parseInt(i) + parseInt(1))
									+ "</td>" + "<td class='col-md-2'>"
									+ m.name + "</td>"
									+ "<td class='col-md-2'>" + m.adress
									+ "</td>" + "<td class='col-md-2'>"
									+ m.descption + "</td>"
									+ "<td class='col-md-2'>--</td>"
									+ "<td class='col-md-2'>--</td>" + "</tr>"
							$("#tbodyMemory").append(dual);
						});
				name = "";
			});
		};

		//初始化页数  
		var pageAll;
		function SelectPageNum(name) {
			//alert("PageNum___"+"name:"+name);
			$("#tbPageNum").empty();
			$.post("/selectMemoryCount", {
				name : name,
				address : null,
				desc : null
			}, function(date) {
				pageAll = date;
				$("#tbPageNum").append("<li><a href='#'>&laquo;</a></li>");
				for (var i = 1; i <= date; i++) {
					var p = "<li><a onclick = 'runPage(" + i + ")'>" + i
							+ "</a></li>";
					$("#tbPageNum").append(p);
				}
				$("#tbPageNum").append("<li><a href='#'>&raquo;</a></li>");
			}, "json");
		};

		function addCssAll(){
			$("div[id='tbPageNumDIV']").css("margin","-15px");
			//$("body").css();
		}
		
		//翻页  将page 值改变
		function runPage(runp) {
			runOnload(name, runp);
		}

		var name;
		//根据Name搜索 信息 
		function googleName() {
			name = getPCName();
			runOnload(name, 1);
		}

		//获得文本框的值
		function getPCName() {
			var goName = $("#placeHolder").val();
			return goName;
		}

		// 删除一条数据
		var mid1;
		$("#deleteMemoryByID").click(function() {
			if (mid1 == null) {
				return;
			}
			$.post("/deleteMemoryByID", {
				id : mid1
			}, function(status) {
				if (status == "successful") {
					runOnload(null, 1);
					alert("删除成功");
				} else {
					alert("删除失败");
				}

			}, "text");
		});

		//点击之后获得ID 就可以方便删除点击的数据
		var pre;
		function getID(mid, a) {
			mid1 = mid;
			if (a == pre) {
				$(a).attr("class", "info");
				$(pre).attr("class", "active");
				mid1 = null;
			} else {
				$(a).attr("class", "info");
				$(pre).attr("class", "active");
			}
			if (pre != a) {
				pre = a;
			}
		};
	</script>
</body>
</html>