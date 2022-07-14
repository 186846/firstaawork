package 第一周作业;

public class CardLost extends Lost{
    String stu_ID;



    public String getStu_ID(){
        return stu_ID;
    }
    public void setStu_ID(String stu_ID) {
        this.stu_ID = stu_ID;
    }


    public CardLost(String stu_ID,String name, Integer month, Integer day, String adress){
        this.stu_ID = stu_ID;
        this.name = name;
        this.month = month;
        this.day = day;
        this.adress = adress;
    }
}
