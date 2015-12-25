<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/resources/css/posts.css">
    <script src="/resources/js/login.js"></script>
</head>
<body>

<header>
    <div class="intro">
        <h1>Blog</h1>
    </div>
    <form class="login-form">
        <h2>Sign in</h2>
        <label for="inputUsername" class="input-field">Username</label>
        <input type="text" id="inputUsername" class="form-control" placeholder="Username" required autofocus>
        <label for="inputPassword" class="input-field">Password</label>
        <input type="text" id="inputPassword" class="form-control" placeholder="Password" required autofocus>
        <br>
        <button class="btn btn-primary btn-block" type="submit">Sign in</button>
    </form>
</header>
<nav>

</nav>
<section>
    <div>
    <#-- @ftlvariable name="posts" type="java.util.List<com.bodiukh.blog.domain.Post>" -->
       <#if posts?has_content><table><tr></#if>
        <#list posts as post>
            <td>${post.author}</td>
            <td>${post.text}</td>
        </#list>
        <#if posts?has_content></tr></table></#if>
    </div>
</section>
<footer>
    Copyright @ Andrii Bodiukh
</footer>

</body>
</html>

