<#-- @ftlvariable name="post_id" type="java.lang.String" -->
<#import "./common.ftlh" as c>

<@c.page>
        <#if post_id?has_content>
                <#assign u = c.findBy(posts, "id", post_id)!/>
                <@c.postss u>
                        <p>${u.text}</p>
                </@c.postss>
        </#if>
</@c.page>