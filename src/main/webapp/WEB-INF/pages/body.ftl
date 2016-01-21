<#macro renderBody>
<body>
<#include "header.ftl">
<div id="main">
    <nav></nav>
    <section>
        <#nested>
    </section>
</div>
    <#include "footer.ftl">
</body>
</#macro>

