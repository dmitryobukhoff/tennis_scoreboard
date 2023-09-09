package ru.dmitryobukhoff.servlets;

import org.hibernate.Session;
import ru.dmitryobukhoff.utils.HibernateSessionFactoryUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/index")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
