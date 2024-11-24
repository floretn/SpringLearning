<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/"><b>Payment System</b></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
        </ul>
        <div class="navbar-text mr-3">
            <#if user??>
                ${name}
            <#else>
                Guest
            </#if>
        </div>
        <#if user??>
            <@l.logout/>
        <#else>
            <a href="http://localhost:8081/login" class="btn btn-primary" role="button" aria-pressed="true">
                Sign In/Up
            </a>
        </#if>
    </div>
</nav>