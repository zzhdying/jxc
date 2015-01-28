<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>一起推</title>	
	
</head>
<body>
	
	<div data-dom-cache="true" class="index-page pull-index-page index-canpageshow main-page" data-add-back-btn="true" data-back-btn-text="首页" id="page" data-role="page" data-theme="b">
		<style type="text/css">
			#page h3{
				font-size:14px;
			}
		</style>
		
		<div  data-role="content" style="padding:0px;background-color: #fff;">
	
			<div data-iscroll="" style="padding:16px;">			
				
			<div class="iscroll-pulldown">
		    	<span class="iscroll-pull-icon"></span><span class="iscroll-pull-label" data-iscroll-loading-text="数据正在加载中..." data-iscroll-pulled-text="放开后刷新">下拉刷新 </span>
		    </div>
		    		
			
			<form action="" id="searchForm">
				<input name="start" type="hidden">
				<input name='keyword' id='keyword' type="hidden" value='${keyword }'/>
				<input name='cityName' id='cityName' type="hidden" value="${cityName }"/>
				<input name='workAddressId' id='workAddressId' type="hidden" value='${workAddressId }'/>
				<input name='jobTypeCode' id='jobTypeCode' type="hidden" value='${jobTypeCode }'/>
				<input name='jobTypeCodeNm' id='jobTypeCodeNm' type="hidden" value='${jobTypeCodeNm }'/> 
				<input name='jobPositionCode' id='jobPositionCode' type="hidden" value='${jobPositionCode }'/>
				<input name='jobPositionCodeNm' id='jobPositionCodeNm' type="hidden" value='${jobPositionCodeNm }'/>
				<input name='salary' id='salary' type="hidden" value='${salary }'/>
				<input name='salaryNm' id='salaryNm' type="hidden" value='${salaryNm }'/>
			</form>
	        <div style="padding-top:1px;">
	        	<input type="hidden" id="recommendJobId" name="recommendJobId" value="${recommendJobId }"/>
				<ul data-role="listview" id="swipeMe" class="index-list-view"></ul>
			</div>	        
	        <div class="iscroll-pullup">
	          <span class="iscroll-pull-icon"></span><span class="iscroll-pull-label" data-iscroll-loading-text="数据正在加载中..." data-iscroll-pulled-text="放开后加载数据">上拉加载</span>
	        </div>
	        </div>
		</div>
	<script type="text/javascript">
	
	
	
</script>

</div>
</body>
</html>