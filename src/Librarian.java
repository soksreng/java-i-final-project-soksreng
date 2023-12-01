import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.event.*;

public class Librarian implements ActionListener{
    private int adminID;
    private String adminName;
    private String adminPass;

    static String searchResults1 = null;
    

    //TXT file that stores librarian
    static File  file = new File("Lib.txt");

    // Initialize an Arraylist to store books
    static ArrayList<Librarian> libArr = new ArrayList<Librarian>();


    public Librarian(){
        //Default Constructor
    }

    //Constructor
    public Librarian(int adminID, String adminName, String adminPass){
        this.adminID = adminID;
        this.adminName = adminName;
        this.adminPass = adminPass;
    }

    //Frames
    static JFrame LMainFrame;
    static JDialog addBookFrame;
    static JDialog editBookFrame;
    static JFrame delBookFrame;
    static JFrame disBookFrame;
    static JFrame recordFrame;
    static JDialog mlibFrame;
    static JFrame temp;

    //Global Panels
    private static JPanel LuserPanel;
    static JPanel disBookPanel;
    static JPanel disBookRPanel;
    private JPanel delTLibPanel;
    private static JPanel tbPanel;
    private static  JPanel temPanel;
    static JPanel LMainPanel;
    static JPanel seaPanel;
    static JPanel recordSPanel;
    static JPanel mlibPanel;
    static JPanel addLibPanel;
    
    //Buttons
    private static JButton LbooksButton;
    private static JButton LmlibButton;
    private static JButton addLib;
    private static JButton delLib;
    private static JButton LaddButton; 
    private static JButton LaddSubmit;
    private static JButton LaddBack;
    private static JButton LdelButton;
    private static JButton LdelButton1;
    private static JButton LeditButton;
    private static JButton LeditButton1;
    private static JButton LeditSubmit;
    private static JButton LeditBack;
    private static JButton LrecordButton;
    private static JButton clear;

    private static JButton Llogout;  
    
    //Label
    private static JLabel lnLabel;
    private static JLabel lpLabel;
    private static JLabel bidLabel;
    private static JLabel bidLabel1;
    private static JLabel bnLabel;
    private static JLabel auLabel;

    static JTable table1;
    static JScrollPane scrollPane;

    static JComboBox rsearchChoice;

    //Text Field
    private static JTextField bnField,auField,rsearchField;

    static Book book = new Book();
    static ArrayList<Book> books = book.getBooks();
    static ArrayList<Book> searchResults = book.getSearchResults();

    static Borrow record = new Borrow();
    ArrayList<Borrow> searchRecords = record.getsearchRecords();


    static int bid;
    static String bn,au;
    static Boolean Check = false;

    static Boolean BookPanel = false;
    static Boolean RecordPanel = true;
    static Boolean LibPanel = true;

    public static void Lmenu(){
        Book.booksArr.clear();
        book.loadBook();         
         //go toBook Record     
        Borrow.recordArr.clear();            
        record.loadRecord();
           
        
        JLabel LMainLabel;        
        
        LMainFrame = new JFrame("Librarian Menu");
        LMainFrame.setLocation(new Point(350,200));
        LMainFrame.setBackground(Color.decode("#29315a"));  

        LMainPanel = new JPanel();
        LMainPanel.setLayout(null);
        LMainPanel.setBounds(10, 100, 220,550 );
        LMainPanel.setBackground(Color.decode("#1b234c"));    
        
        LuserPanel = new JPanel();
        LuserPanel.setBackground(Color.decode("#1b234c"));  
        LMainFrame.add(LuserPanel);

        LuserPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(3, 5, 0, 5);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        
        String lmName = "Unknown";
        String lmType = "Admin";
        libArr.clear();
        loadLib();

        if(Authentication.LoggedLID == 0){
            lmType = "Admin";
        }else{
            lmType = "Librarian ";
        }

        for(Librarian L : libArr){
            if(L.getLibID() == Authentication.LoggedLID){
                lmName = L.getLibName();
                break;
            }
        }


        LMainLabel = new JLabel("Welcome, " + lmName);
        LMainLabel.setForeground(Color.decode("#b3b9dc"));
        LMainLabel.setFont(new Font("Sans Serif", Font.BOLD, 18));
        c.gridx = 1;
        LuserPanel.add(LMainLabel, c);

        JLabel LuType = new JLabel(lmType);
        LuType.setFont(new Font("Arial", Font.PLAIN, 11));
        LuType.setForeground(Color.decode("#6d7688"));
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        LuserPanel.add(LuType, c);
        LuserPanel.setBounds(10, 20, 220,70 );

        temPanel = new JPanel();
        temPanel.setLayout(null);
        temPanel.setBackground(Color.decode("#29315a"));

        tbPanel = new JPanel();
        tbPanel.setLayout(null);
        tbPanel.setBounds(250,20, 930, 630);
        tbPanel.setBackground(Color.decode("#1b234c"));
        tbPanel.setBorder(BorderFactory.createEmptyBorder(35,50,15,45));

        seaPanel = new JPanel();
        seaPanel.setLayout(null);
        seaPanel.setBounds(350,70, 250, 30);
        seaPanel.setBackground(Color.decode("#08103a"));
        
        LMainFrame.add(LMainPanel);
        LMainFrame.setSize(new Dimension(1200,700));
        LMainFrame.setResizable(false);
        LMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        //Initialize
        LdelButton1 = new JButton();
        LeditButton1 = new JButton();
        addLib = new JButton();
        delLib = new JButton();
        rsearchField = new JTextField();
        rsearchChoice = new JComboBox();
        LMainFrame.add(rsearchField);
        Borrow.recordPanel = new JPanel();
        LMainFrame.add(Borrow.recordPanel);
        recordSPanel = new JPanel();
        LMainFrame.add(recordSPanel);
        mlibPanel = new JPanel();
        LMainFrame.add(mlibPanel);

        

        //Create manage lib button 
        LmlibButton = new JButton("Librarians");
        LmlibButton.setBackground(Color.decode("#1b234c"));
        LmlibButton.setForeground(Color.decode("#aeb7c0"));
        LmlibButton.setBounds(10, 115, 190, 50);
        LmlibButton.setHorizontalAlignment(SwingConstants.LEFT);
        LmlibButton.setFont(new Font("Arial", Font.BOLD, 14));
        LmlibButton.setFocusable(false); 
        LmlibButton.setBorder(BorderFactory.createEmptyBorder(0,60,0,0));

        LmlibButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(LibPanel == true){
                LmlibButton.setBackground(Color.decode("#29315a"));
                LmlibButton.setForeground(Color.WHITE);
                LmlibButton.setFont(new Font("Arial", Font.BOLD, 15));
                }
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if(LibPanel == true){
                LmlibButton.setBackground(Color.decode("#1b234c"));
                LmlibButton.setForeground(Color.decode("#aeb7c0"));
                LmlibButton.setFont(new Font("Arial", Font.BOLD, 14));
                }
            }
        });
