<#include "main.ftl">
<#-- @ftlvariable name="post" type="com.bodiukh.blog.domain.Post" -->
<#-- @ftlvariable name="permissions" type="java.util.List<java.lang.String>" -->
<#-- @ftlvariable name="readonly" type="java.lang.Boolean" -->

<@renderPage>
    <div id="content" ng-controller="buttonManager">
        <#if !readonly>
            <div class="button-list">
                <#if permissions?seq_contains('write')>
                    <button id="edit" ng-click="turnEditMode()" class="btn-secondary">Edit</button>
                    <button id="preview" ng-click="turnPreviewMode()" class="btn-secondary">Preview</button>
                    <button id="save" ng-click="savePost()" class="btn-secondary">Save</button>
                </#if>
                <#if permissions?seq_contains('publish')>
                    <button id="publish" ng-click="publishPost()" class="btn-secondary" type="submit">Publish</button>
                    <button id="unpublish" ng-click="unpublishPost()" class="btn-secondary" type="submit">Unpublish</button>
                </#if>
                <#if permissions?seq_contains('delete')>
                    <button id="delete" ng-click="deletePost()" class="btn-secondary" type="submit">Delete</button>
                </#if>
            </div>
        </#if>
        <div class="post" ng-show="viewMode">
            <h1><a href="/post/${post.id}">${post.title}</a></h1>
            <p><#if post.text?has_content>${post.text}</#if></p>
        </div>
        <div class="edit-post" ng-show="editMode">
            <label for="inputTitle" class="input-field">Title</label>
            <input type="text" name="input" id="inputTitle" class="form-control"
                   placeholder="Title" required autofocus ng-model="postData.title">
            <textarea ng-model="postData.text"></textarea>
        </div>
    </div>
</@renderPage>