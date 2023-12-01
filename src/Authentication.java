import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Authentication implements ActionListener {

    Librarian Lib = new Librarian();
    Member Mem = new Member();
    
    //Variables
    static String UserType;
    static String UserName;
    static String Password;

    //Logged in user
    static int LoggedmID;
    static int LoggedLID;


    
    //Frames
    static JFrame AuthenticationMenuFrame;
    static JFrame LoginFrame;
    static JFrame RegisterFrame;
    static JFrame LibrarianFrame;
    static JFrame MemberFrame;
    static JFrame temp;

    //Label
    static JLabel background;

    //Login
    static JTextField Name1;
    static JPasswordField Passwords1;
    static Choice ch1;

    //Register
    static JTextField Name2;
    static JPasswordField Passwords2;
    static Choice ch2;

    public void actionPerformed(ActionEvent e){
        String button = e.getActionCommand();

        if(button.equals("-Login")){
            AuthenticationMenuFrame.dispose();
            Login();
        }
        else if(button.equals("Back")){
            LoginFrame.dispose();
            Menu();
        }
        else if(button.equals("-Register")){
            AuthenticationMenuFrame.dispose();
            Register();
        }
        else if(button.equals("Back.")){
            RegisterFrame.dispose();
            Menu();
        }
        else if(button.equals("-Exit")){
            AuthenticationMenuFrame.dispose();
        }
        else if(button.equals("Login")){
            UserName = Name1.getText();
            Password = Passwords1.getText();
            UserType = ch1.getSelectedItem().toLowerCase();
            String UserName2 = null;
            String PassWord2 =null;
            boolean check = false;                
    
                    
                        
                        if(UserType.equals("member")){
                            Member.memArr.clear();
                            Mem.loadMem();
                            ArrayList<Member> Mems = Mem.getMems();
                            
                            for(Member Mem : Mems){
                                if(UserName.equals(Mem.getMemName()) && Password.equals(Mem.getMemPass())){
                                    LoggedmID = Mem.getMemID();
                                    UserName2 = Mem.getMemName();
                                    PassWord2 = Mem.getMemPass();
                                }
                            
                            if(UserName.equals(UserName2) && Password.equals(PassWord2)){
                                
                                check = true;
                                JOptionPane.showMessageDialog(temp, "Login Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                LoginFrame.dispose();

                            Member.member();
                            break;
                            }
                          }
                        }
                        else if(UserType.equals("librarian")){
                            Lib.loadLib();
                            ArrayList<Librarian> libs = Lib.getLibs();
                            
                            for(Librarian Lib : libs){
                               if(UserName.equals(Lib.getLibName()) && Password.equals(Lib.getLibPass())){
                                LoggedLID = Lib.getLibID();
                                UserName2 = Lib.getLibName();
                                PassWord2 = Lib.getLibPass();
                               }

                            if(UserName.equals(UserName2) && Password.equals(PassWord2)){ 
                                
                                check = true;
                                JOptionPane.showMessageDialog(temp, "Login Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                LoginFrame.dispose();
                            
                            Librarian.Lmenu();
                            break;
                        }
                    }
                    }
    

                
                if(!check){
                    JOptionPane.showMessageDialog(temp, "No matching record or please register a new account", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }else if(button.equals("Register")){
            Member.memArr.clear();
            Mem.loadMem();
            if(Name2.getText().isEmpty()|| Passwords2.getText().isEmpty()){
                JOptionPane.showMessageDialog(temp, "Please fill in all the information!", "Empty Input", JOptionPane.INFORMATION_MESSAGE);
                
            }else{
            UserName = Name2.getText();
            Password = Passwords2.getText();
           
            String UserName2;
            int f=-1;

            
            ArrayList<Member> Mems = Mem.getMems();
            for(Member Mem : Mems){
                UserName2 = Mem.getMemName();
                
                if(UserName.equals(UserName2)){
                    JOptionPane.showMessageDialog(temp, "Please enter another username!", "Username Exist", JOptionPane.INFORMATION_MESSAGE);
                    f++;            
                    break;
                }
            }
            if(f==-1){
            Mems.add(new Member(Mem.getLastMemID()+1,UserName,Password));
            Mem.saveMem();
            JOptionPane.showMessageDialog(temp, "Register Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            RegisterFrame.dispose();
            Menu(); 
            }          

          }   
            Mem.clearmemArr();
        }
        else if(button.equals("Log Out")){
            int option = JOptionPane.showConfirmDialog(temp,"Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(option == JOptionPane.YES_OPTION){
                if(UserType.equals("librarian")){
                    LibrarianFrame.dispose();
                    Menu();
                }
                else if(UserType.equals("member")){
                    MemberFrame.dispose();
                    Menu();
                }  
            }
        }
    }

    public static void Menu(){

        background = new JLabel(); 
        background.setIcon(new ImageIcon("Library_Background.jpg")); 
        background.setBounds(0, 0, 400, 250);

        JPanel AuthenticationMenuPanel;
        JLabel AuthenticationMenulabel;
        JButton login;
        JButton register;
        JButton exit;
        
        AuthenticationMenuPanel = new JPanel();
        AuthenticationMenuPanel.setLayout(null);

        AuthenticationMenuFrame = new JFrame("Menu Page");
        AuthenticationMenuFrame.setLocation(new Point(750,400));
        AuthenticationMenuFrame.add(AuthenticationMenuPanel);
        AuthenticationMenuFrame.setSize(new Dimension(400, 250));
        AuthenticationMenuFrame.setResizable(false);
        AuthenticationMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AuthenticationMenulabel = new JLabel("Welcome to library management system");
        AuthenticationMenulabel.setBounds(80, 8, 300, 20);
        AuthenticationMenuPanel.add(AuthenticationMenulabel);

        login = new JButton("-Login");
        login.setBounds(150, 50, 90, 25);
        login.setForeground(Color.WHITE);
        login.setBackground(Color.BLACK);

        register = new JButton("-Register");
        register.setBounds(150, 90, 90, 25);
        register.setForeground(Color.WHITE);
        register.setBackground(Color.BLACK);

        exit = new JButton("-Exit");
        exit.setBounds(150, 130, 90, 25);
        exit.setForeground(Color.WHITE);
        exit.setBackground(Color.BLACK);
        
        AuthenticationMenuPanel.add(AuthenticationMenulabel);
        AuthenticationMenuPanel.add(login);
        AuthenticationMenuPanel.add(register);
        AuthenticationMenuPanel.add(exit);
        AuthenticationMenuPanel.add(background);

        Authentication obj = new Authentication();

        login.addActionListener(obj);
        register.addActionListener(obj);
        exit.addActionListener(obj);

        AuthenticationMenuFrame.setVisible(true);
    }

    public static void Login(){

        JPanel LoginPanel;
        JButton login;
        JButton back;
        JLabel Username;
        JLabel Password;
        JLabel Usertype;

        LoginPanel = new JPanel();
        LoginPanel.setLayout(null);

        LoginFrame = new JFrame("Login Page");
        LoginFrame.setLocation(new Point(750, 400));
        LoginFrame.add(LoginPanel);
        LoginFrame.setSize(new Dimension(400, 250));
        LoginFrame.setResizable(false);
        LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Username = new JLabel("Username (Case Sensitive)");
        Username.setBounds(100, 8, 170, 20);
        LoginPanel.add(Username);

        Name1 = new JTextField();
        Name1.setBounds(100, 27, 193, 28);
        LoginPanel.add(Name1);

        Password = new JLabel("Password (Case Sensitive)");
        Password.setBounds(100, 55, 170, 20);
        LoginPanel.add(Password);

        Passwords1 = new JPasswordField();
        Passwords1.setBounds(100, 75, 193, 28);
        LoginPanel.add(Passwords1);

        Usertype = new JLabel("User Type");
        Usertype.setBounds(100, 100, 170, 20);
        LoginPanel.add(Usertype);

        ch1 = new Choice();
        ch1.add("Member");
        ch1.add("Librarian");
        ch1.setBounds(100, 120, 193, 28);
        LoginPanel.add(ch1);

        login = new JButton("Login");
        login.setBounds(100, 150, 90, 25);
        login.setForeground(Color.WHITE);
        login.setBackground(Color.BLACK);
        LoginPanel.add(login);

        back = new JButton("Back");
        back.setBounds(200, 150, 90, 25);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        LoginPanel.add(back);

        Authentication obj = new Authentication();

        back.addActionListener(obj);
        login.addActionListener(obj);

        LoginPanel.add(background);

        LoginFrame.setVisible(true);
    }

    public static void Register(){

        JPanel RegisterPanel;
        JButton register;
        JButton back;
        JLabel Username;
        JLabel Password;
        JLabel Usertype;

        RegisterPanel = new JPanel();
        RegisterPanel.setLayout(null);

        RegisterFrame = new JFrame("Register Page");
        RegisterFrame.setLocation(new Point(750, 400));
        RegisterFrame.add(RegisterPanel);
        RegisterFrame.setSize(new Dimension(400, 250));
        RegisterFrame.setResizable(false);
        RegisterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Username = new JLabel("Username (Case Sensitive)");
        Username.setBounds(100, 8, 170, 20);
        RegisterPanel.add(Username);

        Name2 = new JTextField();
        Name2.setBounds(100, 27, 193, 28);
        RegisterPanel.add(Name2);

        Password = new JLabel("Password (Case Sensitive)");
        Password.setBounds(100, 55, 170, 20);
        RegisterPanel.add(Password);

        Passwords2 = new JPasswordField();
        Passwords2.setBounds(100, 75, 193, 28);
        RegisterPanel.add(Passwords2);

        Usertype = new JLabel("User Type");
        Usertype.setBounds(100, 100, 170, 20);
        RegisterPanel.add(Usertype);

        ch2 = new Choice();
        ch2.add("Member");
        ch2.setBounds(100, 120, 193, 28);
        RegisterPanel.add(ch2);

        register = new JButton("Register");
        register.setBounds(100, 150, 90, 25);
        register.setForeground(Color.WHITE);
        register.setBackground(Color.BLACK);
        RegisterPanel.add(register);

        back = new JButton("Back.");
        back.setBounds(200, 150, 90, 25);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        RegisterPanel.add(back);

        Authentication obj = new Authentication();

        back.addActionListener(obj);
        register.addActionListener(obj);

        RegisterPanel.add(background);

        RegisterFrame.setVisible(true);
    }

    public static void LibrarianMenu(){

        JPanel p;
        JLabel l;
        JButton b;

        p = new JPanel();
        p.setLayout(null);

        LibrarianFrame = new JFrame("Librarian Menu");
        LibrarianFrame.setLocation(new Point(750, 400));
        LibrarianFrame.add(p);
        LibrarianFrame.setSize(new Dimension(400, 250));
        LibrarianFrame.setResizable(false);
        LibrarianFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l = new JLabel("Welcome to Librarian Menu");
        l.setBounds(120, 20, 300, 20);
        p.add(l);

        b = new JButton("Log Out");
        b.setBounds(140, 70, 90, 25);
        b.setForeground(Color.WHITE);
        b.setBackground(Color.BLACK);
        p.add(b);

        p.add(background);

        Authentication obj = new Authentication();

        b.addActionListener(obj);

        LibrarianFrame.setVisible(true);

    }

    public static void MemberMenu(){

        JPanel p;
        JLabel l;
        JButton b;

        p = new JPanel();
        p.setLayout(null);

        MemberFrame = new JFrame("Member Menu");
        MemberFrame.setLocation(new Point(750, 400));
        MemberFrame.add(p);
        MemberFrame.setSize(new Dimension(400, 250));
        MemberFrame.setResizable(false);
        MemberFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l = new JLabel("Welcome to Member Menu");
        l.setBounds(120, 20, 300, 20);
        p.add(l);

        b = new JButton("Log Out");
        b.setBounds(140, 70, 90, 25);
        b.setForeground(Color.WHITE);
        b.setBackground(Color.BLACK);
        p.add(b);

        p.add(background);

        Authentication obj = new Authentication();

        b.addActionListener(obj);

        MemberFrame.setVisible(true);

    }
}
