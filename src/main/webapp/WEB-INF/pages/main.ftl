<#import "body.ftl" as body>

<#macro renderPage>
<!DOCTYPE html>
<html ng-app="blog">
<head>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/styles.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/header.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/nav.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/post.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/footer.css">
    <script src="/resources/js/modules/angular.js"></script>
    <script src="/resources/js/modules/angular-sanitize.js"></script>
    <script src="/resources/js/modules/angularjs-dropdown-multiselect.js"></script>
    <script src="/resources/js/modules/underscore.js"></script>
    <script src="/resources/js/blog.js"></script>
    <script src="/resources/js/controllers/login.js"></script>
    <script src="/resources/js/controllers/creator.js"></script>
    <script src="/resources/js/controllers/editor.js"></script>
    <script src="/resources/js/controllers/users.js"></script>
</head>
    <@body.renderBody><#nested></@body.renderBody>
</html>
</#macro>