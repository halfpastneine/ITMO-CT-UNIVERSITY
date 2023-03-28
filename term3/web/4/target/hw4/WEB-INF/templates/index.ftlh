<#import "./common.ftlh" as c>

<@c.page>
    <#list posts as post>
        <@c.postss post>
            <p>${post.text?truncate(350)}</p>
        </@c.postss>
    </#list>

</@c.page>
