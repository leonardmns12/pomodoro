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
import state.Timers;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemColor;

public class Pomodoro extends JFrame {
	private JPanel midPanel, topPanel;
	private JLabel time, shortBreak, longBreak;
	private JButton start, pause, skip, pomodoroBtn, shortBreakBtn, longBreakBtn;
	private JTextField pomodoroTimer, shortBreakTime, longBreakTime;
	int minute = 0, second = 0, elapsedTime = 0;
	String str_minute = String.format("%02d", minute);
	String str_second = String.format("%02d", second);
	boolean isStarted = false;
	Timer timer;
	private JPanel bottomPanel;
	
	public Pomodoro() {
		//RUN WINDOW
		init();
		
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 450);
		setVisible(true);
		setTitle("Pomodoro");
	}
	
	public void init() {
		//TOP PANEL
		topPanel = new JPanel();
		topPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		topPanel.setFont(new Font("Tahoma", Font.BOLD, 18));
		topPanel.setBackground(Color.WHITE);
		topPanel.setLayout(new GridBagLayout());
		GridBagConstraints cTop = new GridBagConstraints();
		cTop.fill = GridBagConstraints.HORIZONTAL;
		cTop.insets = new Insets(25, 2, 25, 2);
		cTop.ipadx = 20;
		cTop.ipady = 20;
		getContentPane().add(topPanel, BorderLayout.NORTH);

		
		shortBreak = new JLabel();
        shortBreak.setText("Short Break Time: ");
        topPanel.add(shortBreak, cTop);
		
		shortBreakTime = new JTextField();
		shortBreakTime.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        shortBreakTime.setText("0:05:00");
        shortBreakTime.setAlignmentY(0.6F);
        topPanel.add(shortBreakTime,cTop);
        
        
        longBreak = new JLabel();
        longBreak.setText("Long Break Time: ");
        topPanel.add(longBreak, cTop);
        
        longBreakTime = new JTextField();
        longBreakTime.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        longBreakTime.setText("0:30:00");
        longBreakTime.setAlignmentY(0.6F);
        topPanel.add(longBreakTime, cTop);
        
	
		//MID PANEL
		midPanel = new JPanel();
		midPanel.setBackground(Color.WHITE);
		midPanel.setLayout(new GridLayout(0, 1, 0, 0));
		getContentPane().add(midPanel , BorderLayout.CENTER);
		
		Border border = BorderFactory.createEmptyBorder();

		time = new JLabel(str_minute + ":" + str_second);
		time.setFont(new Font("Verdana", Font.PLAIN , 40));
		time.setHorizontalAlignment(JLabel.CENTER);
		midPanel.add(time);	
		
		//BOTTOM PANEL
		bottomPanel = new JPanel();
		bottomPanel.setBackground(SystemColor.info);
		bottomPanel.setLayout(new GridBagLayout());
		bottomPanel.setPreferredSize(new Dimension(0, 120));
		GridBagConstraints cBottom = new GridBagConstraints();
		cBottom.fill = GridBagConstraints.HORIZONTAL;
		cBottom.ipadx = 50;
		cBottom.ipady = 30;
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		//START BUTTON
		start = new JButton("Play", new ImageIcon(getClass().getResource("/play.png")));
		start.setFont(new Font("Tahoma", Font.BOLD, 20));
		start.setVerticalTextPosition(SwingConstants.BOTTOM);
		start.setHorizontalTextPosition(SwingConstants.CENTER);
		start.setBackground(SystemColor.info);
		start.setFocusPainted(false);
		start.setBorder(border);
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				start();
				start.setForeground(Color.lightGray);
				pause.setForeground(Color.BLACK);
			}
		});
		bottomPanel.add(start, cBottom);
		
		//PAUSE BUTTON
		pause = new JButton("Pause", new ImageIcon(getClass().getResource("/pause.png")));
		pause.setFont(new Font("Tahoma", Font.BOLD, 20));
		pause.setVerticalTextPosition(SwingConstants.BOTTOM);
		pause.setHorizontalTextPosition(SwingConstants.CENTER);
		pause.setBackground(SystemColor.info);
		pause.setFocusPainted(false);
		pause.setBorder(border);
		pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				stop();
				pause.setForeground(Color.lightGray);
				start.setForeground(Color.BLACK);
			}
		});
		bottomPanel.add(pause, cBottom);
		
		//SKIP BUTTON
		skip = new JButton("Skip", new ImageIcon(getClass().getResource("/skip.png")));
		skip.setFont(new Font("Tahoma", Font.BOLD, 20));
		skip.setVerticalTextPosition(SwingConstants.BOTTOM);
		skip.setHorizontalTextPosition(SwingConstants.CENTER);
		skip.setBackground(SystemColor.info);
		skip.setFocusPainted(false);
		skip.setBorder(border);
		bottomPanel.add(skip, cBottom);
		
		Timers timers = new Timers(0);
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				time.setText(timers.printTime());
	    		timers.Start();
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
		
//		Timers timers = new Timers(0);
//		timer = new Timer(1000, new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				time.setText(timers.printTime());
//	    		timers.Start();
//			}
//		});
		
	}
	
	public void stop() {
		timer.stop();
	}
}