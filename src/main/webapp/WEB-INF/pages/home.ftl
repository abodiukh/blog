<#include "main.ftl">
<#-- @ftlvariable name="posts" type="java.util.List<com.bodiukh.blog.domain.Post>" -->
<#--<#assign posts=posts>-->

<@renderPage>
<div id="content">
<#if posts?has_content>
<#list posts as post>
    <article>
        <div class="post">
            <p class="date">May 22, 2015</p>
            <h1><a href="/posts/${post.id}">Designing in the Build</a></h1>
            <p>${post.text}</p>
            <p><a href="/posts/${post.id}" class="button">Read more</a></p>
        </div>
    </article>
</#list>
</#if>
</div>
</@renderPage>