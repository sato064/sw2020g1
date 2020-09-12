package controllers.session;

import models.InputError;
import models.Member;
import services.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/signIn.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String loginid = request.getParameter("loginid");
        String password = request.getParameter("password");
        MemberService memberService = new MemberService();
        Member member = memberService.SignIn(mail, password);
        if (member != null) {
            HttpSession session = request.getSession();
            session.setAttribute("member", member);
            response.sendRedirect("/SE19G1/flight/search");
        } else {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");
            InputError inputError = new InputError();
            inputError.setWrongMailOrPassword("メールアドレスまたはパスワードが違います");
            request.setAttribute("error", inputError);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/session/signIn.jsp");
            dispatcher.forward(request, response);
        }
    }
}