<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 18.11.2019
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="panel-body">
        <input id="groupNumber" type="text" name="id" placeholder="Group number"/>
        <button id="deleteGroup" class="btn btn-sm btn-primary">Delete Group</button>
        <button id="addGroup" class="btn btn-sm btn-primary">Add Group</button>
        <p class="message"></p>
    </div>

    <div class="panel-body">
        <input id="teacherFirstName" type="text" name="id" placeholder="Teacher's first name"/>
        <input id="teacherLastName" type="text" name="id" placeholder="Teacher's last name"/>
        <button id="deleteTeacher" class="btn btn-sm btn-primary">Delete Teacher</button>
        <button id="addTeacher" class="btn btn-sm btn-primary">Add Teacher</button>
        <p class="message"></p>
    </div>

    <div class="panel-body">
        <input id="roomNumber" type="text" name="id" placeholder="Room's number"/>
        <button id="deleteRoom" class="btn btn-sm btn-primary">Delete Room </button>
        <button id="addRoom" class="btn btn-sm btn-primary">Add Room</button>
        <p class="message"></p>
    </div>

    <div class="panel-body">
        <input id="SubjectName" type="text" name="id" placeholder="Subject number"/>
        <button id="deleteSubject" class="btn btn-sm btn-primary">Delete Subject</button>
        <button id="addSubject" class="btn btn-sm btn-primary">Add Subject</button>
        <p class="message"></p>
    </div>
</body>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="view/script/manageGroup.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
</html>
