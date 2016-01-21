<#include "main.ftl">
<#-- @ftlvariable name="post" type="com.bodiukh.blog.domain.Post" -->

<@renderPage>
    <div id="content">
        <div class="button-list">
            <button class="btn btn-primary btn-inline" type="submit">Edit</button>
            <button class="btn btn-primary btn-inline" type="submit">Save</button>
            <button class="btn btn-primary btn-inline" type="submit">Publish</button>
            <button class="btn btn-primary btn-inline" type="submit">Unpublish</button>
            <button class="btn btn-primary btn-inline" type="submit">Delete</button>
        </div>
        <div class="edit-post">
            <p class="date">May 22, 2015</p>
            <textarea>
                <h1>Designing in the Build</h1>
                <p>${post.text}</p>
            </textarea>
        </div>
    </div>
</@renderPage>