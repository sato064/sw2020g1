package beans;

import java.time.LocalDateTime;

public class Task {

    private Integer taskID = null;
    private Integer projectID = null;
    private String taskTITLE = null;
    private String overview = null;
    private String deadline = null;
    private Integer taskSTATUS = null;
    private boolean isDelayed = false;

    public Task(int taskID, int projectID,String taskTITLE,String overview,String deadline,int taskSTATUS,boolean isDelayed){
        this.taskID = taskID;
        this.projectID = projectID;
        this.taskTITLE = taskTITLE;
        this.overview = overview;
        this.deadline = deadline;
        this.taskSTATUS = taskSTATUS;
        this.isDelayed = isDelayed;
    }

    public Task(){
        
    }


    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
    public void setTaskTITLE(String taskTITLE) {
        this.taskTITLE = taskTITLE;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    
    }
    
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    public void setTaskSTATUS(int taskSTATUS) {
        this.taskSTATUS = taskSTATUS;
    }
    public void setIsDelayed(boolean isDelayed) {
        this.isDelayed = isDelayed;
    }

    public int getTaskID(){
        return this.taskID;
    }

    public String getTaskIdStr(){
        return this.taskID.toString();

    }
        

    

    public int getProjectID(){
        return this.projectID;
    }
    public String getTaskTITLE(){
        return this.taskTITLE;
    }
    public String getOverview(){
        return this.overview;

    }
    public String getDeadline(){
        return this.deadline;
    }
    public int getTaskSTATUS(){
        return this.taskSTATUS;
    }
    public boolean getIsDelayed() {
        return this.isDelayed;
    }
}