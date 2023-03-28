<#-- @ftlvariable name="user.type" type="java.lang.Enum" -->
<#-- @ftlvariable name="uri" type="java.lang.String" -->
<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="icon" href="/favicon.ico">
    </head>
    <body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#macro header>
    <header>
        <a href="#" class="logo"><img alt="Codeforces" title="Codeforces"
                                      src="/img/codeforces-logo-with-telegram.png"></a>
        <div class="languages">
            <a href="#"><img alt="In English" title="In English"
                             src="/img/gb.png"></a>
            <a href="#"><img alt="In Russian" title="In Russian"
                             src="/img/ru.png"></a>
        </div>
        <div class="enter-or-register-box">
            <#if user??>
                <@userlink user true/>
                |
                <a href="/logout">Logout</a>
            <#else>
                <a href="/enter">Enter</a>
                |
                <a href="/register">Register</a>
            </#if>
        </div>
        <nav>
            <#assign items=[
            {"href": "/index", "name": "Home"},
            {"href": "/misc/help", "name": "Help"},
            {"href": "/contests", "name": "Contests"},
            {"href": "/users", "name": "Users"}
            ]/>
            <ul>
                <#list items as item>
                    <li>
                        <#if uri == item.href>
                            <a class="underline" href="${item.href}">${item.name}</a>
                        <#else>
                            <a href="${item.href}">${item.name}</a>
                        </#if>
                    </li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>

<#macro sidebar>
    <aside>
        <#list posts as post>
            <section>
                <div class="header">Post ${post.id}</div>
                <div class="body"><p>${post.text?truncate(350)}</p></div>
                <div class="footer">
                    <a href="/post?post_id=${post.id}">View all</a>
                </div>
            </section>
        </#list>
    </aside>
</#macro>

<#macro footer>
    <footer>
        2010-2021 &copy; by Mike Mirzayanov
    </footer>
</#macro>

<#macro postss post>
    <#assign u = c.findBy(users, "id", post.user_id)!/>
    <div class="title">${post.title}</div>
    <div class="information">by ${u.name}</div>
    <#nested/>
<#--    <p>${post.text?truncate(350)}</p>-->
    <ul class="attachment">
        <li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>
        <li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>
    </ul>
    <div class="footer">
        <div class="left">
            <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
            <span class="positive-score">+173</span>
            <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
        </div>
    </div>
</#macro>

<#macro userlink user nameOnly>
    <#if nameOnly>
        <a href="/user?handle=${user.handle}">${user.name}</a>
    <#else>
        <a href="/user?handle=${user.handle}"
           style="text-decoration: none; color: ${user.type.toString()}; font-family: 'Marker Felt', serif ">${user.handle}
        </a>
    </#if>
</#macro>

<#function findBy items key value>
    <#list items as item>
        <#if item[key] == value>
            <#return item/>
        </#if>
    </#list>
</#function>

<#function count items key value>
    <#assign x = 0>
    <#list items as item>
            <#if item[key] == value>
                <#assign x++>
            </#if>
    </#list>
    <#return x/>
</#function>

