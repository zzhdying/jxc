<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../comm/taglibs.jsp" %>
<%@ include file="../comm/config.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../comm/headConfig.jsp" %>
</head>
<body>
	<%-- 引入头部 --%>		
	<%@ include file="../comm/headmenu.jsp" %>
	
	<div class="main-container mglf-content" id="main-container">
		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#">
				<span class="menu-text"></span>
			</a>
			<%-- 引入左侧菜单--%>		
			<%@ include file="../comm/leftmenu.jsp" %>
			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<ul class="breadcrumb">
						<li>
							<i class="icon-home home-icon"></i>
							<a href="${root }">首页</a>
						</li>
						<li class="active">Dashboard</li>
					</ul>					
				</div>
				<div class="page-content">
					<div class="page-header">
						<h1>
							Dashboard
							<small>
								<i class="icon-double-angle-right"></i>
								overview &amp; stats
							</small>
						</h1>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<%-- 后退按钮 --%>
							<%@ include file="../comm/goBackBtn.jsp" %>	
	
	
							
	
	
							
	
						</div>
					</div>
				</div>
			</div>
			<%@ include file="../comm/rightfloatmenu.jsp" %>
		</div>
	</div>

	<!-- 底部 -->
	<%@ include file="../comm/footer.jsp" %>

		<script type="text/javascript">
			jQuery(function($) {
				
			
			})
		</script>
	</body>

</html>