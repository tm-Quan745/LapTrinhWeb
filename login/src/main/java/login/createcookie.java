package login;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/helloform"})
public class createcookie extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ten = request.getParameter("ten");
        String holot = request.getParameter("holot");

        Cookie firstName = new Cookie("ten", ten);
        Cookie lastName = new Cookie("holot", holot);

        firstName.setMaxAge(60 * 60 * 24);
        lastName.setMaxAge(60 * 60 * 24);

        response.addCookie(firstName);
        response.addCookie(lastName);

        // Nên đặt trước khi lấy out
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // In ra thông tin cookie vừa tạo
        out.println("<b>First Name</b>: " + firstName.getValue() + " - <b>Last Name</b>: " + lastName.getValue() + "<br/>");

        // Lấy danh sách cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            out.println("<h2>Found Cookies Name and Value</h2>");
            for (Cookie cookie : cookies) {
                out.print("Name : " + cookie.getName() + ", ");
                out.print("Value: " + cookie.getValue() + " <br/>");
            }
        } else {
            out.println("<h2>No cookies found</h2>");
        }

        out.close();
    }
}
