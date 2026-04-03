package cs.sbs.web.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class OrderDetailServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        String pathInfo = req.getPathInfo(); // 获取 /1001 部分

        if (pathInfo == null || pathInfo.equals("/")) {
            resp.getWriter().print("Error: Order ID missing");
            return;
        }

        try {
            int orderId = Integer.parseInt(pathInfo.substring(1)); // 去掉开头的 /

            for (cs.sbs.web.model.Order o : OrderCreateServlet.orders) {
                if (o.getId() == orderId) {
                    resp.getWriter().println("Order Detail");
                    resp.getWriter().println("Order ID: " + o.getId());
                    resp.getWriter().println("Customer: " + o.getCustomer());
                    resp.getWriter().println("Food: " + o.getFood());
                    resp.getWriter().println("Quantity: " + o.getQuantity());
                    return;
                }
            }
            resp.getWriter().print("Error: Order not found");
        } catch (NumberFormatException e) {
            resp.getWriter().print("Error: Invalid Order ID format");
        }
    }
}
