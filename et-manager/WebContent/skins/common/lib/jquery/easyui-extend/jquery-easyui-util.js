//提交json对象数据
$.postJson=function(url,data,callback) {
	$.ajax({
		url:url,
		type:'POST',
		contentType:'application/json',
		data:JSON.stringify(data||{}),
		dataType:'json',
		success:callback||$.noop
	});
};

/**
 * 显示提示信息--在右下角从下往上弹出信息窗口
 * @param {} text
 */
function showInfo(text,width,height) {
	var w=width||300;
	var h=height||130;
	$.messager.show({
		title:'提示信息',
		msg:text,
		width:w,
		height:h,
		timeout:5000,
		showType:'slide'
	});
}

/**
 * 动态获取dialog
 * 如果存在则返回,不存在则创建
 * @param id	dialog id
 */
function getDialog(id) {
	var dialog=$('#'+id);
	if(dialog.size()>0) return dialog;
	dialog=$('<div id="'+id+'" data-options="closed:false,cache:false,modal:true"></div>').appendTo(document.body);
	return dialog;
}

//==============jquery easyui 扩展===============
$.extend($.fn.form.methods, {
	
	//将表单域数据转换json对象
	jsonObject:function(jq) {
		var jsonObj = {};
		var array = jq.serializeArray();
		$.each(array, function () {
			if (jsonObj[this.name]) {	//如果已存在了,那么转换为数组
				if (!jsonObj[this.name].push) {
					jsonObj[this.name] = [jsonObj[this.name]];	//转为数组
				}
				jsonObj[this.name].push(this.value || '');
			} else {
				jsonObj[this.name] = this.value || '';
			}
		});
		return jsonObj;
	},
	
	//将表单置为只读
	readonly:function(jq) {
		function readonly(target) {
			$('input,textarea', target).prop('readonly',true);
			$('select', target).prop('disabled',true);
			var t = $(target);
			var plugins = ['combo','combobox','combotree','combogrid','slider'];
			for(var i=0; i<plugins.length; i++){
				var plugin = plugins[i];
				var r = t.find('.'+plugin+'-f');
				if (r.length && r[plugin]){
					r[plugin]('disable');
				}
			}
			//$('input', target).css('border','none');
		}
		return jq.each(function(){
			readonly(this);
		});
	}
});

//easyui validatebox 规则扩展
$.extend($.fn.validatebox.defaults.rules, {
	regexp:{		//正则表达式验证规则 ['/\\w+/'] 必须是字母数字下划线
		validator:function (value, regex) {
			var regex = new RegExp(regex[0]);
			return regex.test(value);
		}, 
		message:'数据不合法!'
	}, 
	equals:{		//相等验证规则 ['#name'] 必须与表单域name值相等
		validator:function (value, jqexp) {
			return $(jqexp[0]).val() == value;
		}, 
		message:'数据不合法!'
	}, 
	fn:{		//函数验证规则 [validateFn] 使用validateFn(value)函数验证,外界需要有此函数,并返回true或false
		validator:function (value, fn) {
			return fn[0](value);
		}, 
		message:'数据不合法!'
	},
	bytelen:{		//字节数验证规则 [0,20] 最少0个字节,最多20个字节
		validator:function (value, len) {
			var min = len.length == 1 ? 0 : len[0];
			var max = len.length == 1 ? len[0] : len[1];
			var blen = value.getByteNum();
			return min <= blen && blen <= max;
		},
		message:'请输入最少{0}个字节,最多{1}个字节!'
	}, 
	strlen:{		//字符数验证规则 [0,20] 最少0个字符,最多20个字符
		validator:function (value, len) {
			var min = len.length == 1 ? 0 : len[0];
			var max = len.length == 1 ? len[0] : len[1];
			var slen = value.length;
			return min <= slen && slen <= max;
		}, 
		message:'请输入最少{0}个字符,最多{1}个字符!'
	},
	decimal:{			//小数验证规则,[5,2] 整数5位,小数2位
		validator:function (value, param) {
			var regstr=null;
			if(param.length==1) {
				regstr='^\\d{1,'+param[0]+'}$';
			} else {
				regstr='^\\d{1,'+param[0]+'}(\\.\\d{1,'+param[1]+'})?$';
			}
			var reg=new RegExp(regstr);
			return reg.test(value);
		},
		message:'数据不合法!'
	},
	date:{			//日期格式验证.yyyy-MM-dd
		validator:function (value) {
			var match=/^(\d{4})-(\d{1,2})-(\d{1,2})$/.test(value);
			if(!match) return false;
			
			//var year=parseInt(RegExp.$1);
			var month=parseInt(RegExp.$2);
			var day=parseInt(RegExp.$3);
			
			return (month>=1 && month<=12) && (day>=1 && day<=31);
		}, 
		message:'日期格式不正确!'
	},
	datetime:{			//日期时间格式验证.yyyy-MM-dd HH:mm:ss
		validator:function (value) {
			var match=/^(\d{4})-(\d{1,2})-(\d{1,2})\s(\d{1,2})\:(\d{1,2})(\:\d{1,2})?$/.test(value);
			if(!match) return false;
			
			//var year=parseInt(RegExp.$1);
			var month=parseInt(RegExp.$2);
			var day=parseInt(RegExp.$3);
			var hour=parseInt(RegExp.$4);
			var minute=parseInt(RegExp.$5);
			
			return (month>=1 && month<=12) && (day>=1 && day<=31) && (hour>=0 && hour<=23) && (minute>=0 && minute<=59);
		}, 
		message:'日期格式不正确!'
	},
	emails: {		//验证多个邮件用","隔开
		validator:function(value) {
			var match = /^(\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*,?)+$/.test(value);
			return match;
		},
		message:'请输入正确的邮箱地址,多个请用","隔开!'
	}
});

