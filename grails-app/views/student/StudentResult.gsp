<%--
  Created by IntelliJ IDEA.
  User: sachin
  Date: 2/4/2015
  Time: 5:53 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="result">
    <g:if test="${ResultInstanceList}">
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
                        def total=0,status=null,percentage,fullMark=0
                        for(int i=0;i<SubjectExaminationInstance.size();i++) {
                            total+=ResultInstanceList.marks[i]
                            fullMark+=SubjectExaminationInstance.fullMarks[i]
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

    </g:if>
</div>
</body>
</html>