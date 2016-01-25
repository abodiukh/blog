<#include "main.ftl">
<#-- @ftlvariable name="post" type="com.bodiukh.blog.domain.Post" -->

<@renderPage>
    <div id="content" ng-controller="buttonManager">
        <div class="button-list">
            <button id="edit" ng-click="turnEditMode()" class="btn-secondary">Edit</button>
            <button id="preview" ng-click="turnPreviewMode()" class="btn-secondary">Preview</button>
            <button id="save" ng-click="savePost()" class="btn-secondary">Save</button>
            <button id="publish" class="btn-secondary" type="submit">Publish</button>
            <button id="unpublish" class="btn-secondary" type="submit">Unpublish</button>
            <button id="delete" class="btn-secondary" type="submit">Delete</button>
        </div>
        <p class="date">May 22, 2015</p>
        <div class="post" ng-show="viewMode">
            ${post.text}
        </div>
        <div class="edit-post" ng-show="editMode">
            <textarea ng-model="textModel"></textarea>
        </div>
    </div>
</@renderPage>