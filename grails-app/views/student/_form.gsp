<%@ page import="ResultViewer.Student" %>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'name', 'error')} ">
    <label for="name">
        <g:message code="student.name.label" default="Name" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" value="${studentInstance?.name}" placeholder="Name" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'rollno', 'error')} required">
    <label for="rollno">
        <g:message code="student.rollno.label" default="Rollno" />
        <span class="required-indicator">*</span>
    </label>
    <g:field name="rollno" type="text" placeholder="RollNo" required=""/>%{-- value="${studentInstance.rollno}"--}%
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'batch', 'error')} required">
	<label for="batch">
		<g:message code="student.batch.label" default="Batch" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="batch" type="text"  placeholder="Batch" required=""/>%{--value="${studentInstance.batch}"--}%
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