package Servlets;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.Gson;
import database.DBConnection;
import model.Day;
import model.Group;
import model.Lesson;
import model.NumberLesson;
import org.json.simple.JSONObject;
import service.ServiceSchedule;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Service;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/ServletGroup")
public class ServletGroup extends HttpServlet {
    private static final String ERROR_PAGE = "/view/jsp/error.jsp";
    private static final String LESSONS_PAGE = "/view/jsp/lessonsByGroup.jsp";
    private static final String GROUP_NUMBER_PARAMETER = "groupNumber";
    private static final String ERROR_MESSAGE = "Error, please come later";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceSchedule serviceSchedule = new ServiceSchedule();

        String forward;
        String groupNumber = (String) request.getParameter(GROUP_NUMBER_PARAMETER);
        try {
            List<Lesson> lessons = serviceSchedule.selectByNumberGroup(groupNumber);

            if (lessons.size() == 0) {
                request.setAttribute("errorMessage",
                        "Group with number " + groupNumber + " don't have any lessons");
                forward = ERROR_PAGE;
            } else {
                Map<NumberLesson, Map<Day,Lesson>> schedule;
                schedule = serviceSchedule.getMapSchedule(lessons);

                request.setAttribute("schedule", schedule);
                request.setAttribute(GROUP_NUMBER_PARAMETER,groupNumber);
                request.setAttribute("numbers", NumberLesson.values());
                request.setAttribute("days", Day.values());

                forward = LESSONS_PAGE;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            forward = ERROR_PAGE;
            request.setAttribute("errorMessage", ERROR_MESSAGE);
        }

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(forward);
        response.setContentType("application/json");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ServiceSchedule serviceSchedule = new ServiceSchedule() ;

            String groupnumber = request.getParameter(GROUP_NUMBER_PARAMETER);
            serviceSchedule.insertGroup(new Group(groupnumber));

        } catch (SQLException e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.getWriter().write("{ \"error\" : \"True\" }" );
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            ServiceSchedule serviceSchedule = new ServiceSchedule() ;

            String groupNumber = request.getParameter(GROUP_NUMBER_PARAMETER);
            serviceSchedule.deleteGroup(groupNumber);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.getWriter().write("{ \"error\" : \"True\" }" );
        }
    }


    @Override
    public void destroy() {
        try {
            DBConnection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
