import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.border.LineBorder;

import java.awt.event.*;

public class Member implements ActionListener{

    private int memberID;
    private String memberName;
    private String memberPass;

    //TXT file that stores members
    static File  file = new File("mem.txt");

    // Initialize an Arraylist to store books
    static ArrayList<Member> memArr = new ArrayList<Member>();


    public Member(){
        //Default Constructor
    }

    //Constructor
    public Member(int memberID, String memberName, String memberPass){
        this.memberID = memberID;
        this.memberName = memberName;
        this.memberPass = memberPass;
    }


   
    //Frames
    static JFrame MMainFrame;
    static JFrame borrowBookFrame;
    static JFrame returnBookFrame;
    static JFrame temp;
    
    //Panel
    static JPanel MMainPanel;
    static JPanel MuserPanel;
    static JPanel mtemPanel;
    static JPanel mtbPanel;
    static JPanel disBookPanel;
    static JPanel disBookRPanel;
    static JPanel seaPanel;
    static JPanel returnBookPanel;

    //Buttons
     static JButton borrowButton; 
     static JButton borrowSubmit;
     static JButton borrowSubmit1;
     static JButton borrowBack;  
     static JButton returnButton;
	 static JButton returnSubmit;
     static JButton returnBack;
     static JButton logoutButton;
     static JButton clear;

     static DateFormat dateFormat = new SimpleDateFormat ("dd-MM-yyyy");
	
    static int bid, uid;
    private static JFormattedTextField bdate, rtdate;

    static Book book = new Book();
    static Borrow record = new Borrow();
    static ArrayList<Book> books = book.getBooks();
    static ArrayList<Book> searchResults = book.getSearchResults();
    static ArrayList<Borrow> records = record.getRecords();
    static ArrayList<Borrow> searchrecords = record.getsearchRecords();

    static boolean BorrowPanel = false;
    static boolean ReturnPanel = true;
    static boolean Check = true;

    static String mmName;


    public static void member(){
         
        
        JLabel MMainLabel;          

        MMainFrame = new JFrame("Member Menu");
        MMainFrame.setLocation(new Point(350,200));
        MMainFrame.setBackground(Color.decode("#29315a")); 
        MMainFrame.setSize(new Dimension(1200,700));
        MMainFrame.setResizable(false);
        MMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MMainPanel = new JPanel();
        MMainPanel.setLayout(null);
        MMainPanel.setBounds(10, 100, 220,550 );
        MMainPanel.setBackground(Color.decode("#1b234c"));    
        

        MuserPanel = new JPanel();
        MuserPanel.setBackground(Color.decode("#1b234c"));  
        MMainFrame.add(MuserPanel);

        MuserPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2, 5, 0, 5);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;

        
        memArr.clear();
        loadMem();
        for(Member M : memArr){
            if(M.getMemID() == Authentication.LoggedmID){
                mmName = M.getMemName();
                break;
            }
        }
        MMainLabel = new JLabel("Welcome, " + mmName);
        MMainLabel.setForeground(Color.decode("#b3b9dc"));
        MMainLabel.setFont(new Font("Sans Serif", Font.BOLD, 18));
        c.gridx = 1;
        MuserPanel.add(MMainLabel, c);

        JLabel MuType = new JLabel("Member");
        MuType.setFont(new Font("Arial", Font.PLAIN, 11));
        MuType.setForeground(Color.decode("#6d7688"));
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        MuserPanel.add(MuType, c);
        MuserPanel.setBounds(10, 20, 220,70 );
        MMainFrame.add(MMainPanel);

        mtemPanel = new JPanel();
        mtemPanel.setLayout(null);
        mtemPanel.setBackground(Color.decode("#29315a"));

        mtbPanel = new JPanel();
        mtbPanel.setLayout(null);
        mtbPanel.setBounds(250,20, 930, 630);
        mtbPanel.setBackground(Color.decode("#1b234c"));
        mtbPanel.setBorder(BorderFactory.createEmptyBorder(35,50,15,45));

