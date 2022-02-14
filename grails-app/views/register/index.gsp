<%@ page import="org.pucrs.ages.Project; org.pucrs.ages.Role" %>
<html>
<head>
    <meta name="layout" content="${gspLayout ?: 'main'}"/>
    <title><g:message code="default.security.register.page.title"/></title>
</head>

<body>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function(event) {
        document.forms['loginForm'].elements['username'].focus();
    });

    function information(topic, information) {
        swal(topic, information)
    }
</script>

<div class="row">
    <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="vertical-center card card-signin my-5">
            <div class="card-body">
                <h5 class="card-title text-center"><g:message code="default.security.register.page"/></h5>
                <g:if test="${flash.message}">
                    <div class="alert alert-danger" role="alert">${flash.message}</div>
                </g:if>
                <g:if test="${flash.info}">
                    <div class="alert alert-message" role="alert">${flash.info}</div>
                </g:if>

                <form class="form-signin" action="register" method="POST" id="loginForm" autocomplete="off">

                    <div class="form-group">
                        <label for="project"><g:message code="default.security.project"/></label>
                        <g:select class="form-control" name="project.id"
                                  from="${Project.list()}"
                                  optionKey="id" />
                    </div>

                    <div class="form-group">
                        <label for="username"><g:message code="default.security.username"/></label>
                        <label class="information" onclick="information('${message(code:'default.security.username')}', '${message(code:'default.security.username.information')}')"> </label>
                        <input required type="text" placeholder="<g:message code="default.security.form.username"/>" class="form-control" name="username" id="username" autocapitalize="none"/>
                    </div>

                    <div class="form-group">
                        <label for="password"><g:message code="default.security.password"/></label>
                        <label class="information" onclick="information('${message(code:'default.security.password')}', '${message(code:'default.security.password.information')}')"> </label>
                        <input required type="password" placeholder="<g:message code="default.security.form.password"/>" class="form-control" name="password" id="password"/>
                    </div>

                    <div class="form-group">
                        <label for="password"><g:message code="default.security.reenterpassword"/></label>
                        <input required type="password" placeholder="<g:message code="default.security.form.reenterpassword"/>" class="form-control" name="repassword" id="repassword"/>
                    </div>

                    <div class="form-group">
                        <label for="name"><g:message code="default.security.name"/></label>
                        <input required type="text" placeholder="<g:message code="default.security.form.name"/>" class="form-control" name="name" id="name" autocapitalize="none"/>
                    </div>

                    <div class="form-group">
                        <label for="registration"><g:message code="default.security.registration"/></label>
                        <label class="information" onclick="information('${message(code:'default.security.registration')}', '${message(code:'default.security.registration.information')}')"> </label>
                        <input required type="text" pattern="\d{8}-\d{1}" placeholder="<g:message code="default.security.form.registration"/>" class="form-control" name="registration" id="registration" autocapitalize="none"/>
                    </div>

                    <button id="submit" class="btn btn-lg btn-primary btn-block text-uppercase" type="submit"><g:message code="default.security.form.confirm"/></button>
                    <hr class="my-4">
                    <p><g:message code="default.security.form.register.message.login"/> <g:link controller="login" action="auth"><g:message code="default.security.form.register.login"/></g:link></p>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
