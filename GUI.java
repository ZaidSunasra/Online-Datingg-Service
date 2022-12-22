import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import java.sql.*;
import java.util.Objects;

class GUI {
    public static void main(String[] args) {

        //Initializing all components of GUI.
        JLabel l1, l2, l3, l4, l5, l6, l7, l8;
        JButton b1, b2;
        JComboBox cb1, cb2, cb3, cb4;
        JRadioButton rb1, rb2;
        JTextField t1;
        JTable t2;

        //Making GUI using SWING
        JFrame f = new JFrame();
        f.setBounds(100, 100, 1250, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setTitle("User Login");
        Container c = f.getContentPane();
        c.setBackground(Color.WHITE);
        c.setLayout(null);

        //Username label
        l1 = new JLabel("User Name");
        c.add(l1);
        l1.setBounds(30, 30, 150, 30);
        t1 = new JTextField();
        c.add(t1);
        t1.setBounds(200, 30, 200, 30);

        //Gender label
        l2 = new JLabel("Gender");
        c.add(l2);
        l2.setBounds(30, 70, 150, 30);
        rb1 = new JRadioButton("Male");
        c.add(rb1);
        rb1.setBounds(200, 70, 100, 30);
        rb1.setBackground(Color.WHITE);
        rb2 = new JRadioButton("Female");
        c.add(rb2);
        rb2.setBounds(300, 70, 100, 30);
        rb2.setBackground(Color.WHITE);
        ButtonGroup gender = new ButtonGroup();
        gender.add(rb1);
        gender.add(rb2);

        //Religion label
        l3 = new JLabel("Religion");
        c.add(l3);
        l3.setBounds(30, 110, 150, 30);
        String[] religion = new String[]{"Muslim", "Hindu", "Sikh", "Christian", "Jain"};
        cb1 = new JComboBox(religion);
        c.add(cb1);
        cb1.setBounds(200, 110, 200, 30);
        cb1.setSelectedItem(null);

        //Sports label
        l4 = new JLabel("Favourite Sport");
        c.add(l4);
        l4.setBounds(30, 150, 150, 30);
        String[] sport = new String[]{"Football", "Cricket", "Volleyball", "Basketball", "Badminton"};
        cb2 = new JComboBox(sport);
        c.add(cb2);
        cb2.setBounds(200, 150, 200, 30);
        cb2.setSelectedItem(null);

        //Music label
        l5 = new JLabel("Music Preference");
        c.add(l5);
        l5.setBounds(30, 190, 150, 30);
        String[] music = new String[]{"Classical Music", "Rock Music", "Lofi", "Rap", "Country Music"};
        cb3 = new JComboBox(music);
        c.add(cb3);
        cb3.setBounds(200, 190, 200, 30);
        cb3.setSelectedItem(null);

        //Hobbies label
        l6 = new JLabel("Hobbies");
        c.add(l6);
        l6.setBounds(30, 230, 150, 30);
        String[] hobbies = new String[]{"Art", "Cooking", "Playing Games", "Watching Movies", "Listening Songs", "Dancing & Singing"};
        cb4 = new JComboBox(hobbies);
        c.add(cb4);
        cb4.setBounds(200, 230, 200, 30);
        cb4.setSelectedItem(null);

        //Save button
        b1 = new JButton("Save");
        c.add(b1);
        b1.setBounds(200, 280, 100, 30);

        //Displaying info label
        l7 = new JLabel();
        c.add(l7);
        l7.setBounds(30, 320, 500, 30);

        //Table for showing output
        String[][] matchdata =new  String[0][0];
        String[] coloumname={"Name","Gender","Religion","Music","Game","Hobby","Match %"};
        DefaultTableModel model=new DefaultTableModel(matchdata,coloumname);
        t2= new JTable(model);
        JScrollPane panetable=new JScrollPane(t2);
        panetable.setBounds(500,100,700,400);
        f.add(panetable);

        //Show output   button
        b2 = new JButton("Find A Match");
        c.add(b2);
        b2.setBounds(200, 360, 150, 30);

        //Heading above table
        l8 = new JLabel();
        c.add(l8);
        l8.setBounds(500,30,700,30);

        //Action Listener of Save button
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String s1 = t1.getText();
                String s3 = (String) cb1.getSelectedItem();
                String s4 = (String) cb2.getSelectedItem();
                String s5 = (String) cb3.getSelectedItem();
                String s6 = (String) cb4.getSelectedItem();
                String s2 = null;
                if (rb2.isSelected()) {
                    s2 = "Female";
                }
                if (rb1.isSelected()) {
                    s2 = "Male";
                }
                l7.setText("Your Information: " + s1 + "," + s2 + "," + s3 + "," + s4 + "," + s5 + ","+ s6);
            }
        });

        //Action listener to show output
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String url="jdbc:mysql://localhost:3306/userdata";
                String password="12345678";
                String username="root";
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection=DriverManager.getConnection(url,username,password);
                    Statement statement=connection.createStatement();
                    ResultSet resultset=statement.executeQuery("SELECT * FROM userdata.`final data`");
                    int noofmatch=0;

                    while (resultset.next()){
                        String s3 = (String) cb1.getSelectedItem();
                        String s4 = (String) cb2.getSelectedItem();
                        String s5 = (String) cb3.getSelectedItem();
                        String s6 = (String) cb4.getSelectedItem();
                        String s2 = null;
                        if (rb2.isSelected()) {
                            s2 = "Female";
                        }
                        if (rb1.isSelected()) {
                            s2 = "Male";
                        }
                        String name=resultset.getString(2);
                        String gender=resultset.getString(3);
                        String sports=resultset.getString(4);
                        String music=resultset.getString(5);
                        String religion=resultset.getString(6);
                        String hobbies=resultset.getString(7);

                        int count=0;

                        if(!Objects.equals(s2, gender)){
                            if(Objects.equals(s3, religion)){
                                count=count+10;
                            }
                            if(Objects.equals(s4, sports)){
                                count=count+10;
                            }
                            if(Objects.equals(s5, music)){
                                count=count+10;
                            }
                            if(Objects.equals(s6, hobbies)){
                                count=count+10;
                            }
                        }
                        if(count>=30){
                            noofmatch=noofmatch+1;
                            int matchpercent= (int) (count*2.5);
                            String[] matchdata={name,gender,religion,music,sports,hobbies, String.valueOf(matchpercent)};
                            DefaultTableModel model=(DefaultTableModel)t2.getModel();
                            model.addRow(matchdata);
                            l8.setText("COMPATIBLE PARTNERS");
                        }
                    }
                    if(noofmatch==0){
                        l8.setText("No Match found");
                    }
                } catch (ClassNotFoundException | SQLException ex ) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}

