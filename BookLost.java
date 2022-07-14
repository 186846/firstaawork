package 第一周作业;

public class BookLost extends Lost{
    String title;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public BookLost(String title,String name, Integer month, Integer day, String adress){
        this.title = title;
        this.name = name;
        this.month = month;
        this.day = day;
        this.adress = adress;
    }
}
