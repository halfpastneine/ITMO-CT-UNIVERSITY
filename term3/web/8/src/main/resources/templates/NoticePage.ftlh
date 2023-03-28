<#import "commons.ftlh" as c>

<@c.page>
    <div class="notice">
        <form method="post" action="">
            <div class="name">
                <label for="Notice">Notice</label>
            </div>
            <div class="content">
                <textarea id="content" name="content" rows="5" cols="30"></textarea>
            </div>

            <@c.error "noticeForm.content"/>
            <div class="button-field">
                <input type="submit" value="Notice">
            </div>
        </form>
    </div>
</@c.page>