        seaPanel = new JPanel();
        seaPanel.setLayout(null);
        seaPanel.setBounds(350,70, 250, 30);
        seaPanel.setBackground(Color.decode("#08103a"));

        //Initialize
        borrowSubmit1 = new JButton();

        //borrow
        borrowButton = new JButton("Borrow");
        borrowButton.setBackground(Color.decode("#008f67"));
        borrowButton.setForeground(Color.WHITE);
        borrowButton.setBounds(15, 15, 190, 50);
        borrowButton.setHorizontalAlignment(SwingConstants.LEFT);
        borrowButton.setFont(new Font("Arial", Font.BOLD, 15));
        borrowButton.setFocusable(false); 
        borrowButton.setBorder(BorderFactory.createEmptyBorder(0,60,0,0));

        borrowButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    if(BorrowPanel == true){
                borrowButton.setBackground(Color.decode("#29315a"));
                borrowButton.setForeground(Color.WHITE);
                borrowButton.setFont(new Font("Arial", Font.BOLD, 15));
                    }
                }
            
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    if(BorrowPanel == true){
                borrowButton.setBackground(Color.decode("#1b234c"));
                borrowButton.setForeground(Color.decode("#aeb7c0"));
                borrowButton.setFont(new Font("Arial", Font.BOLD, 14));
                
                } }
            });

      

       borrowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                
               borrowButton.setBackground(Color.decode("#29315a"));

                MMainFrame.remove(mtemPanel);
                MMainFrame.remove(mtbPanel);
                MMainFrame.remove(returnBookPanel); 
              
                MMainFrame.add(mtbPanel);
                MMainFrame.add(mtemPanel);  

                borrowSubmit.setVisible(true);
                Book.LseaSubmit.setVisible(true);    
                Book.seaField.setVisible(true);
                seaPanel.setVisible(true);
                disBookPanel.setVisible(true);
                disBookRPanel.setVisible(true);
                Book.seaField.setText("");
                clear.setVisible(false);
                returnSubmit.setVisible(false);

               borrowButton.setBounds(15, 15, 190, 50);
               borrowButton.setBackground(Color.decode("#008f67"));
               borrowButton.setForeground(Color.WHITE);
               borrowButton.setFont(new Font("Arial", Font.BOLD, 15));
                

                returnButton.setBackground(Color.decode("#1b234c"));
                returnButton.setForeground(Color.decode("#aeb7c0"));
                returnButton.setFont(new Font("Arial", Font.BOLD, 14));
                returnButton.setBounds(10, 65, 190, 50);

                BorrowPanel = false;
                ReturnPanel = true;
                MMainFrame.validate();
                MMainFrame.repaint();
            }
        });

        clear = new JButton("X");
        clear.setBounds(210, 0, 50, 30);
        clear.setOpaque(false);
        clear.setContentAreaFilled(false);
        clear.setBorderPainted(false);
        clear.setFocusable(false);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                Book.seaField.setText("");
                disBookPanel.setVisible(true);
                clear.setVisible(false);
                borrowSubmit.setVisible(true);
                borrowSubmit1.setVisible(false);
                
                MMainFrame.validate();
                MMainFrame.repaint();
            }
        });

        clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clear.setBackground(Color.decode("#03b17e"));
            
            
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clear.setBackground(Color.decode("#008f65"));
            
                
            }
        });

        //Create borrow button
        borrowSubmit = new JButton("Borrow");
        borrowSubmit.setBounds(785, 50, 95, 30);
        borrowSubmit.setContentAreaFilled(false);
        borrowSubmit.setBorder(new LineBorder(Color.decode("#178470"), 2));
        borrowSubmit.setForeground(Color.decode("#aeb7c0"));
        borrowSubmit.setFocusable(false);

        borrowSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                borrowSubmit.setBackground(Color.decode("#29315a"));
                borrowSubmit.setForeground(Color.WHITE);
                borrowSubmit.setBounds(785, 49, 95, 30);
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                borrowSubmit.setBackground(Color.decode("#1b234c"));
                borrowSubmit.setForeground(Color.decode("#aeb7c0"));
                borrowSubmit.setBounds(785, 50, 95, 30);
            }
        });

        borrowSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
               int selectedRow = Book.dbtable.getSelectedRow();
                if (selectedRow != -1) {
                    Check = false;
                    borrowBook(selectedRow);
                }else{
                    JOptionPane.showMessageDialog(temp, "Select the book you want to delete in the table!", "No Book Selected", JOptionPane.INFORMATION_MESSAGE);
                }
                
                        
            }

        });

       //return
        returnButton = new JButton("Return Book");
        returnButton.setBackground(Color.decode("#1b234c"));
        returnButton.setForeground(Color.decode("#aeb7c0"));
        returnButton.setBounds(10, 65, 190, 50);
        returnButton.setHorizontalAlignment(SwingConstants.LEFT);
        returnButton.setFont(new Font("Arial", Font.BOLD, 14));
        returnButton.setFocusable(false); 
        returnButton.setBorder(BorderFactory.createEmptyBorder(0,60,0,0));

        returnButton.addMouseListener(new java.awt.event.MouseAdapter() {
            
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(ReturnPanel == true){
                returnButton.setBackground(Color.decode("#29315a"));
                returnButton.setForeground(Color.WHITE);
                returnButton.setFont(new Font("Arial", Font.BOLD, 15));
                }
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if(ReturnPanel == true){
                returnButton.setBackground(Color.decode("#1b234c"));
                returnButton.setForeground(Color.decode("#aeb7c0"));
                returnButton.setFont(new Font("Arial", Font.BOLD, 14));
                }
            }
        });

               

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
 
           // Create panel for return book 
           returnBookPanel = new JPanel();
           returnBookPanel.setLayout(new BorderLayout());
           returnBookPanel.setBounds(300,120, 835, 500);
  
           returnBookPanel.setBackground(Color.decode("#1b234c"));  
  
          //display borrowed books
          memArr.clear();
          Book.booksArr.clear();
          Borrow.recordArr.clear();
          Borrow.searchRecordsArr.clear();
          book.loadBook();
          record.loadRecord();
          loadMem();
  
          for (int i = 0; i < records.size(); i++) {
              Borrow b = records.get(i);
          if(Integer.toString(b.getBmemberID()).equals(Integer.toString(Authentication.LoggedmID)) && b.getReturnDate().equals("-")){
              int mi = b.getBmemberID();
              String mn = memArr.get(b.getBmemberID()-1).getMemName();
              int bi = b.getBbookID();
              String bn = books.get(b.getBbookID()-1).getBookName();
              String bd = b.getBorrowDate();
              String rd = b.getReturnDate();
              record.searchRecordsArr.add(new Borrow(mi, mn, bi, bn, bd, rd));
  
          }
      }
      record.displaySearchRecord();
  
          returnBookPanel.add(Borrow.disResultscrollPane); 
          
          MMainFrame.remove(mtemPanel);
          MMainFrame.remove(mtbPanel);
          
          MMainFrame.add(returnBookPanel);
          mtbPanel.add(returnSubmit);
          MMainFrame.add(mtbPanel);
          MMainFrame.add(mtemPanel);     
  
          borrowSubmit.setVisible(false);
          borrowSubmit1.setVisible(false);
          Book.LseaSubmit.setVisible(false);                
          Book.seaField.setVisible(false);
          seaPanel.setVisible(false);
          disBookPanel.setVisible(false);
          disBookRPanel.setVisible(false);
          Book.seaField.setText("");
          clear.setVisible(false);
          returnSubmit.setVisible(true);
  
          MMainFrame.validate();
          MMainFrame.repaint();

                 BorrowPanel = true;                 
                 borrowButton.setBackground(Color.decode("#1b234c"));
                 borrowButton.setForeground(Color.decode("#aeb7c0"));
                 borrowButton.setFont(new Font("Arial", Font.BOLD, 14));
                 borrowButton.setBounds(10, 15, 190, 50);

                 ReturnPanel = false;
                 returnButton.setBackground(Color.decode("#008f67"));
                 returnButton.setForeground(Color.WHITE);
                 returnButton.setFont(new Font("Arial", Font.BOLD, 15));
                 returnButton.setBounds(15, 65, 190, 50);

               
            }
        });

        // Create return button
        returnSubmit = new JButton("Return");
        returnSubmit.setBounds(785, 50, 95, 30);
        returnSubmit.setContentAreaFilled(false);
        returnSubmit.setBorder(new LineBorder(Color.decode("#178470"), 2));
        returnSubmit.setForeground(Color.decode("#aeb7c0"));
        returnSubmit.setFocusable(false);

        returnSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                returnSubmit.setBackground(Color.decode("#29315a"));
                returnSubmit.setForeground(Color.WHITE);
                returnSubmit.setBounds(785, 49, 95, 30);
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                returnSubmit.setBackground(Color.decode("#1b234c"));
                returnSubmit.setForeground(Color.decode("#aeb7c0"));
                returnSubmit.setBounds(785, 50, 95, 30);
            }
        });

        returnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
               int selectedRow = Borrow.dsrtable.getSelectedRow();
                if (selectedRow != -1) {
                    returnBook(selectedRow);                    
                }else{
                    JOptionPane.showMessageDialog(temp, "Select the book you want to delete in the table!", "No Book Selected", JOptionPane.INFORMATION_MESSAGE);
                }
                
                        
            }

        });

        //logout
        logoutButton = new JButton("Logout");
        logoutButton.setBackground(Color.decode("#1b234c"));
        logoutButton.setForeground(Color.decode("#aeb7c0"));
        logoutButton.setBounds(15, 450, 190, 50);
        logoutButton.setHorizontalAlignment(SwingConstants.LEFT);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setFocusable(false); 
        logoutButton.setBorder(BorderFactory.createEmptyBorder(0,60,0,0));

        logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutButton.setBackground(Color.decode("#29315a"));
                logoutButton.setForeground(Color.WHITE);
                logoutButton.setBounds(18, 450, 190, 50);
                
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutButton.setBackground(Color.decode("#1b234c"));
                logoutButton.setForeground(Color.decode("#aeb7c0"));
                logoutButton.setBounds(15, 450, 190, 50);
                
            }
        });
        
        
        MMainPanel.add(borrowButton);
        MMainPanel.add(returnButton);
        MMainPanel.add(logoutButton);

       Member mem = new Member();
    
        borrowButton.addActionListener(mem);
        returnButton.addActionListener(mem);
        logoutButton.addActionListener(mem);


        disBookPanel = new JPanel();
        disBookPanel.setLayout(new BorderLayout());
        disBookPanel.setBounds(300,120, 835, 500);
        
        disBookPanel.setBackground(Color.decode("#1b234c"));   

        disBookRPanel = new JPanel();
        disBookRPanel.setLayout(new BorderLayout());
        disBookRPanel.setBounds(300,120, 835, 500);
        
        disBookRPanel.setBackground(Color.decode("#1b234c"));   
        
        //Call search book field
        Book.searchBookPanel();
        Book.seaField.setBounds(55, 50, 250, 30);
        

        
        //Call Book Table
        Book.displayBook();
        disBookPanel.add(Book.disBookscrollPane);
        MMainFrame.add(disBookPanel,BorderLayout.CENTER);

        Book.dbtable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me1) {
              if (me1.getClickCount() == 1) {
                Book.seaField.transferFocus();
              }
            }
          });
        
        
        mtbPanel.add(Book.seaField);
        seaPanel.add(clear);
        clear.setVisible(false);
        
        mtbPanel.add(borrowSubmit);
        mtbPanel.add(borrowSubmit1);
        //mtbPanel.add(LaddButton); 
        mtbPanel.add(Book.LseaSubmit);
        MMainFrame.add(seaPanel);
        MMainFrame.add(mtbPanel);
        
        MMainFrame.add(mtemPanel);


        Book.LseaSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                if(!Book.seaField.getText().isEmpty()){

                clear.setVisible(true);
                borrowSubmit.setVisible(false);
               
                
                books.clear();
                searchResults.clear();
                Book.loadBook();
                
                Book.searchBook();
                
                MMainFrame.remove(disBookRPanel);
                disBookPanel.setVisible(false);
                MMainFrame.remove(mtemPanel);
                MMainFrame.remove(mtbPanel);
                Book.displaySearchResults();
                disBookRPanel.add(Book.disBookResultscrollPane);

                //Create borrowSubmit1 for delete book in searchresult
                borrowSubmit1 = new JButton("Borrow");

                borrowSubmit1.setBounds(785, 50, 95, 30);
                borrowSubmit1.setContentAreaFilled(false);
                borrowSubmit1.setBorder(new LineBorder(Color.decode("#178470"), 2));
                borrowSubmit1.setForeground(Color.decode("#aeb7c0"));
                borrowSubmit1.setFocusable(false);

                borrowSubmit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                borrowSubmit1.setBackground(Color.decode("#29315a"));
                borrowSubmit1.setForeground(Color.WHITE);
                borrowSubmit1.setBounds(785, 49, 95, 30);
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                borrowSubmit1.setBackground(Color.decode("#1b234c"));
                borrowSubmit1.setForeground(Color.decode("#aeb7c0"));
                borrowSubmit1.setBounds(785, 50, 95, 30);
            }
        });
                
                MMainFrame.remove(borrowSubmit1);
                mtbPanel.add(borrowSubmit1);
                MMainFrame.add(disBookRPanel);
                MMainFrame.add(mtbPanel);
                MMainFrame.add(mtemPanel);
                MMainFrame.validate();
                MMainFrame.repaint();

                borrowSubmit1.addActionListener(new ActionListener() {
                   
                    public void actionPerformed(ActionEvent e) { 
                       int selectedRow = Book.dbrtable.getSelectedRow();
                        if (selectedRow != -1) {
                            borrowBook(selectedRow);
                        }else{
                            JOptionPane.showMessageDialog(temp, "Select the book you want to delete in the table!", "No Book Selected", JOptionPane.INFORMATION_MESSAGE);
                        }
                        
                                
                    }
        
                });

                Book.dbrtable.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent me) {
                      if (me.getClickCount() == 1) {                    
                             Book.seaField.transferFocus();                                                   
                      }
                    }
                  });


              
            }else{
                MMainFrame.remove(disBookRPanel);
                disBookPanel.setVisible(true);
            }
            }

        });



        
        MMainFrame.add(mtbPanel);        
        MMainFrame.add(mtemPanel);
        
        MMainFrame.setVisible(true);
        
	}     
    

    public void actionPerformed(ActionEvent e){
       //
	  if(e.getSource() == logoutButton){
        int option = JOptionPane.showConfirmDialog(temp,"Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(option == JOptionPane.YES_OPTION){
            MMainFrame.dispose();
            Authentication.Menu();
        }
       }
	}
	

    //Add borrow Page
    public static void borrowBook(int bid2){
        
                Book.booksArr.clear();
                Borrow.recordArr.clear();
                Book.loadBook();
                record.loadRecord();
                bdate= new JFormattedTextField(dateFormat);
                bdate.setValue (new Date());
                String borrowdate = bdate.getText();
                int f = -1;
                
                if(Check = false){
                    for (int i = 0; i < books.size(); i++) {

                        if(books.get(bid2).getBookID()== books.get(i).getBookID() && books.get(i).getStatus() == false){
                            f=i;
                            JOptionPane.showMessageDialog(borrowBookFrame, "This Book had been borrowed!" + books.get(i).getBookID() + bid2, "Book Unavailable", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        
                        }else if (books.get(bid2).getBookID()== books.get(i).getBookID() && books.get(i).getStatus() != false ) { // get(i) is to get the data from the i value of array & define getBookID() to get the bookID
                            f=i;
                            int option = JOptionPane.showConfirmDialog(borrowBookFrame,"Are you sure you want to borrow " + books.get(f).getBookName() +"?", "Borrow Book", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if(option == JOptionPane.YES_OPTION){                                
                            int bid = books.get(bid2).getBookID();
                            
                            book.updateStatus(bid,false);
                            JOptionPane.showMessageDialog(borrowBookFrame, "Book Borrow Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            book.saveBook();
                            records.add(new Borrow(Authentication.LoggedmID,bid,borrowdate,"-"));
                            record.saveRecord();
                            MMainFrame.remove(mtemPanel);
                            MMainFrame.remove(mtbPanel);
                            MMainFrame.remove(disBookPanel);
            
                            if(Book.dbtable != null) {
                                disBookPanel.remove(Book.dbtable);
                                disBookPanel.remove(Book.disBookscrollPane);
                            }
                            Book.displayBook();     
            
            
                            disBookPanel.add(Book.disBookscrollPane);
                            MMainFrame.add(disBookPanel);
                            MMainFrame.add(mtbPanel);
                            MMainFrame.add(mtemPanel);
            
                            disBookPanel.setVisible(true);
                            Book.seaField.setText("");
                            clear.setVisible(false);
                            
            
                            MMainFrame.validate();
                            MMainFrame.repaint();
                            break;
                            }
                        }
                    }
               
                   
            
                }else{
                    for (int i = 0; i < books.size(); i++) {

                        if(books.get(i).getBookID()== searchResults.get(bid2).getBookID() && books.get(i).getStatus() == false){
                            f=i;
                            JOptionPane.showMessageDialog(borrowBookFrame, "This Book had been borrowed!" + books.get(i).getBookID() + bid2, "Book Unavailable", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        
                        }else if (books.get(i).getBookID()== searchResults.get(bid2).getBookID() && books.get(i).getStatus() != false ) { // get(i) is to get the data from the i value of array & define getBookID() to get the bookID
                            f=i;
                            int option = JOptionPane.showConfirmDialog(borrowBookFrame,"Are you sure you want to borrow " + books.get(f).getBookName() +"?", "Borrow Book", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if(option == JOptionPane.YES_OPTION){                                
                            int bid = books.get(bid2).getBookID();
                            
                            book.updateStatus(bid,false);
                            JOptionPane.showMessageDialog(borrowBookFrame, "Book Borrow Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            book.saveBook();
                            records.add(new Borrow(Authentication.LoggedmID,bid,borrowdate,"-"));
                            record.saveRecord();

                            MMainFrame.remove(mtemPanel);
                            MMainFrame.remove(mtbPanel);
                            MMainFrame.remove(disBookRPanel);
            
                            if(Book.dbtable != null) {
                                disBookPanel.remove(Book.dbtable);
                                disBookPanel.remove(Book.disBookscrollPane);
                            }
                            Book.displayBook();     
            
            
                            disBookPanel.add(Book.disBookscrollPane);
                            MMainFrame.add(disBookPanel);
                            MMainFrame.add(mtbPanel);
                            MMainFrame.add(mtemPanel);
            
                            disBookPanel.setVisible(true);
                            Book.seaField.setText("");
                            clear.setVisible(false);
                            
            
                            MMainFrame.validate();
                            MMainFrame.repaint();

                            break;
                            }
                        }
                    }


                   
                }

                    if(f==-1){
                        JOptionPane.showMessageDialog(borrowBookFrame, "Book ID Not Found!", "No Record Found", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }                        
                    
               
    }
		
		
        public static void returnBook(int bid){
            Book.booksArr.clear();
            book.loadBook();
            
            for (int i = 0; i < searchrecords.size(); i++) {
                if(searchrecords.get(bid).getBbookID() == searchrecords.get(i).getBbookID() && searchrecords.get(i).getReturnDate().equals("-")){
                    for (Book b : books){
                        if(searchrecords.get(bid).getBbookID() == b.getBookID()){
                    int option = JOptionPane.showConfirmDialog(Member.MMainFrame,"Are you sure you want to return " + b.getBookName() +"?", "Return Book", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(option == JOptionPane.YES_OPTION){
                        rtdate= new JFormattedTextField(dateFormat);
                        rtdate.setValue (new Date());       
                        Borrow.updateReturnDate(searchrecords.get(i).getBbookID(),rtdate.getText());
                        Book.updateStatus(searchrecords.get(i).getBbookID(),true);
                        book.saveBook();      

                        MMainFrame.remove(mtemPanel);
                            MMainFrame.remove(mtbPanel);
                            MMainFrame.remove(returnBookPanel);
            
                           //display borrowed books
                            memArr.clear();
                            Book.booksArr.clear();
                            Borrow.recordArr.clear();
                            Borrow.searchRecordsArr.clear();
                            book.loadBook();
                            record.loadRecord();
                            loadMem();
                    
                            for (int j = 0; j < records.size(); j++) {
                                Borrow b1 = records.get(j);
                            if(Integer.toString(b1.getBmemberID()).equals(Integer.toString(Authentication.LoggedmID)) && b1.getReturnDate().equals("-")){
                                int mi = b1.getBmemberID();
                                String mn = memArr.get(b1.getBmemberID()-1).getMemName();
                                int bi = b1.getBbookID();
                                String bn = books.get(b1.getBbookID()-1).getBookName();
                                String bd = b1.getBorrowDate();
                                String rd = b1.getReturnDate();
                                record.searchRecordsArr.add(new Borrow(mi, mn, bi, bn, bd, rd));
                    
                            }
                        }
                           Borrow.displaySearchRecord();   
            
            
                            returnBookPanel.add(Borrow.disResultscrollPane);
                            MMainFrame.add(returnBookPanel);
                            MMainFrame.add(mtbPanel);
                            MMainFrame.add(mtemPanel);
            
                            returnBookPanel.setVisible(true);
                    
            
                            MMainFrame.validate();
                            MMainFrame.repaint();
                        }
                        break;
                    }
                }
                    break;

                }
            }
        }

        public static void loadMem(){
        
            try{   
                Scanner sc = new Scanner(file);
                while(sc.hasNextLine()){
                    int bbid = Integer.parseInt(sc.nextLine());
                    String n = sc.nextLine();
                    String a = sc.nextLine();
                    memArr.add(new Member(bbid,n,a));
    
    
                }
    
                sc.close();
            } catch (IOException e){
                System.out.println("Failed to load member: " + e.getMessage());
            }
            
        }

        public void saveMem(){
            try{ 
                PrintWriter add = new PrintWriter(file);

                    for(Member a : memArr){
                        add.println(a.memberID);
                        add.println(a.memberName);
                        add.println(a.memberPass);
                    }
                    add.close();
            } catch (IOException e){
                    System.out.println("Failed to save member: " + e.getMessage());
            }
        }
    
        public int getMemID(){
            return memberID;
        }

        public int getLastMemID(){
            for(Member a:memArr){
                memberID = a.getMemID();
            }
            return memberID;
        }
    
        public String getMemName(){
            return memberName;
        }
    
        public String getMemPass(){
            return memberPass;
        }
    
        public ArrayList<Member> getMems(){
            return memArr;
        }

        public void clearmemArr(){
            memArr.clear();
        }
	
}