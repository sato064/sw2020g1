package control;

import java.sql.Connection;
import java.util.List;
import javax.servlet.http.HttpServlet;

import beans.Participate;
import dao.ParticipateDAO;

public class ParticipateManager extends HttpServlet{
    private Connection connection = null;

    public ParticipateManager(){

    }
    public void createParticipate(Participate participate){
        ParticipateDAO participateDAO = new ParticipateDAO();
        this.connection = participateDAO.createConnection();
        participateDAO.createParticipate(participate,this.connection);
        participateDAO.closeConnection(this.connection);
        this.connection = null;
    }
    public void deleteParticipate(Participate participate){
        ParticipateDAO participateDAO = new ParticipateDAO();
        this.connection = participateDAO.createConnection();
        participateDAO.deleteParticipate(participate,this.connection);
        participateDAO.closeConnection(this.connection);
        this.connection = null;
    }
}