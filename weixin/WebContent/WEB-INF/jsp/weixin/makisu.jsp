<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <title>Makisu ~ CSS 3D Dropdown Concept</title>
    <link rel="stylesheet" href="static/style.css">
</head>
<body>

    <section class="demo">
        <dl class="list maki">
            <dt>Maki</dt>
            <dd><a href="#">Ana-kyu</a></dd>
            <dd><a href="#">Chutoro</a></dd>
            <dd><a href="#">Kaiware</a></dd>
            <dd><a href="#">Kampyo</a></dd>
            <dd><a href="#">Kappa</a></dd>
            <dd><a href="#">Natto</a></dd>
            <dd><a href="#">Negitoro</a></dd>
            <dd><a href="#">Oshinko</a></dd>
            <dd><a href="#">Otoro</a></dd>
            <dd><a href="#">Tekka</a></dd>
        </dl>
    </section>

    <script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="static/makisu.js"></script>
    <script>

        // The `enabled` flag will be `false` if CSS 3D isn't available

        if ( $.fn.makisu.enabled ) {

            var $maki = $( '.maki' );

            // Create Makisus
            $maki.makisu({
                selector: 'dd',
                overlap: 0.6,
                speed: 0.85
            });

            // Open all
            
            $( '.list' ).makisu( 'open' );

            // Toggle on click
        } 

    </script>
</body>
</html>