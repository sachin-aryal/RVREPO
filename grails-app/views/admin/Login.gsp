<%--
  Created by IntelliJ IDEA.
  User: sachin
  Date: 2/3/2015
  Time: 11:01 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title> Student Result Viewer </title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'custom.css')}" type="text/css">
</head>
<div id="mainWrapper">
    <header>
        <div id="headerWrapper">
            <div id="logo">
                <img src="${resource(dir: 'images', file: 'dwit_logo.png')}"/>
            </div>
            <div id="prName">
                <h1>Result Viewer</h1>
            </div>
        </div>
    </header>

    <body>
    <div id="container">

        <g:form  class="loginForm" controller="admin" action="loginValidator">
            <fieldset>
                <legend>
                    <h2>Login:</h2>
                </legend>

                <div class="loginFormInside">

                    <g:textField type="text" name="Username" placeholder="Username" required=""/>
                    <br>
                    <br>
                    <g:passwordField name="Password" placeholder="Password" required=""/>
                    <br>
                    <br>
                    <g:submitButton name="Login">Login</g:submitButton>
                    %{--<input type="submit" value="Login">--}%
                    <br>
                    <br>

                  <g:if test="${flash.message}">
                      ${flash.message}
                  </g:if>
                </div>
            </fieldset>
        </g:form>

    </div>

    </body>

    <footer>

        <div id="footerWrapper">
            <p>
                Copyright &copy; 2015
            </p>
        </div>

    </footer>

</div>
</html>