package cs.sbs.web.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class OrderCreateServlet extends HttpServlet {

    // 模拟订单数据库，用 static 保证数据不丢
    public static java.util.List<cs.sbs.web.model.Order> orders = new java.util.ArrayList<>();
    private static int nextId = 1001;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        try {
            String customer = req.getParameter("customer");
            String food = req.getParameter("food");
            String quantityStr = req.getParameter("quantity");

            // 异常处理：参数为空
            if (customer == null || food == null || quantityStr == null || customer.isEmpty() || food.isEmpty()) {
                resp.getWriter().print("Error: Missing parameters");
                return;
            }

            // 异常处理：数量不是合法数字
            int quantity = Integer.parseInt(quantityStr);

            cs.sbs.web.model.Order newOrder = new cs.sbs.web.model.Order(nextId++, customer, food, quantity);
            orders.add(newOrder);

            resp.getWriter().print("Order Created: " + newOrder.getId());
        } catch (NumberFormatException e) {
            resp.getWriter().print("Error: quantity must be a valid number");
        }
    }
}
