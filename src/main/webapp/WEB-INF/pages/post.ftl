<#include "page.ftl">
<#-- @ftlvariable name="post" type="com.bodiukh.blog.domain.Post" -->

<@displayPage>
    <div id="content">
        <div class="post">
            <p class="date">May 22, 2015</p>
            <h1>Designing in the Build</h1>
            <p>${post.text}</p>
            <textarea>
                Some text
            </textarea>
        </div>
    </div>
</@displayPage>