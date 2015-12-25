<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/resources/css/posts.css">
</head>
<body>

<header>
    <h1>Blog</h1>
</header>
<nav>
    Topic<br>
</nav>
<section>
    <h2>Topic</h2>
    <p>First post</p>
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

