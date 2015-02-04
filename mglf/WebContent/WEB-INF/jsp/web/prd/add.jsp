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
						<li class="active">商品中心</li>
					</ul>					
				</div>
				<div class="page-content">
					<div class="page-header">
						<h1>
							商品中心
							<small>
								<i class="icon-double-angle-right"></i>
								添加新商品
							</small>
						</h1>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<%-- 后退按钮 --%>
							<%@ include file="../../comm/goBackBtn.jsp" %>
							
							<form action="${root }/prd/adddo" method="post" class="form-horizontal" id="prdform">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 条形码 </label>
									<div class="col-sm-9">
										<input type="text" name="code" tabindex="1" placeholder="条形码 注：可手动输入也可以通过扫码枪录入" class="col-xs-10 col-sm-5" value=""/>
										<span class="help-inline col-xs-12 col-sm-7">
											<span class="middle">条形码商品维一编码，全球唯一。</span>
										</span>
									</div>
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 商品名称 </label>
									<div class="col-sm-9">
										<input type="text" name="name" tabindex="2" placeholder="商品名称" class="col-xs-10 col-sm-5" />
									</div>
								</div>
								
								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-unit">单位</label>

									<div class="col-sm-9">
										<input type="text" name="unit" tabindex="3" class="tag-input" id="form-unit" placeholder="请输入商品的计量单位 如：个、台、包。" /> 
										<span class="help-inline col-xs-12 col-sm-7">
											<span class="middle"> 回车确认输入</span>
										</span>
									</div>									
								</div>
								
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-prdtype"> 所属类别 </label>
									<div class="col-sm-9">
										<input type="text" id="form-prdtype" name="prdTypeId" tabindex="4" class="tag-input" placeholder="请输入商品的所属类别 如 纸巾、食品" /> 
										<span class="help-inline col-xs-12 col-sm-7">
											<span class="middle"> 回车确认输入</span>
										</span>
									</div>
								</div>
								
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="ownEntname">所属公司</label>
									<div class="col-sm-9">
										<input type="text" id="ownEntname" name="ownEntname" tabindex="5" class="tag-input"  placeholder="请输入商品所属的公司名称 如：恒安集团。" /> 
										<span class="help-inline col-xs-12 col-sm-7">
											<span class="middle"> 回车确认输入</span>
										</span>
									</div>									
								</div>

								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">默认采购价</label>

									<div class="col-sm-9">
										<span class="input-icon">
											<input type="number" tabindex="6" name="buyPrice" class="input-medium f-l" id="buyPrice" placeholder="商品的默认采购价格。"/>
											<i class="icon-jpy orange2"></i>
											<span class="help-inline col-xs-12 col-sm-7">
												<span class="middle"> 该价格只是默认值，可在创建采购单时再设当时真实价格</span>
											</span>
										</span>
									</div>
								</div>	
								
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">默认销售价</label>

									<div class="col-sm-9">
										<span class="input-icon">
											<input type="number" name="sellPrice" tabindex="7" class="input-medium  f-l" id="sellPrice"  placeholder="商品的默认销售价格。"/>
											<i class="icon-jpy orange2"></i>
											<span class="help-inline col-xs-12 col-sm-7">
												<span class="middle"> 该价格只是默认值，可在创建采购单时再设当时真实价格</span>
											</span>
										</span>
									</div>
								</div>
								
								<div class="space-4"></div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">描述</label>

									<div class="col-sm-9">
										<textarea class="col-sm-5" name="comment" id="comment"></textarea>
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
				$("#form-unit").tag({
					placeholder:$("#form-unit").attr('placeholder'),
					tabindex:$("#form-unit").attr('tabindex'),
					source: ["包01","台","瓶"]
				});
				$("#ownEntname").tag({
					placeholder:$("#ownEntname").attr('placeholder'),
					tabindex:$("#ownEntname").attr('tabindex'),
					source: ["001 恒安集团","002 钟氏集团"]
				});
				$("#form-prdtype").tag({
					placeholder:$("#form-prdtype").attr('placeholder'),
					tabindex:$("#form-prdtype").attr('tabindex'),
					source: ["001 抽纸","002 卫生巾"]
				});
				
				$(".chosen-select").chosen(); 
				
				$('#prdform').validate({
					errorElement: 'div',
					errorClass: 'col-sm-9 help-block m-l-25p',
					focusInvalid: false,
					rules: {
						code: {
							required: true
						},
						name: {
							required: true
						}
					},
					messages: {
						code: {
							required: "商品条形码不能为空，请录入商品的条形码。"
						},
						name: {
							required: "商品名称不能为空，请录入商品的名称。"
						}
					},
					invalidHandler: function (event, validator) {
						//$('.alert-danger', $('.login-form')).show();
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