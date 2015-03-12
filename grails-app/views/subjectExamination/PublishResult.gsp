<%--
  Created by IntelliJ IDEA.
  User: sachin
  Date: 2/3/2015
  Time: 3:05 PM
--%>
<%--index File in subjectExamination--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>
<div id="subjectList">
    <g:if test="${flash.message}">
        ${flash.message}
    </g:if>
        <g:render template="subjectList"/>
    <div class="importResult">
        <g:uploadForm controller="result" action="importResult">
            <fieldset class="form">
                <input type="file" name="file" />
            </fieldset>
            <fieldset class="uploadResult">
                <g:submitButton name="doUpload" value="Upload" />
            </fieldset>
            <g:hiddenField name="Semester" value="${SubjectList.semester.get(0)}"/>
            <g:hiddenField name="Examination" value="${SubjectList.examination.get(0)}"/>
        </g:uploadForm>
    </div>
</div>


</body>
</html>