package services;

import dao.MembersDao;
//import beans.Grade;
import beans.Menber;

import java.sql.Connection;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MemberService {
    private Connection connection = null;

    private void createConnection(MembersDao dao) {
        this.connection = dao.createConnection();
    }

    private void closeConnection(MembersDao dao) {
        dao.closeConnection(this.connection);
        this.connection = null;
    }

    public void signUp(Member member) {
        MembersDao dao = new MembersDao();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        member.setPassword(encoder.encode(member.getPassword()));
        createConnection(dao);
        dao.create(member, connection);
        closeConnection(dao);
    }

    public Member signIn(String loginid, String password) {
        MembersDao dao = new MembersDao();
        createConnection(dao);
        Member member = dao.showByMail(mail, connection);
        if (member != null) {
            member.setBoardingTimes(dao.howManyTimes(member.getId(), connection));
            member.setGrade(Grade.getGradeByTimes(member.getBoardingTimes()));
            System.out.println(member.getGrade());
        }

        closeConnection(dao);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (member != null && encoder.matches(password, member.getPassword())) {
            return member;
        }
        return null;
    }

    public void updateMail(int memberId, String newMail) {
        MembersDao dao = new MembersDao();

        createConnection(dao);
        dao.updateMail(memberId, newMail, connection);
        closeConnection(dao);
    }

    public void updatePassword(int memberId, String newPassword) {
        MembersDao dao = new MembersDao();

        createConnection(dao);
        dao.updatePassword(memberId, newPassword, connection);
        closeConnection(dao);
    }

    public boolean doesExist(String mail){
        MembersDao dao =new MembersDao();

        createConnection(dao);
        Member member = dao.showByMail(mail,connection);
        closeConnection(dao);

        if(member==null){
            return false;
        }

        return true;
    }
}