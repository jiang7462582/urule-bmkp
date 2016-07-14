package com.bmkp.urule.demo.control;

import com.bmkp.urule.demo.model.User;
import com.bmkp.urule.demo.test.TestURule;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jiang on 16/7/14.
 */
public class TestServlet extends HttpServlet {
    private String message;

    public void init() throws ServletException {
        // 执行必需的初始化
        message = "Hello World";
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        Double distance = Double.parseDouble(request.getParameter("distance"));
        Long waitTime = Long.parseLong(request.getParameter("waitTime"));
        User user = new User();
        user.setDistance(distance);
        user.setWaitTime(waitTime);
        Double cost = null;
        try {
            cost = new TestURule().userRuleTest(user);
        }catch (Exception ex){

        }
        // 设置响应内容类型
        response.setContentType("text/html");

        // 实际的逻辑是在这里
        PrintWriter out = response.getWriter();
        out.println("<h1>" + cost + "</h1>");
    }

    public void destroy() {
        // 什么也不做
    }

}
