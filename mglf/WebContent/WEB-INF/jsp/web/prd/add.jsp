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
							<a href="">首页</a>
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
								添加新产品
							</small>
						</h1>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 条形码 </label>
									<div class="col-sm-9">
										<input type="text" name="code" tabindex="0" placeholder="条形码 注：可手动输入也可以通过扫码枪录入" class="col-xs-10 col-sm-5" value=""/>
										<span class="help-inline col-xs-12 col-sm-7">
											<span class="middle">条形码商品维一编码，全球唯一。</span>
										</span>
									</div>
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 产品名称 </label>
									<div class="col-sm-9">
										<input type="text" name="name" tabindex="1" placeholder="产品名称" class="col-xs-10 col-sm-5" />
									</div>
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 单位 </label>
									<div class="col-sm-9">
										<input type="text" name="name" tabindex="1" placeholder="产品名称" class="col-xs-10 col-sm-5" />
									</div>
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-4">Relative Sizing</label>

									<div class="col-sm-9">
										<input class="input-sm" type="text" id="form-field-4" placeholder=".input-sm" />
										<div class="space-2"></div>

										<div class="help-block" id="input-size-slider"></div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-5">Grid Sizing</label>

									<div class="col-sm-9">
										<div class="clearfix">
											<input class="col-xs-1" type="text" id="form-field-5" placeholder=".col-xs-1" />
										</div>

										<div class="space-2"></div>

										<div class="help-block" id="input-span-slider"></div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">Input with Icon</label>

									<div class="col-sm-9">
										<span class="input-icon">
											<input type="text" id="form-field-icon-1" />
											<i class="icon-leaf blue"></i>
										</span>

										<span class="input-icon input-icon-right">
											<input type="text" id="form-field-icon-2" />
											<i class="icon-leaf green"></i>
										</span>
									</div>
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-6">Tooltip and help button</label>

									<div class="col-sm-9">
										<input data-rel="tooltip" type="text" id="form-field-6" placeholder="Tooltip on hover" title="Hello Tooltip!" data-placement="bottom" />
										<span class="help-button" data-rel="popover" data-trigger="hover" data-placement="left" data-content="More details." title="Popover on hover">?</span>
									</div>
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-tags">Tag input</label>

									<div class="col-sm-9">
										<input type="text" name="tags" id="form-field-tags" placeholder="Enter tags ..." />
									</div>
								</div>

								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-info" type="button">
											<i class="icon-ok bigger-110"></i>
											确认添加
										</button>

										&nbsp; &nbsp; &nbsp;
										<button class="btn" type="reset">
											<i class="icon-undo bigger-110"></i>
											重&nbsp;置
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
				var tag_input = $('#form-field-tags');
				tag_input.tag({
					placeholder:tag_input.attr('placeholder'),
					source: ["包","台","瓶"]
				  });
			});
		</script>
	</body>

</html>