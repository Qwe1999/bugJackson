<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11.11.2019
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <p></p>
</head>
<style>
    div {
        text-align: center
    }
</style>
<body>
    <div class="panel-body">
        <input id="groupNumber" type="text" name="id" placeholder="Group's number"/>
        <button id="showGroup" class="btn btn-sm btn-primary">Show group's lessons</button>
    </div>

    <div class="panel-body">
        <input id="teacherFirstName" type="text" name="id" placeholder="Teacher's first name"/>
        <input id="teacherLastName" type="text" name="id" placeholder="Teacher's last name"/>
        <button id="showTeacher" class="btn btn-sm btn-primary">Show teacher's lessons</button>
    </div>

    <div class="panel-body">
        <input id="roomNumber" type="text" name="id" placeholder="Room's name"/>
        <button id="showRoom" class="btn btn-sm btn-primary">Show Room's lessons</button>
    </div>

    <div class="panel-body">
        <input id="SubjectName" type="text" name="id" placeholder="Teacher's name"/>
        <button id="showSubject" class="btn btn-sm btn-primary">Show subject's lessons</button>
    </div>

    <div class="panel-body">
        <button id="showAll" class="btn btn-sm btn-primary">Show all lessons</button>
    </div>

</body>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="view/script/form.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
</html>
