<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>jQuery Mobile</title>
	<link rel="stylesheet" href="static/default.min.css" />
	<link rel="stylesheet" href="static/jquery.mobile.icons.min.css" />
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.2/jquery.mobile.structure-1.4.2.min.css" />
	
	<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js"></script>
</head>
<body>
	<div data-dom-cache="true" data-add-back-btn="true" data-back-btn-text="首页" id="page" data-role="page" data-theme="a">
		<%@ include file="/common/menu.jsp"%>
		<%@ include file="/common/header.jsp"%>
		<div data-role="content" >
			
			<ul id="swipeMe" data-role="listview" data-inset="true" data-filter="true" data-filter-placeholder="Search...">
				<li  data-role="list-divider">时间<span class="ui-li-count">4</span></li>
				<li ><a href="view.jsp">111<span class="ui-li-count">Reign 37 Years</span>
				　　</a>
				</li>
				<li ><a href="form.jsp">11<span class="ui-li-count">Reign 38 Years</span>
					</a>
				</li>
				<li ><a href="#">TTT</a></li>
				<li ><a href="#">TTTT</a></li>
				<li ><a href="#">TTT</a></li>
				<li ><a href="#">TTTT</a></li>
				
			</ul>
			<a  id="much" data-role="button">更多</a>
		</div>
		<%@ include file="/common/footer.jsp"%>
	</div>
	
</body>
<script>
	$('#page').on("pageinit", function(){
		//$.mobile.loadPage('form.jsp');
		//$.mobile.loadPage('view.jsp');
		
	});
	
	$(document).ready(function(){
		//更多按钮触发
		$('#much').click(function(){
               for (var i=0;i<=5;i++){
               	$("<li><a href='#'>xxx</a></li>").appendTo("#swipeMe");
               	$('#swipeMe').listview('refresh');
               }
		})
		//自动加载更多
		$(window).scroll(function () {
			if (($(document).height() - $(this).scrollTop())- $(this).height()<50) {
				$('#much').html('loading...');
				$('#much').click();
				$('#much').html('更多');
            }
		});
		
	})
	
</script>
</html>