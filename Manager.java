package ��һ����ҵ;


import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Manager {
    //������������
    static ArrayList<CardLost> cardLosts = new ArrayList<>();
    static ArrayList<BookLost> bookLosts = new ArrayList<>();
    //�ܹ�����
    public static void main(String[] args) {

        System.out.println("��ӭʹ�ã�");

        while (true){
            System.out.println("*****��ӭ����ʧ�����ϵͳ*****");
            System.out.println("1 ����һ��ͨ��ɾ�Ĳ�");
            System.out.println("2 ���ж�ʧ�鼮��ɾ�Ĳ�");
            System.out.println("3   �˳�");
            System.out.println("���������ѡ��");

            Scanner in = new Scanner(System.in);
            int a = in.nextInt();
            switch (a) {
                case 1 ->
                    //����һ��ͨ���в���
                    CardManager();
                case 2 ->
                    //������ʧ�鼮���в���
                    BookManager();
                case 3 -> {
                    System.out.println("ллʹ��");
                    System.exit(0);//jvm�˳�
                }
                default -> throw new IllegalStateException("Unexpected value: " + a);
            }
         }
    }


    //һ��ͨ����
    public static void CardManager(){
        System.out.println("1 ���һ��ͨ");
        System.out.println("2 �޸�һ��ͨ");
        System.out.println("3 ɾ��һ��ͨ");
        System.out.println("4 ��ѯһ��ͨ");
        System.out.println("5 ��һ��ͨ����ʱ������");
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        switch (a) {
            case 1 ->
                //���һ��ͨ
                    AddCard(cardLosts);
            case 2 ->
                //�޸�һ��ͨ
                    ResetCard(cardLosts);
            case 3 ->
                //ɾ��һ��ͨ
                    Carddelete(cardLosts);
            case 4 -> {
                //��ѯһ��ͨ
                System.out.println("������ѧ��:" );
                CardSelect(cardLosts, select());
            }
            case 5->
                SortCardLost(cardLosts);
            default -> throw new IllegalStateException("Unexpected value: " + a);
        }
    }

    //һ��ͨʱ������
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

    //���һ��ͨ
    public static void AddCard(ArrayList<CardLost> cardLosts) {
        Scanner in = new Scanner(System.in);
        String id;

        while (true) {
            System.out.println("������ѧ�ţ�");
            id = in.nextLine();
            boolean flag = Repeat(cardLosts, id);
            if (flag) {
                System.out.println("ѧ���ظ�������������");
            } else
                break;
        }


        System.out.println("������������");
        String name = in.nextLine();
        System.out.println("�����붪ʧ���·ݣ�");
        Integer month = in.nextInt();
        System.out.println("�����붪ʧ�ļ��գ�");
        Integer day = in.nextInt();
        System.out.println("�����붪ʧ�ĵص㣺");
        String adress = in.next();
        //��¼�����Ϣ����Stu1����࣬Ȼ����뵽ArrayList��
        CardLost card = new CardLost(id,name,month,day,adress);
        //��ӵ�������
        cardLosts.add(card);
        System.out.println("��ӳɹ�");
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

    //�޸�һ��ͨ
    public static void ResetCard(ArrayList<CardLost> cardLosts){
        Scanner in = new Scanner(System.in);
        System.out.println("������Ҫ�޸ĵ�ѧ��ѧ��");
        String id = in.nextLine();
        System.out.println("������������");
        String name = in.nextLine();
        System.out.println("�����붪ʧ���·ݣ�");
        Integer month = in.nextInt();
        System.out.println("�����붪ʧ�ļ��գ�");
        Integer day = in.nextInt();
        System.out.println("�����붪ʧ�ĵص㣺");
        String adress = in.next();
        //��¼�����Ϣ����Stu1����࣬Ȼ����뵽ArrayList��
        CardLost card = new CardLost(id,name,month,day,adress);
        //��stu�б������ҵ���Ӧ�Ľ����޸�
        for(int i=0;i<cardLosts.size();i++)
        {
            CardLost c = cardLosts.get(i);
            if(c.getStu_ID().equals(id))
            {
                //��set���������޸�
                cardLosts.set(i,card);
                break;
            }
        }
        System.out.println("�޸ĳɹ�");
    }


    //ɾ��һ��ͨ
    public static void Carddelete(ArrayList<CardLost> cardLosts){
        System.out.println("��ѡ��Ҫɾ����ѧ��ѧ��");
        Scanner in = new Scanner(System.in);
        String id= in.nextLine();
        //������һ��flag�����ж�Ҫɾ����ѧ���Ƿ����
        int flag = -1;
        int i=0;
        while (i<cardLosts.size()) {
            //�½�ѧ������s1�õ������е�Ԫ��
            CardLost card = cardLosts.get(i);
            if(card.getStu_ID().equals(id)) {
                cardLosts.remove(i);
                flag=1;
                break;
            }
            i++;
        }
        if(flag==1)
            System.out.println("ɾ���ɹ�");
        else
            System.out.println("����ɾ����ѧ��ѧ�Ų�����");
    }
    //���Ҷ�ʧ��һ��ͨ
    public static void CardSelect(ArrayList<CardLost> cardLosts, String stu_ID){
        boolean flag = false;
        for (CardLost card : cardLosts) {
            //�½�ѧ������s1�õ������е�Ԫ��
            if (card.getStu_ID().equals(stu_ID)) {
                System.out.println("ѧ�ţ�" + card.getStu_ID() + ",������" + card.getName() + ",��ʧ��ʱ�䣺" + card.getMonth() + "." + card.getDay() + "����ʧ�ĵص�:" + card.getAdress());
                flag = true;
            }
        }
        if (flag){
            System.out.println("��ѯ�ɹ�");
        }
        else {
            System.out.println("��ѯʧ��");
        }
    }


    //�鼮����
    private static void BookManager() {
        System.out.println("1 ��Ӷ�ʧ�鼮");
        System.out.println("2 �޸Ķ�ʧ�鼮��Ϣ");
        System.out.println("3 ɾ����ʧ�鼮��Ϣ");
        System.out.println("4 ��ѯ��ѯ��ʧ�鼮");
        System.out.println("5 ���鼮����ʱ������");
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        switch (a) {
            case 1 ->
                //����鼮
                    AddBook(bookLosts);
            case 2 ->
                //�޸��鼮��Ϣ
                    Resetbook(bookLosts);
            case 3 ->
                //ɾ���鼮��Ϣ
                    bookdelete(bookLosts);
            case 4 ->
                //��ѯ�鼮��Ϣ
                    bookSelect(bookLosts, select1());
            case 5->
                //���鼮����ʱ������
                    SortBookList(bookLosts);
            default -> throw new IllegalStateException("Unexpected value: " + a);
        }
    }

    //���鼮����ʱ������ķ���
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

    //һ��ͨѧ������
    public static String select() {
        System.out.println("������ѧ��ѧ��:");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    //�鼮������
    public static String select1() {
        System.out.println("����������:");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    //��ѯ��ʧ�鼮
    public static void bookSelect(ArrayList<BookLost> bookLosts, String title) {
        boolean flag = false;
        for (BookLost book : bookLosts) {
            //�½�ѧ������s1�õ������е�Ԫ��
            if (Objects.equals(title, book.getTitle())) {
                System.out.println("������ " + book.getTitle() +"�鼮������"+book.getName()+ ",��ʧ��ʱ�䣺 " + book.getMonth() + "." + book.getDay() + "����ʧ�ĵص�:   " + book.getAdress());
                flag = true;
            }
        }
        if (flag) {
            System.out.println("��ѯ�ɹ�");
        } else {
            System.out.println("��ѯʧ��!");
        }
    }

    //ɾ����ʧ�鼮��Ϣ

    public static void bookdelete(ArrayList<BookLost> bookLosts) {
        System.out.println("��ѡ��Ҫɾ����ѧ��ѧ��");
        Scanner in = new Scanner(System.in);
        String title= in.nextLine();
        //������һ��flag�����ж�Ҫɾ�����鼮�Ƿ����
        int flag = -1;
        int i=0;
        while (i<bookLosts.size()) {
            //�½�ѧ������s1�õ������е�Ԫ��
            BookLost book = bookLosts.get(i);
            if(title.equals(book.getTitle())) {
                bookLosts.remove(i);
                flag=1;
                break;
            }
            i++;
        }
        if(flag==1)
            System.out.println("ɾ���ɹ�");
        else
            System.out.println("����ɾ�����鼮������");
    }

    //�޸Ķ�ʧ�鼮��Ϣ
    public static void Resetbook(ArrayList<BookLost> bookLosts) {
        Scanner in = new Scanner(System.in);
        System.out.println("������������");
        String title = in.nextLine();
        System.out.println("����������:");
        String name = in.nextLine();
        System.out.println("�����붪ʧ���·ݣ�");
        Integer month = in.nextInt();
        System.out.println("�����붪ʧ�ļ��գ�");
        Integer day = in.nextInt();
        System.out.println("�����붪ʧ�ĵص㣺");
        String adress = in.next();
        //��¼�����Ϣ����Stu1����࣬Ȼ����뵽ArrayList��
        BookLost book = new BookLost(title,name,month,day,adress);
        //��stu�б������ҵ���Ӧ�Ľ����޸�
        for(int i=0;i<bookLosts.size();i++)
        {
            BookLost b = bookLosts.get(i);
            if(b.getTitle().equals(title))
            {
                //��set���������޸�
                bookLosts.set(i,book);
                break;
            }
        }
        System.out.println("�޸ĳɹ�");
    }
    //��Ӷ�ʧ�鼮��Ϣ
    public static void AddBook(ArrayList<BookLost> bookLosts) {
        Scanner in = new Scanner(System.in);
        System.out.println("������������");
        String title = in.nextLine();
        System.out.println("����������:");
        String name = in.nextLine();
        System.out.println("�����붪ʧ���·ݣ�");
        Integer month = in.nextInt();
        System.out.println("�����붪ʧ�ļ��գ�");
        Integer day = in.nextInt();
        System.out.println("�����붪ʧ�ĵص㣺");
        String adress = in.next();
        BookLost book = new BookLost(title,name,month,day,adress);
        bookLosts.add(book);
        System.out.println("��ӳɹ�");
    }

    //��ʾһ��ͨ����������
    public static void allCards(ArrayList<CardLost> cardLosts){
         for (CardLost card:cardLosts){
             System.out.println("ѧ�ţ�" + card.getStu_ID() + ",������" + card.getName() + ",��ʧ��ʱ�䣺" + card.getMonth() + "." + card.getDay() + "����ʧ�ĵص�:" + card.getAdress());
         }
    }
    public static void allBooks(ArrayList<BookLost> bookLosts){
        for (BookLost book:bookLosts){
            System.out.println("������ " + book.getTitle() +"�鼮������"+book.getName()+ ",��ʧ��ʱ�䣺 " + book.getMonth() + "." + book.getDay() + "����ʧ�ĵص�:   " + book.getAdress());
        }
    }
}