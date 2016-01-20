<#include "page.ftl">
<#-- @ftlvariable name="post" type="com.bodiukh.blog.domain.Post" -->
<#assign post=post>

<#macro content>
    <div class="post">
        <p class="date">May 22, 2015</p>
        <h1>Designing in the Build</h1>
        <p>${post.text}</p>
    </div>
</#macro>

<@displayPage/>