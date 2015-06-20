<%@ page import="ResultViewer.Student" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
    <title>Result Viewer</title>
    <style type="text/css">
    .message{
        font-style: italic;
        color:red;
    }
    .content {
        width: 75%;
    }
    div#Register {
        margin-left: 20px;
        margin-top: -15px;
    }
    .Regisration-head span {
        background: url(../images/user-icon.png) no-repeat -5px -6px;
        position: absolute;
        top: 19%;
        left: 25%;
        height: 40px;
        width: 40px;
    }
    </style>

</head>
<body>
<div id="create-student" class="content scaffold-create" role="main">
    <div class="importStudent1">
        <g:uploadForm controller="student" action="doUploadNewStudent">
            <fieldset class="form">
                <input type="file" name="file" />
            </fieldset>
            <fieldset class="uploadStudent">
                <g:submitButton name="doUpload" value="Upload" />
            </fieldset>
        </g:uploadForm>
    </div>
    <div class="Regisration">
        <div class="Regisration-head">
            <h2><span></span><div id="Register">Register</div></h2>
        </div>
        <g:if test="${flash.message}">
            <div class="message" role="status"><i>${flash.message}</i></div>
        </g:if>
        <g:form action="save" >
            <fieldset class="NewAccount">
                <g:render template="form"/>
            </fieldset>
            <fieldset class="buttons">
                <div class="submit">
                    <g:submitButton name="create" class="save" value="Register" /><%--${message(code: 'default.button.create.label', default: 'Create')}--%>
                </div>
            </fieldset>
        </g:form>

    </div>


</div>
</body>
</html>