Namespace('easyui.util',{
	
	/**
	 * 初始化datagrid
	 */
	initDatagrid:function(datagrid,options) {
		var _options={
				fit:true,
	   			fitColumns:true,
	   			border:false,
				rownumbers:true,
				singleSelect:true,
				autoRowHeight:true,
				striped:true,
				pagination:true,
				pageList:[10,15,20,25,30],
				pageSize:15,
				onSelect:function() {
					$('#toolbar a[group=single]').linkbutton('enable');
				},
				onLoadSuccess:function() {
					$('#toolbar a[group=single]').linkbutton('disable');
				}
		};
		$.extend(_options,options);
		$(datagrid).datagrid(_options);
		/*$(window).resize(function() {
			$(datagrid).datagrid('resize');
		});*/
	},
	
	/**
	 * 添加tab页
	 */
	addTab:function(tabs,id,title, url, closedCallback) {
		closedCallback=closedCallback||$.noop;
		var content='<iframe src="'+url+'" frameborder="0" scrolling="auto" style="width:100%;height:100%"></iframe>';
		if($(tabs).tabs('exists',title)) {		//如果存在该tab页了,那么直接选中,否则打开新的tab页
			$(tabs).tabs('select',title);
			var tab = $(tabs).tabs('getTab',title);
			$(tabs).tabs('update', {
				tab: tab,
				options:{
					id:id,
					title:title,
					content:content,
					closable:true,
					iconCls:'icon-pict',
					onDestroy:closedCallback
				}
			});
		} else {
			$(tabs).tabs('add',{
				id:id,
				title:title,
				content:content,
				closable:true,
				iconCls:'icon-pict',
				onDestroy:closedCallback
			});
		}
		
		//添加双击tab标题时,自动关闭tab页.
		$('.tabs-selected',$(tabs)).dblclick(function(){
	        var tabTitle = $(this).find('.tabs-title').text();
	        $(tabs).tabs('close',tabTitle);
	    });
	},
	
	/**
	 * 修改当前tab页的标题
	 */
	updateCurrentTabTitle:function(tabs,title) {
		var tab = $(tabs).tabs('getSelected');
		$(tabs).tabs('update', {
			tab: tab,
			options: {
				title: title
			}
		});
	},
	
	/**
	 * 为tabs添加头部菜单,主要用于关闭tab页
	 * @param tabs
	 */
	initTabsHeaderMenu:function(tabs) {
		var id='tabs_header_menu';
		var $menu=$('#'+id);
		if($menu.size()>0) return $menu;
		
		$menu=$('<div id="'+id+'" style="width:120px"></div>').appendTo(document.body);
		$menu.menu({});
		$menu.menu('appendItem', {
			text: '关闭',
			iconCls: 'icon-cancel',
			onclick: function(){
				easyui.util.closeTab(tabs,'close');
			}
		});
		$menu.menu('appendItem', {
			text: '关闭其它',
			onclick: function(){
				easyui.util.closeTab(tabs,'other');
			}
		});
		$menu.menu('appendItem', {
			text: '关闭全部',
			onclick: function(){
				easyui.util.closeTab(tabs,'all');
			}
		});
		$menu.menu('appendItem', {
			text: '关闭左边',
			onclick: function(){
				easyui.util.closeTab(tabs,'left');
			}
		});
		$menu.menu('appendItem', {
			text: '关闭右边',
			onclick: function(){
				easyui.util.closeTab(tabs,'right');
			}
		});
		
		$(tabs).tabs('options').onContextMenu=function(e,title,index) {
			e.preventDefault();
			e.stopPropagation();
			
			$(tabs).data('selected_title',title);
			$menu.menu('show',{
				left: e.pageX,
				top: e.pageY
			});
			return false;
		};
		return $menu;
	},
	
	/**
	 * 关闭tab页
	 * @param action	
 * 				action取值范围:
 * 				close:关闭title指定的tab
 * 				active:关闭当前激活的tab,首页除外
 * 				all:关闭所有tab,首页除外
 * 				other:关闭除title以外的tab,首页除外
 * 				left:关闭title左边的tab,首页除外
 * 				right:关闭title右边的tab,首页除外
	 * @param title		tab title
	 */
	closeTab:function(tabsId,action,title) {
		
		var $tabs=$(tabsId);
		var tabs=$tabs.tabs('tabs');
		if(action==null) action='close';
		if(title==null) title=$tabs.data('selected_title');
		
		if(action=='close') {
			
			$tabs.tabs('close',title);
		} else if(action=='active') {
			
			var p=$tabs.tabs('getSelected');
			var _title=p.panel('options').title;
			$tabs.tabs('close',_title);
		} else if(action=='all') {
			
			for(var i=tabs.length-1;i>=0;i--) {
				var _title=tabs[i].panel('options').title;
				$tabs.tabs('close',_title);
			}
		} else if(action=='other') {
			
			for(var i=tabs.length-1;i>=0;i--) {
				var _title=tabs[i].panel('options').title;
				if(_title==title) continue;
				$tabs.tabs('close',_title);
			}
		} else if(action=='left') {
			
			var left=false;
			for(var i=tabs.length-1;i>=0;i--) {
				var _title=tabs[i].panel('options').title;
				if(_title==title) {
					left=true;
					continue;
				}
				if(left) {
					$tabs.tabs('close',_title);
				}
			}
		} else if(action=='right') {
			
			for(var i=tabs.length-1;i>=0;i--) {
				var _title=tabs[i].panel('options').title;
				if(_title==title) {
					break;
				}
				$tabs.tabs('close',_title);
			}
		}
	}
});