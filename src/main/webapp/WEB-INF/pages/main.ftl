<#import "body.ftl" as body>

<#macro renderPage>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <script src="/resources/js/angular.js"></script>
    <script src="/resources/js/blog.js"></script>
    <#--<script src="/resources/tinymce/tinymce.min.js"></script>-->
    <#--<script src="/resources/js/tinymce.conf.js"></script>-->
</head>
    <@body.renderBody><#nested></@body.renderBody>
</html>
</#macro>