///LIBRARIAN 
        LmlibButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
              
                LMainFrame.remove(mlibPanel);
                LMainFrame.remove(recordSPanel);
                LMainFrame.remove(Borrow.recordPanel);

                rsearchChoice.setVisible(false);
           


                libArr.clear();   
                loadLib();
                manageLib();       

                LmlibButton.setBounds(15, 115, 190, 50);
                LmlibButton.setBackground(Color.decode("#008f67"));
                LmlibButton.setForeground(Color.WHITE);
                LmlibButton.setFont(new Font("Arial", Font.BOLD, 15));
                        
                LbooksButton.setBounds(10, 15, 190, 50);
                LbooksButton.setBackground(Color.decode("#1b234c"));
                LbooksButton.setForeground(Color.decode("#aeb7c0"));
                LbooksButton.setFont(new Font("Arial", Font.BOLD, 14));                

                LrecordButton.setBackground(Color.decode("#1b234c"));
                LrecordButton.setForeground(Color.decode("#aeb7c0"));
                LrecordButton.setFont(new Font("Arial", Font.BOLD, 14));
                LrecordButton.setBounds(10, 65, 190, 50);

                BookPanel = true;
                RecordPanel = true;
                LibPanel = false;
                LMainFrame.validate();
                LMainFrame.repaint();
            }

        });
        
     
///BOOKS       
        LbooksButton = new JButton("Book");
        LbooksButton.setBackground(Color.decode("#008f67"));
        LbooksButton.setForeground(Color.WHITE);
        LbooksButton.setBounds(15, 15, 190, 50);
        LbooksButton.setHorizontalAlignment(SwingConstants.LEFT);
        LbooksButton.setFont(new Font("Arial", Font.BOLD, 15));
        LbooksButton.setFocusable(false); 
        LbooksButton.setBorder(BorderFactory.createEmptyBorder(0,60,0,0));
    
        LbooksButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(BookPanel == true){
                LbooksButton.setBackground(Color.decode("#29315a"));
                LbooksButton.setForeground(Color.WHITE);
                LbooksButton.setFont(new Font("Arial", Font.BOLD, 15));
                }
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if(BookPanel == true){
                LbooksButton.setBackground(Color.decode("#1b234c"));
                LbooksButton.setForeground(Color.decode("#aeb7c0"));
                LbooksButton.setFont(new Font("Arial", Font.BOLD, 14));
               
            } }
        });

      

        LbooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                
                LbooksButton.setBackground(Color.decode("#29315a"));

                LMainFrame.remove(mlibPanel);
                LMainFrame.remove(temPanel);
                LMainFrame.remove(tbPanel);

                //hide record panel
                rsearchField.setVisible(false);
                rsearchChoice.setVisible(false);
                LMainFrame.remove(Borrow.recordPanel);
                LMainFrame.remove(recordSPanel);

                LMainFrame.add(tbPanel);
              LMainFrame.add(temPanel);           
             
        
                LdelButton.setVisible(true);
                LaddButton.setVisible(true);
                Book.LseaSubmit.setVisible(true);                
                Book.seaField.setVisible(true);
                seaPanel.setVisible(true);
                disBookPanel.setVisible(true);
                disBookRPanel.setVisible(true);
                delLib.setVisible(false);
                LeditButton.setVisible(true);

                LbooksButton.setBounds(15, 15, 190, 50);
                LbooksButton.setBackground(Color.decode("#008f67"));
                LbooksButton.setForeground(Color.WHITE);
                LbooksButton.setFont(new Font("Arial", Font.BOLD, 15));
                

                LrecordButton.setBackground(Color.decode("#1b234c"));
                LrecordButton.setForeground(Color.decode("#aeb7c0"));
                LrecordButton.setFont(new Font("Arial", Font.BOLD, 14));
                LrecordButton.setBounds(10, 65, 190, 50);

                LmlibButton.setBackground(Color.decode("#1b234c"));
                LmlibButton.setForeground(Color.decode("#aeb7c0"));
                LmlibButton.setFont(new Font("Arial", Font.BOLD, 14));
                LmlibButton.setBounds(10, 115, 190, 50);

                Book.seaField.setText("");
                clear.setVisible(false);

                BookPanel = false;
                RecordPanel = true;
                LibPanel = true;
                LMainFrame.validate();
                LMainFrame.repaint();
            }
        });

                clear = new JButton("X");
                clear.setBounds(120,180,120,25);
                clear.setOpaque(false);
                clear.setContentAreaFilled(false);
                clear.setBorderPainted(false);
                clear.setFocusable(false);
                clear.setForeground(Color.WHITE);
                clear.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) { 
                        Book.seaField.setText("");
                        LMainFrame.remove(disBookRPanel);
                        LMainFrame.remove(temPanel);
                        LMainFrame.remove(tbPanel);
                        
                        LMainFrame.remove(disBookPanel);

                        if(Book.dbtable != null) {
                            disBookPanel.remove(Book.dbtable);
                            disBookPanel.remove(Book.disBookscrollPane);
                        }
                        Book.displayBook();     


                        disBookPanel.add(Book.disBookscrollPane);
                        LMainFrame.add(disBookPanel);
                        LMainFrame.add(tbPanel);
                        LMainFrame.add(temPanel);

                        disBookPanel.setVisible(true);
                        LeditButton.setVisible(true);
                        clear.setVisible(false);
                        LdelButton.setVisible(true);
                        LdelButton1.setVisible(false);
                        LeditButton1.setVisible(false);
                        
                        LMainFrame.validate();
                        LMainFrame.repaint();
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

         //Create add button
         LaddButton = new JButton("ADD");
         
         LaddButton.setBackground(Color.decode("#1b234c"));
         LaddButton.setBorder(new LineBorder(Color.decode("#178470"), 2));
         LaddButton.setBounds(785, 50, 95, 30);
         LaddButton.setForeground(Color.decode("#aeb7c0"));
         LaddButton.setFont(new Font("Arial", Font.BOLD, 12));
         LaddButton.setFocusable(false);       

         LaddButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LaddButton.setBackground(Color.decode("#29315a"));
                LaddButton.setForeground(Color.WHITE);
                LaddButton.setBounds(785, 49, 95, 30);
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LaddButton.setBackground(Color.decode("#1b234c"));
                LaddButton.setForeground(Color.decode("#aeb7c0"));
                LaddButton.setBounds(785, 50, 95, 30);
            }
        });

        LeditButton = new JButton("EDIT");
        LeditButton.setBounds(685, 51, 95, 30);
        LeditButton.setContentAreaFilled(false);
        LeditButton.setBorder(new LineBorder(Color.decode("#0000FF"), 2));
        LeditButton.setForeground(Color.decode("#aeb7c0"));
        LeditButton.setFocusable(false);

        LeditButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LeditButton.setBackground(Color.decode("#29315a"));
                LeditButton.setForeground(Color.WHITE);
                LeditButton.setBounds(685, 50, 95, 30);
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LeditButton.setBackground(Color.decode("#1b234c"));
                LeditButton.setForeground(Color.decode("#aeb7c0"));
                LeditButton.setBounds(685, 51, 95, 30);
            }
        });

        LeditButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
               int selectedRow = Book.dbtable.getSelectedRow();
                if (selectedRow != -1) {
                    Check = false;
                    editBook(selectedRow);
                    
                }else{
                    JOptionPane.showMessageDialog(temp, "Select the book you want to delete in the table!", "No Book Selected", JOptionPane.INFORMATION_MESSAGE);
                }
                
                        
            }

        });

        
       
        //Create delete button
        LdelButton = new JButton("DELETE");
        LdelButton.setBounds(585, 50, 95, 30);
        LdelButton.setContentAreaFilled(false);
        LdelButton.setBorder(new LineBorder(Color.RED, 2));
        LdelButton.setForeground(Color.decode("#aeb7c0"));
        LdelButton.setFocusable(false);

        LdelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LdelButton.setBackground(Color.decode("#29315a"));
                LdelButton.setForeground(Color.WHITE);
                LdelButton.setBounds(585, 49, 95, 30);
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LdelButton.setBackground(Color.decode("#1b234c"));
                LdelButton.setForeground(Color.decode("#aeb7c0"));
                LdelButton.setBounds(585, 50, 95, 30);
            }
        });

        LdelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
               int selectedRow = Book.dbtable.getSelectedRow();
                if (selectedRow != -1) {
                    
                    delBook(selectedRow);
                }else{
                    JOptionPane.showMessageDialog(temp, "Select the book you want to delete in the table!", "No Book Selected", JOptionPane.INFORMATION_MESSAGE);
                }
                
                        
            }

        });
