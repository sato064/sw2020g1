package model;

public class Project {
    private Integer projectID = null;
    private String projectNAME = null;
    private String projectINFO = null;
    private String authorID = null;
    private String detailedINFO = null;
    private String authorNAME = null;

    public Project(int projectID,String projectNAME,String projectINFO,String authorID,String detailedINFO,String authorNAME){
        this.projectID = projectID;
        this.projectNAME = projectNAME;
        this.projectINFO = projectINFO;
        this.authorID = authorID;
        this.detailedINFO = detailedINFO;
        this.authorNAME = authorNAME;
    }

    public Project(){

    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
    public void setProjectNAME(String projectNAME) {
        this.projectNAME = projectNAME;
    }
    public void setProjectINFO(String projectINFO) {
        this.projectINFO = projectINFO;
    }
    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }
    public void setDetailedINFO(String detailedINFO) {
        this.detailedINFO = detailedINFO;
    }
    public void setAuthorNAME(String authorNAME) {
        this.authorNAME = authorNAME;
    }


    public int getProjectID(int projectID){
        return this.projectID;
    }
    public String getProjectNAME(String projectNAME){
        return this.projectNAME;
    }
    public String getProjectINFO(String projectINFO){
        return this.projectINFO;
    }
    public String getAuthorID(String authorID){
        return this.authorID;
    }
    public String getDetailedINFO(String detailedINFO){
        return this.detailedINFO;
    }
    public String getAuthorNAME(String authorNAME){
        return this.authorNAME;
    }
}
