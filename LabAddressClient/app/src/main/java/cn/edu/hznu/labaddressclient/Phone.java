package cn.edu.hznu.labaddressclient;

public class Phone {
    private String name;
    private String phone;
    private String id;

    public Phone(String name,String phone,String id){
        this.name=name;
        this.phone=phone;
        this.id=id;
    }
    public String getName() {return name;}
    public String getPhone() {return phone;}
    public String getid() {return id;}
}
