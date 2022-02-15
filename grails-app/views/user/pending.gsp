<%@ page import="org.pucrs.ages.User; org.pucrs.ages.UserService" %>
<%--
  Created by IntelliJ IDEA.
  User: cleys
  Date: 14/02/2022
  Time: 17:05
--%>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'pending.label', default: 'Pending')}" />
    <title><g:message code="default.home.label" args="[entityName]" /></title>
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
    <div class="col-sm-7 col-md-12 col-lg-5 mx-auto">
        <div class="vertical-center card card-signin my-5">
            <div class="card-body card-color">
                <h5 class="card-title text-center"><g:message code="default.user.pending"/></h5>
                <g:if test="${flash.message}">
                    <div class="alert alert-danger" role="alert">${flash.message}</div>
                </g:if>
                <g:if test="${flash.info}">
                    <div class="alert alert-message" role="alert">${flash.info}</div>
                </g:if>

                <g:form class="form-signin" resource="${it}" method="PUT" id="loginForm" autocomplete="off">
                    <table>
                        <tr>
                            <td class="text-center"><g:message code="default.security.name"/></td>
                            <td class="text-center"><g:message code="default.security.registration"/></td>
                            <td class="text-center"><g:message code="default.security.ages"/></td>
                            <td class="text-center"><g:message code="default.security.project"/></td>
                            <td class="text-center"><g:message code="default.security.accept"/></td>
                            <td class="text-center"><g:message code="default.security.reject"/></td>
                        </tr>
                        <g:each in="${pendingList}">
                            <tr>
                                <th class="text-center">${it.name}</th>
                                <th class="text-center">${it.registrationId}</th>
                                <th class="text-center">${it.ages}</th>
                                <th class="text-center">${UserService.findProjectByUsers(it as User)}</th>
                                <th class="center"><button id="submit" class="approval" type="submit"></button></th>
                                <th class="center"><button id="cancel" class="refusal" type="submit"></button></th>
                            </tr>
                        </g:each>
                    </table>
                </g:form>

%{--                <g:form class="form-signin" resource="${this.user}" method="PUT" id="loginForm" autocomplete="off">--}%
%{--                    <div class="form-group">--}%
%{--                        <div class="form-group">--}%
%{--                            <label for="theme"><g:message code="default.user.configurations.theme"/></label>--}%
%{--                            <g:select class="form-control" name="theme.id"--}%
%{--                                      from="${ThemeMode.list()}"--}%
%{--                                      optionKey="id" />--}%
%{--                        </div>--}%
%{--                        <div class="form-group">--}%
%{--                            <label for="password"><g:message code="default.security.password"/></label>--}%
%{--                            <label class="information" onchange="fill()" onclick="information('${message(code:'default.security.password')}', '${message(code:'default.security.password.information')}')"> </label>--}%
%{--                            <input type="password" placeholder="<g:message code="default.security.form.password"/>" class="form-control" name="password" id="password"/>--}%
%{--                        </div>--}%
%{--                        <div class="form-group">--}%
%{--                            <label for="password"><g:message code="default.security.reenterpassword"/></label>--}%
%{--                            <input type="password" placeholder="<g:message code="default.security.form.reenterpassword"/>" class="form-control" name="repassword" id="repassword"/>--}%
%{--                        </div>--}%
%{--                    </div>--}%


%{--                    <div class="form-validate">--}%
%{--                        <div class="btn-align">--}%
%{--                            <g:form class="btn-align" controller="dashboard">--}%
%{--                                <g:submitButton class="btn btn-lg btn-primary btn-block text-uppercase" name="cancel" value="${message(code:'default.user.configurations.cancel')}"/>--}%
%{--                            </g:form>--}%
%{--                        </div>--}%
%{--                        <div class="btn-align">--}%
%{--                            <button id="submit" class="btn btn-lg btn-primary btn-block text-uppercase" type="submit"><g:message code="default.user.configurations.confirm"/></button>--}%
%{--                        </div>--}%
%{--                    </div>--}%
%{--                </g:form>--}%
            </div>
        </div>
    </div>
</div>


</body>
</html>