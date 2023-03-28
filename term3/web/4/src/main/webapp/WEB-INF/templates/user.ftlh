<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if handle?has_content>
        <#assign u = c.findBy(users, "handle", handle)!/>
    <#elseif user_id?has_content>
        <#assign u = c.findBy(users, "id", user_id)!/>
    </#if>

    <#if u?has_content>
        <div class="user">
            <img src="https://userpic.codeforces.com/no-title.jpg" alt="">
            <a href="#">${u.name}</a>
            <ul>
                <li>id: ${u.id}</li>
                <li>handle: ${u.handle}</li>
                <li>name: ${u.name}</li>
            </ul>
        </div>
        <div class="arr">
            <#assign x = users?seq_index_of(u) - 1>
            <#assign y = users?seq_index_of(u) + 1>
            <#list users as user>
                <#if user?is_first && x == -1>
                    <a class="arrow2">&#x2190</a>
                </#if>
                <#if user?is_last && y == user?index + 1>
                    <a class="arrow2">&#x2192</a>
                </#if>
                <#if x == user?index>
                    <a class="arrow1" href="/user?handle=${user.handle}">&#x2190</a>
                </#if>
                <#if y == user?index>
                    <a class="arrow1" href="/user?handle=${user.handle}">&#x2192</a>
                </#if>
            </#list>
        </div>
        <#assign x = c.count(posts, "user_id", u.id)>
        <a href="/posts?user_id=${u.id}">${x}</a>
    <#else>
        No such user
    </#if>
</@c.page>
