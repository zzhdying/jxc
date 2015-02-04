<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="sidebar" id="sidebar">
	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
	</script>

	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success">
				<i class="icon-signal"></i>
			</button>

			<button class="btn btn-info">
				<i class="icon-pencil"></i>
			</button>

			<button class="btn btn-warning">
				<i class="icon-group"></i>
			</button>

			<button class="btn btn-danger">
				<i class="icon-cogs"></i>
			</button>
		</div>

		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span>

			<span class="btn btn-info"></span>

			<span class="btn btn-warning"></span>

			<span class="btn btn-danger"></span>
		</div>
	</div><!-- #sidebar-shortcuts -->

	<ul class="nav nav-list">
		
		<li class="<c:if test="${empty curmenu || curmenu=='index'}">active</c:if>">
			<a href="${root }">
				<i class="icon-dashboard"></i>
				<span class="menu-text"> 首页 </span>
			</a>
		</li>

		<li class="<c:if test="${curmenu=='prd'}">active</c:if>">
			<a href="${root }/prd/index">
				<i class="icon-gift"></i>
				<span class="menu-text"> 商品中心 </span>
			</a>
		</li>

		<li>
			<a href="#" class="dropdown-toggle">
				<i class="icon-th"></i>
				<span class="menu-text"> 系统设置 </span>

				<b class="arrow icon-angle-down"></b>
			</a>

			<ul class="submenu">
				<li>
					<a href="elements.html">
						<i class="icon-double-angle-right"></i>
						商品单位
					</a>
				</li>

				<li>
					<a href="buttons.html">
						<i class="icon-double-angle-right"></i>
						企业管理
					</a>
				</li>

				<li>
					<a href="treeview.html">
						<i class="icon-double-angle-right"></i>
						类别管理
					</a>
				</li>
			</ul>
		</li>
	</ul>

	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
	</div>

	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
	</script>
</div>