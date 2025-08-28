package controller;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chỉ show form đăng ký
        request.getRequestDispatcher("decorators/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Kiểm tra mật khẩu khớp
        if (password == null || !password.equals(confirmPassword)) {
            request.setAttribute("alert", "Mật khẩu không khớp!");
            request.getRequestDispatcher("decorators/register.jsp").forward(request, response);
            return;
        }

        // Kiểm tra user tồn tại
        if (userService.get(username) != null) {
            request.setAttribute("alert", "Tên đăng nhập đã tồn tại!");
            request.getRequestDispatcher("decorators/register.jsp").forward(request, response);
            return;
        }

        // Tạo user mới
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        boolean success = userService.register(user);
        if (success) {
            request.setAttribute("success", "Đăng ký thành công! Bạn có thể đăng nhập ngay.");
        } else {
            request.setAttribute("alert", "Có lỗi xảy ra, vui lòng thử lại.");
        }

        request.getRequestDispatcher("decorators/register.jsp").forward(request, response);
    }
}
