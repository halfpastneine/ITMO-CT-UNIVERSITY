<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if user_id?has_content>
        <#list posts as item>
            <#if item["user_id"] == user_id>
                <@c.postss item>
                    <p>${item.text}</p>
                </@c.postss>
            </#if>
        </#list>
    </#if>
</@c.page>