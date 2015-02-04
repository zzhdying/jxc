<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../comm/taglibs.jsp" %>
<%@ include file="../../comm/config.jsp" %>
<c:set var="curmenu" value="supplier"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../../comm/headConfig.jsp" %>
</head>
<body>
	<%-- 引入头部 --%>		
	<%@ include file="../../comm/headmenu.jsp" %>
	
	<div class="main-container mglf-content" id="main-container">
		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#">
				<span class="menu-text"></span>
			</a>
			<%-- 引入左侧菜单--%>		
			<%@ include file="../../comm/leftmenu.jsp" %>
			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<ul class="breadcrumb">
						<li>
							<i class="icon-home home-icon"></i>
							<a href="#">首页</a>
						</li>
						<li class="active">系统中心</li>
					</ul>					
				</div>
				<div class="page-content">
					<div class="page-header">
						<h1>
							供应商管理
							<small>
								<i class="icon-double-angle-right"></i>
								供应商列表
							</small>
						</h1>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="m-b-10x">
								<a href="${root }/supplier/add" class="btn btn-default radius-4">
									<i class="icon-plus align-top"></i>
									添&nbsp;加
								</a>
							</div>
							
							<div class="table-responsive">
								<table id="sample-table-1" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th width="100" align="center">编号</th>
											<th width="200">供应商名称</th>
											<th width="200">联系人</th>
											<th width="200">联系电话</th>
											<th width="200">地址</th>
											<th width="80" align="center"></th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${list }" var="item">
											<tr data-key="${item.id }">
												<td>${item.num }</td>
												<td>${item.entname }</td>
												<td>${item.name }</td>
												<td>${item.linkphone }</td>
												<td>${item.address }</td>
												<td align="center">
													<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
														<button class="btn btn-xs btn-info edit-btn" data-key="${item.id }">
															<i class="icon-edit bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-danger del-btn" data-key="${item.id }" data-val01="${item.name }">
															<i class="icon-trash bigger-120"></i>
														</button>
													</div>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@ include file="../../comm/rightfloatmenu.jsp" %>
		</div>
	</div>
	
	<div id="dialog-confirm" class="hide">
		<div class="alert alert-info bigger-110">
			<div class="content-msg"></div>
			删除后将不可再找到。
		</div>
		<div class="space-6"></div>
		<p class="bigger-110 bolder center grey">
			<i class="icon-hand-right blue bigger-120"></i>
			确定要删除吗?
		</p>
	</div>
	
	<!-- 底部 -->
	<%@ include file="../../comm/footer.jsp" %>

		<script type="text/javascript">
			jQuery(function($) {
				$("#sample-table-1 tr").dblclick(function(){
					location.href = "${root}/supplier/edit/"+$(this).attr("data-key");
				});
				$(".edit-btn").click(function(){
					location.href = "${root}/supplier/edit/"+$(this).attr("data-key");
				});
				
				$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
					_title: function(title) {
						var $title = this.options.title || '&nbsp;'
						if( ("title_html" in this.options) && this.options.title_html == true )
							title.html($title);
						else title.text($title);
					}
				}));
				$(".del-btn").click(function(){
					var $this = $(this);
					var id = $(this).attr("data-key");
					var name = $(this).attr("data-val01");
					$( "#dialog-confirm" ).removeClass('hide').dialog({
						resizable: false,
						modal: true,
						title: "<div class='widget-header'><h4 class='smaller'><i class='icon-warning-sign red'></i> 删除确认</h4></div>",
						title_html: true,
						buttons: [
							{
								html: "<i class='icon-trash bigger-110'></i>&nbsp; 确定删除",
								"class" : "btn btn-danger btn-xs",
								click: function() {
									$.ajax({
										url:"${root}/supplier/del",
										data:{'id':id},
										type:'post'
									});
									$this.closest("tr").remove();
									$( this ).dialog( "close" );
								}
							}
							,
							{
								html: "<i class='icon-remove bigger-110'></i>&nbsp; Cancel",
								"class" : "btn btn-xs",
								click: function() {
									$( this ).dialog( "close" );
								}
							}
						]
					});
				});				
			})
		</script>
	</body>

</html>