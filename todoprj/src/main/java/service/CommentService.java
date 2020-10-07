package service;

import dao.CommentDao;
import dao.ProjectDao;
import model.Comment;
import model.Project;
import model.Student;

import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.util.List;

public class CommentService extends HttpServlet {

    // 属性
    private Connection connection = null;

    // 引数を持たないコンストラクタ
    public CommentService() {
    }

    public void registComment(Comment comment) {
        CommentDao commentDao = new CommentDao();
        this.connection = commentDao.createConnection();
        commentDao.registComment(comment, this.connection);
        commentDao.closeConnection(this.connection);
        this.connection = null;
    }

    public List<Comment> commentList() {
        CommentDao commentDao = new CommentDao();
        this.connection = commentDao.createConnection();
        List<Comment> commentList = commentDao.commentList(this.connection);
        commentDao.closeConnection(this.connection);
        this.connection = null;
        return commentList;
    }

    public void deleteComment(Comment comment){
        CommentDao commentDao = new CommentDao();
        this.connection = commentDao.createConnection();
        commentDao.deleteComment(comment,this.connection);
        commentDao.closeConnection(this.connection);
        this.connection = null;
    }
}