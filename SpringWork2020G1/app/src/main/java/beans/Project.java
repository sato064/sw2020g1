package beans;

public class Project {
    private Integer projectID = null;
    private String projectTITLE = null;
    private String overview = null;
    private String hostID = null;
    private DateTime deadline = null;
    private Integer projectSTATUS = null;
    private boolean isDelayed = false;

    public Project(int projectID,String projectTITLE,String overview,String hostID,DateTime deadline,int projectSTATUS,boolean isDelayed){
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
    public void setDeadline(Datatime deadline) {
        this.deadline = deadline;
    }
    public void setProjectSTATUS(int projectSTATUS) {
        this.projectSTATUS = projectSTATUS;
    }
    public void setIsDelayed(boolean isDelayed) {
        this.isDelayed = isDelayed;
    }

    public int getProjectID(int projectID){
        return this.projectID;
    }
    public String getProjectTITLE(String projectTITLE){
        return this.projectTITLE;
    }
    public String getOverview(String overview){
        return this.overview;
    }
    public String getHostID(String hostID){
        return this.hostID;
    }
    public DateTime getDeadline(DateTime deadline){
        return this.deadline;
    }
    public int getProjectSTATUS(int projectSTATUS){
        return this.projectSTATUS;
    }
    public boolean getIsDelayed(boolean isDelayed) {
        return this.isDelayed;
    }
}