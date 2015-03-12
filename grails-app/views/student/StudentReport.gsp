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
</head>

<body>
<div class="studentReport">
    <div class="tableDesign">
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
                    <td>${"Semester"+i}</td>
                    <td>${"Examination"+i}</td>
                    <td><g:formatNumber number="${Percentage[i]}" format="0.00"/> </td>
                </tr>
                </g:if>
            </g:each>

            </tbody>
        </table>
    </div>
    <div id="curve_chart" style="width: 960px; height: 500px;">

    </div>
    %{--var data = new google.visualization.arrayToDataTable([['Midterm', 'Preboard', 'Percentage'], ['${Examination[0]}', '${Semester[0]}', ${Percentage[0]}], ['${Examination[1]}', '${Semester[1]}', ${Percentage[1]}], ['${Examination[2]}', '${Semester[2]}', ${Percentage[2]}], ['${Examination[4]}', '${Semester[4]}', ${Percentage[4]}], ['${Examination[5]}', '${Semester[5]}', ${Percentage[5]}]]);--}%

</div>
<g:javascript>
    var myData=new Array();
    var data=new Array();
    data=${Percentage};
    console.log("Data Size"+data.length);
    var i=0;
    for(i=0;i<16;i++){
        if(data[i]!=null){
            myData.push([i+1,data[i]]);

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
myChart.setTitleColor('#383838');
myChart.setGraphExtend(true);
myChart.setGridColor('#38a4d9');
myChart.setSize(616, 321);
myChart.setAxisPaddingLeft(140);
myChart.setAxisPaddingRight(140);
myChart.setAxisPaddingTop(60);
myChart.setAxisPaddingBottom(45);
myChart.setTextPaddingLeft(105);
myChart.setTextPaddingBottom(12);
myChart.setLineSpeed(90);
myChart.setLineWidth(4);
myChart.setBackgroundImage('path/background.jpg');
for(i=0;i<16;i++){
        if(data[i]!=null){
            myChart.setTooltip([i+1,"Semester"+i+1])
        }
    }
myChart.setTooltipBackground('#fff');
%{--myChart.setTooltip([1,'${Semester[0]}'+" Semester Midterm->"+${Percentage[0]}]);
myChart.setTooltip([2,'${Semester[0]}'+" Semester Preboard->"+${Percentage[1]}]);
myChart.setTooltip([3,'${Semester[1]}'+" Semester Midterm->"+${Percentage[2]}]);
myChart.setTooltip([4,'${Semester[1]}'+" Semester Preboard->"+${Percentage[3]}]);
myChart.setTooltip([5,'${Semester[2]}'+" Semester Midterm->"+${Percentage[4]}]);
myChart.setTooltip([6,'${Semester[2]}'+" Semester Preboard->"+${Percentage[5]}]);--}%
    myChart.draw();
</g:javascript>
</body>
</html>