<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'modifyPassword.jsp' starting page</title>
<script type="text/javascript">

$('#ff').form({    
    url:"${pageContext.request.contextPath}/user/modifyPassword",    
    onSubmit: function(){    
        // do some check    
        // return false to prevent submit; 
    	$.extend($.fn.validatebox.defaults.rules, {    
    	    equals: {
    	    	
    	        validator: function(value,param){    
    	            return value == $(param[0]).val();    
    	        },    
    	        message: 'Field do not match.'   
    	    }    
    	});
    },    
    success:function(data){    
        alert(data)    
    }    
});    
// submit the form    
$('#ff').submit();




</script>
 

</head>

<body style="margin: 1px">
	<div align="center">
	
	<form id="ff" method="post">   
    <div>   
        <label for="name">请输入新密码</label>   
        <input id="pwd" name="pwd" type="password" class="easyui-validatebox" data-options="required:true" />   
    </div>   
    <div>   
        <label for="email"> 请确认新密码</label>   
       <input id="rpwd" name="rpwd" type="password" class="easyui-validatebox"     
    required="required" validType="equals['#pwd']" />   
    </div> 
    <a id="btn"  onclick="javascript:alert('确认修改')" class="easyui-linkbutton" data-options="iconCls:'icon-zzkf'">确认修改</a> 
	</div>    
</form> 
	
	
	
   	 
	 
</body>
</html>
