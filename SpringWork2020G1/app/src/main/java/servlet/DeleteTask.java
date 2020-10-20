//--------------------------------
// SearchInfo.java
//--------------------------------
//　自分が格納されているフォルダ名
package servlet;

//自分が格納されているフォルダの外にある必要なクラス

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import beans.Task;
import control.TaskManager;

@WebServlet("/DeleteTask")
// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class DeleteTask extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        int task_id = Integer.parseInt(request.getParameter("id"));
        TaskManager manager = new TaskManager();
        manager.deleteTask(task_id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/deleteTaskSuccess.jsp");
        dispatcher.forward(request, response);
    }

}
