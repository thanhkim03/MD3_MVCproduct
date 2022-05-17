<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/16/2022
  Time: 3:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>danh sách sản phẩm</h1>
<a href="/products?action=create">tạo mới sản phẩm</a>
<c:forEach items='${danhSach}' var="sanPham">
    <h3>${sanPham.id},${sanPham.name},${sanPham.price},
        <a href="/products?action=edit&id=${sanPham.id}">sửa</a>
    </h3>
</c:forEach>
</body>
</html>
