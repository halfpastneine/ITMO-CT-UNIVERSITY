<#import "./common.ftlh" as c>

<@c.page>
    <table>
        <tr class="header">
            <th>Handle</th>
            <th>Id</th>
            <th>Name</th>
        </tr>
        <#list users as u>
            <tr>
                <td>
                    <#if user?? && user.id == u.id>*</#if>
                    <@c.userlink u false/>
                </td>
                <td>
                    ${u.id}
                </td>
                <td>
                    ${u.name}
                </td>
            </tr>
        </#list>
    </table>

</@c.page>
