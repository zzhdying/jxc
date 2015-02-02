<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../comm/taglibs.jsp" %>
<%@ include file="../../comm/config.jsp" %>
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
						<li class="active">产品中心</li>
					</ul>					
				</div>
				<div class="page-content">
					<div class="page-header">
						<h1>
							产品中心
							<small>
								<i class="icon-double-angle-right"></i>
								产品列表
							</small>
						</h1>
					</div>
					<div class="row">
						<div class="col-xs-12">
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
											<tr>
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
														
														<button class="btn btn-xs btn-info">
															<i class="icon-edit bigger-120"></i>
														</button>
	
														<button class="btn btn-xs btn-danger">
															<i class="icon-trash bigger-120"></i>
														</button>
	
													</div>
												</td>
											</tr>
										</c:forEach>
										<!-- <tr>
											<td>
												<a href="#">ace.com</a>
											</td>
											<td>$45</td>
											<td class="hidden-480">3,330</td>
											<td>Feb 12</td>

											<td class="hidden-480">
												<span class="label label-sm label-warning">Expiring</span>
											</td>

											<td>
												<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
													
													<button class="btn btn-xs btn-info">
														<i class="icon-edit bigger-120"></i>
													</button>

													<button class="btn btn-xs btn-danger">
														<i class="icon-trash bigger-120"></i>
													</button>

												</div>

												
											</td>
										</tr> -->
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

	<!-- 底部 -->
	<%@ include file="../../comm/footer.jsp" %>

		<script type="text/javascript">
			jQuery(function($) {
				
			
			})
		</script>
	</body>

</html>