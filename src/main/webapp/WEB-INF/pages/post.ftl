<#include "main.ftl">
<#-- @ftlvariable name="post" type="com.bodiukh.blog.domain.Post" -->

<@renderPage>
    <div id="content" ng-app="blog" ng-controller="buttonManager">
        <div class="button-list">
            <button id="edit" ng-click="turnEditMode()" class="btn-secondary" type="submit">Edit</button>
            <button id="preview" ng-click="turnPreviewMode()" class="btn-secondary" type="submit">Preview</button>
            <button id="save" class="btn-secondary" type="submit">Save</button>
            <button id="publish" class="btn-secondary" type="submit">Publish</button>
            <button id="unpublish" class="btn-secondary" type="submit">Unpublish</button>
            <button id="delete" class="btn-secondary" type="submit">Delete</button>
        </div>
        <div ng-show="editMode" class="edit-post">
            <p class="date">May 22, 2015</p>
            <textarea ng-model="textModel">
                <p>${post.text}</p>
            </textarea>
        </div>
    </div>
</@renderPage>