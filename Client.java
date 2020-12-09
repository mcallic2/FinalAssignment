
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame implements ActionListener {
// Text field for receiving radius
   private JTextField jtfWeight = new JTextField();
   private JTextField jtfHeight = new JTextField();
  // Text area to display contents
   private JTextArea jta = new JTextArea();
 
   // IO streams
   private DataOutputStream outputToServer;
   private DataInputStream inputFromServer;
 
   public static void main(String[] args) {
     new Client();
   }
    @SuppressWarnings("resource")
public Client() {     // Panel p to hold the label and text field
 JPanel pParent = new JPanel();
 JPanel pWeight = new JPanel();
 JPanel pHeight = new JPanel();
 
     pWeight.setLayout(new BorderLayout());
     pWeight.add(new JLabel("Enter weight (in kg):"), BorderLayout.WEST);
     pWeight.add(jtfWeight, BorderLayout.CENTER);
     
     pHeight.setLayout(new BorderLayout());
     pHeight.add(new JLabel("Enter height (in meters):"), BorderLayout.WEST);
     pHeight.add(jtfHeight, BorderLayout.CENTER);
     
     jtfWeight.setHorizontalAlignment(JTextField.RIGHT);
     jtfHeight.setHorizontalAlignment(JTextField.RIGHT);
 
     getContentPane().setLayout(new BorderLayout());
     getContentPane().add(pWeight, BorderLayout.NORTH);
     getContentPane().add(pHeight, BorderLayout.SOUTH);
     getContentPane().add(new JScrollPane(jta), BorderLayout.CENTER);
 
     jtfWeight.addActionListener(this); // Register listener
     jtfHeight.addActionListener(this); // Register listener
 
     setTitle("Client");
     setSize(500, 300);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setVisible(true); // It is necessary to show the frame here!

  try {
   // Create a socket to connect to the server
   Socket socket = new Socket("localhost", 8000);
   //Socket socket = new Socket("192.168.1.4, 8000");
   //Socket socket = new Socket("CAITLYNS-AIR", 8000);
 
       // Create an input stream to receive data from the server
       inputFromServer = new DataInputStream(
         socket.getInputStream());
 
       // Create an output stream to send data to the server
   outputToServer =
     new DataOutputStream(socket.getOutputStream());
 }
 catch (IOException ex) {
   jta.append(ex.toString() + '\n');
   
     }
   }
 
   public void actionPerformed(ActionEvent e) {
     String actionCommand = e.getActionCommand();
 if (e.getSource() instanceof JTextField) {
       try {
         // Get the radius from the text field
         double weight = Double.parseDouble(jtfWeight.getText().trim());
         double height = Double.parseDouble(jtfHeight.getText().trim());
 
         // Send the radius to the server
         outputToServer.writeDouble(height);
         outputToServer.writeDouble(weight);
         outputToServer.flush();
 
        // Get area from the server
         double bmi = inputFromServer.readDouble();
 
         // Display to the text area
 jta.append("Height is " + height + "\n");
 jta.append("Weight is " + weight + "\n");
 jta.append("BMI received from the server is "
   + bmi + '\n');
       }
       catch (IOException ex) {
         System.err.println(ex);
       }
     }
   }
 }

