<table class="tableDesign">
<g:if test="${flash.message}">
    <div class="userListMessage">
        <p>${flash.message}</p>
    </div>
</g:if>

<h1 id="tableheader">User List</h1>

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
<g:if test="${studentInstanceList}">
    <div id="userValue">
        <tbody>
        <g:each in="${studentInstanceList}" status="i" var="studentInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}" style="text-align: center">
                <td id="userData">${studentInstance.id}</td>

                <td id="userData">${fieldValue(bean: studentInstance, field: "rollno")} </td>
                <g:if test="${studentInstance.name.length()>=17}">
                    <td id="userData">${fieldValue(bean: studentInstance, field: "name").subSequence(0,13)+".."}</td>
                </g:if>
                <g:else>
                    <td id="userData">${fieldValue(bean: studentInstance, field: "name")}</td>
                </g:else>

                <td id="userData">${studentInstance?.batch}</td>

                <td id="userData">${fieldValue(bean: studentInstance, field: "userName")}</td>

                <td id="userData">${fieldValue(bean: studentInstance, field: "password").encodeAsHex()}</td>
                <g:form action="edit">
                    <g:hiddenField type="number" name="id" value="${studentInstance.id}"/>
                    <td><button>Edit</button></td>
                </g:form>
                <g:form action="delete">
                    <g:hiddenField type="number" name="id" value="${studentInstance.id}"/>
                    <td><button onclick="alert('Deleting the User')">Delete</button></td>
                </g:form>
            </tr>
        </g:each>
        </tbody>
    </div>
<tfoot>
<div class="pagination">
    <g:paginate total="${studentInstanceTotal}" />
</div>
</tfoot>
</g:if>
<g:else>
    <h1>No Result Found!!</h1>
</g:else>
</table>