package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import builder.JButtonBuilder;
import state.Timers;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemColor;

public class Pomodoro extends JFrame {
	private JLabel time;
	private JButton start, pause, skip;
	int minute = 0, second = 0;
	String str_minute = String.format("%02d", minute);
	String str_second = String.format("%02d", second);
	boolean isStarted = false;
	Timer timer;
	private JPanel panel, midPanel, botPanel;
	private JLabel phase1, phase2, phase3, phase4;
	
	public Pomodoro() {
		//RUN WINDOW
		init();
		setSize(400 , 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setIconImage(new ImageIcon(getClass().getResource("/res/tomato.png")).getImage());
		setTitle("Pomodoro");
	}
	
	public void init() {
		//BORDER
		Border border = BorderFactory.createEmptyBorder();
		
		//JBUTTON BUILDER
		JButtonBuilder builder = new JButtonBuilder();

		start = builder.setIcon("/res/play.png").setBackground(Color.red).setBorder(border).setFocus(false).setVisible(true).build();
		skip = builder.setIcon("/res/skip.png").setBackground(Color.red).setFont("Tahoma", 10).setFocus(false).setVisible(true).build();
		
		//JLABEL BUILDER
		
		//TIMERS
		Timers timers = new Timers(1500000);
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				time.setText(timers.printTime());
	    		timers.Start();
	    		setDot(timers.printState());
	    		System.out.println(timers.printState());
	    		if(timers.printState() % 2 == 0 && timers.printState() < 8) {
	    			panel.setBackground(Color.blue);
	    			start.setBackground(Color.blue);
	    			skip.setBackground(Color.blue);
	    			midPanel.setBackground(Color.blue);
	    			botPanel.setBackground(Color.blue);
	    		} else if(timers.printState() % 2 != 0 && timers.printState() < 8) {
	    			panel.setBackground(Color.red);
	    			start.setBackground(Color.red);
	    			skip.setBackground(Color.red);
	    			midPanel.setBackground(Color.red);
	    			botPanel.setBackground(Color.red);
	    		} else if(timers.printState() == 8){
	    			panel.setBackground(Color.yellow);
	    			start.setBackground(Color.yellow);
	    			skip.setBackground(Color.yellow);
	    			midPanel.setBackground(Color.yellow);
	    			botPanel.setBackground(Color.yellow);
	    		} else {
	    			panel.setBackground(Color.green);
	    			start.setBackground(Color.green);
	    			skip.setBackground(Color.green);
	    			midPanel.setBackground(Color.green);
	    			botPanel.setBackground(Color.green);
	    			timers.resetTime();
	    			stop();
	    			time.setText("00:00");
	    			emptyDot();
	    			start.setIcon(new ImageIcon(getClass().getResource("/res/play.png")));
	    			isStarted = false;
	    			skip.setVisible(false);
	    			System.out.println("pomodoro finish!");
	    		}
			}

			private void setDot(int printState) {
				if(printState == 1) {
					phase1.setIcon(new ImageIcon(getClass().getResource("/res/twotone_dot.png")));
				}
				if(printState == 2) {
					phase1.setIcon(new ImageIcon(getClass().getResource("/res/filled_dot.png")));
				}
				if(printState == 3) {
					phase2.setIcon(new ImageIcon(getClass().getResource("/res/twotone_dot.png")));
				}
				if(printState == 4) {
					phase2.setIcon(new ImageIcon(getClass().getResource("/res/filled_dot.png")));
				}
				if(printState == 5) {
					phase3.setIcon(new ImageIcon(getClass().getResource("/res/twotone_dot.png")));
				}
				if(printState == 6) {
					phase3.setIcon(new ImageIcon(getClass().getResource("/res/filled_dot.png")));
				}
				if(printState == 7) {
					phase4.setIcon(new ImageIcon(getClass().getResource("/res/twotone_dot.png")));
				}
				if(printState == 8) {
					phase4.setIcon(new ImageIcon(getClass().getResource("/res/filled_dot.png")));
				}
			}
		});
		
		//TOP PANEL
		panel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(panel , BoxLayout.Y_AXIS);
		panel.setLayout(boxLayout);
		panel.setBackground(Color.red);
		minute = 0;
		second = 0;
		
		//MID PANNEL
		midPanel = new JPanel();
		midPanel.setBackground(Color.red);
		
		//BOT PANEL
		botPanel = new JPanel();
		botPanel.setBackground(Color.red);
		
		//
		phase1 = new JLabel(new ImageIcon(getClass().getResource("/res/outline_dot.png")));
		phase2 = new JLabel(new ImageIcon(getClass().getResource("/res/outline_dot.png")));
		phase3 = new JLabel(new ImageIcon(getClass().getResource("/res/outline_dot.png")));
		phase4 = new JLabel(new ImageIcon(getClass().getResource("/res/outline_dot.png")));
		botPanel.add(phase1);
		botPanel.add(phase2);
		botPanel.add(phase3);
		botPanel.add(phase4);
		
		String str_minute = String.format("%02d", minute);
		String str_second = String.format("%02d" , second);
		time = new JLabel(str_minute + ":" + str_second);
		time.setFont(new Font("Verdana" , Font.PLAIN , 40));
		time.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		midPanel.add(start);
		midPanel.add(skip);
		panel.add(time); 
		panel.add(midPanel);
		panel.add(botPanel);
		add(panel , BorderLayout.CENTER);	
		skip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				timers.skipState();
			}
		});
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isStarted) {
					start();
					start.setIcon(new ImageIcon(getClass().getResource("/res/pause.png")));
					isStarted = true;
					skip.setVisible(true);
				}else {
					stop();
					start.setIcon(new ImageIcon(getClass().getResource("/res/play.png")));
					isStarted = false;
					skip.setVisible(false);
				}
				
			}
		});
	}
	
	public void start() {
		Runnable myRunnable =
		    new Runnable() {
		        public void run() {
		        	timer.start();
		        }
		};
		myRunnable.run();
	}
	
	public void stop() {
		timer.stop();
	}
	
	public void emptyDot() {
		phase1.setIcon(new ImageIcon(getClass().getResource("/res/outline_dot.png")));
		phase2.setIcon(new ImageIcon(getClass().getResource("/res/outline_dot.png")));
		phase3.setIcon(new ImageIcon(getClass().getResource("/res/outline_dot.png")));
		phase4.setIcon(new ImageIcon(getClass().getResource("/res/outline_dot.png")));
	}
}