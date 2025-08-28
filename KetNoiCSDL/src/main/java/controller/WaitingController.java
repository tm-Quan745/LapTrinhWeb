package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.User;

@WebServlet(urlPatterns = "/waiting")
public class WaitingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("account") != null) {
            User u = (User) session.getAttribute("account");

            switch (u.getRoleId()) {
                case 1:
                    resp.sendRedirect(req.getContextPath() + "/admin/home");
                    break;
                case 2:
                    resp.sendRedirect(req.getContextPath() + "/manager/home");
                    break;
                default:
                    resp.sendRedirect(req.getContextPath() + "/home");
                    break;
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
