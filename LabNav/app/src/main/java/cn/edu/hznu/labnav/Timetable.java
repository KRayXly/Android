package cn.edu.hznu.labnav;

//事件时间表类
public class Timetable {
    private String nextTime;
    private String endTime;
    private String type;
    private String tableName;

    public Timetable(){}

    public Timetable(String nextTime, String endTime, String type, String tableName, boolean isSolve) {
        this.nextTime = nextTime;
        this.endTime = endTime;
        this.type = type;
        this.tableName = tableName;
        this.isSolve = isSolve;
    }

    public boolean isSolve() {
        return isSolve;
    }

    public void setSolve(boolean solve) {
        isSolve = solve;
    }

    private boolean isSolve;

    public String getNextTime() {
        return nextTime;
    }

    public void setNextTime(String nextTime) {
        this.nextTime = nextTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
