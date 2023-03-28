<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Created</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <#if users?size==0>
                <tr class="noData">
                    <td colspan="3">
                        No data
                    </td>
                </tr>
            <#else>
                <#list users as user>
                    <tr>
                        <td class="id">${user.id}</td>
                        <td class="login">${user.login}</td>
                        <td class="creationTime">${user.creationTime}</td>
                        <td class="button">
                            <form method="post" action="">
                                <input type="hidden" name="userId" value=${user.id}>
                                <div class="button-field">
                                    <#if user.disabled>
                                        <input type="submit" value="Disable">
                                        <input type="hidden" name="access" value="Disable">
                                    <#else>
                                        <input type="submit" value="Enable">
                                        <input type="hidden" name="access" value="Enable">
                                    </#if>
                                </div>
                            </form>
                        </td>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</@c.page>
