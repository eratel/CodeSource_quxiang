<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>详情</title>
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="/PC-WAP/css/amazeui.min.css">
<link rel="stylesheet" href="/PC-WAP/css/petshow.css">
<link rel="stylesheet" href="/PC-WAP/css/animate.min.css">
<script src="/PC-WAP/js/jquery.min.js"></script>
<script src="/PC-WAP/js/amazeui.min.js"></script>
<script src="/PC-WAP/js/amazeui.lazyload.min.js"></script>


</head>
<body>
	<header class="am-topbar am-topbar-inverse">
		<div class="amz-container">
			<h1 class="am-topbar-brand">
				<a href="#" class="am-topbar-logo"> <img
					src="/PC-WAP/img/logo.png?1" alt="">
				</a>
			</h1>
			<button
				class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
				data-am-collapse="{target: '#doc-topbar-collapse-5'}">
				<span class="am-sr-only"> 导航切换 </span> <span class="am-icon-bars">
				</span>
			</button>
			<div class="am-collapse am-topbar-collapse"
				id="doc-topbar-collapse-5">
				<ul class="am-nav am-nav-pills am-topbar-nav">
					<li class="am-active"><a href="#"> 首页 </a></li>
					<li class="am-dropdown" data-am-dropdown=""><a
						class="am-dropdown-toggle" data-am-dropdown-toggle=""
						href="javascript:;"> 发现萌宠 <span class="am-icon-caret-down">
						</span>
					</a>
						<ul class="am-dropdown-content">
							<li><a href="#"> 编辑推荐 </a></li>
							<li><a href="#"> 人气排行 </a></li>
							<li><a href="#"> 最新发布 </a></li>
							<li><a href="#"> 语音涂鸦 </a></li>
						</ul></li>
					<li><a href="#"> 萌宠趣闻 </a></li>
					<li><a href="#"> 萌宠专题 </a></li>
					<li class="am-dropdown" data-am-dropdown=""><a
						class="am-dropdown-toggle" data-am-dropdown-toggle=""
						href="javascript:;"> 萌宠服务 <span class="am-icon-caret-down">
						</span>
					</a>
						<ul class="am-dropdown-content">
							<li><a href="#"> 宠物医院 </a></li>
							<li><a href="#"> 宠物美容 </a></li>
							<li><a href="#"> 宠物店铺 </a></li>
							<li><a href="#"> 更多服务 </a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</header>
	<!-- 下面就是主要为详情信息 -->
	<div class="am-g bdfw_nr">
		<ol class="am-breadcrumb bdfw_link">
			<li><a href="/" class="am-icon-home">首页</a></li>
			<li><a href="#">${memory.ADRESS}</a></li>
			<li class="am-active">${memory.NAME}</li>
		</ol>
		<div class="am-u-sm-12">
			<div class="bdfw_nr_block">
				<div class="bdfw_nr_block_l">
					<div class="bdfw_nr_title">${memory.NAME}</div>
					<div class="bdfw_nr_brief_info">
						<span class="bdfw_nr_brief_star"><img
							src="/PC-WAP/img/xx.png" alt=""><img
							src="/PC-WAP/img/xx.png" alt=""><img
							src="/PC-WAP/img/xx.png" alt=""><img
							src="/PC-WAP/img/xx.png" alt=""><img
							src="/PC-WAP/img/xx.png" alt=""></span> <span
							class="bdfw_nr_brief_moreinfo">地区:${memory.ADRESS}</span>
					</div>
					<div class="bdfw_nr_brief_address">地址：东城区 鼓楼东大街275号</div>
					<div class="bdfw_nr_brief_address">电话：********</div>
					<div class="bdfw_nr_smalltitle">更多信息</div>
					<div class="bdfw_nr_brief_info_nr">
						<div class="bdfw_nr_brief_info_nr">
							<span>时间:</span> <span>2017年</span>
						</div>
						<div class="bdfw_nr_brief_info_nr">
							<span>描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述:</span> <span>${memory.DESCPTION}</span>
						</div>
					</div>
				</div>

				<div class="bdfw_nr_block_r">
					<a href="###"><img src="/pages/img/${memory.COVER_ONE}" alt=""></a>
					<div class="bdfw_nr_block_r_info">
						<span><i class="am-icon-camera"></i> 美景（12）</span>
					</div>
				</div>
				<a href="tel:18309826655"
					class="am-btn am-btn-default am-round am-btn-success bdfw_tel">拨打电话</a>
			</div>
		</div>

	</div>
	<footer class="am_footer">
		<div class="am_footer_con">
			<div class="am_footer_link">
				<span>关于我的去向</span>
				<ul>
					<li><a href="###">关于我们</a></li>
					<li><a href="###">发展历程</a></li>
					<li><a href="###">友情链接</a></li>
				</ul>
			</div>


			<div class="am_footer_don">
				<span>去向秀</span>
				<dl>
					<dt>
						<img src="/PC-WAP/img/footdon.png?1" alt="">
					</dt>
					<dd>
						一起Show我们的去向吧！去向秀是图片配文字、涂鸦、COSPLAY的移动手机社区，这里有各种去向和旅途中各种趣事。 <a
							href="###" class="footdon_pg ">
							<div class="foot_d_pg am-icon-apple ">App store</div>
						</a><a href="###" class="footdon_az animated">
							<div class="foot_d_az am-icon-android ">Android</div>
						</a>
					</dd>

				</dl>
			</div>

			<div class="am_footer_erweima">
				<div class="am_footer_weixin">
					<img src="/PC-WAP/img/wx.jpg" alt="">

					<div class="am_footer_d_gzwx am-icon-weixin">关注微信</div>
				</div>
				<div class="am_footer_ddon">
					<img src="/PC-WAP/img/wx.jpg" alt="">

					<div class="am_footer_d_dxz am-icon-cloud-download">扫码下载</div>
				</div>

			</div>

		</div>
		<div class="am_info_line">
			Copyright(c)2015 <span>PetShow</span> All Rights Reserved
		</div>
	</footer>
	<script>
		$(function() {
			auto_window_height();
			$(window).resize(function() {
				auto_window_height();
			});
			function auto_window_height() {
				$('.bdfw_nr').css('min-height', $(window).height() - 52 - 220);
			}
		});
	</script>
</body>
</html>
















