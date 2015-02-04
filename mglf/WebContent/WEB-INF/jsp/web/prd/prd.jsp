<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../comm/taglibs.jsp" %>
<%@ include file="../../comm/config.jsp" %>
<c:set var="curmenu" value="prd"></c:set>
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
						<li class="active">商品中心</li>
					</ul>					
				</div>
				<div class="page-content">
					<div class="page-header">
						<h1>
							商品中心
							<small>
								<i class="icon-double-angle-right"></i>
								商品列表
							</small>
						</h1>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="m-b-10x">
								<a href="${root }/prd/add" class="btn btn-default radius-4">
									<i class="icon-plus align-top"></i>
									添&nbsp;加
								</a>
							</div>
							
							<div class="table-responsive">
								<table id="sample-table-1" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th width="70" align="center">编号</th>
											<th width="200">名称</th>
											<th width="100" >类型</th>
											<th width="60" style="text-align: center;">单位</th>
											<th width="150">条形码</th>
											<th width="80">采购价/元</th>
											<th width="80">预售价/元</th>
											<th width="100">所属企业</th>
											<th>描述</th>
											<th width="80" align="center"></th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${prdlist }" var="item">
											<tr data-key="${item.id }">
												<td>${item.num }</td>
												<td>${item.name }</td>
												<td>${item.prdTypeId }</td>
												<td style="text-align: center;">${item.unit }</td>
												<td>${item.code }</td>
												<td>${item.buyPrice }</td>
												<td>${item.sellPrice }</td>
												<td>${item.ownEntname }</td>
												<td>${item.description }</td>
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
							</div><!-- /.table-responsive -->
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
			删除商品后将不可再找到该商品。
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
					location.href = "${root}/prd/edit/"+$(this).attr("data-key");
				});
				$(".edit-btn").click(function(){
					location.href = "${root}/prd/edit/"+$(this).attr("data-key");
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
					$("#dialog-confirm .content-msg").html("商品名： " + name);
					$( "#dialog-confirm" ).removeClass('hide').dialog({
						resizable: false,
						modal: true,
						title: "<div class='widget-header'><h4 class='smaller'><i class='icon-warning-sign red'></i> 删除商品确认</h4></div>",
						title_html: true,
						buttons: [
							{
								html: "<i class='icon-trash bigger-110'></i>&nbsp; 确定删除",
								"class" : "btn btn-danger btn-xs",
								click: function() {
									$.ajax({
										url:"${root}/prd/del",
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