///RECORD
        //Create record button
        LrecordButton = new JButton("Record");
        LrecordButton.setBackground(Color.decode("#1b234c"));
        LrecordButton.setForeground(Color.decode("#aeb7c0"));
        LrecordButton.setBounds(10, 65, 190, 50);
        LrecordButton.setHorizontalAlignment(SwingConstants.LEFT);
        LrecordButton.setFont(new Font("Arial", Font.BOLD, 14));
        LrecordButton.setFocusable(false); 
        LrecordButton.setBorder(BorderFactory.createEmptyBorder(0,60,0,0));


        LrecordButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(RecordPanel == true){
                LrecordButton.setBackground(Color.decode("#29315a"));
                LrecordButton.setForeground(Color.WHITE);
                LrecordButton.setFont(new Font("Arial", Font.BOLD, 15));
                }
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if(RecordPanel == true){
                LrecordButton.setBackground(Color.decode("#1b234c"));
                LrecordButton.setForeground(Color.decode("#aeb7c0"));
                LrecordButton.setFont(new Font("Arial", Font.BOLD, 14));
                }
            }
        });

        LrecordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                rsearchField.setVisible(false);
                rsearchChoice.setVisible(false);
                LMainFrame.remove(mlibPanel);
                LMainFrame.remove(Borrow.recordPanel);
                LMainFrame.remove(recordSPanel);
                

                addLib.setVisible(false);
                delLib.setVisible(false);

                mlibPanel.setVisible(false);
                record();
            }
        });
        
       


        //Create logout button
        Llogout = new JButton("Log Out");
        Llogout.setBackground(Color.decode("#1b234c"));
        Llogout.setForeground(Color.decode("#aeb7c0"));
        Llogout.setBounds(15, 450, 190, 50);
        Llogout.setHorizontalAlignment(SwingConstants.LEFT);
        Llogout.setFont(new Font("Arial", Font.BOLD, 14));
        Llogout.setFocusable(false); 
        Llogout.setBorder(BorderFactory.createEmptyBorder(0,60,0,0));

        Llogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Llogout.setBackground(Color.decode("#29315a"));
                Llogout.setForeground(Color.WHITE);
                Llogout.setBounds(18, 450, 190, 50);
                
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Llogout.setBackground(Color.decode("#1b234c"));
                Llogout.setForeground(Color.decode("#aeb7c0"));
                Llogout.setBounds(15, 450, 190, 50);
                
            }
        });
        
        temPanel.add(new JButton()); // Empty space
        LMainPanel.add(LbooksButton);
        //LMainPanel.add(LdelButton);
        //LMainPanel.add(LdispButton);
        //LMainPanel.add(LsearchButton);
        LMainPanel.add(LrecordButton);
        //LMainPanel.add(LMainLabel);
        if(Authentication.LoggedLID == 0){
        LMainPanel.add(LmlibButton);
        }
        LMainPanel.add(Llogout);


        Librarian lib = new Librarian();
    
        LmlibButton.addActionListener(lib);
        LaddButton.addActionListener(lib);
        LdelButton.addActionListener(lib);
        LrecordButton.addActionListener(lib);
        Llogout.addActionListener(lib);

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
        clear.setBounds(210, 0, 50, 30);

        
        //Call Book Table
        Book.displayBook();
        disBookPanel.add(Book.disBookscrollPane);
        LMainFrame.add(disBookPanel,BorderLayout.CENTER);

        Book.dbtable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me1) {
              if (me1.getClickCount() == 1) {
                Book.seaField.transferFocus();
              }
            }
          });
        
        
        tbPanel.add(Book.seaField);
        seaPanel.add(clear);
        clear.setVisible(false);
        
        tbPanel.add(LdelButton);
        tbPanel.add(LdelButton1);
        tbPanel.add(LeditButton);
        tbPanel.add(LeditButton1);
        tbPanel.add(LaddButton); 
        tbPanel.add(Book.LseaSubmit);
        LMainFrame.add(seaPanel);
        LMainFrame.add(tbPanel);
        
        LMainFrame.add(temPanel);

      

        LMainFrame.setVisible(true);       

        Book.LseaSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                if(!Book.seaField.getText().isEmpty()){

                clear.setVisible(true);
                LdelButton.setVisible(false);
                LeditButton.setVisible(false);
               
                Book.searchBook();
                
                LMainFrame.remove(disBookRPanel);
                disBookPanel.setVisible(false);
                LMainFrame.remove(temPanel);
                LMainFrame.remove(tbPanel);
                Book.displaySearchResults();
                disBookRPanel.add(Book.disBookResultscrollPane);

                //Create LeditButton1 for edit book in searchresult
                LeditButton1 = new JButton("EDIT");
                LeditButton1.setBounds(685, 51, 95, 30);
                LeditButton1.setContentAreaFilled(false);
                LeditButton1.setBorder(new LineBorder(Color.decode("#0000FF"), 2));
                LeditButton1.setForeground(Color.decode("#aeb7c0"));
                LeditButton1.setFocusable(false);

                LeditButton1.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        LeditButton1.setBackground(Color.decode("#29315a"));
                        LeditButton1.setForeground(Color.WHITE);
                        LeditButton1.setBounds(685, 50, 95, 30);
                    }
                
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        LeditButton1.setBackground(Color.decode("#1b234c"));
                        LeditButton1.setForeground(Color.decode("#aeb7c0"));
                        LeditButton1.setBounds(685, 51, 95, 30);
                    }
                });

                
                //Create LdelButton1 for delete book in searchresult
                LdelButton1 = new JButton("DELETE");

                LdelButton1.setBounds(585, 50, 95, 30);
                LdelButton1.setContentAreaFilled(false);
                LdelButton1.setBorder(new LineBorder(Color.RED, 2));
                LdelButton1.setForeground(Color.decode("#aeb7c0"));
                LdelButton1.setFocusable(false);

        LdelButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LdelButton1.setBackground(Color.decode("#29315a"));
                LdelButton1.setForeground(Color.WHITE);
                LdelButton1.setBounds(585, 49, 95, 30);
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LdelButton1.setBackground(Color.decode("#1b234c"));
                LdelButton1.setForeground(Color.decode("#aeb7c0"));
                LdelButton1.setBounds(585, 50, 95, 30);
            }
        });

                LMainFrame.remove(LdelButton1);
                tbPanel.add(LdelButton1);
                LMainFrame.remove(LeditButton1);
                tbPanel.add(LeditButton1);
                LMainFrame.add(disBookRPanel);
                LMainFrame.add(tbPanel);
                LMainFrame.add(temPanel);
                LMainFrame.validate();
                LMainFrame.repaint();
                
                LeditButton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) { 
                    int selectedRow = Book.dbrtable.getSelectedRow();
                        if (selectedRow != -1) {
                            Check = true;
                            editBook(selectedRow);
                        }else{
                            JOptionPane.showMessageDialog(temp, "Select the book you want to delete in the table!", "No Book Selected", JOptionPane.INFORMATION_MESSAGE);
                        }
                        
                                
                    }

                });


                LdelButton1.addActionListener(new ActionListener() {
                   
                    public void actionPerformed(ActionEvent e) { 
                       int selectedRow = Book.dbrtable.getSelectedRow();
                        if (selectedRow != -1) {
                            Check = true;
                            delBook(selectedRow);
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
                LMainFrame.remove(disBookRPanel);
                disBookPanel.setVisible(true);
            }
            }

        });
        
    }

    public static void manageLib(){        

        mlibPanel = new JPanel();
        mlibPanel.setLayout(new BorderLayout());
        mlibPanel.setBounds(300,120, 835, 500);        
        mlibPanel.setBackground(Color.decode("#1b234c")); 
        
        addLibPanel = new JPanel(); 
        addLibPanel.setLayout(null);

        //Register Librarian
        addLib = new JButton("Register");
        addLib.setBackground(Color.decode("#1b234c"));
        addLib.setBorder(new LineBorder(Color.decode("#178470"), 2));
        addLib.setBounds(785, 50, 95, 30);
        addLib.setForeground(Color.decode("#aeb7c0"));
        addLib.setFont(new Font("Arial", Font.BOLD, 12));
        addLib.setFocusable(false);       

        addLib.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            addLib.setBackground(Color.decode("#29315a"));
            addLib.setForeground(Color.WHITE);
            addLib.setBounds(785, 49, 95, 30);
        }
    
        public void mouseExited(java.awt.event.MouseEvent evt) {
            addLib.setBackground(Color.decode("#1b234c"));
            addLib.setForeground(Color.decode("#aeb7c0"));
            addLib.setBounds(785, 50, 95, 30);
            }
        });
       
        //Create delete button
        delLib = new JButton("Delete");
        delLib.setBounds(685, 50, 95, 30);
        delLib.setContentAreaFilled(false);
        delLib.setBorder(new LineBorder(Color.RED, 2));
        delLib.setForeground(Color.decode("#aeb7c0"));
        delLib.setFocusable(false);

        delLib.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                delLib.setBackground(Color.decode("#29315a"));
                delLib.setForeground(Color.WHITE);
                delLib.setBounds(685, 49, 95, 30);
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                delLib.setBackground(Color.decode("#1b234c"));
                delLib.setForeground(Color.decode("#aeb7c0"));
                delLib.setBounds(685, 50, 95, 30);
            }
        });

        addLib.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                         
                mlibFrame = new JDialog(LMainFrame,"Register Librarian");
                mlibFrame.setLocation(new Point(750,400));        
                mlibFrame.setSize(new Dimension(400,300));
                mlibFrame.setResizable(false);

                mlibFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                mlibFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        LMainFrame.setEnabled(true);
                    }
                });       
                
                lnLabel = new JLabel("Username");
                lnLabel.setBounds(100, 50, 170, 20);
                
                lpLabel = new JLabel("Password");
                lpLabel.setBounds(100,120,170,20);

                // Create labels and text fields                
                final JTextField lnField = new JTextField();
                lnField.setBounds(100, 80, 193, 28);
                
                final JTextField lpField = new JTextField();
                lpField.setBounds(100,150,193,28);


                JButton addLibSubmit = new JButton("Register");
                addLibSubmit.setBounds(150, 200, 90, 25);
                addLibSubmit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        libArr.clear();
                        loadLib();
                        if(lnField.getText().isEmpty()|| lpField.getText().isEmpty()){
                            JOptionPane.showMessageDialog(temp, "Please fill in all the information!", "Empty Input", JOptionPane.INFORMATION_MESSAGE);
                            
                        }else{
                        String UserName = lnField.getText();
                        String Password = lpField.getText();
                    
                        String UserName2;
                        int f=-1;
                        
                        for(Librarian Lib : libArr){
                            UserName2 = Lib.getLibName();
                            
                            if(UserName.equals(UserName2)){
                                JOptionPane.showMessageDialog(temp, "Please enter another username!", "Username Exist", JOptionPane.INFORMATION_MESSAGE);
                                f++;            
                                break;
                            }
                        }
                        if(f==-1){
                        libArr.add(new Librarian(getLastLibID()+1,UserName,Password));
                        savelib();
                        
                        JOptionPane.showMessageDialog(temp, "Register Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        lnField.setText("");
                        lpField.setText("");
                        mlibFrame.dispose();
                        LMainFrame.remove(temPanel);
                        LMainFrame.remove(tbPanel);
                        LMainFrame.remove(mlibPanel);
                        displayT();
                        tbPanel.add(addLib);
                        tbPanel.add(delLib);

                        mlibPanel.add(scrollPane);
                        LMainFrame.add(mlibPanel);
                        LMainFrame.add(tbPanel);
                        LMainFrame.add(temPanel);
        
                        LMainFrame.validate();
                        LMainFrame.repaint();
                        }          

                    }   
                    }
                });
                
                addLibPanel.add(lnLabel);
                addLibPanel.add(lnField);
                addLibPanel.add(lpLabel);
                addLibPanel.add(lpField);
                addLibPanel.add(addLibSubmit);

                mlibFrame.add(addLibPanel);
                mlibFrame.setVisible(true);
            }
        });

        delLib.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
               int selectedRow = table1.getSelectedRow();
                if (selectedRow != -1) {
                    
                    int did = selectedRow;
                    int f = -1;

                   
                    for (int i = 0; i < libArr.size(); i++) {

                        if (libArr.get(did).getLibID() == 0 && libArr.get(i).getLibID()== 0) { 
                            f=i;
                            JOptionPane.showMessageDialog(temp, "You Cannot Delete Admin Account!", "Access Denied", JOptionPane.INFORMATION_MESSAGE);
                            break;
                            }else if(libArr.get(did).getLibID() == libArr.get(i).getLibID() && libArr.get(i).getLibID() != 0 ){
                                f=i;
                                int option = JOptionPane.showConfirmDialog(delBookFrame,"Are you sure you want to delete " + libArr.get(f).getLibName() +"?", "Delete Librarian", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                                if(option == JOptionPane.YES_OPTION){                                   
                                libArr.remove(i);
                                savelib();
                                
                                JOptionPane.showMessageDialog(temp, "Librarian Deleted Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                LMainFrame.remove(temPanel);
                                LMainFrame.remove(tbPanel);
                                LMainFrame.remove(mlibPanel);
                                displayT();
                            


                                tbPanel.add(addLib);
                                tbPanel.add(delLib);

                                mlibPanel.add(scrollPane);
                                LMainFrame.add(mlibPanel);
                                LMainFrame.add(tbPanel);
                                LMainFrame.add(temPanel);
                
                                LMainFrame.validate();
                                LMainFrame.repaint();
                                break;
                            }else{
                                break;
                            }         
                                        
                        
                    }
                
                }

                        if(f==-1){
                            JOptionPane.showMessageDialog(temp, "Librarian Not Found!", "No Record Found", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                    
                }else{
                    JOptionPane.showMessageDialog(temp, "Select the librarian you want to delete in the table!", "No Book Selected", JOptionPane.INFORMATION_MESSAGE);
                }
                
                        
            }

        });



               tbPanel.remove(rsearchField);
               LMainFrame.remove(rsearchField);
                LMainFrame.remove(mlibPanel);
                LMainFrame.remove(temPanel);
                LMainFrame.remove(tbPanel);
                LMainFrame.remove(Borrow.recordPanel);                

                //hide record panel
                
                rsearchField.setVisible(false);
                Borrow.recordPanel.setVisible(false);
                //recordFrame.add(LrecordBack);
                displayT();
                
                tbPanel.add(addLib);
                tbPanel.add(delLib);

                mlibPanel.add(scrollPane);
                LMainFrame.add(mlibPanel);
                LMainFrame.add(tbPanel);
                LMainFrame.add(temPanel);
        
                LdelButton.setVisible(false);
                LaddButton.setVisible(false);
                Book.LseaSubmit.setVisible(false);                
                Book.seaField.setVisible(false);
                seaPanel.setVisible(false);
                disBookPanel.setVisible(false);
                disBookRPanel.setVisible(false);
                LeditButton.setVisible(false);            

               
                LbooksButton.setBackground(Color.decode("#1b234c"));
                LbooksButton.setForeground(Color.decode("#aeb7c0"));
                LbooksButton.setFont(new Font("Arial", Font.BOLD, 15));
                LbooksButton.setBounds(15, 15, 190, 50);

                LrecordButton.setBackground(Color.decode("#1b234c"));
                LrecordButton.setForeground(Color.decode("#aeb7c0"));
                LrecordButton.setFont(new Font("Arial", Font.BOLD, 14));
                LrecordButton.setBounds(15, 65, 190, 50);

                LdelButton1.setVisible(false);
                 Book.seaField.setText("");
                 clear.setVisible(false);

                BookPanel = false;
                RecordPanel = true;
                LibPanel = true;
                LMainFrame.validate();
                LMainFrame.repaint();
                
                       
                LMainFrame.validate();
                LMainFrame.repaint();



    }

  

    public static void displayT(){


                libArr.clear();
                loadLib();
                
                String[] columnNames = {"","Librarian ID", "Username",""};
                Object[][] data = new Object[libArr.size()][4];
                for (int i = 0; i < libArr.size(); i++) {
                    String UType = null;
                    Librarian b = libArr.get(i);

                    if(b.getLibID()==0){
                        UType = "ADMIN";
                    }

                    data[i][1] = b.getLibID();
                    data[i][2] = b.getLibName();
                    data[i][3] = UType;
                }

                if(table1 != null) {
                    mlibPanel.remove(table1);
                    mlibPanel.remove(scrollPane);
                }

                table1 = new JTable(data, columnNames);

                    
                    TableColumn Column0 = table1.getColumnModel().getColumn(0);
                    Column0.setMaxWidth(35);

                    TableColumn Column1 = table1.getColumnModel().getColumn(1);
                    Column1.setPreferredWidth(250);

                    TableColumn Column2 = table1.getColumnModel().getColumn(2);
                    Column2.setPreferredWidth(250);
                    

                    

                    table1.setBackground(Color.decode("#29315a"));
                    table1.setForeground(Color.WHITE);
                    table1.setShowGrid(false);
                    table1.setRowHeight(50); 
                    table1.setIntercellSpacing(new Dimension(0, 0));
                    table1.setUI(new BasicTableUI());
                    table1.setFocusable(false);

                    table1.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
                        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                            label.setBackground(Color.decode("#08103a"));
                            label.setFont(new Font("Arial", Font.BOLD, 14));
                            
                            return label;
                        }
                    });
                    table1.getTableHeader().setPreferredSize(new Dimension(50,50));
                   
                    table1.getTableHeader().setForeground(Color.WHITE);
                    table1.getTableHeader().setBorder(new LineBorder(Color.decode("#1b234c")));
            
                    DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
                        Font font = new Font("Arial", Font.BOLD, 14);
                    
                        @Override
                        public Component getTableCellRendererComponent(JTable table,
                                Object value, boolean isSelected, boolean hasFocus,
                                int row, int column) {
                            super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                                    row, column);
                            setFont(font);
                            return this;
                        }
                    
                    };
                    table1.getColumnModel().getColumn(3).setCellRenderer(r); 
                    
                   
                    table1.setFillsViewportHeight(true);
                    
                    scrollPane = new JScrollPane(table1);
                   scrollPane.setBorder(new LineBorder(Color.decode("#1b234c")));
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == LaddButton){
            int maxBookID = 0;
                for(Book book : books){
                    if(book.getBookID()>maxBookID){
                        maxBookID =book.getBookID();
                    }
                }
                maxBookID++;
                bid = maxBookID;
            addBook();
            LMainFrame.setEnabled(false);
        //Submit Book information to add book            
        }else if(e.getSource() == LaddSubmit){
                   
            if(bnField.getText().isEmpty()|| auField.getText().isEmpty()){
                JOptionPane.showMessageDialog(addBookFrame, "Please fill in all the information!", "Empty Input", JOptionPane.INFORMATION_MESSAGE);
                
            }else{              
                bn = bnField.getText();
                au = auField.getText();                
                books.add(new Book(bid,bn,au,true));
                book.saveBook();
                LMainFrame.setEnabled(true);
                addBookFrame.dispose();
                JOptionPane.showMessageDialog(addBookFrame, "Book Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                bnField.setText("");
                auField.setText("");

                LMainFrame.remove(temPanel);
                LMainFrame.remove(tbPanel);
                LMainFrame.remove(disBookPanel);

                if(Book.dbtable != null) {
                    disBookPanel.remove(Book.dbtable);
                    disBookPanel.remove(Book.disBookscrollPane);
                }
                Book.displayBook();     


                disBookPanel.add(Book.disBookscrollPane);
                LMainFrame.add(disBookPanel);
                LMainFrame.add(tbPanel);
                LMainFrame.add(temPanel);

                LMainFrame.validate();
                LMainFrame.repaint();
                
            }
        }else if(e.getSource() == LaddBack){
             LMainFrame.setEnabled(true);
            addBookFrame.dispose();
           
        }else if(e.getSource() == Llogout){
            int option = JOptionPane.showConfirmDialog(temp,"Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(option == JOptionPane.YES_OPTION){
                LMainFrame.dispose();
                Authentication.Menu();
            }
        }
    }    
    //Add Book Page
    public void addBook(){
        Librarian lib = new Librarian();        
        addBookFrame = new JDialog(LMainFrame,"Add Book");
        addBookFrame.setSize(new Dimension(400,250));
        addBookFrame.setLayout(null);
        addBookFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addBookFrame.setResizable(false);
        
        addBookFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                LMainFrame.setEnabled(true);
            }
        });       
        
        JPanel addBookPanel = new JPanel();
        addBookPanel.setBounds(50,25, 300, 200);
       
        addBookPanel.setLayout(new GridLayout(5, 2, 10, 10));
        addBookFrame.setLocation(new Point(750,400));

        // Create labels and text fields
        JLabel bidLabel = new JLabel("Book ID:");
        JLabel bidLabel1 = new JLabel(""+(bid));
        bidLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel bnLabel = new JLabel("Book Name:");
        bnField = new JTextField();
        JLabel auLabel = new JLabel("Author:");
        auField = new JTextField();

        // Create buttons
        LaddSubmit = new JButton("Add");
        LaddSubmit.setBounds(210,160,120,25);
        LaddBack = new JButton("Back");
        LaddBack.setBounds(60,160,120,25);

        // Add components to panel
        addBookPanel.add(bidLabel);
        addBookPanel.add(bidLabel1);
        //addBookPanel.add(bidField);
       
        addBookPanel.add(bnLabel);
        addBookPanel.add(bnField);

        addBookPanel.add(auLabel);
        addBookPanel.add(auField);

        addBookPanel.add(new JLabel()); // Empty space
        addBookFrame.add(LaddBack);
        addBookFrame.add(LaddSubmit);
        
       
         // Add panel to frame and show frame
         addBookFrame.add(addBookPanel);
        
        addBookFrame.setVisible(true);
        
        LaddSubmit.addActionListener(lib);
        LaddBack.addActionListener(lib);

    }

    public static void delBook(int delid){
        
        int did = delid;
        int f = -1;

        if(Check == true){
            for (int i = 0; i < books.size(); i++) {

                if (books.get(i).getBookID() == searchResults.get(did).getBookID()) { // get(i) is to get the data from the i value of array & define getBookID() to get the bookID
                    f=i;
                    Check = false;
                    int option = JOptionPane.showConfirmDialog(temp,"Are you sure you want to delete " + books.get(f).getBookName() +"?", "Delete Book", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(option == JOptionPane.YES_OPTION){                                   
                    books.remove(i);
                     book.saveBook();
                    
                  JOptionPane.showMessageDialog(temp, "Book Deleted Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                  LMainFrame.remove(temPanel);
                  LMainFrame.remove(tbPanel);
                  LMainFrame.remove(disBookRPanel);
  
                  if(Book.dbtable != null) {
                      disBookPanel.remove(Book.dbtable);
                      disBookPanel.remove(Book.disBookscrollPane);
                  }
                  Book.displayBook();     
  
  
                  disBookPanel.add(Book.disBookscrollPane);
                  LMainFrame.add(disBookPanel);
                  LMainFrame.add(tbPanel);
                  LMainFrame.add(temPanel);

                  disBookPanel.setVisible(true);
                  Book.seaField.setText("");
                  clear.setVisible(false);
                  
  
                  LMainFrame.validate();
                  LMainFrame.repaint();
  
                    
                    break;
                    }else{
                        break;
                    }         
                                
                
            }
        }
    }else{
        for (int i = 0; i < books.size(); i++) {

            if (books.get(did).getBookID() == books.get(i).getBookID()) { // get(i) is to get the data from the i value of array & define getBookID() to get the bookID
                f=i;
                int option = JOptionPane.showConfirmDialog(delBookFrame,"Are you sure you want to delete " + books.get(f).getBookName() +"?", "Delete Book", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(option == JOptionPane.YES_OPTION){                                   
                books.remove(i);
                book.saveBook();
                
                JOptionPane.showMessageDialog(delBookFrame, "Book Deleted Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                LMainFrame.remove(temPanel);
                LMainFrame.remove(tbPanel);
                LMainFrame.remove(disBookPanel);

                if(Book.dbtable != null) {
                    disBookPanel.remove(Book.dbtable);
                    disBookPanel.remove(Book.disBookscrollPane);
                }
                Book.displayBook();     


                disBookPanel.add(Book.disBookscrollPane);
                LMainFrame.add(disBookPanel);
                LMainFrame.add(tbPanel);
                LMainFrame.add(temPanel);

                LMainFrame.validate();
                LMainFrame.repaint();


                break;
                }else{
                    break;
                }         
                            
            
        }
    }
    }

            if(f==-1){
                JOptionPane.showMessageDialog(delBookFrame, "Book ID Not Found!", "No Record Found", JOptionPane.INFORMATION_MESSAGE);
                return;
            }   
            
            
                           
    }

    public static void editBook(final int bid){
        LMainFrame.setEnabled(false);       
        editBookFrame = new JDialog(LMainFrame,"Edit Book");
        editBookFrame.setSize(new Dimension(400,250));
        editBookFrame.setLayout(null);
        editBookFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editBookFrame.setResizable(false);
        
        editBookFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                LMainFrame.setEnabled(true);
            }
        });       
        
        JPanel editBookPanel = new JPanel();
        editBookPanel.setBounds(50,25, 300, 200);
       
        editBookPanel.setLayout(new GridLayout(5, 2, 10, 10));
        editBookFrame.setLocation(new Point(750,400));

       if(Check == true){

        for (int i = 0; i < books.size(); i++){
            if(searchResults.get(bid).getBookID() == books.get(i).getBookID()){

            // Create labels and text fields
            bidLabel = new JLabel("Book ID:");
            bidLabel1 = new JLabel(""+ books.get(i).getBookID());
            bidLabel1.setHorizontalAlignment(SwingConstants.CENTER);
            bnLabel = new JLabel("Book Name:");
            bnField = new JTextField(books.get(i).getBookName());
            auLabel = new JLabel("Author:");
            auField = new JTextField(books.get(i).getAuthor());
      
            // Create buttons
            LeditSubmit = new JButton("Edit");
            LeditSubmit.setBounds(210,160,120,25);
            LeditBack = new JButton("Back");
            LeditBack.setBounds(60,160,120,25);

            // Add components to panel
            editBookPanel.add(bidLabel);
            editBookPanel.add(bidLabel1);
            //addBookPanel.add(bidField);
        
            editBookPanel.add(bnLabel);
            editBookPanel.add(bnField);

            editBookPanel.add(auLabel);
            editBookPanel.add(auField);

            editBookPanel.add(new JLabel()); // Empty space
            editBookFrame.add(LeditBack);
            editBookFrame.add(LeditSubmit);
            
        
            // Add panel to frame and show frame
            editBookFrame.add(editBookPanel);
            
            editBookFrame.setVisible(true);
            
        
            LeditSubmit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { 
                    if(bnField.getText().isEmpty()|| auField.getText().isEmpty()){
                        JOptionPane.showMessageDialog(editBookFrame, "Please fill in all the information!", "Empty Input", JOptionPane.INFORMATION_MESSAGE);
                        
                    }else{              
                        bn = bnField.getText();
                        au = auField.getText();    
                               
                        searchResults.get(bid).bookName = bn;
                        searchResults.get(bid).author =au;
                        book.saveBook();
                        LMainFrame.setEnabled(true);
                        editBookFrame.dispose();
                        JOptionPane.showMessageDialog(editBookFrame, "Book Edited Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        bnField.setText("");
                        auField.setText("");
        
                        LMainFrame.remove(temPanel);
                        LMainFrame.remove(tbPanel);
                        LMainFrame.remove(disBookRPanel);
        
                        Book.searchBook();
                        Book.displaySearchResults();   
        
        
                        disBookRPanel.add(Book.disBookResultscrollPane);
                        LMainFrame.add(disBookRPanel);
                        LMainFrame.add(tbPanel);
                        LMainFrame.add(temPanel);
        
                        LMainFrame.validate();
                        LMainFrame.repaint();
                    
                    }
                }
            });       
            
        
            LeditBack.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {                             
                    LMainFrame.setEnabled(true);
                    editBookFrame.dispose();
                }
            });  

                break;
            }
        }

       }else{
        for (int i = 0; i < books.size(); i++){
            if(books.get(bid).getBookID() == books.get(i).getBookID()){
 
            // Create labels and text fields
            bidLabel = new JLabel("Book ID:");
            bidLabel1 = new JLabel(""+ books.get(i).getBookID());
            bidLabel1.setHorizontalAlignment(SwingConstants.CENTER);
            bnLabel = new JLabel("Book Name:");
            bnField = new JTextField(books.get(i).getBookName());
            auLabel = new JLabel("Author:");
            auField = new JTextField(books.get(i).getAuthor());
      
            // Create buttons
            LeditSubmit = new JButton("Edit");
            LeditSubmit.setBounds(210,160,120,25);
            LeditBack = new JButton("Back");
            LeditBack.setBounds(60,160,120,25);

            // Add components to panel
            editBookPanel.add(bidLabel);
            editBookPanel.add(bidLabel1);
            //addBookPanel.add(bidField);
        
            editBookPanel.add(bnLabel);
            editBookPanel.add(bnField);

            editBookPanel.add(auLabel);
            editBookPanel.add(auField);

            editBookPanel.add(new JLabel()); // Empty space
            editBookFrame.add(LeditBack);
            editBookFrame.add(LeditSubmit);
            
        
            // Add panel to frame and show frame
            editBookFrame.add(editBookPanel);
            
            editBookFrame.setVisible(true);
            
        
            LeditSubmit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { 
                    if(bnField.getText().isEmpty()|| auField.getText().isEmpty()){
                        JOptionPane.showMessageDialog(addBookFrame, "Please fill in all the information!", "Empty Input", JOptionPane.INFORMATION_MESSAGE);
                        
                    }else{              
                        bn = bnField.getText();
                        au = auField.getText();    
                               
                        books.get(bid).bookName = bn;
                        books.get(bid).author =au;
                        book.saveBook();
                        LMainFrame.setEnabled(true);
                        editBookFrame.dispose();
                        JOptionPane.showMessageDialog(addBookFrame, "Book Edited Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        bnField.setText("");
                        auField.setText("");
        
                        LMainFrame.remove(temPanel);
                        LMainFrame.remove(tbPanel);
                        LMainFrame.remove(disBookPanel);
        
                        if(Book.dbtable != null) {
                            disBookPanel.remove(Book.dbtable);
                            disBookPanel.remove(Book.disBookscrollPane);
                        }
                        Book.displayBook();     
        
        
                        disBookPanel.add(Book.disBookscrollPane);
                        LMainFrame.add(disBookPanel);
                        LMainFrame.add(tbPanel);
                        LMainFrame.add(temPanel);
        
                        LMainFrame.validate();
                        LMainFrame.repaint();
                    
                    }
                }
            });       
            
        
            LeditBack.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {                             
                    LMainFrame.setEnabled(true);
                    editBookFrame.dispose();
                }
            });  

                break;
            }
        }
    
    }

    }


    public static void record(){
        

        recordSPanel = new JPanel();
        recordSPanel.setLayout(new BorderLayout());
        recordSPanel.setBounds(300,120, 835, 500);
        
        recordSPanel.setBackground(Color.decode("#1b234c"));  
        
                rsearchField = new JTextField();
                rsearchField.setBorder(BorderFactory.createEmptyBorder(3,5,3,5));
                
                rsearchField.setCaretColor(Color.WHITE);
                rsearchField.setBackground(Color.decode("#08103a"));
                rsearchField.setText("Press Enter to Search");
                rsearchField.setForeground(Color.decode("#313962"));
                rsearchField.setBounds(55, 50, 250, 30);
                rsearchField.setPreferredSize(new Dimension(500, 30));      
                
                String[] Litems = {"All Records", "Unreturned","Returned & Unreturned"};
                rsearchChoice = new JComboBox(Litems);               
                
                rsearchChoice.setBackground(Color.decode("#008f65"));
                rsearchChoice.setForeground(Color.WHITE);
                rsearchChoice.setBounds(320,50, 180, 28);
                rsearchChoice.setFont(new Font("Arial", Font.BOLD, 13));
                rsearchChoice.setFocusable(false);                 
                rsearchChoice.setBorder(BorderFactory.createEmptyBorder(0,5, 0, 0));

            
                rsearchChoice.setRenderer(new DefaultListCellRenderer() {
                    @Override
                    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                        JLabel c = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                        c.setBackground(Color.decode("#008f65"));
                        c.setForeground(Color.WHITE);
                        
                       
                        if (isSelected) {
                            c.setBackground(Color.decode("#008f65"));
                            c.setBorder(BorderFactory.createMatteBorder(0, 15, 0, 0, getBackground()));
                            c.setForeground(Color.WHITE);
                        } else {
                            c.setBackground(Color.WHITE);
                            c.setForeground(Color.BLACK);
                            c.setBorder(BorderFactory.createEmptyBorder(0,15, 0,0));
                        }
                        return c;



                        
                    }
                });

                rsearchChoice.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            if (e.getItem().equals("Returned & Unreturned")) {
                                Borrow.recordPanel.setVisible(false);
                                LMainFrame.remove(recordSPanel);
                                LMainFrame.remove(temPanel);
                                LMainFrame.remove(tbPanel);
                                record.SearchRecord(searchResults1, 0);
                                rsearchChoice.setSelectedItem("Returned & Unreturned");
                                recordSPanel.add(Borrow.disResultscrollPane);
                                LMainFrame.add(recordSPanel);
                                LMainFrame.add(tbPanel);
                                LMainFrame.add(temPanel);
                                LMainFrame.validate();
                                LMainFrame.repaint();

                                Borrow.dsrtable.addMouseListener(new MouseAdapter() {
                                    public void mousePressed(MouseEvent me) {
                                    if (me.getClickCount() == 1) {
                                        if (rsearchField.getText().isEmpty()) {
                                        rsearchField.setText("Press Enter to Search");
                                        rsearchField.transferFocus();
                                        rsearchField.setForeground(Color.decode("#313962"));
                                        
                                        }
                                    }
                                    }
                                });
                            }else if(e.getItem().equals("Unreturned")){
                                Borrow.recordPanel.setVisible(false);
                                LMainFrame.remove(recordSPanel);
                                LMainFrame.remove(temPanel);
                                LMainFrame.remove(tbPanel);
                                record.SearchRecord(searchResults1, 1);
                                rsearchChoice.setSelectedItem("Unreturned");
                                recordSPanel.add(Borrow.disResultscrollPane);
                                LMainFrame.add(recordSPanel);
                                LMainFrame.add(tbPanel);
                                LMainFrame.add(temPanel);
                                LMainFrame.validate();
                                LMainFrame.repaint();

                                Borrow.dsrtable.addMouseListener(new MouseAdapter() {
                                    public void mousePressed(MouseEvent me) {
                                    if (me.getClickCount() == 1) {
                                        if (rsearchField.getText().isEmpty()) {
                                        rsearchField.setText("Press Enter to Search");
                                        rsearchField.transferFocus();
                                        rsearchField.setForeground(Color.decode("#313962"));
                                        
                                        }
                                    }
                                    }
                                });
                            }else if(e.getItem().equals("All Records")){
                                LMainFrame.remove(recordSPanel);
                                Borrow.recordPanel.setVisible(true);
                                rsearchChoice.setSelectedItem("All Records");
                            }
                        }
                    }
                });
                
        
        
                if(searchResults1 == null){
                    Borrow.displayRecord();
                   
                
                }
        
                rsearchField.addKeyListener(new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            // User pressed Enter, perform search
                            searchResults1 = rsearchField.getText();
                            rsearchField.setText("");
                            if(searchResults1.equals("")){
                                LMainFrame.remove(recordSPanel);
                                Borrow.recordPanel.setVisible(true);
                                rsearchChoice.setVisible(false);
                               

                            }else{
                            rsearchChoice.setVisible(true);
                            Borrow.recordPanel.setVisible(false);
                            LMainFrame.remove(recordSPanel);
                            LMainFrame.remove(temPanel);
                            LMainFrame.remove(tbPanel);
                            record.SearchRecord(searchResults1, 1);
                            rsearchChoice.setSelectedItem("Unreturned");
                            recordSPanel.add(Borrow.disResultscrollPane);
                            LMainFrame.add(recordSPanel);
                            LMainFrame.add(tbPanel);
                            LMainFrame.add(temPanel);
                            LMainFrame.validate();
                            LMainFrame.repaint();

                            Borrow.dsrtable.addMouseListener(new MouseAdapter() {
                                public void mousePressed(MouseEvent me) {
                                  if (me.getClickCount() == 1) {
                                    if (rsearchField.getText().isEmpty()) {
                                      rsearchField.setText("Press Enter to Search");
                                      rsearchField.transferFocus();
                                      rsearchField.setForeground(Color.decode("#313962"));
                                      
                                    }
                                  }
                                }
                              });
                            }
                            
                        }
                    }
                });
                LMainFrame.remove(mlibPanel);
                LMainFrame.remove(temPanel);
                LMainFrame.remove(tbPanel);

                //recordFrame.add(LrecordBack);
                tbPanel.add(rsearchField);
                tbPanel.add(rsearchChoice);
                rsearchChoice.setVisible(false);
                Borrow.displayRecord();
                LMainFrame.add(Borrow.recordPanel);
               
                
                LMainFrame.add(tbPanel);
                LMainFrame.add(temPanel);             
              
                LdelButton.setVisible(false);
               
                LaddButton.setVisible(false);
                Book.LseaSubmit.setVisible(false);                
                Book.seaField.setVisible(false);
                seaPanel.setVisible(false);
                disBookPanel.setVisible(false);
                disBookRPanel.setVisible(false);
                LeditButton.setVisible(false);
                LeditButton1.setVisible(false);

                 LdelButton1.setVisible(false);
                 Book.seaField.setText("");
                 clear.setVisible(false);
                 
                 BookPanel = true;                 
                 LbooksButton.setBackground(Color.decode("#1b234c"));
                 LbooksButton.setForeground(Color.decode("#aeb7c0"));
                 LbooksButton.setFont(new Font("Arial", Font.BOLD, 14));
                 LbooksButton.setBounds(10, 15, 190, 50);

                 RecordPanel = false;
                 LrecordButton.setBackground(Color.decode("#008f67"));
                 LrecordButton.setForeground(Color.WHITE);
                 LrecordButton.setFont(new Font("Arial", Font.BOLD, 15));
                 LrecordButton.setBounds(15, 65, 190, 50);

                 LibPanel = true;
                 LmlibButton.setBackground(Color.decode("#1b234c"));
                 LmlibButton.setForeground(Color.decode("#aeb7c0"));
                 LmlibButton.setFont(new Font("Arial", Font.BOLD, 14));
                 LmlibButton.setBounds(10, 115, 190, 50);
                 
                LMainFrame.validate();
                LMainFrame.repaint();

                Borrow.dstable.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent me) {
                      if (me.getClickCount() == 1) {
                        if (rsearchField.getText().isEmpty()) {
                          rsearchField.setText("Press Enter to Search");
                          rsearchField.transferFocus();
                          rsearchField.setForeground(Color.decode("#313962"));
                          
                        }
                      }
                    }
                  });

                  rsearchField.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent me) {
                      if (me.getClickCount() == 1) {
                        if (rsearchField.getText().equals("Press Enter to Search")) {
                            
                            rsearchField.setText("");
                            rsearchField.setForeground(Color.WHITE);
                            

                        }
                      }
                    }
                  });

                  rsearchField.addFocusListener(new FocusListener() {
                    @Override
                   
                    public void focusGained(FocusEvent e) {
                        
                        if (rsearchField.getText().equals("Press Enter to Search")) {
                            rsearchField.setText("");
                            rsearchField.setForeground(Color.WHITE);
                        }
                    }
        
                    @Override
                    public void focusLost(FocusEvent e) {
                        
                        if (rsearchField.getText().isEmpty()) {
                            rsearchField.setText("Press Enter to Search");
                            rsearchField.setForeground(Color.decode("#313962"));
                        }
                    }
                });


    }  

    public static void loadLib(){
        
        try{   
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                int bbid = Integer.parseInt(sc.nextLine());
                String n = sc.nextLine();
                String a = sc.nextLine();
                libArr.add(new Librarian(bbid,n,a));


            }

            sc.close();
        } catch (IOException e){
            System.out.println("Failed to load book: " + e.getMessage());
        }
        
    }

    public static void savelib(){
        try{ 
            PrintWriter add = new PrintWriter(file);

                for(Librarian a : libArr){
                    add.println(a.getLibID());
                    add.println(a.getLibName());
                    add.println(a.getLibPass());
                }
                

                add.close();
        } catch (IOException e){
                System.out.println("Failed to save librarian: " + e.getMessage());
            }
    }

    public int getLibID(){
        return adminID;
    }

    public String getLibName(){
        return adminName;
    }

    public String getLibPass(){
        return adminPass;
    }

    public static int getLastLibID(){
        int Lid = -1;
        for(Librarian a: libArr){
            int currentID = a.getLibID();
            if(currentID > Lid)
                Lid = currentID;
        }
        return Lid;
    }

    public ArrayList<Librarian> getLibs(){
        return libArr;
    }
}
