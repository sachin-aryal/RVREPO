
<%@ page import="ResultViewer.Student" %>
<!DOCTYPE html>
<html>
<head>


    <meta name="layout" content="main">
    <title>Result Viewer</title>

</head>
<body>
<div id="userList" class="content scaffold-list" role="main">

    <div class="exportExcelStudentList">
        <g:link controller="student" action="exportStudentList">Export to Excel</g:link>
    </div>
    <div class="importStudent">
        <g:uploadForm controller="student" action="doUploadNewStudent">
            <fieldset class="form">
                <input type="file" name="file" />
            </fieldset>
            <fieldset class="uploadStudent">
                <g:submitButton name="doUpload" value="Upload" />
            </fieldset>
        </g:uploadForm>
    </div>
%{--    <g:form enctype="multipart/form-data" controller = "student">
        <fieldset>
            <g:textArea name="sheetName" placeholder="SheetName"></g:textArea>
        </fieldset>
        <fieldset>
            <input type="file" name="file" />
        </fieldset>
        <g:actionSubmit value="Submit" action = "importStudent"/>
    </g:form>--}%
<table class="tableDesign">

    <g:if test="${flash.message}">
        <div class="userListMessage">
            <p>${flash.message}</p>
        </div>
    </g:if>
    <g:if test="${studentInstanceList}">
        <h1 id="tableheader">User List</h1>
        <div class="searchUser">
            <g:form action="SearchUser">
                <g:textField name="query" placeholder="KeywordSearch" required=""/>
                <button>Search</button>
            </g:form>
        </div>
        <thead>
        <tr>
            <g:sortableColumn id="userListHeader" property="id" title="${message(code: 'student.id.label', default: 'Id')}" />

            <g:sortableColumn id="userListHeader" property="rollno" title="${message(code: 'student.rollno.label', default: 'Rollno')}" />

            <g:sortableColumn id="userListHeader" property="name" title="${message(code: 'student.name.label', default: 'Name')}" />

            <g:sortableColumn id="userListHeader" property="batch" title="${message(code: 'student.batch.label', default: 'Batch')}" />

            <g:sortableColumn id="userListHeader" property="userName" title="${message(code: 'student.userName.label', default: 'User Name')}" />

            <g:sortableColumn id="userListHeader" property="password" title="${message(code: 'student.password.label', default: 'Password')}" />

            <th>Edit</th>

            <th>Delete</th>


        </tr>
        </thead>
        <tbody>
        <g:each in="${studentInstanceList}" status="i" var="studentInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td id="userData">${studentInstance.id}</td>

                <td id="userData">${fieldValue(bean: studentInstance, field: "rollno")} </td>

                <td id="userData">${fieldValue(bean: studentInstance, field: "name")}</td>

                <td id="userData">${studentInstance?.batch}</td>

                <td id="userData">${fieldValue(bean: studentInstance, field: "userName")}</td>

                <td id="userData">${fieldValue(bean: studentInstance, field: "password")}</td>
                <g:form action="edit">
                    <g:hiddenField type="number" name="Rollno" value="${studentInstance.rollno}"/>
                    <td><button>Edit</button></td>
                </g:form>
                <g:form action="delete">
                    <g:hiddenField type="number" name="Rollno" value="${studentInstance.rollno}"/>
                    <td><button onclick="alert('Deleting the User')">Delete</button></td>
                </g:form>
            </tr>
        </g:each>
        </tbody>

    %{--${flash.message}--}%
        </table>
        <div class="pagination">
            <g:paginate total="${studentInstanceTotal}" />
        </div>
    </g:if>
</div>
</body>
</html>

