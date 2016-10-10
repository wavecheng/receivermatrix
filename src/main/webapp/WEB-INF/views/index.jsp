<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>Citrix Receiver Feature Matrix</title>
	<link rel="stylesheet" media="screen" href="${contextPath}/resources/handsontable.full.min.css">
	<link rel="stylesheet" media="screen" href="${contextPath}/resources/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
    	<h1 style="text-align:center">Receiver Feature Matrix</h1>
    	<div class="row">
    	    <p class="pull-right">
    	      <span class="msgConsole text-success h3 "></span>&nbsp;&nbsp;&nbsp;
	          <button name="save" id="save" class="btn btn-primary" >Save</button>         
	        </p> 
	        
    	</div>   	
      <div class="row">        	 
        <div id="example1" style="background-color:#eee;"></div>
  	  </div>
    </div>
</body>
<script src="${contextPath}/resources/jquery.min.js"></script>
<script src="${contextPath}/resources/bootstrap.min.js"></script>
<script src="${contextPath}/resources/handsontable.full.min.js"></script>
<script src="${contextPath}/resources/main.js">

</script>
</html>
