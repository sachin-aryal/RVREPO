
<g:if test="${SubjectList}">

    <table class="tableDesign">
        <g:form class="subjectListForm" controller="result" action="PublishResult">
            <thead>
            <tr id="resultTableHeader">
                <th>Rollno</th>
                <g:each in="${SubjectList}" var="subjectList">
                    <th>${subjectList.subjectName}</th>
                </g:each>
            </tr>
            </thead>
            <tbody>
            <tr id="resultTableData">
                <td><g:textField type="number" name="Rollno" required="" placeholder="Rollno"/></td>
                <g:each in="${SubjectList}" var="subjectList">
                    <td><g:textField type="text" name="${subjectList.subjectName}" placeholder="${subjectList.subjectName}" required="" max="${subjectList.fullMarks}" min="0"/></td>
                </g:each>
            </tr>
            </tbody>
            <g:hiddenField name="Semester" value="${SubjectList.semester.get(0)}"/>
            <g:hiddenField name="Examination" value="${SubjectList.examination.get(0)}"/>

                <td><g:submitButton name="Save" class="submitResult" value="Save" /></td><%--${message(code: 'default.button.create.label', default: 'Create')}--%>

        </g:form>
    </table>
</g:if>
<g:else>
    <g:message>No Subject List is Received.</g:message>
</g:else>
