<#macro renderBody>
<body>
<#include "header.ftl">
<div id="main">
    <nav></nav>
    <section>
        <#nested>
    </section>
</div>
<footer>
    <h1>Copyright @ Andrii Bodiukh</h1>
</footer>
</body>
</#macro>

