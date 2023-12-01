import java.io.*;
import java.util.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicTableUI;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class Borrow {
    private int bookID;
    private int memberID;
    private String borrowDate;
    private String returnDate;

    private String Bname;
    private String Mname;

    //TXT file that stores BorrowRecord
    static File  rfile = new File("BorrowRecord.txt");

    // Initialize an Arraylist to store records
    static ArrayList<Borrow> recordArr = new ArrayList<Borrow>();

    // Initialize an Arraylist to store the search results
    static ArrayList<Borrow> searchRecordsArr = new ArrayList<Borrow>();

    static Book book = new Book();
    static ArrayList<Book> books = book.getBooks();


    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String SborrowDate;
    String SreturnDate;

    static JPanel recordPanel;
    
    static JScrollPane BorrowscrollPane;

    static JTable dsrtable, dstable;
    static JScrollPane disResultscrollPane;


    
    public Borrow(){
        //Default Constructor
    }

    //Constructor
    public Borrow(int memberID, int bookID, String borrowDate, String returnDate){
        this.bookID = bookID;
        this.memberID = memberID;
        this.borrowDate=borrowDate;
        this.returnDate = returnDate;
       
    }

    public Borrow(int memberID, String Mname, int bookID, String Bname, String borrowDate, String returnDate){
        this.bookID = bookID;
        this.Mname = Mname;
        this.memberID = memberID;
        this.Bname = Bname;
        this.borrowDate=borrowDate;
        this.returnDate = returnDate;
       
    }

    public static void displayRecord(){
        books.clear();
        Member.memArr.clear();
        book.loadBook();
        Member.loadMem();
        recordPanel = new JPanel();
        recordPanel.setLayout(new BorderLayout());
        recordPanel.setBounds(300,120, 835, 500);
        
        recordPanel.setBackground(Color.decode("#1b234c"));  
        
        String[] columnNames = {"","Mem. ID","Member Name", "Book ID","Book Name", "Borrow Date","Return Date"};
        Object[][] data = new Object[recordArr.size()][7];
        for (int i = 0; i < recordArr.size(); i++) {
            Borrow b = recordArr.get(i); 

            String temp = "(Deleted Book)";
            for(Book book: books){
                if(book.getBookID()==b.getBbookID()){
                    temp = book.getBookName();   
           
                    data[i][1] = b.getBmemberID();
                    data[i][2] = Member.memArr.get(b.getBmemberID()-1).getMemName();
                    data[i][3] = b.getBbookID();
                    data[i][4] = temp;
                    data[i][5] = b.getBorrowDate();
                    data[i][6] = b.getReturnDate();
                }else{
                    data[i][1] = b.getBmemberID();
                    data[i][2] = Member.memArr.get(b.getBmemberID()-1).getMemName();
                    data[i][3] = b.getBbookID();
                    data[i][4] = temp;
                    data[i][5] = b.getBorrowDate();
                    data[i][6] = b.getReturnDate();
                }
            }
            
        }

        if(dstable != null) {
            recordPanel.remove(dstable);
            recordPanel.remove(BorrowscrollPane);
        }

        dstable = new JTable(data, columnNames);

        

        TableColumn Column0 = dstable.getColumnModel().getColumn(0);
        Column0.setMaxWidth(35);

        TableColumn Column1 = dstable.getColumnModel().getColumn(1);
        Column1.setPreferredWidth(50);

        TableColumn Column2 = dstable.getColumnModel().getColumn(2);
        Column2.setPreferredWidth(150);

        TableColumn Column3 = dstable.getColumnModel().getColumn(3);
        Column3.setPreferredWidth(50);

        TableColumn Column4 = dstable.getColumnModel().getColumn(4);
        Column4.setPreferredWidth(250);

        TableColumn Column5 = dstable.getColumnModel().getColumn(5);
        Column5.setPreferredWidth(80);

        TableColumn Column6 = dstable.getColumnModel().getColumn(6);
        Column6.setPreferredWidth(80);

        dstable.setBackground(Color.decode("#29315a"));
        dstable.setForeground(Color.WHITE);
        dstable.setShowGrid(false);
        dstable.setRowHeight(50); 
        dstable.setIntercellSpacing(new Dimension(0, 0));
        dstable.setUI(new BasicTableUI());
        dstable.setFocusable(false);



            

        dstable.setFillsViewportHeight(true);
        dstable.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(Color.decode("#08103a"));
                label.setFont(new Font("Arial", Font.BOLD, 14));
                
                return label;
            }
        });
        dstable.getTableHeader().setPreferredSize(new Dimension(50,50));
       
        dstable.getTableHeader().setForeground(Color.WHITE);
        dstable.getTableHeader().setBorder(new LineBorder(Color.decode("#1b234c")));

        BorrowscrollPane = new JScrollPane(dstable);
        BorrowscrollPane.setBorder(new LineBorder(Color.decode("#1b234c")));
        recordPanel.add(BorrowscrollPane);

       
        
    }

    public void SearchRecord(String rSearch, int stype){
        searchRecordsArr.clear();
        book.loadBook();
        Member.loadMem();

        for (int i = 0; i < recordArr.size(); i++) {
            Borrow b = recordArr.get(i);

            String temp = "(Deleted Book)";
            String temp1 = "(Unknown Member)";
            for(Book book: books){
                 for(Member m: Member.memArr){
                if(book.getBookID()==b.getBbookID()){
                    temp = book.getBookName();   

                }           

            
           
                if(m.getMemID()==b.getBmemberID()){
                    temp1 = m.getMemName();   


                }
            }

        }
        if(stype == 1){
            if(Integer.toString(b.getBmemberID()).equals(rSearch) || Integer.toString(b.getBbookID()).equals(rSearch) || temp1.toLowerCase().contains(rSearch.toLowerCase()) || temp.toLowerCase().contains(rSearch.toLowerCase())&& temp != "(Deleted Book)" && b.getReturnDate().equals("-")){
                int mi = b.getBmemberID();
                String mn = temp1;
                int bi = b.getBbookID();
                String bn = temp;
                String bd = b.getBorrowDate();
                String rd = b.getReturnDate();
                
                searchRecordsArr.add(new Borrow(mi, mn, bi, bn, bd, rd));
            }
            
        }else{

        if(Integer.toString(b.getBmemberID()).equals(rSearch) || Integer.toString(b.getBbookID()).equals(rSearch) || temp1.toLowerCase().contains(rSearch.toLowerCase()) || temp.toLowerCase().contains(rSearch.toLowerCase())&& temp != "(Deleted Book)" ){
        
            int mi = b.getBmemberID();
            String mn = temp1;
            int bi = b.getBbookID();
            String bn = temp;
            String bd = b.getBorrowDate();
            String rd = b.getReturnDate();
            
            searchRecordsArr.add(new Borrow(mi, mn, bi, bn, bd, rd));
        }

        }
    }
    displaySearchRecord();
    
    }

    public static void displaySearchRecord(){

         
        
        String[] columnNames = {"","Mem. ID","Member Name", "Book ID","Book Name", "Borrow Date","Return Date"};
        Object[][] data = new Object[searchRecordsArr.size()][7];
        for (int i = 0; i < searchRecordsArr.size(); i++) {
            Borrow b = searchRecordsArr.get(i);           
           
            data[i][1] = b.getBmemberID();
            data[i][2] = b.Mname;
            data[i][3] = b.getBbookID();
            data[i][4] = b.Bname;
            data[i][5] = b.getBorrowDate();
            data[i][6] = b.getReturnDate();
            
        }

        if(dsrtable != null) {
            try{
            Librarian.recordSPanel.remove(dsrtable);
            Librarian.recordSPanel.remove(disResultscrollPane);
            }catch(NullPointerException e){

            }
            try{
                Member.returnBookPanel.remove(dsrtable);
                Member.returnBookPanel.remove(disResultscrollPane);
            }catch(NullPointerException e1){

            }
        }

        dsrtable = new JTable(data, columnNames);

        TableColumn Column0 = dsrtable.getColumnModel().getColumn(0);
        Column0.setMaxWidth(35);

        TableColumn Column1 = dsrtable.getColumnModel().getColumn(1);
        Column1.setPreferredWidth(50);

        TableColumn Column2 = dsrtable.getColumnModel().getColumn(2);
        Column2.setPreferredWidth(150);

        TableColumn Column3 = dsrtable.getColumnModel().getColumn(3);
        Column3.setPreferredWidth(50);

        TableColumn Column4 = dsrtable.getColumnModel().getColumn(4);
        Column4.setPreferredWidth(250);

        TableColumn Column5 = dsrtable.getColumnModel().getColumn(5);
        Column5.setPreferredWidth(80);

        TableColumn Column6 = dsrtable.getColumnModel().getColumn(6);
        Column6.setPreferredWidth(80);

        dsrtable.setBackground(Color.decode("#29315a"));
        dsrtable.setForeground(Color.WHITE);
        dsrtable.setShowGrid(false);
        dsrtable.setRowHeight(50); 
        dsrtable.setIntercellSpacing(new Dimension(0, 0));
        dsrtable.setUI(new BasicTableUI());
        dsrtable.setFocusable(false);

        dsrtable.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(Color.decode("#08103a"));
                label.setFont(new Font("Arial", Font.BOLD, 14));
                
                return label;
            }
        });    

        dsrtable.setFillsViewportHeight(true);
        dsrtable.getTableHeader().setPreferredSize(new Dimension(50,50));
       
        dsrtable.getTableHeader().setForeground(Color.WHITE);
        dsrtable.getTableHeader().setBorder(new LineBorder(Color.decode("#1b234c")));

        disResultscrollPane = new JScrollPane(dsrtable);
        disResultscrollPane.setBorder(new LineBorder(Color.decode("#1b234c")));
        
    }

    public ArrayList<Borrow> getRecords(){
        return recordArr;
    }

    public ArrayList<Borrow> getsearchRecords(){
        return searchRecordsArr;
    }

    

    public int getBmemberID(){
        return memberID;
    }

    public int getBbookID(){
        return bookID;
    }

    public String getBorrowDate(){
        return borrowDate;
    }

    public String getReturnDate(){
        return returnDate;
    }

    public static void loadRecord(){
        try{   
            Scanner rsc = new Scanner(rfile);
            while(rsc.hasNextLine()){
                int bbid = Integer.parseInt(rsc.nextLine());
                int n = Integer.parseInt(rsc.nextLine());
                String a = rsc.nextLine();
                String s = rsc.nextLine();
                recordArr.add(new Borrow(bbid,n,a,s));


            }

            rsc.close();
        } catch (IOException e){
            System.out.println("Failed to load record: " + e.getMessage());
        }
    }

    public static void saveRecord(){
        try{ 
            FileWriter fw = new FileWriter(rfile);
            PrintWriter add = new PrintWriter(fw);

                for(Borrow a : recordArr){
                    add.println(a.memberID);
                    add.println(a.bookID);
                    add.println(a.borrowDate);
                    add.println(a.returnDate);
                }
                add.close();
                fw.close();
        } catch (IOException e){
                System.out.println("Failed to save book: " + e.getMessage());
        }

    }

    public static void updateReturnDate(int bid, String rd){
        recordArr.clear();
        loadRecord();

        for (int i = 0; i < recordArr.size(); i++) {
            Borrow b = recordArr.get(i);
        if(Integer.toString(b.getBbookID()).equals(Integer.toString(bid)) && b.getReturnDate().equals("-") ){
            b.returnDate = rd;
            break;
        }
    }
    saveRecord();
    }

}