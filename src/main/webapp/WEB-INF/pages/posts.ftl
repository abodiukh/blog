<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/resources/css/posts.css">
    <script src="/resources/js/angular.js"></script>
    <script src="/resources/js/login.js"></script>
</head>
<body>

<header>
    <div class="intro">
        <h1>Blog</h1>
    </div>
    <div id="login" ng-app="login" ng-controller="loginController">
        <form class="login-form" ng-submit="postForm()">
            <div ng-controller="expandController">
                <h1 ng-click="expand()">Sign in</h1>
                <div id="login-box" ng-show="isExpanded">
                   <div id="inner-box">
                    <label for="inputUsername" class="input-field">Username</label>
                    <input type="text" id="inputUsername" class="form-control" placeholder="Username" required autofocus ng-model="user.username">
                    <label for="inputPassword" class="input-field">Password</label>
                    <input type="text" id="inputPassword" class="form-control" placeholder="Password" required autofocus ng-model="user.password">
                    <br>
                    <button class="btn btn-primary btn-block" type="submit">Sign in</button>
                   </div>
                </div>
            </div>
        </form>
    </div>
</header>
<div id="main">
    <nav></nav>
    <section>
        <#if posts?has_content><div id="posts"></#if>
        <#-- @ftlvariable name="posts" type="java.util.List<com.bodiukh.blog.domain.Post>" -->
        <#list posts as post>
        <article>
          <div class="post">
            <p class="date">May 22, 2015</p>
            <h1><a href="/blog/designing-in-the-build/">Designing in the Build</a></h1>
            <p>${post.text}</p>
            <p><a href="/blog/designing-in-the-build/" class="button">Read more</a></p>
          </div>
        </article>
        </#list>
        <#if posts?has_content></div</#if>
    </section>
</div>
<footer>
    <h1>Copyright @ Andrii Bodiukh</h1>
</footer>
</body>
</html>

