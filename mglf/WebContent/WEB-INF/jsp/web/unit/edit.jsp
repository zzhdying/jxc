<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../comm/taglibs.jsp" %>
<%@ include file="../../comm/config.jsp" %>
<c:set var="curmenu" value="prd"></c:set>
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
						<li class="active">系统中心</li>
					</ul>					
				</div>
				<div class="page-content">
					<div class="page-header">
						<h1>
							商品单位
							<small>
								<i class="icon-double-angle-right"></i>
								修改计量单位
							</small>
						</h1>
					</div>
					<div class="row">
						<div class="col-xs-12">
							
							<form action="${root }/unit/editdo" method="post" class="form-horizontal" id="unitform">
								<input type="hidden" name="id" value="${unit.id }"/>
								<input type="hidden" name="entid" value="${unit.entid }"/>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="unitid"> 编号 </label>
									<div class="col-sm-9">
										<input type="text" id="unitid" name="num" tabindex="1" placeholder="编号可以让您更方便找到该计量单位  如：001、002、003" class="col-xs-10 col-sm-5" value="${unit.num }"/>
									</div>
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 单位名称 </label>
									<div class="col-sm-9">
										<input type="text" name="name" tabindex="2" placeholder="单位名称" class="col-xs-10 col-sm-5" value="${unit.name }"/>
									</div>
								</div>
								
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-info" type="submit" data-last="Finish " id="submitBtn" tabindex="9">
											<i class="icon-ok bigger-110"></i>
											确认添加
										</button>

										&nbsp; &nbsp; &nbsp;
										<button id="gobackBtn" class="btn" type="button" tabindex="10">
											<i class="icon-undo bigger-110"></i>
											返&nbsp;回
										</button>
									</div>
								</div>
							</form>	
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
							required: "单位名称不能为空，请输入单位名称。"
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