package Servlets;

import database.DBConnection;
import model.Day;
import model.Lesson;
import model.NumberLesson;
import model.Teacher;
import service.ServiceSchedule;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/ServletTeacher")
public class ServletTeacher extends HttpServlet {
    private static final String ERROR_PAGE = "/view/jsp/error.jsp";
    private static final String LESSONS_PAGE = "/view/jsp/lessonsByTeacher.jsp";
    private static final String FIRST_NAME_PARAMETER = "firstName";
    private static final String LAST_NAME_PARAMETER = "lastName";
    private static final String ERROR_MESSAGE = "Error, please come later";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceSchedule serviceSchedule = new ServiceSchedule();

        String forward;
        String firstName = request.getParameter(FIRST_NAME_PARAMETER);
        String lastName =request.getParameter(LAST_NAME_PARAMETER);
        try {
            List<Lesson> lessons = serviceSchedule.selectByTeacherName(firstName,lastName);

            if (lessons.size() == 0) {
                request.setAttribute("errorMessage",
                         firstName + " " + lastName + " don't have any lessons");
                forward = ERROR_PAGE;
            } else {
                Map<NumberLesson, Map<Day,Lesson>> schedule;
                schedule = serviceSchedule.getMapSchedule(lessons);

                request.setAttribute("schedule", schedule);
                request.setAttribute(FIRST_NAME_PARAMETER,firstName);
                request.setAttribute(LAST_NAME_PARAMETER,lastName);
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

            String firstName = request.getParameter(FIRST_NAME_PARAMETER);
            String lastName =request.getParameter(LAST_NAME_PARAMETER);
            serviceSchedule.insertTeacher(new Teacher(firstName,lastName));

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

            String firstName = request.getParameter(FIRST_NAME_PARAMETER);
            String lastName =request.getParameter(LAST_NAME_PARAMETER);
            serviceSchedule.deleteTeacher(firstName,lastName);
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
