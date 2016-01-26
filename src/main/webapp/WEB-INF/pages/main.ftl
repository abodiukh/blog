<#import "body.ftl" as body>

<#macro renderPage>
<!DOCTYPE html>
<html ng-app="blog">
<head>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <script src="/resources/js/angular.js"></script>
    <script src="/resources/js/angular-sanitize.js"></script>
    <script src="/resources/js/blog.js"></script>
</head>
    <@body.renderBody><#nested></@body.renderBody>
</html>
</#macro>