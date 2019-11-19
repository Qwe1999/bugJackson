<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11.11.2019
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Day" %>
<%@ page import="java.util.List" %>
<%@ page import="model.NumberLesson" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 08.11.2019
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<style>
    table {
        border: 1px solid #69c;
    }
    th { vertical-align: baseline }
    td {
        vertical-align: middle;
        height: 100px;
        width: 100px;
    }
    th, td {
        border: 2px solid #69c;
    }
    td#tdNumber{
        width: 10px;
    }
</style>
<head>
    <title>$Title$</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
<table border="1" width="50%" >
    <caption><p>Teacher - ${firstName} ${lastName}</p></caption>
    <thead>
    <td>
    <th>Monday</th>
    <th>Tuesday</th>
    <th>Wednesday</th>
    <th>Thursday</th>
    <th>Friday</th>
    </td>
    </thead>
    <tbody>

    <c:forEach items="${numbers}" var="numberLesson">
        <tr>
            <td id="tdNumber" width="10">${numberLesson.number}</td>
            <c:forEach items="${days}" var="dayLesson">
                <c:if test="${list[numberLesson][dayLesson] != null}">
                    <td>
                        <p>Subject - ${list[numberLesson][dayLesson].subject.name}</p>
                        <p>Group - ${list[numberLesson][dayLesson].group.number}</p>
                        <p>Room - ${list[numberLesson][dayLesson].room.number}</p>
                    </td>
                </c:if>
                <c:if test="${list[numberLesson][dayLesson] == null}">
                    <td></td>
                </c:if>
            </c:forEach>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
