<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<div data-role="panel" data-position="right" id="mypanel">
			<div data-role="collapsibleset" data-theme="a" data-content-theme="a">
			<h2>Choose a car model...</h2>
			    <ul data-role="listview" data-filter="true">
			        <li><a id="closepanel" data-rel="close" href="#my-header">Close panel</a></li>
			        <li><a href="index.html">Audi</a></li>
			        <li><a href="index.html">BMW</a></li>
			        <li><a href="index.html">Cadillac</a></li>
			        <li><a href="index.html">Chrysler</a></li>
			        <li><a href="index.html">Dodge</a></li>
			        <li><a href="index.html">Ferrari</a></li>
			        <li><a href="index.html">Ford</a></li>
			        <li><a href="index.html">GMC</a></li>
			        <li><a href="index.html">Honda</a></li>
			    </ul>
			</div>
	</div>
	
<script>
/*$( document ).on( "pageinit", "div[data-role=page]", function() {
	$(document).bind('swipe', 
		//Generic swipe handler for all directions
		function(event, direction, distance, duration, fingerCount) {
			//$(this).text("You swiped " + direction + " " + ++count + " times " );	
			$( "#mypanel" ).panel( "open" );
		}
	);
});*/
</script>