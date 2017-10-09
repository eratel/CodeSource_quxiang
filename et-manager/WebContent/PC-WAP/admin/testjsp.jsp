<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<button type="button" id="butoon"></button>
	<script src="/PC-WAP/admin/js/jquery-1.10.2.js"></script>
	<script type="text/javascript">
		$("#butoon").click(function(){
			alert(1);
			$.ajax({
			    url : 'http://localhost:9000/jsonp',
			    dataType : "jsonp",
			    type : "GET",
			    success : function(data){
			        if(data.status == 200){
			            console.log(data);
			        }
			    }
			});

		});
	</script>
</body>
</html>