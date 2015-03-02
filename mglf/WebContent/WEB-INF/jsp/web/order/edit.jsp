<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../comm/taglibs.jsp" %>
<%@ include file="../../comm/config.jsp" %>
<c:set var="curmenu" value="order"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../../comm/headConfig.jsp" %>
<style type="text/css">
.tags{
width: 41.66666666%;
float: left;
}
</style>
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
							<a href="">首页</a>
						</li>
						<li class="active">采购单</li>
					</ul>					
				</div>
				<div class="page-content">
					<div class="page-header">
						<h1>
							采购单管理
							<small>
								<i class="icon-double-angle-right"></i>
								单号：${order.num }
							</small>
						</h1>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="col-sm-5">
								<h4>状态：<c:if test="${order.isOk==0 }"><span class="red">未确认</span></c:if> 
										<c:if test="${order.isOk==1 }"><span class="green">已确认</span></c:if></h4>
								<h5>创建时间：<fmt:formatDate value="${order.createTime }" pattern="yyyy.MM.dd HH:mm"/></h5>
								<table id="sample-table-1" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th width="200">商品数量</th>
											<th width="200">采购总价</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${order.prdCount }</td>
											<td>${order.buyPrice }</td>
										</tr>
									</tbody>
								</table>
								<hr>
								<div class="grey m-b-20x">
									<h4><i class="icon-shopping-cart" style="font-size:24px;padding-right:10px"></i>添加商品</h4>
								</div>
								
								<form action="${root }active/editdo" method="post" class="form-horizontal" id="unitform">
									<input type="hidden" name="id" value="${dic.id }"/>
									<input type="hidden" name="entid" value="${dic.entid }"/>
									<div class="form-group">
										<div id="caseInsensitive"></div>
										<label class="col-sm-2 control-label no-padding-right" for="prdid"> 商品 </label>
										<div class="col-sm-10">
											<input type="text" id="prdid" name="prdId" tabindex="1" placeholder="" class="col-xs-10 col-sm-8"/>
										</div>
									</div>
	
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="unitid"> 数量 </label>
										<div class="col-sm-10">
											<input type="number" id="unitid" name="num" tabindex="2" placeholder="" class="col-xs-10 col-sm-4"/>
										</div>
									</div>
									
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="unitid"> 单价 </label>
										<div class="col-sm-10">
											<span class="input-icon">
												<input type="number" tabindex="3" name="buyPrice" class="input-medium f-l" id="buyPrice" placeholder=""/>
												<i class="icon-jpy"></i>
											</span>
										</div>
									</div>
									
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="unitid"> 实际单价 </label>
										<div class="col-sm-10">
											<span class="input-icon">
												<input type="number" tabindex="4" name="buyPrice" class="input-medium f-l" id="buyPrice" placeholder=""/>
												<i class="icon-jpy"></i>
											</span>
										</div>
									</div>
									
									<div class="space-4"></div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="unitid"> 总金额 </label>
										<div class="col-sm-10">
											<span class="input-icon">
												<input type="number" tabindex="4" name="buyPrice" class="input-medium f-l" id="buyPrice" placeholder="" readonly="readonly"/>
												<i class="icon-jpy"></i>
											</span>
										</div>
									</div>
									
									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="button" data-last="Finish " id="submitBtn" tabindex="9">
												<i class="icon-plus bigger-110"></i>
												添&nbsp;加
											</button>
	
											&nbsp; &nbsp; &nbsp;
											<button id="gobackBtn" class="btn" type="button" tabindex="10">
												<i class="icon-undo bigger-110"></i>
												返&nbsp;回
											</button>
										</div>
									</div>
								</form>	
								<div class="hr hr32 hr-dotted"></div>
							</div>	
							
							<div class="col-sm-7">
								<table id="sample-table-1" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th width="200">商品编号</th>
											<th width="200">商品名称</th>
											<th width="200">商品规格</th>
											<th width="200">数量</th>
											<th width="200">单价</th>
											<th width="200">实际单价</th>
											<th width="200">总金额</th>
										</tr>
									</thead>
									<tbody>
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

	<!-- 底部 -->
	<%@ include file="../../comm/footer.jsp" %>

		<script type="text/javascript">
			jQuery(function($) {
				$("#gobackBtn").click(function(){
					history.go(-1);
				});
				$("#prdid").tag({
					caseInsensitive:true,
					maxSize:1,
					data:baseUnitValue,
					source: baseUnit
				});
				
				$("#unitid").focus();
				$('#unitform').validate({
					errorElement: 'div',
					errorClass: 'col-sm-9 help-block m-l-25p',
					focusInvalid: false,
					rules: {
						num: {
							required: true
						},
						name: {
							required: true
						}
					},
					messages: {
						num: {
							required: "编号不能为空，请输入对应的编号。"
						},
						name: {
							required: "企业名称不能为空，请输入企业名称。"
						}
					},
					highlight: function (e) {
						$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
					},
					success: function (e,b) {
						$(b).closest('.form-group').removeClass('has-error').addClass('has-info');
						$(b).closest('.form-group').find(".help-block").remove();
					},
					errorPlacement: function (error, element) {
						if(element.closest(".form-group").find(".help-block").length == 0){
							if(element.is(':checkbox') || element.is(':radio')) {
								var controls = element.closest('div[class*="col-"]');
								if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
								else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
							}
							else if(element.is('.select2')) {
								error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
							}
							else if(element.is('.chosen-select')) {
								error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
							}
							else error.insertAfter(element.parent());
						}
					},
					submitHandler: function (form) {
						form.submit();
					},
					invalidHandler: function (form) {
					}
				});
			});
		</script>
	</body>

</html>