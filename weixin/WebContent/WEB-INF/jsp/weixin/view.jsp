<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
	<meta charset="utf-8">
	<meta content="width=device-width, initial-scale=1" name="viewport"> 
	<title>Multi-page template</title> 
	<link rel="stylesheet" href="static/default.min.css" />
	<link rel="stylesheet" href="static/jquery.mobile.icons.min.css" />
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.2/jquery.mobile.structure-1.4.2.min.css" />
	
	<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js"></script>
		
</head> 

	
<body> 

<!-- Start of first page: #one -->
<div id="one" data-role="page" data-theme="a"  style="min-height: 800px;">
	<%@ include file="/common/menu.jsp"%>
	<%@ include file="/common/header.jsp"%>
	<div data-role="content" class="ui-content" role="main">	
		<h2>测试</h2>
		<img src="QrCodeServlet">
		<p>测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试</p>	

		<h3>pages:1</h3>
		<p><a data-role="button" href="#two" data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-theme="a" class="ui-btn ui-shadow ui-btn-corner-all ui-btn-up-c"><span class="ui-btn-inner ui-btn-corner-all"><span class="ui-btn-text">Show page "two"</span></span></a></p>	
		<p><a data-transition="pop" data-rel="dialog" data-role="button" href="#popup" data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-theme="c" class="ui-btn ui-shadow ui-btn-corner-all ui-btn-up-c"><span class="ui-btn-inner ui-btn-corner-all"><span class="ui-btn-text">Show page "popup" (as a dialog)</span></span></a></p>
	</div><!-- /content -->
	
	<%@ include file="/common/footer.jsp"%>
</div><!-- /page one -->


<!-- Start of second page: #two -->
<div data-theme="a" id="two" data-role="page"  style="min-height: 800px;" data-url="two" tabindex="0">

	<%@ include file="/common/menu.jsp"%>
	<%@ include file="/common/header.jsp"%>

	<div data-role="content" role="main">	
		<h2>page：2</h2>
		
	</div><!-- /content -->
	
	<%@ include file="/common/footer.jsp"%>
</div><!-- /page two -->


<!-- Start of third page: #popup -->
<div id="popup" data-role="page"  style="min-height: 800px;" data-url="popup">

	<%@ include file="/common/menu.jsp"%>
	<%@ include file="/common/header.jsp"%>

	<div data-role="content">	
		<h2>弹出层</h2>
		<p>页面:3</p>		
		<p><a data-icon="back" data-inline="true" data-role="button" data-rel="back" href="#one">Back to page "one"</a></p>	
	</div><!-- /content -->
	
	<%@ include file="/common/footer.jsp"%>
</div><!-- /page popup -->


<div class="ui-loader ui-corner-all ui-body-a ui-loader-default"><span class="ui-icon ui-icon-loading"></span><h1>loading</h1></div>
<script type="text/javascript">

</script>
</body>
</html>
