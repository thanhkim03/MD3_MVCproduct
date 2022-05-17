package controlller;

import model.Product;
import view.ProductServiceIMPL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    ProductServiceIMPL productServiceIMPL = new ProductServiceIMPL();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(req, resp);
                break;
            case "edit":
                showEditForm(req,resp);
            default:
                showListPage(req, resp);
                break;
        }
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("product/edit.jsp");
        int id= Integer.parseInt(req.getParameter("id"));
        Product product=productServiceIMPL.findById(id);
        req.setAttribute("sanPhamSua",product);
        requestDispatcher.forward(req, resp);
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("product/create.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showListPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("product/list.jsp");
        List<Product> products = productServiceIMPL.findAll();
        req.setAttribute("danhSach", products);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createProduct(req, resp);
                break;
            case "edit":
                editProduct(req,resp);
                break;
        }
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int price = Integer.parseInt(req.getParameter("price"));
        String name = req.getParameter("name");
        productServiceIMPL.update(id, new Product(id,name,price));
        resp.sendRedirect("/products");
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int price = Integer.parseInt(req.getParameter("price"));
        String name = req.getParameter("name");
        productServiceIMPL.save(new Product(id,name,price));
        resp.sendRedirect("/products");
    }
}
