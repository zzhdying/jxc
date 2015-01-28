<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="header" data-position="fixed" data-role="header" >
	<img id="logo" src="./static/images/logo.png">
<a href="#popupLogin" id="loginBtn" data-icon="check" class="ui-btn-right" data-role="none" data-rel="popup" data-theme="b" data-position-to="window" data-transition="pop">登陆</a>	
</div>
<div  data-role="popup" id="popupLogin" data-theme="a" class="ui-corner-all">
        <div style="padding:10px 20px;">
            <h3>Please sign in</h3>
            <label for="un" class="ui-hidden-accessible">Username:</label>
            <input type="text" name="user" id="un" value="" placeholder="username" data-theme="a">
            <label for="pw" class="ui-hidden-accessible">Password:</label>
            <input type="password" name="pass" id="pw" value="" placeholder="password" data-theme="a">
            <button type="submit" class="ui-btn ui-corner-all ui-shadow ui-btn-b ui-btn-icon-left ui-icon-check">Sign in</button>
        </div>
	</div>