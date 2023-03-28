<#import "commons.ftlh" as c>

<@c.page>
    <#if user??>
        <div class="columns">
            <div>id: ${user.id}</div>
            <div>login: ${user.login}</div>
            <div>creationTine: ${user.creationTime}</div>
        </div>
        <#else>
            No such user
    </#if>
</@c.page>