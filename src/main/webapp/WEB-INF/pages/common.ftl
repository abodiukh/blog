<#include "main.ftl">
<#-- @ftlvariable name="posts" type="java.util.List<com.bodiukh.blog.domain.Post>" -->

<@renderPage>
<div id="content" ng-controller="postCreator">
    <form class="add-form" ng-submit="addPost()">
        <span class="plus-icon" ng-click="expand()" ng-show="plusIcon"></span>
        <span class="minus-icon" ng-click="expand()" ng-show="minusIcon"></span>
        <div id="post-box" ng-show="isExpanded">
            <div id="inner-box">
                <label for="inputTitle" class="input-field">Title</label>
                <input type="text" id="inputTitle" class="form-control"
                       placeholder="Title" required autofocus ng-model="title">
                <button class="btn-secondary" type="submit">Add</button>
            </div>
        </div>
    </form>
<#if posts?has_content>
<#list posts as post>
    <article>
        <div class="post">
            <p class="date">May 22, 2015</p>
            <h1><a href="/post/${post.id}">Designing in the Build</a></h1>
            <p>${post.text}</p>
            <p><a href="/post/${post.id}" class="button">Read more</a></p>
        </div>
    </article>
</#list>
</#if>
</div>
</@renderPage>