<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng ký tài khoản</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"/>
    <style>
        body { background: #f5f5f5; }
        .register-container {
            max-width: 420px;
            margin: 60px auto;
            background: #fff;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.15);
        }
        .register-container h2 { margin-bottom: 25px; font-weight: bold; }
        .btn-primary { background-color: #337ab7; border: none; }
        .btn-primary:hover { background-color: #286090; }
    </style>
</head>

<body>
    <div class="container">
        <div class="register-container">
            <form action="register" method="post" class="form-horizontal">
                <h2 class="text-center">Tạo tài khoản mới</h2>

                <!-- Hiển thị thông báo từ controller -->
                <c:if test="${not empty alert}">
                    <div class="alert alert-danger text-center">${alert}</div>
                </c:if>
                <c:if test="${not empty success}">
                    <div class="alert alert-success text-center">${success}</div>
                </c:if>

                <!-- Form đăng ký -->
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                        <input type="text" name="username" class="form-control" placeholder="Tên đăng nhập" required>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                        <input type="email" name="email" class="form-control" placeholder="Email" required>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                        <input type="password" name="password" class="form-control" placeholder="Mật khẩu" required>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                        <input type="password" name="confirmPassword" class="form-control" placeholder="Nhập lại mật khẩu" required>
                    </div>
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">Đăng ký</button>
                </div>

                <p class="text-center">
                    Đã có tài khoản? <a href="login.jsp">Đăng nhập</a>
                </p>
            </form>
        </div>
    </div>
</body>
</html>
