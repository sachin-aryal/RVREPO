
<%@ page import="ResultViewer.Student" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Result Viewer</title>
    <g:javascript library="jquery"/>
    %{--<g:javascript>--}%
        %{--function showResult(str){--}%
        %{--<g:remoteFunction controller="student" action="SearchStudent" update="StudentSearch" params="'query='+str"/>--}%
        %{--}--}%
    %{--</g:javascript>--}%
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
    %{--<div id="ajaxSearch">
        <form>
            <input type="text" size="30" onkeyup="showResult(this.value)"/>
        </form>
    </div>--}%
    %{--    <g:form enctype="multipart/form-data" controller = "student">
            <fieldset>
                <g:textArea name="sheetName" placeholder="SheetName"></g:textArea>
            </fieldset>
            <fieldset>
                <input type="file" name="file" />
            </fieldset>
            <g:actionSubmit value="Submit" action = "importStudent"/>
        </g:form>--}%
    <g:if test="${studentInstanceList}">
        <g:if test="${listType.equalsIgnoreCase("All")}">
            <table class="tableDesign">
                <g:render template="userList"/>
            </table>
        </g:if>

        <div id="StudentSearch" class="tableDesign">

        </div>
    </g:if>
    <g:else>
        <p>No User Found!!</p>
    </g:else>

</div>

</body>
</html>

