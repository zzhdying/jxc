<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>jQuery Mobile</title>
	<link rel="stylesheet" href="static/default.min.css" />
	<link rel="stylesheet" href="static/jquery.mobile.icons.min.css" />
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.2/jquery.mobile.structure-1.4.2.min.css" />
	
	<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			//刷新select
			var myselect = $( "#select-native-fc" );
			myselect[0].selectedIndex = 2;
			myselect.selectmenu( "refresh" );
		});
		
		//$.mobile.resetActivePageHeight();重新渲染
	</script>
</head>
<body>
	<div id="page" data-role="page" data-theme="a">
		<div data-role="content">
			<label for="text-basic">正则输入:</label>
			<input data-clear-btn="true" name="text-basic" pattern="[0-9]*" placeholder="正则输入" id="text-basic" value="" type="text">
			<label for="password">Password:</label>
			<input name="password" id="password" value="" autocomplete="off" type="password">
			
			
			<div class="ui-field-contain">
			    <label for="select-native-fc">Native select:</label>
			    <select name="select-native-fc" id="select-native-fc">
			        <option value="small">One</option>
			        <option value="medium">Two</option>
			        <option value="large">Three</option>
			    </select>
			</div>
			
			
			<div class="ui-field-contain">
		        <label for="slider">Slider:</label>
		        <input name="slider" id="slider" min="0" max="100" value="50" type="range">
		    </div>
		    
		    <label for="email-2">email</label>
    		<input data-clear-btn="true" name="email-2" id="email-2" value="" type="email">
    		
    		<label for="url-1">Url</label>
    		<input data-clear-btn="true" name="url-1" id="url-1" value="" type="url">
    		
    		<fieldset data-role="controlgroup" data-type="horizontal">
		        <legend>Vertical:</legend>
		        <input name="checkbox-v-2a" id="checkbox-v-2a" type="checkbox">
		        <label for="checkbox-v-2a">One</label>
		        <input name="checkbox-v-2b" id="checkbox-v-2b" type="checkbox">
		        <label for="checkbox-v-2b">Two</label>
		        <input name="checkbox-v-2c" id="checkbox-v-2c" type="checkbox">
		        <label for="checkbox-v-2c">Three</label>
		    </fieldset>
		    
		    <fieldset data-role="controlgroup" data-type="horizontal">
		        <legend>Horizontal:</legend>
		        <input name="radio-choice-h-2" id="radio-choice-h-2a" value="on" checked="checked" type="radio">
		        <label for="radio-choice-h-2a">One</label>
		        <input name="radio-choice-h-2" id="radio-choice-h-2b" value="off" type="radio">
		        <label for="radio-choice-h-2b">Two</label>
		        <input name="radio-choice-h-2" id="radio-choice-h-2c" value="other" type="radio">
		        <label for="radio-choice-h-2c">Three</label>
		    </fieldset>
    		<div data-role="collapsible" data-collapsed="false">
			    <h4>Heading</h4>
			    <ul data-role="listview">
			        <li><a href="#">List item 1</a></li>
			        <li><a href="#">List item 2</a></li>
			        <li><a href="#">List item 3</a></li>
			    </ul>
			</div>
			
			<fieldset data-role="controlgroup">
			    <legend>selectgroup:</legend>
			    <label for="select-v-2a">Select A</label>
			    <select name="select-v-2a" id="select-v-2a">
			        <option value="#">One</option>
			        <option value="#">Two</option>
			        <option value="#">Three</option>
			    </select>
			    <label for="select-v-2b">Select B</label>
			    <select name="select-v-2b" id="select-v-2b">
			        <option value="#">One</option>
			        <option value="#">Two</option>
			        <option value="#">Three</option>
			    </select>
			    <label for="select-v-2c">Select C</label>
			    <select name="select-v-2c" id="select-v-2c">
			        <option value="#">One</option>
			        <option value="#">Two</option>
			        <option value="#">Three</option>
			    </select>
			</fieldset>
    		
    		
    		 <label for="flip-checkbox-4">Flip toggle switch checkbox:</label>
    		 <input type="checkbox" data-role="flipswitch" name="flip-checkbox-4" id="flip-checkbox-4" checked="">
    		
    		<div data-role="rangeslider">
		        <label for="range-10a">Rangeslider:</label>
		        <input type="range" name="range-10a" id="range-10a" min="0" max="10" step=".1" value="2.6">
		        <label for="range-10b">Rangeslider:</label>
		        <input type="range" name="range-10b" id="range-10b" min="0" max="10" step=".1" value="5.4">
		    </div>
		    
    		<fieldset class="ui-grid-a">
			    <div class="ui-block-a"><input type="submit" value="Submit" data-theme="a"></div>
			    <div class="ui-block-b"><input type="reset" value="Reset" data-theme="b"></div>
			</fieldset>
		</div>
		</div>
</body>
</html>