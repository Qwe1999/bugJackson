package Servlets;

import com.google.gson.Gson;
import database.DBConnection;
import model.Day;
import model.Group;
import model.Lesson;
import model.NumberLesson;
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

@WebServlet("/ServletForm")
public class ServletForm extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/jsp/form.jsp");
        dispatcher.forward(request, response);
    }


}
