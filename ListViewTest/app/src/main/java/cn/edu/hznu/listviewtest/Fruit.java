package cn.edu.hznu.listviewtest;

public class Fruit {
    private String name;
    private String chinese;
    private int imageId;

    public Fruit(String name,String chinese,int imageId){
        this.name=name;
        this.imageId=imageId;
        this.chinese=chinese;
    }
    public String getName() {return name;}
    public String getChinese() {return chinese;}
    public  int getImageId() {return imageId;}

}
