package 第一周作业;


import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Manager {
    //定义两个容器
    static ArrayList<CardLost> cardLosts = new ArrayList<>();
    static ArrayList<BookLost> bookLosts = new ArrayList<>();
    //总管理器
    public static void main(String[] args) {

        System.out.println("欢迎使用！");

        while (true){
            System.out.println("*****欢迎来到失物管理系统*****");
            System.out.println("1 进行一卡通增删改查");
            System.out.println("2 进行丢失书籍增删改查");
            System.out.println("3   退出");
            System.out.println("请输入你的选择");

            Scanner in = new Scanner(System.in);
            int a = in.nextInt();
            switch (a) {
                case 1 ->
                    //跳到一卡通进行操作
                    CardManager();
                case 2 ->
                    //跳到丢失书籍进行操作
                    BookManager();
                case 3 -> {
                    System.out.println("谢谢使用");
                    System.exit(0);//jvm退出
                }
                default -> throw new IllegalStateException("Unexpected value: " + a);
            }
         }
    }


    //一卡通管理
    public static void CardManager(){
        System.out.println("1 添加一卡通");
        System.out.println("2 修改一卡通");
        System.out.println("3 删除一卡通");
        System.out.println("4 查询一卡通");
        System.out.println("5 对一卡通进行时间排序");
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        switch (a) {
            case 1 ->
                //添加一卡通
                    AddCard(cardLosts);
            case 2 ->
                //修改一卡通
                    ResetCard(cardLosts);
            case 3 ->
                //删除一卡通
                    Carddelete(cardLosts);
            case 4 -> {
                //查询一卡通
                System.out.println("请输入学号:" );
                CardSelect(cardLosts, select());
            }
            case 5->
                SortCardLost(cardLosts);
            default -> throw new IllegalStateException("Unexpected value: " + a);
        }
    }

    //一卡通时间排序
    private static void SortCardLost(ArrayList<CardLost> cardLosts) {
        CardLost cardmsg;
        for(int i = 0;i<cardLosts.size();i++){
            for (int j = 0;j<cardLosts.size()-1-i;j++){
                if (cardLosts.get(j).getMonth()<cardLosts.get(j+1).getMonth()){
                    cardmsg = cardLosts.get(j);
                    cardLosts.set(j,cardLosts.get(j+1));
                    cardLosts.set(j+1,cardmsg);
                }
                else if (Objects.equals(cardLosts.get(j).getMonth(), cardLosts.get(j + 1).getMonth()) &&cardLosts.get(j).getDay()<cardLosts.get(j+1).getDay()){
                    cardmsg = cardLosts.get(j);
                    cardLosts.set(j,cardLosts.get(j+1));
                    cardLosts.set(j+1,cardmsg);
                }
            }
        }
        allCards(cardLosts);
    }

    //添加一卡通
    public static void AddCard(ArrayList<CardLost> cardLosts) {
        Scanner in = new Scanner(System.in);
        String id;

        while (true) {
            System.out.println("请输入学号：");
            id = in.nextLine();
            boolean flag = Repeat(cardLosts, id);
            if (flag) {
                System.out.println("学号重复，请重新输入");
            } else
                break;
        }


        System.out.println("请输入姓名：");
        String name = in.nextLine();
        System.out.println("请输入丢失的月份：");
        Integer month = in.nextInt();
        System.out.println("请输入丢失的几日：");
        Integer day = in.nextInt();
        System.out.println("请输入丢失的地点：");
        String adress = in.next();
        //将录入的信息加入Stu1这个类，然后加入到ArrayList中
        CardLost card = new CardLost(id,name,month,day,adress);
        //添加到集合里
        cardLosts.add(card);
        System.out.println("添加成功");
    }

    private static boolean Repeat(ArrayList<CardLost> cardLosts, String id) {
        boolean flag = false;
        for (CardLost card:cardLosts){
            if (card.getStu_ID().equals(id)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    //修改一卡通
    public static void ResetCard(ArrayList<CardLost> cardLosts){
        Scanner in = new Scanner(System.in);
        System.out.println("输入需要修改的学生学号");
        String id = in.nextLine();
        System.out.println("请输入姓名：");
        String name = in.nextLine();
        System.out.println("请输入丢失的月份：");
        Integer month = in.nextInt();
        System.out.println("请输入丢失的几日：");
        Integer day = in.nextInt();
        System.out.println("请输入丢失的地点：");
        String adress = in.next();
        //将录入的信息加入Stu1这个类，然后加入到ArrayList中
        CardLost card = new CardLost(id,name,month,day,adress);
        //在stu中遍历，找到对应的进行修改
        for(int i=0;i<cardLosts.size();i++)
        {
            CardLost c = cardLosts.get(i);
            if(c.getStu_ID().equals(id))
            {
                //用set方法进行修改
                cardLosts.set(i,card);
                break;
            }
        }
        System.out.println("修改成功");
    }


    //删除一卡通
    public static void Carddelete(ArrayList<CardLost> cardLosts){
        System.out.println("请选择要删除的学生学号");
        Scanner in = new Scanner(System.in);
        String id= in.nextLine();
        //先设置一个flag用来判断要删除的学号是否存在
        int flag = -1;
        int i=0;
        while (i<cardLosts.size()) {
            //新建学生对象s1得到集合中的元素
            CardLost card = cardLosts.get(i);
            if(card.getStu_ID().equals(id)) {
                cardLosts.remove(i);
                flag=1;
                break;
            }
            i++;
        }
        if(flag==1)
            System.out.println("删除成功");
        else
            System.out.println("你所删除的学生学号不存在");
    }
    //查找丢失的一卡通
    public static void CardSelect(ArrayList<CardLost> cardLosts, String stu_ID){
        boolean flag = false;
        for (CardLost card : cardLosts) {
            //新建学生对象s1得到集合中的元素
            if (card.getStu_ID().equals(stu_ID)) {
                System.out.println("学号：" + card.getStu_ID() + ",姓名：" + card.getName() + ",丢失的时间：" + card.getMonth() + "." + card.getDay() + "，丢失的地点:" + card.getAdress());
                flag = true;
            }
        }
        if (flag){
            System.out.println("查询成功");
        }
        else {
            System.out.println("查询失败");
        }
    }


    //书籍管理
    private static void BookManager() {
        System.out.println("1 添加丢失书籍");
        System.out.println("2 修改丢失书籍信息");
        System.out.println("3 删除丢失书籍信息");
        System.out.println("4 查询查询丢失书籍");
        System.out.println("5 对书籍进行时间排序");
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        switch (a) {
            case 1 ->
                //添加书籍
                    AddBook(bookLosts);
            case 2 ->
                //修改书籍信息
                    Resetbook(bookLosts);
            case 3 ->
                //删除书籍信息
                    bookdelete(bookLosts);
            case 4 ->
                //查询书籍信息
                    bookSelect(bookLosts, select1());
            case 5->
                //对书籍进行时间排序
                    SortBookList(bookLosts);
            default -> throw new IllegalStateException("Unexpected value: " + a);
        }
    }

    //对书籍进行时间排序的方法
    private static void SortBookList(ArrayList<BookLost> bookLosts) {
        BookLost bookmsg;
        for(int i = 0;i<bookLosts.size();i++){
            for (int j = 0;j<bookLosts.size()-1-i;j++){
                if (bookLosts.get(j).getMonth()<bookLosts.get(j+1).getMonth()){
                    bookmsg = bookLosts.get(j);
                    bookLosts.set(j,bookLosts.get(j+1));
                    bookLosts.set(j+1,bookmsg);
                }
                else if (Objects.equals(bookLosts.get(j).getMonth(), bookLosts.get(j + 1).getMonth()) &&bookLosts.get(j).getDay()<bookLosts.get(j+1).getDay()){
                    bookmsg = bookLosts.get(j);
                    bookLosts.set(j,bookLosts.get(j+1));
                    bookLosts.set(j+1,bookmsg);
                }
            }
        }
        allBooks(bookLosts);
    }

    //一卡通学号输入
    public static String select() {
        System.out.println("请输入学生学号:");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    //书籍名输入
    public static String select1() {
        System.out.println("请输入书名:");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    //查询丢失书籍
    public static void bookSelect(ArrayList<BookLost> bookLosts, String title) {
        boolean flag = false;
        for (BookLost book : bookLosts) {
            //新建学生对象s1得到集合中的元素
            if (Objects.equals(title, book.getTitle())) {
                System.out.println("书名： " + book.getTitle() +"书籍物主："+book.getName()+ ",丢失的时间： " + book.getMonth() + "." + book.getDay() + "，丢失的地点:   " + book.getAdress());
                flag = true;
            }
        }
        if (flag) {
            System.out.println("查询成功");
        } else {
            System.out.println("查询失败!");
        }
    }

    //删除丢失书籍信息

    public static void bookdelete(ArrayList<BookLost> bookLosts) {
        System.out.println("请选择要删除的学生学号");
        Scanner in = new Scanner(System.in);
        String title= in.nextLine();
        //先设置一个flag用来判断要删除的书籍是否存在
        int flag = -1;
        int i=0;
        while (i<bookLosts.size()) {
            //新建学生对象s1得到集合中的元素
            BookLost book = bookLosts.get(i);
            if(title.equals(book.getTitle())) {
                bookLosts.remove(i);
                flag=1;
                break;
            }
            i++;
        }
        if(flag==1)
            System.out.println("删除成功");
        else
            System.out.println("你所删除的书籍不存在");
    }

    //修改丢失书籍信息
    public static void Resetbook(ArrayList<BookLost> bookLosts) {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入书名：");
        String title = in.nextLine();
        System.out.println("请输入物主:");
        String name = in.nextLine();
        System.out.println("请输入丢失的月份：");
        Integer month = in.nextInt();
        System.out.println("请输入丢失的几日：");
        Integer day = in.nextInt();
        System.out.println("请输入丢失的地点：");
        String adress = in.next();
        //将录入的信息加入Stu1这个类，然后加入到ArrayList中
        BookLost book = new BookLost(title,name,month,day,adress);
        //在stu中遍历，找到对应的进行修改
        for(int i=0;i<bookLosts.size();i++)
        {
            BookLost b = bookLosts.get(i);
            if(b.getTitle().equals(title))
            {
                //用set方法进行修改
                bookLosts.set(i,book);
                break;
            }
        }
        System.out.println("修改成功");
    }
    //添加丢失书籍信息
    public static void AddBook(ArrayList<BookLost> bookLosts) {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入书名：");
        String title = in.nextLine();
        System.out.println("请输入物主:");
        String name = in.nextLine();
        System.out.println("请输入丢失的月份：");
        Integer month = in.nextInt();
        System.out.println("请输入丢失的几日：");
        Integer day = in.nextInt();
        System.out.println("请输入丢失的地点：");
        String adress = in.next();
        BookLost book = new BookLost(title,name,month,day,adress);
        bookLosts.add(book);
        System.out.println("添加成功");
    }

    //显示一卡通的所有数据
    public static void allCards(ArrayList<CardLost> cardLosts){
         for (CardLost card:cardLosts){
             System.out.println("学号：" + card.getStu_ID() + ",姓名：" + card.getName() + ",丢失的时间：" + card.getMonth() + "." + card.getDay() + "，丢失的地点:" + card.getAdress());
         }
    }
    public static void allBooks(ArrayList<BookLost> bookLosts){
        for (BookLost book:bookLosts){
            System.out.println("书名： " + book.getTitle() +"书籍物主："+book.getName()+ ",丢失的时间： " + book.getMonth() + "." + book.getDay() + "，丢失的地点:   " + book.getAdress());
        }
    }
}