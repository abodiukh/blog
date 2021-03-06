<#include "main.ftl">
<#-- @ftlvariable name="posts" type="java.util.List<com.bodiukh.blog.domain.Post>" -->
<#-- @ftlvariable name="permissions" type="java.util.List<java.lang.String>" -->

<@renderPage>
<div id="content" ng-controller="postCreator">
    <#if permissions?seq_contains("create")>
        <div class="add-box">
            <div class="add-menu">
                <i class="plus-icon" ng-click="expand()" ng-show="plusIcon"></i>
                <i class="minus-icon" ng-click="expand()" ng-show="minusIcon"></i>
            </div>
            <form name="form" class="add-form" ng-submit="addPost()">
                <div class="post-box" ng-show="isExpanded">
                    <div class="inner-box">
                        <label for="inputTitle" class="input-field">Title</label>
                        <input type="text" name="input" id="inputTitle" class="form-control"
                               placeholder="Title" required autofocus ng-model="post.title">
                        <button class="btn-secondary" type="submit">Add</button>
                    </div>
                </div>
            </form>
        </div>
    </#if>
<#if posts?has_content>
<#list posts as post>
    <article>
        <div class="post">
            <#if !post.published><p>Unpublished</p></#if>
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
    </article>
</#list>
</#if>
</div>
</@renderPage>