<!-- baidu -->



<!-- error log -->
<script type="text/javascript">
    var br = navigator.userAgent.toLowerCase();
    var browserVer = (br.match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [0, '0'])[1];
    var brower = userBrowser();

    function userBrowser() {
        var browserName = navigator.userAgent.toLowerCase();
        if (/msie/i.test(browserName) && !/opera/.test(browserName)) {
            return "IE";
        } else if (/firefox/i.test(browserName)) {
            return "Firefox";
        } else if (/chrome/i.test(browserName) && /webkit/i.test(browserName) && /mozilla/i.test(browserName)) {
            return "Chrome";
        } else if (/opera/i.test(browserName)) {
            return "Opera";
        } else if (/webkit/i.test(browserName) && !(/chrome/i.test(browserName) && /webkit/i.test(browserName) && /mozilla/i.test(browserName))) {
            return "Safari";
        } else if(/Trident/i.test(browserName) && /rv:11/i.test(browserName)){
            return 'IE11';
        }else {
            return "unKnow";
        }
    }

    
    function errorLog(){
        arglen=arguments.length;
        var errorMsg="{";
        for(var i=0;i<arglen;i++){
            errorMsg+='"'+(i+1)+'":"'+arguments[i]+'",';
        }
        errorMsg+='"brower":"'+brower+'","browserVer":"'+browserVer+'"}';
        //alert(errorMsg);
        window.onerror=null;
        
        $.ajax({
    		url: '/jserrlog',
    		data: { data:errorMsg}, 
    		type: "POST",
    		dataType:"text",
    		beforeSend: function (xhr) {
    			xhr.setRequestHeader("X-Ajax-call", "true");
    		},
            success: function(result) {  
            },
            error: function(XMLHttpRequest, textStatus, errorThrown){ 
            	return false; 
            }
    	});
        
        window.onerror=errorLog;
        return true;
    }

    window.onerror=errorLog;
</script>