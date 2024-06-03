import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;

//Clock class is defined. It extends the JFrame class
public class Clock extends JFrame {
    
	private static final long serialVersionUID = 1L;
	
// Instance variables to manage time-related information and GUI components
	Calendar calendar;
    SimpleDateFormat timeFormat;
    JLabel timeLabel;
    String time;
    String day;
 
//The constructor Clock() initializes the JFrame window and sets its properties    
    Clock(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("My Clock Program");
        this.setLayout(new FlowLayout());
        this.setSize(350,200);
        this.setResizable(false);
 
//Constructor Clock() initializes the timeFormat with a specific time format, 
//It creates a JLabel (timeLabel) for displaying the time.
//sets its properties such as font, color, and background.
        timeFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Verdana", Font.PLAIN,25));
        timeLabel.setForeground(new Color(0x00FF00));
        timeLabel.setBackground(Color.black);
        timeLabel.setOpaque(true);
 
//Objects of the Clock class created to display a clock with a time label
        this.add(timeLabel);
        this.setVisible(true);
        
// Create and start threads; updateTimeThread and displayTimeThread
        Thread updateTimeThread = new Thread(this::setTime); //handles updating the time variable every 500 milliseconds.
        Thread displayTimeThread = new Thread(this::updateTimeLabel); //updating the timeLabel text by displaying the current time.
        
// Create setPriority() method to set thread priorities
        updateTimeThread.setPriority(Thread.MIN_PRIORITY); // Background update thread
        displayTimeThread.setPriority(Thread.MAX_PRIORITY); // Display thread
        
// The start() method is called on both threads to begin their execution.       
        updateTimeThread.start();
        displayTimeThread.start();
    }
    
// setTime() method continuously updates time variable by formatting the current time using the timeFormat
// It uses a while(true) loop to repeatedly update the time.
    public void setTime() { 
        while(true ) {
            time = timeFormat.format(Calendar.getInstance().getTime());
            try {
                Thread.sleep(500); //Thread.sleep() is called to pause execution for 500 milliseconds (0.5 seconds).
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
  
// updateTimeLabel() method continuously updates the timeLabel text with the current value of time
// It runs in an infinite loop, setting the text of timeLabel to the current time value.  
    public void updateTimeLabel() { 
        while(true) {  
            timeLabel.setText(time);
        }
    }

    public static void main(String[] args) {
        new Clock();
    }
}