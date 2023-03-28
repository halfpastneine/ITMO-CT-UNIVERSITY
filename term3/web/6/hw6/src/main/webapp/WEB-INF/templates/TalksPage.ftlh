<#-- @ftlvariable name="message" type="java.lang.String" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="send-message">
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="enter"/>
                <div class="field">
                    <div class="name">
                        <label for="message">Message</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text" rows="5" cols="30"></textarea>
                    </div>
                </div>
                <div class="button-field">
                    <input type="submit" value="Send">
                </div>
            </form>
        </div>
    </div>
</@c.page>