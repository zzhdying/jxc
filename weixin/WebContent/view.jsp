<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>职位详情</title>
	<link rel="stylesheet" href="static/blue.min.css" />
	<link rel="stylesheet" href="static/view.css" />
	<link rel="stylesheet" href="static/jquery.mobile.icons.min.css" />
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.2/jquery.mobile.structure-1.4.2.min.css" />
	
	<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js"></script>
</head>
<body>
	<div data-dom-cache="true" data-add-back-btn="true" data-back-btn-text="首页" id="page" data-role="page" data-theme="a">
		<%@ include file="/common/menu.jsp"%>
		<div id="header" data-position="fixed" data-role="header" >
			<a data-role="none" href="#"><img id="back"  src="./static/images/back.png"> 职位详情</a>
			<a class="ui-btn-right" data-role="none" data-ajax="false" href="#mypanel"><img src="./static/images/menu.png"> </a>	
		</div>
		<div data-role="content" >
			<div id="job-title">APP推广专员 新媒体营销<img src="./static/images/new.png"><br/> <p>2014年5月6日</p></div>
			<div class="job-span">
				<div class="bom-dashed" style="float: left;">
					<ul>
						<li>月薪 <span class="job-blue">8-10K</span></li>
						<li>最低学历  <span class="job-blue">本科</span></li>
					</ul>
					<ul>
						<li>工作年限 <span class="job-blue">2-5年</span></li>
						<li>返利佣金  <span class="job-o">9200元</span></li>
					</ul>
					<ul>
						<li style="width:100%;">工作地点 <span class="job-blue">上海市黄浦区中山南路1111号602室</span> <img src="./static/images/map.png"></li>
					</ul>
				</div>
				<div class="bom-dashed">
					<div class="job-t">职位描述</div>
					<div class="job-c">
						<div>1.负责本公司软件产品及手机应用的UI设计、宣传资料的视觉设计工作；</div>
						<div>2.制定产品UI设计方向，充分应用视觉设计经验，结合用户研究和产品分析的成果，不断的优化、提升界面的用户体验工程；</div>
						<div>3.完成界面视觉设计的准确表达与优化提升，进一步提高产品的易用性。</div>
					</div>
				</div>
				<div class="bom-dashed">
					<div class="job-t">职位要求</div>
					<div class="job-c">
						<div>1.二年以上相关工作经验，Web产品、手机产品的UI设计工作.经验者优先；曾参与过至少一款以上的软件产品UI工作的完整履历；</div>
						<div>2.精通Photoshop、Flash、illustrator、Dreamweaver等网页设计工具，以及视觉设计的相应设计软件；有丰富</div>
					</div>
				</div>
			</div>	
			<div class="job-span">
				<ul  data-role="listview" data-split-theme="a">	
				<li id="companyinfo">
					<img style="vertical-align:middle;top:17px;left:10px;border-radius:100px;" src="./static/images/51108887774b5.jpg" />
					<div id="company-text">
						<h3 id="company-name">华为技术有限公司</h3>
					<p class="job-xz">IT行业/上市公司/5000人以上</p>
					</div>
				<!-- <a href="#链接地址2" data-rel="dialog" data-transition="slideup">Purchase album</a> -->
				</li>
				</ul>
			</div>	
			<a href="#" id="blue-btn" data-role="button" data-theme="b">我要应聘</a>
			<a href="#" id="green-btn" data-role="button" data-theme="b">推荐朋友</a>
		</div>
		
	</div>
	
</body>
</html>