

<div id="nav">
    <ul id="navElementStudent">
        <li>
<g:link controller="student" action="index">Home</g:link>
</li>

<li>
    <a href="#"onclick="alert('Use the Drop Down Menu')">View Result</a>
    <ul>
        <li>
            <a href="#"onclick="alert('Use the Drop Down Menu')">Semester I</a>
            <ul>
                <li>
                    <g:link controller="student" action="getResult" params="[Semester:'1',Examination:'1']">Midterm</g:link>
                </li>
                <li>
                    <g:link controller="student" action="getResult" params="[Semester:'1',Examination:'2']">Preboard</g:link>
                </li>
            </ul>
        </li>
        <li>
            <a href="#"onclick="alert('Use the Drop Down Menu')">Semester II</a>
            <ul>
                <li>
                    <g:link controller="student" action="getResult" params="[Semester:'2',Examination:'1']">Midterm</g:link>
                </li>
                <li>
                    <g:link controller="student" action="getResult" params="[Semester:'2',Examination:'2']">Preboard</g:link>
                </li>
            </ul>
        </li>
        <li>
            <a href="#"onclick="alert('Use the Drop Down Menu')">Semester III</a>
            <ul>
                <li>
                    <g:link controller="student" action="getResult" params="[Semester:'3',Examination:'1']">Midterm</g:link>
                </li>
                <li>
                    <g:link controller="student" action="getResult" params="[Semester:'3',Examination:'2']">Preboard</g:link>
                </li>
            </ul>
        </li>
        <li>
            <a href="#"onclick="alert('Use the Drop Down Menu')">Semester IV</a>
            <ul>
                <li>
                    <g:link controller="student" action="getResult" params="[Semester:'4',Examination:'1']">Midterm</g:link>
                </li>
                <li>
                    <g:link controller="student" action="getResult" params="[Semester:'4',Examination:'2']">Preboard</g:link>
                </li>
            </ul>
        </li>
        <li>
            <a href="#"onclick="alert('Use the Drop Down Menu')">Semester V</a>
            <ul>
                <li>
                    <g:link controller="student" action="getResult" params="[Semester:'5',Examination:'1']">Midterm</g:link>
                </li>
                <li>
                    <g:link controller="student" action="getResult" params="[Semester:'5',Examination:'2']">Preboard</g:link>
                </li>
            </ul>
        </li>
        <li>
            <a href="#"onclick="alert('Use the Drop Down Menu')">Semester VI</a>
            <ul>
                <li>
                    <g:link controller="student" action="getResult" params="[Semester:'6',Examination:'1']">Midterm</g:link>
                </li>
                <li>
                    <g:link controller="student" action="getResult" params="[Semester:'6',Examination:'2']">Preboard</g:link>
                </li>
            </ul>
        </li>
        <li>
            <a href="#"onclick="alert('Use the Drop Down Menu')">Semester VII</a>
            <ul>
                <li>
                    <g:link controller="student" action="getResult" params="[Semester:'7',Examination:'1']">Midterm</g:link>
                </li>
                <li>
                    <g:link controller="student" action="getResult" params="[Semester:'7',Examination:'2']">Preboard</g:link>
                </li>
            </ul>
        </li>
        <li>
            <a href="#"onclick="alert('Use the Drop Down Menu')">Semester VIII</a>
            <ul>
                <li>
                    <g:link controller="student" action="getResult" params="[Semester:'8',Examination:'1']">Midterm</g:link>
                </li>
                <li>
                    <g:link controller="student" action="getResult" params="[Semester:'8',Examination:'2']">Preboard</g:link>
                </li>

            </ul>
        </li>
    </ul>
</li>
<li>
    <%
        def id=session.getAttribute("id")
    %>

    <g:link controller="student" action="StudentReport" params="[id: id]">View Report</g:link>
</li>
%{--  <li>
      <a href="#"onclick="alert('Use the Drop Down Menu')">Student</a>
      <ul>
          <li>
              <g:link controller="student" action="studentList" params="[batch:2015]">Batch 2015</g:link>
          </li>
          <li>
              <g:link controller="student" action="studentList" params="[batch:2016]">Batch 2016</g:link>
          </li>
          <li>
              <g:link controller="student" action="studentList" params="[batch:2017]">Batch 2017</g:link>
          </li>
          <li>
              <g:link controller="student" action="studentList" params="[batch:2018]">Batch 2018</g:link>
          </li>
      </ul>
  </li>--}%
<li>
    <g:link controller="admin" action="aboutUs">AboutUs</g:link>
</li>
<li>
    <g:link controller="admin" action="contactUs">ContactUs</g:link>
</li>

<li>
    <g:link controller="admin" action="logout">Logout</g:link>
</li>
</ul>
</div>
<!-- end navbar -->


