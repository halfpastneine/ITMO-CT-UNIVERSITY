<#-- @ftlvariable name="post" type="ru.itmo.wp.domain.Post" -->
<#-- @ftlvariable name="comment" type="ru.itmo.wp.domain.Comment" -->
<#-- @ftlvariable name="posts" type="ru.itmo.wp.domain.Post[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <#if post??>
        <@c.post post=post/>
        <div class="form" style="margin-bottom: 2rem">
            <#if user??>
                <div class="header">Write Comment</div>
                <div class="body">
                    <form method="post" action="">
                        <input type="hidden" name="action" value="makeComment"/>

                        <div class="field">
                            <div class="name">
                                <label for="text">Text</label>
                            </div>
                            <div class="value">
                                <textarea id="text" name="text" rows="5" cols="30">${comment.text!}</textarea>
                            </div>
                            <@c.error "comment.text"/>
                        </div>
                        <div class="button-field">
                            <input type="submit" value="Write">
                        </div>

                    </form>
                </div>
            </#if>
        </div>
        <table>
            <#list post.comments as comment>
                <tr>
                    <td class="author">Author: ${comment.user.login}</td>
                    <td class="text">Text: ${comment.text}</td>
                    <td class="creationTime">Date: ${comment.creationTime}</td>
                </tr>
            </#list>
        </table>
    <#else>
        No such post
    </#if>
</@c.page>
