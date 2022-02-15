<%@ page import="org.pucrs.ages.UserService" %>
<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
    <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

    <g:if test="${ theme }">
        <asset:stylesheet src="${theme}.css"/>
    </g:if>
    <g:else>
        <asset:stylesheet src="application.css"/>
    </g:else>

    <g:layoutHead/>
</head>

<body  class="body-background">
    <g:set var="dotDistance" value="1em"/>

    <svg class="background-pattern">
        <mask maskUnits="userSpaceOnUse" id="fade">
            <linearGradient id="gradient" x1="0" y1="0" x2="100%" y2="100%">
                <stop offset="0" style="stop-color: #11252c"></stop>
            </linearGradient>
            <rect fill="url(#gradient)" width="100%" height="100%"></rect>
        </mask>
        <pattern id="pattern-circles" x="1em" y="1em" width="${dotDistance}" height="${dotDistance}" patternUnits="userSpaceOnUse">
            <circle mask="url(#fade)" cx="2px" cy="2px" r="2px"></circle>
        </pattern>
        <rect x="0" y="0" width="100%" height="100%" fill="url(#pattern-circles)"></rect>
    </svg>

    <nav class="navbar navbar-expand-lg navbar-dark navbar-static-top header-background" role="navigation">
        <a class="navbar-brand" width="200" height="70" href="/dashboard"><asset:image src="ages.svg" alt="AGES Logo"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" aria-expanded="false" style="height: 0.8px;" id="navbarContent">
            <ul class="nav navbar-nav ml-auto">
                <g:pageProperty name="page.nav"/>
                <sec:ifLoggedIn>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            <sec:loggedInUserInfo field='name'/>
                        </a>
                        <div class="dropdown-menu navbar-dark dropdown-color">
                            <g:if test="${UserService.hasHigherPermissions(this.user)}">
                                <g:form controller="user" action="pending">
                                    <g:submitButton class="dropdown-item navbar-dark color-light" name="Submit" value="${message(code:'default.user.pending')}"/>
                                </g:form>
                            </g:if>
                            <g:form controller="user" action="configurations">
                                <g:submitButton class="dropdown-item navbar-dark color-light" name="Submit" value="${message(code:'default.user.configurations')}"/>
                            </g:form>
                            <g:form controller="logout">
                                <g:submitButton class="dropdown-item navbar-dark color-light" name="Submit" value="${message(code:'default.security.logout')}"/>
                            </g:form>
                        </div>
                    </li>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                    <a class="nav-link  navbar-dark" href="/login" id="navlogin">
                        <g:message code="default.security.form.register.login"/>
                    </a>
                </sec:ifNotLoggedIn>
            </ul>
        </div>
    </nav>



    <div class="container">
        <g:layoutBody/>
    </div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <asset:javascript src="application.js"/>
</body>
</html>