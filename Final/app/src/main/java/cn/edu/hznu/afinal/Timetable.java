package cn.edu.hznu.afinal;

//事件时间表类
public class Timetable {
    private String tdate;
    private String ttime;
    private String tableName;
    private String notype;
    private boolean isSolve;

    public Timetable(){}

    public Timetable(String tdate, String ttime, String tableName, String notype,boolean isSolve) {
        this.tdate = tdate;
        this.ttime = ttime;
        this.tableName = tableName;
        this.notype = notype;
        this.isSolve = isSolve;
    }

    public boolean isSolve() {
        return isSolve;
    }

    public void setSolve(boolean solve) {
        isSolve = solve;
    }

    public String getTdate() {
        return tdate;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public String getTtime() {
        return ttime;
    }

    public void setTtime(String ttime) {
        this.ttime= ttime;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getNotype() {
        return notype;
    }

    public void setNotype(String tableName) {
        this.notype= notype;
    }
}
