<%--
  Created by IntelliJ IDEA.
  User: sachin
  Date: 3/8/2015
  Time: 4:14 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Result Viewer</title>
    <meta name="layout" content="main">
    <style type="text/css">
    div#reportTitle {
        display: block;
        text-align: center;
        font-size: 22px;
        font-style: inherit;
        font-family: serif;
        color: rgb(28, 72, 119);
    }
    </style>
</head>

<body>

<%
    def username
    if(StudentInstance){
        username=StudentInstance.userName
    }
    else if(session.getAttribute("Username")){
        username=session.getAttribute("Username")
    }
    else{
        username=null
    }
%>
<div id="sReport" style="width: 960px;margin: 0px auto;border: 1px solid black">
    <div class="ui list" style="margin: 0px auto">
        <div class="item">
            <img class="ui top aligned avatar image" src="${resource(dir: 'StudentImage', file:username+".jpg")}" style="height: 80px;width: 100px">
            <div class="content">
                <div class="header">${StudentInstance.name}</div>

                <div class="list">
                    <div class="item">
                        <i class="right triangle icon"></i>
                        <div class="content">
                            <a class="header">Roll No</a>
                            <div class="description">${StudentInstance.rollno}</div>
                        </div>
                    </div>
                    <div class="item">
                        <i class="right triangle icon"></i>
                        <div class="content">
                            <a class="header">Batch</a>
                            <div class="description">${StudentInstance.batch}</div>
                        </div>
                    </div>
                    <div class="item">
                        <i class="right triangle icon"></i>
                        <div class="content">
                            <a class="header">Address</a>
                            <div class="description">Gulmi,Nepal</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="studentReport">
        <div class="Report">

            %{--<div id="personalInfo">
                <table class="tableDesign" style="width: 33%;margin-top: 59px;">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>${StudentInstance.name}</th>
                    </tr>
                    <tr>
                        <th>Roll No</th>
                        <th>${StudentInstance.rollno}</th>
                    </tr>
                    <tr>
                        <th>Batch</th>
                        <th>${StudentInstance.batch}</th>
                    </tr>

                    </thead>
                </table>

            </div>--}%


            %{--<img style="height:150px;margin-top: -150px" src="${resource(dir: 'StudentImage', file:username+".jpg")}"/>--}%

            %{--</div>--}%
            %{--<br><br>--}%

            <div id="allReport" style="border: 1px solid rgb(28, 72, 119);  position: relative;margin-bottom: 43px;">
                <div id="reportTitle">
                    <h1><u>Report</u></h1>
                </div>
                <div id="curve_chart" style="width: 960px; height: 500px;  margin-top: 20px;">

                </div>
                <div class="tableDesign" style="margin-top: -50px;">
                    <table cellpadding="10" cellspacing="10" border="0">
                        <thead>
                        <tr id="resultTableHeader">
                            <th>Semester</th>
                            <th>Examination</th>
                            <th>Percentage</th>
                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${Percentage}" status="i" var="percentage">
                            <g:if test="${percentage}">
                                <tr id="resultTableData">
                                    <td>${Semester[i]}</td>
                                    <td>${Examination[i]}</td>
                                    <td><g:formatNumber number="${Percentage[i]}" format="0.00"/> </td>
                                </tr>
                            </g:if>
                        </g:each>

                        </tbody>
                    </table>
                </div>

            </div>
            %{--var data = new google.visualization.arrayToDataTable([['Midterm', 'Preboard', 'Percentage'], ['${Examination[0]}', '${Semester[0]}', ${Percentage[0]}], ['${Examination[1]}', '${Semester[1]}', ${Percentage[1]}], ['${Examination[2]}', '${Semester[2]}', ${Percentage[2]}], ['${Examination[4]}', '${Semester[4]}', ${Percentage[4]}], ['${Examination[5]}', '${Semester[5]}', ${Percentage[5]}]]);--}%

        </div>
        <g:javascript>
    var myData=new Array();
    var data="${Percentage}".split(',');
    var semester="${Semester}".split(',');
    var examination="${Examination}".split(',');
    var i;
    for(i=0;i<(data.length);i++){
        if(data[i]!=null){
            myData.push([i+1,parseFloat(data[i].replace('[', '').replace(']',''))]);
        }
    }
var myChart = new JSChart('curve_chart', 'line');
myChart.setDataArray(myData);
myChart.setAxisNameFontSize(13);
myChart.setAxisValuesFontSize(11);
myChart.setAxisNameX('Examination');
myChart.setAxisNameY('Percentage');
myChart.setAxisNameColor('#787878');
myChart.setAxisValuesNumberX(data.length);
//myChart.setAxisValuesNumberY(10);
myChart.setAxisValuesColor('#38a4d9');
myChart.setAxisColor('#38a4d9');
myChart.setLineColor('#1c4877');
myChart.setTitle('Student Report');
myChart.setTitleFontSize(20);
myChart.setTitleColor("#1c4877");
myChart.setGraphExtend(true);
myChart.setGridColor('#38a4d9');
myChart.setSize(960, 400);
myChart.setAxisPaddingLeft(140);
myChart.setAxisPaddingRight(140);
myChart.setAxisPaddingTop(60);
myChart.setAxisPaddingBottom(45);
myChart.setTextPaddingLeft(105);
myChart.setTextPaddingBottom(12);
myChart.setLineSpeed(90);
myChart.setLineWidth(3);
        %{--myChart.setBackgroundImage('path/background.jpg');--}%
            for(i=0;i<16;i++){
                    if(data[i]!=null){
                        myChart.setTooltip([i+1,semester[i].replace('[', '').replace(']', '')+"->"+examination[i].replace('[', '')
                        .replace(']', '')+" Percentage:"+data[i].replace('[', '').replace(']','')]);
                    }
                }
            myChart.setTooltipBackground('#fff');
            myChart.setTooltipFontSize(18);
            //myChart.setTooltipSize(14);
            //myChart.setLegendForLine(1,sachin);
            myChart.draw();

        </g:javascript>
</body>
</html>