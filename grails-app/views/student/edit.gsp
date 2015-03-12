<%@ page import="ResultViewer.Student" %>
<!DOCTYPE html>
<html>
	<head>
        %{--<link rel="stylesheet" href="${resource(dir: 'css', file: 'formDesign.css')}" type="text/css">--}%
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="edit-student" class="content scaffold-edit" role="main">
            <div class="Regisration">
			<h1>Edit</h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status"><i>${flash.message}</i></div>
                </g:if>
			<g:form method="post" action="update" >
				<fieldset class="form">
                    <div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'rollno', 'error')} required">
                        <label for="rollno">
                            <g:message code="student.rollno.label" default="Rollno" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="rollno" type="text" value="${studentInstance?.rollno}" placeholder="RollNo"  required=""/>%{-- value="${studentInstance.rollno}"--}%
                    </div>

                    <div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'name', 'error')} ">
                        <label for="name">
                            <g:message code="student.name.label" default="Name" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="name" value="${studentInstance?.name}" placeholder="Name" required=""/>
                    </div>


                    <div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'batch', 'error')} required">
                        <label for="batch">
                            <g:message code="student.batch.label" default="Batch" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:field name="batch" type="text" value="${studentInstance?.batch}" placeholder="Batch" required=""/>%{--value="${studentInstance.batch}"--}%
                    </div>

                    <div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'userName', 'error')} ">
                        <label for="userName">
                            <g:message code="student.userName.label" default="User Name" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="userName" value="${studentInstance?.userName}" placeholder="Username" required=""/>
                    </div>

                    <div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'password', 'error')} ">
                        <label for="password">
                            <g:message code="student.password.label" default="Password" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="password" value="${studentInstance?.password}" placeholder="Password" required=""/>


                    </div>
				</fieldset>
				<fieldset class="buttons">
                    <div class="submit">
                        <g:hiddenField name="id" value="${studentInstance.id}"/>
                        <g:hiddenField name="actualRollno" value="${studentInstance.rollno}"/>
                        <g:hiddenField name="actualUsername" value="${studentInstance.userName}"/>
                        <g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                    </div>

				</fieldset>
			</g:form>
		</div>
        </div>
	</body>
</html>
