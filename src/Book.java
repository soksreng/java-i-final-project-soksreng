import java.io.*;
import java.util.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class Book {
     private int bookID;
     String bookName;
     String author;
     private Boolean status;


    static JFrame searchBookFrame;

    //Global Panels
    
    private static JPanel searchResultsPanel;

    static JTextField seaField;

    static JButton LseaSubmit;
    
    static JButton LseaBack; 

    static JScrollPane disBookscrollPane;
    static JScrollPane disBookResultscrollPane;

    JTable table;
    static JTable dbtable;
    static JTable dbrtable;

    //TXT file that stores books
    static File  file = new File("Book.txt");

     // Initialize an Arraylist to store books
     static ArrayList<Book> booksArr = new ArrayList<Book>();

     // Initialize an Arraylist to store the search results
     static ArrayList<Book> searchResultsArr = new ArrayList<Book>();

    
    public Book(){
        //Default Constructor
    }

    //Constructor
    public Book(int bookID, String bookName, String author, Boolean status){
        this.bookID = bookID;
        this.bookName = bookName;
        this.author = author;
        this.status = status;
    }

    public int getBookID(){
        return bookID;
    }

    public String getBookName(){
        return bookName;
    }

    public String getAuthor(){
        return author;
    }

    public Boolean getStatus(){
        return status;
    }

    public String getStatusString(){
        String s;
        if(status == true){
            s = "Available";
           
        }else if(status == false){
            s = "Unavailable";
            
        }else{
            s = "Unknown";
        }
        return s;
    }

    public ArrayList<Book> getBooks(){
        return booksArr;
    }

    public ArrayList<Book> getSearchResults(){
        return searchResultsArr;
    }

    public static void updateStatus(int bid, Boolean stat){
        
        
        for (int i = 0; i < booksArr.size(); i++) {

            if (bid == booksArr.get(i).getBookID()) { // get(i) is to get the data from the i value of array & define getBookID() to get the bookID
                
                booksArr.get(i).status = stat;
                
                break;
                            
            }
        }
        
        
    }

    public void saveBook(){
        try{ 
            PrintWriter add = new PrintWriter(file);

                for(Book a : booksArr){
                    add.println(a.getBookID());
                    add.println(a.getBookName());
                    add.println(a.getAuthor());
                    add.println(a.getStatus());
                }
                

                add.close();
        } catch (IOException e){
                System.out.println("Failed to save book: " + e.getMessage());
            }

    }

    public static void loadBook(){
        
        try{   
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                int bbid = Integer.parseInt(sc.nextLine());
                String n = sc.nextLine();
                String a = sc.nextLine();
                Boolean s = Boolean.parseBoolean(sc.nextLine());
                booksArr.add(new Book(bbid,n,a,s));


            }

            sc.close();
        } catch (IOException e){
            System.out.println("Failed to load book: " + e.getMessage());
        }
        
    }

    public static void displayBook(){    
                   
        booksArr.clear();
        loadBook();

        String[] columnNames = {"","Book ID", "Book Name", "Author","Status"};
        Object[][] data = new Object[booksArr.size()][5];
        for (int i = 0; i < booksArr.size(); i++) {
            Book b = booksArr.get(i);
            data[i][1] = b.getBookID();
            data[i][2] = b.getBookName();
            data[i][3] = b.getAuthor();
            data[i][4] = b.getStatusString();
        }
        
        
        
        dbtable = new JTable(data, columnNames);

        TableColumn Column0 = dbtable.getColumnModel().getColumn(0);
        Column0.setMaxWidth(35);


        TableColumn Column1 = dbtable.getColumnModel().getColumn(1);
        Column1.setPreferredWidth(50);

        TableColumn Column2 = dbtable.getColumnModel().getColumn(2);
        Column2.setPreferredWidth(250);

        TableColumn Column3 = dbtable.getColumnModel().getColumn(3);
        Column3.setPreferredWidth(150);

        TableColumn Column4 = dbtable.getColumnModel().getColumn(4);
        Column4.setPreferredWidth(80);

     
        
        dbtable.setBackground(Color.decode("#29315a"));
        dbtable.setForeground(Color.WHITE);
        dbtable.setShowGrid(false);
        dbtable.setRowHeight(50); 
        dbtable.setIntercellSpacing(new Dimension(0, 0));
        dbtable.setUI(new BasicTableUI());
        dbtable.setFocusable(false);
        
             
        

        dbtable.setFillsViewportHeight(true);
        disBookscrollPane = new JScrollPane(dbtable);
        disBookscrollPane.setBorder(new LineBorder(Color.decode("#1b234c")));
        dbtable.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(Color.decode("#08103a"));
                label.setFont(new Font("Arial", Font.BOLD, 14));
                
                return label;
            }
        });
        dbtable.getTableHeader().setPreferredSize(new Dimension(50,50));
       
        dbtable.getTableHeader().setForeground(Color.WHITE);
        dbtable.getTableHeader().setBorder(new LineBorder(Color.decode("#1b234c")));

        
    }

    public static void searchBookPanel(){

        seaField = new JTextField();
        //seaField.setHorizontalAlignment(SwingConstants.CENTER);
        seaField.setBorder(BorderFactory.createEmptyBorder(3,5,3,5));
        seaField.setForeground(Color.WHITE);
        seaField.setCaretColor(Color.WHITE);
        seaField.setBackground(Color.decode("#08103a"));
        
       //Create buttons
       LseaSubmit = new JButton("Search");
       LseaSubmit.setBounds(360, 50, 100, 30);
       LseaSubmit.setBorder(BorderFactory.createEmptyBorder());
       LseaSubmit.setBackground(Color.decode("#008f65"));
       LseaSubmit.setBorder(new LineBorder(Color.decode("#178470"), 2));
       LseaSubmit.setForeground(Color.WHITE);
       LseaSubmit.setFont(new Font("Arial", Font.BOLD, 12));
       LseaSubmit.setFocusable(false);

       LseaSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            LseaSubmit.setBackground(Color.decode("#03af7c"));
            LseaSubmit.setForeground(Color.WHITE);
            LseaSubmit.setBounds(359, 49, 102, 32);            
        }
    
        public void mouseExited(java.awt.event.MouseEvent evt) {
            LseaSubmit.setBackground(Color.decode("#008f65"));
            LseaSubmit.setForeground(Color.WHITE);
            LseaSubmit.setBounds(360, 50, 100, 30);           
        }
    });
       
    }

    public static void searchBook(){
        booksArr.clear();
        searchResultsArr.clear();
                Book.loadBook();
                
                String query = Book.seaField.getText();
                
                // Iterate through the list of books
                for (Book book : booksArr) {
                    // Check if the book's name or author match the search query
                    if (book.getBookName().toLowerCase().contains(query.toLowerCase()) || book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                        // If a match is found, add the book to the search results list
                        searchResultsArr.add(book);
                    }
                }
                
    }

    public static void displaySearchResults() {

        String[] columnNames = {"","Book ID", "Book Name", "Author","Status"};
        Object[][] data = new Object[searchResultsArr.size()][5];
        for (int i = 0; i < searchResultsArr.size(); i++) {
            Book b = searchResultsArr.get(i);
            data[i][1] = b.getBookID();
            data[i][2] = b.getBookName();
            data[i][3] = b.getAuthor();
            data[i][4] = b.getStatusString();
        }

        if(dbrtable != null) {
            try{
            Librarian.disBookRPanel.remove(dbrtable);
            Librarian.disBookRPanel.remove(disBookResultscrollPane);
            }catch(NullPointerException e){
                
            }
            try{
                Member.disBookRPanel.remove(dbrtable);
            Member.disBookRPanel.remove(disBookResultscrollPane);
            }catch(NullPointerException a){
                
            }
        }
        
        dbrtable = new JTable(data, columnNames);

        TableColumn Column0 = dbrtable.getColumnModel().getColumn(0);
        Column0.setMaxWidth(35);


        TableColumn Column1 = dbrtable.getColumnModel().getColumn(1);
        Column1.setPreferredWidth(50);

        TableColumn Column2 = dbrtable.getColumnModel().getColumn(2);
        Column2.setPreferredWidth(250);

        TableColumn Column3 = dbrtable.getColumnModel().getColumn(3);
        Column3.setPreferredWidth(150);

        TableColumn Column4 = dbrtable.getColumnModel().getColumn(4);
        Column4.setPreferredWidth(80);

     
        
        dbrtable.setBackground(Color.decode("#29315a"));
        dbrtable.setForeground(Color.WHITE);
        dbrtable.setShowGrid(false);
        dbrtable.setRowHeight(50); 
        dbrtable.setIntercellSpacing(new Dimension(0, 0));
        dbrtable.setUI(new BasicTableUI());
        dbrtable.setFocusable(false);
        
             
        

        dbrtable.setFillsViewportHeight(true);
        disBookResultscrollPane = new JScrollPane( dbrtable);
        disBookResultscrollPane.setBorder(new LineBorder(Color.decode("#1b234c")));
        dbrtable.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(Color.decode("#08103a"));
                label.setFont(new Font("Arial", Font.BOLD, 14));
                
                return label;
            }
        });
        dbrtable.getTableHeader().setPreferredSize(new Dimension(50,50));
       
        dbrtable.getTableHeader().setForeground(Color.WHITE);
        dbrtable.getTableHeader().setBorder(new LineBorder(Color.decode("#1b234c")));
    }

    
    
}
