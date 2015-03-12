<%--
  Created by IntelliJ IDEA.
  User: sachin
  Date: 3/3/2015
  Time: 8:46 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Result Viewer</title>
    <style type="text/css">

    </style>
</head>

<body>
<div id="userList" class="content scaffold-list" role="main">

    <table class="tableDesign">
        <g:if test="${studentInstanceList.size()>60}">
            <h1 id="tableheader">Student List</h1>
        </g:if>
        <g:else>
            <h1 id="tableheader">Student Batch ${studentInstanceList.batch.get(0)}</h1>
        </g:else>
        <thead>
        <tr>
            <g:sortableColumn id="userListHeader" property="id" title="${message(code: 'student.id.label', default: 'Id')}" />

            <g:sortableColumn id="userListHeader" property="rollno" title="${message(code: 'student.rollno.label', default: 'Rollno')}" />

            <g:sortableColumn id="userListHeader" property="name" title="${message(code: 'student.name.label', default: 'Name')}" />

            <g:sortableColumn id="userListHeader" property="batch" title="${message(code: 'student.batch.label', default: 'Batch')}" />

        </tr>
        </thead>
        <tbody>
        <g:each in="${studentInstanceList}" status="i" var="studentInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td id="userData"><g:link controller="student" action="StudentReport" params="[id:studentInstance.id]">${fieldValue(bean: studentInstance, field: "id")}</g:link></td>

                <td id="userData"><g:link controller="student" action="StudentReport" params="[id:studentInstance.id]">${fieldValue(bean: studentInstance, field: "rollno")}</g:link> </td>

                <td id="userData"><g:link controller="student" action="StudentReport" params="[id:studentInstance.id]">${fieldValue(bean: studentInstance, field: "name")}</g:link></td>

                <td id="userData"><g:link controller="student" action="StudentReport" params="[id:studentInstance.id]">${studentInstance?.batch}</g:link></td>
            </tr>
        </g:each>
        </tbody>
        %{--${flash.message}--}%
    </table>
    <div class="pagination">
        <g:paginate next="Next" prev="Back" controller="student" action="StudentList"
                    total="${studentInstanceTotal}" params="${params}"/>
    </div>
</div>
</body>
</html>