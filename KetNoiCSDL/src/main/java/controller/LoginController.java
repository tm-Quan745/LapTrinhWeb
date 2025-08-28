package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.User;
import service.UserService;
import service.UserServiceImpl;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private static final String SESSION_ACCOUNT = "account";
    private static final String COOKIE_REMEMBER = "username";

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute(SESSION_ACCOUNT) != null) {
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }

        // Check cookie remember-me
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (COOKIE_REMEMBER.equals(cookie.getName())) {
                    String username = cookie.getValue();
                    User user = userService.get(username);
                    if (user != null) {
                        session = req.getSession(true);
                        session.setAttribute(SESSION_ACCOUNT, user);
                        resp.sendRedirect(req.getContextPath() + "/waiting");
                        return;
                    }
                }
            }
        }

        req.getRequestDispatcher("/decorators/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        boolean isRememberMe = "on".equals(remember);

        String alertMsg = "";

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/decorators/login.jsp").forward(req, resp);
            return;
        }

        User user = userService.login(username, password);

        if (user != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute(SESSION_ACCOUNT, user);

            if (isRememberMe) {
                saveRememberMe(resp, username);
            }

            resp.sendRedirect(req.getContextPath() + "/waiting");
        } else {
            alertMsg = "Tài khoản hoặc mật khẩu không đúng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/decorators/login.jsp").forward(req, resp);
        }
    }

    private void saveRememberMe(HttpServletResponse response, String username) {
        Cookie cookie = new Cookie(COOKIE_REMEMBER, username);
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 ngày
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
