<%--
  Created by IntelliJ IDEA.
  User: sachin
  Date: 2/26/2015
  Time: 12:46 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Result Viewer</title>
    <meta name="layout" content="main">
</head>

<body>
<div class="result">
    <g:if test="${ResultInstanceList}">
        <table class="tableDesign">
            <div class="ResultTable-head">
                <h1><span>Edit Result</span></h1>
            </div>
            <g:form class="subjectListForm" controller="result" action="update">
                <thead>
                <tr id="resultTableHeader">
                    <th>Rollno</th>
                    <g:each in="${ResultInstanceList}" var="resultInstanceList" status="i">
                        <th>${SubjectExaminationInstance.subjectName.get(i)}</th>
                    </g:each>
                </tr>
                </thead>
                <tbody>
                <tr id="resultTableData">
                    <g:hiddenField name="id" value="${StudentInstance.id}"/>
                    <td><g:textField name="EditedRollno" value="${StudentInstance.rollno}"/></td>
                    <g:each in="${ResultInstanceList}" var="resultInstanceList" status="i">
                        <td><g:textField name="${SubjectExaminationInstance.subjectName.get(i)}" value="${resultInstanceList.marks}"/></td>
                    </g:each>>
                </tr>
                </tbody>
                <g:hiddenField name="Semester" value="${SubjectExaminationInstance.semester.get(0)}"/>
                <g:hiddenField name="Examination" value="${SubjectExaminationInstance.examination.get(0)}"/>
                <td><g:submitButton name="Save" class="submitResult" value="Update" /></td>
            </g:form>
        </table>
    </g:if>
</div>
</body>
</html>