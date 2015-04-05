<div c
<div id="nav">
    <ul id="navElement">
        <li>
            <g:link controller="admin" action="AdminHome">Home</g:link>
        </li>
        <li>
            <a href=" " onclick="alert('Use the Drop Down Menu')">User</a>
            <ul>

                <li>
                    <g:link controller="student" action="create">New User</g:link>
                </li>
                <li>
                    <g:link controller="student" action="list">View User</g:link>
                </li>
            </ul>
        </li>
        <li>
            <a href="#"onclick="alert('Use the Drop Down Menu')">Publish Result</a>
            <ul>
                <li>
                    <a href="#"onclick="alert('Use the Drop Down Menu')">Semester I</a>
                    <ul>
                        <li>
                            <g:link controller="subjectExamination" action="getSubjectList" params="[Semester:'1',Examination:'1']">Midterm</g:link>
                        </li>
                        <li>
                            <g:link controller="subjectExamination" action="getSubjectList" params="[Semester:'1',Examination:'2']">Preboard</g:link>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"onclick="alert('Use the Drop Down Menu')">Semester II</a>
                    <ul>
                        <li>
                            <g:link controller="subjectExamination" action="getSubjectList" params="[Semester:'2',Examination:'1']">Midterm</g:link>
                        </li>
                        <li>
                            <g:link controller="subjectExamination" action="getSubjectList" params="[Semester:'2',Examination:'2']">Preboard</g:link>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"onclick="alert('Use the Drop Down Menu')">Semester III</a>
                    <ul>
                        <li>
                            <g:link controller="subjectExamination" action="getSubjectList" params="[Semester:'3',Examination:'1']">Midterm</g:link>
                        </li>
                        <li>
                            <g:link controller="subjectExamination" action="getSubjectList" params="[Semester:'3',Examination:'2']">Preboard</g:link>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"onclick="alert('Use the Drop Down Menu')">Semester IV</a>
                    <ul>
                        <li>
                            <g:link controller="subjectExamination" action="getSubjectList" params="[Semester:'4',Examination:'1']">Midterm</g:link>
                        </li>
                        <li>
                            <g:link controller="subjectExamination" action="getSubjectList" params="[Semester:'4',Examination:'2']">Preboard</g:link>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"onclick="alert('Use the Drop Down Menu')">Semester V</a>
                    <ul>
                        <li>
                            <g:link controller="subjectExamination" action="getSubjectList" params="[Semester:'5',Examination:'1']">Midterm</g:link>
                        </li>
                        <li>
                            <g:link controller="subjectExamination" action="getSubjectList" params="[Semester:'5',Examination:'2']">Preboard</g:link>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"onclick="alert('Use the Drop Down Menu')">Semester VI</a>
                    <ul>
                        <li>
                            <g:link controller="subjectExamination" action="getSubjectList" params="[Semester:'6',Examination:'1']">Midterm</g:link>
                        </li>
                        <li>
                            <g:link controller="subjectExamination" action="getSubjectList" params="[Semester:'6',Examination:'2']">Preboard</g:link>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"onclick="alert('Use the Drop Down Menu')">Semester VII</a>
                    <ul>
                        <li>
                            <g:link controller="subjectExamination" action="getSubjectList" params="[Semester:'7',Examination:'1']">Midterm</g:link>
                        </li>
                        <li>
                            <g:link controller="subjectExamination" action="getSubjectList" params="[Semester:'7',Examination:'2']">Preboard</g:link>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"onclick="alert('Use the Drop Down Menu')">Semester VIII</a>
                    <ul>
                        <li>
                            <g:link controller="subjectExamination" action="getSubjectList" params="[Semester:'8',Examination:'1']">Midterm</g:link>
                        </li>
                        <li>
                            <g:link controller="subjectExamination" action="getSubjectList" params="[Semester:'8',Examination:'2']">Preboard</g:link>
                        </li>

                    </ul>
                </li>
            </ul>
        </li>
        <li>
            <g:link controller="result" action="index">View Result</g:link>
        </li>
        <li>
            <a href="#"onclick="alert('Use the Drop Down Menu')">Student List</a>
            <ul>
                <li>
                    <g:link controller="student" action="StudentList" params="[batch:'All']">All</g:link>
                </li>
                <li>
                    <g:link controller="student" action="StudentList" params="[batch:2015]">Batch 2015</g:link>
                </li>
                <li>
                    <g:link controller="student" action="StudentList" params="[batch:2016]">Batch 2016</g:link>
                </li>
                <li>
                    <g:link controller="student" action="StudentList" params="[batch:2017]">Batch 2017</g:link>
                </li>
                <li>
                    <g:link controller="student" action="StudentList" params="[batch:2018]">Batch 2018</g:link>
                </li>

            </ul>
        </li>
        <li>
            <g:link controller="admin" action="contactUs">ContactUs</g:link>
        </li>

        <li>
            <g:link controller="admin" action="aboutUs">About Us</g:link>
        </li>
        <li>
            <g:link controller="admin" action="logout">Logout</g:link>
        </li>
    </ul>
</div>
<!-- end navbar -->


