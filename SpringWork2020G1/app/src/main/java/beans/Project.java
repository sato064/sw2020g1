package beans;

import java.time.LocalDateTime;

public class Project {

    private Integer projectID = null;
    private String projectTITLE = null;
    private String overview = null;
    private String hostID = null;
    private String deadline = null;
    private Integer projectSTATUS = null;
    private boolean isDelayed = false;

    public Project(int projectID,String projectTITLE,String overview,String hostID,String deadline,int projectSTATUS,boolean isDelayed){
        this.projectID = projectID;
        this.projectTITLE = projectTITLE;
        this.overview = overview;
        this.hostID = hostID;
        this.deadline = deadline;
        this.projectSTATUS = projectSTATUS;
        this.isDelayed = isDelayed;
    }

    public Project(){

    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
    public void setProjectTITLE(String projectTITLE) {
        this.projectTITLE = projectTITLE;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public void setHostID(String hostID) {
        this.hostID = hostID;
    }
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    public void setProjectSTATUS(int projectSTATUS) {
        this.projectSTATUS = projectSTATUS;
    }
    public void setIsDelayed(boolean isDelayed) {
        this.isDelayed = isDelayed;
    }

    public int getProjectID(){
        return this.projectID;
    }
    public String getProjectTITLE(){
        return this.projectTITLE;
    }
    public String getOverview(){
        return this.overview;
    }
    public String getHostID(){
        return this.hostID;
    }
    public String getDeadline(){
        return this.deadline;
    }
    public int getProjectSTATUS(){
        return this.projectSTATUS;
    }
    public boolean getIsDelayed() {
        return this.isDelayed;
    }
}