package cs.sbs.web.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class MenuListServlet extends HttpServlet {

    // 模拟菜单数据
    private static java.util.List<cs.sbs.web.model.MenuItem> menuItems = java.util.Arrays.asList(
            new cs.sbs.web.model.MenuItem("Fried Rice", 8),
            new cs.sbs.web.model.MenuItem("Fried Noodles", 9),
            new cs.sbs.web.model.MenuItem("Burger", 10)
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        String nameParam = req.getParameter("name"); // 获取查询参数 ?name=xxx

        StringBuilder result = new StringBuilder("Menu List:\n");
        int count = 1;
        for (cs.sbs.web.model.MenuItem item : menuItems) {
            if (nameParam == null || item.getName().toLowerCase().contains(nameParam.toLowerCase())) {
                result.append(count++).append(". ").append(item.getName()).append(" - $").append(item.getPrice()).append("\n");
            }
        }
        resp.getWriter().print(result.toString());
    }
}
