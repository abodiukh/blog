<#include "main.ftl">
<#-- @ftlvariable name="posts" type="java.util.List<com.bodiukh.blog.domain.Post>" -->

<@renderPage>
<div id="content" ng-controller="postCreator">
    <form name="form" class="add-form" ng-submit="addPost()">
        <i class="plus-icon" ng-click="expand()" ng-show="plusIcon"></i>
        <i class="minus-icon" ng-click="expand()" ng-show="minusIcon"></i>
        <div id="post-box" ng-show="isExpanded">
            <div id="inner-box">
                <label for="inputTitle" class="input-field">Title</label>
                <input type="text" name="input" id="inputTitle" class="form-control"
                       placeholder="Title" required autofocus ng-model="post.title">
                <button class="btn-secondary" type="submit">Add</button>
            </div>
        </div>
    </form>
<#if posts?has_content>
<#list posts as post>
    <article>
        <#if post.published>
        <div class="post">
            <p class="date">May 22, 2015</p>
            <h1><a href="/post/${post.id}">${post.title}</a></h1>
            <p><#if post.text?has_content>
                <#if post.text?length<100>
                    ${post.text}
                <#else>
                    ${post.text?substring(0,500) + "..."}
                </#if>
               </#if>
            </p>
            <p><a href="/post/${post.id}" class="button">Read more</a></p>
        </div>
        </#if>
    </article>
</#list>
</#if>
</div>
</@renderPage>