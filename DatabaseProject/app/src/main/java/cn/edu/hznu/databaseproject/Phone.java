package cn.edu.hznu.databaseproject;

public class Phone {
    private String name;
    private String phone;
    private boolean checked;
    private String id;

    public Phone(String name,String phone,boolean checked,String id){
        this.name=name;
        this.phone=phone;
        this.checked=checked;
        this.id=id;
    }
    public void setChecked(boolean c){
        this.checked=c;
    }
    public String getName() {return name;}
    public String getPhone() {return phone;}
    public boolean getCheck() {return checked;}
    public String getid() {return id;}
}
