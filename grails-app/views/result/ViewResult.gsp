<%--
  Created by IntelliJ IDEA.
  User: sachin
  Date: 2/4/2015
  Time: 5:53 PM
--%>
<%@ page import="grails.converters.JSON" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="result">
<div class="searchResult">
    <g:form controller="result" action="ViewResult">
        <div class="Result-head">
            <h2><span></span>Search Result</h2>
        </div>
        <g:textField name="RollNo" placeholder="RollNo"/>
        <select name="Semester">
            <option value="1">Semester I</option>
            <option value="2">Semester II</option>
            <option value="3">Semester II</option>
            <option value="4">Semester IV</option>
            <option value="5">Semester V</option>
            <option value="6">Semester VI</option>
            <option value="7">Semester VII</option>
            <option value="8">Semester VII</option>
        </select>
        <select name="Examination">
            <option value="1">Midterm</option>
            <option value="2">Preboard</option>
        </select>
        <input type="submit" value="Search"/>
    </g:form>

</div>
<g:if test="${ResultInstanceList}">
    <div id="message">
    <g:if test="${flash.message}">
        <p>${flash.message}</p>
    </g:if>
    </div>
    <div class="ResultTable-head">
        <h1><span>Result</span></h1>
    </div>
    <div class="tableDesign">
        <table cellpadding="10" cellspacing="10" border="0">
            <thead>
            <tr id="resultTableHeader">
                <th>Name</th>
                <th>Rollno</th>
                <g:each in="${SubjectExaminationInstance}" var="subjectExaminationInstance">
                    <th>${subjectExaminationInstance.subjectName}</th>
                </g:each>
                <th>Total</th>
                <th>Percentage</th>
                <th>Status</th>
                <th>Semester</th>
                <th>Examination</th>
            </tr>
            </thead>
            <tbody>
            <tr id="resultTableData">
                <td>${StudentInstance.name}</td>
                <td>${StudentInstance.rollno}</td>
                <g:each in="${ResultInstanceList}" var="resultInstance">
                    <td><g:formatNumber number="${resultInstance.marks}" format="0.0"/> </td>
                </g:each>

                <%
                    def total=0,status=null,percentage,fullMark=0,marks=[]
                    for(int i=0;i<SubjectExaminationInstance.size();i++) {
                        total+=ResultInstanceList.marks[i]
                        fullMark+=SubjectExaminationInstance.fullMarks[i]
                        marks[i]=ResultInstanceList.marks[i]
                    }
                    for(int i=0;i<SubjectExaminationInstance.size();i++){
                        if(ResultInstanceList.marks[i]<SubjectExaminationInstance.passMarks[i]){
                            status="Fail"
                            break
                        }else{
                            status="Pass"
                        }

                    }
                    percentage=(total/fullMark)*100
                %>
                <td><g:formatNumber number="${total}" format="0.0"/> </td>
                <td><g:formatNumber number="${percentage}" format="0.00"/> </td>
                <td>${status}</td>
                <td>${semester}</td>
                <td>${examination}</td>
            </tr>

            </tbody>
        </table>

    </div>
    <div id="resultAction">
        <g:form action="edit">
            <g:hiddenField name="Semester" value="${SubjectExaminationInstance.semester.get(0)}"/>
            <g:hiddenField name="Examination" value="${SubjectExaminationInstance.examination.get(0)}"/>
            <g:hiddenField name="id" value="${StudentInstance.id}"/>
            <td><button>Edit</button></td>
        </g:form>
        <g:form action="delete">
            <g:hiddenField name="Semester" value="${SubjectExaminationInstance.semester.get(0)}"/>
            <g:hiddenField name="Examination" value="${SubjectExaminationInstance.examination.get(0)}"/>
            <g:hiddenField name="id" value="${StudentInstance.id}"/>
            <td><button>Delete</button></td>
        </g:form>
    </div>
</g:if>
</body>
</html>
