package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import state.Timers;
import java.awt.Component;
import java.awt.SystemColor;

public class Pomodoro extends JFrame {
	JPanel midPanel;
	JLabel time;
	JButton start , pause;
	int minute , second , elapsedTime;
	String str_minute , str_second;
	boolean isStarted = false;
	Timer timer;
	private JPanel topPanel;
	private JButton pomodoroBtn;
	private JButton shortBreakBtn;
	private JButton longBreakBtn;
	
	public Pomodoro() {
		getContentPane().setBackground(Color.WHITE);
		init();
		setSize(617 , 460);
//		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("Pomodoro");
	}
	
	public void init() {
		topPanel = new JPanel();
		topPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		topPanel.setFont(new Font("Tahoma", Font.BOLD, 18));
		topPanel.setBackground(Color.WHITE);
		topPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 15;
		getContentPane().add(topPanel, BorderLayout.NORTH);
		
		pomodoroBtn = new JButton("Pomodoro");
		pomodoroBtn.setBorderPainted(false);
		pomodoroBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		pomodoroBtn.setForeground(SystemColor.text);
		pomodoroBtn.setFocusPainted(false);
		pomodoroBtn.setBackground(SystemColor.textHighlight);
		topPanel.add(pomodoroBtn, c);
		
		shortBreakBtn = new JButton("Short Break");
		shortBreakBtn.setForeground(Color.WHITE);
		shortBreakBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		shortBreakBtn.setFocusPainted(false);
		shortBreakBtn.setBackground(SystemColor.textHighlight);
		topPanel.add(shortBreakBtn, c);
		
		longBreakBtn = new JButton("Long Break");
		longBreakBtn.setForeground(Color.WHITE);
		longBreakBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		longBreakBtn.setFocusPainted(false);
		longBreakBtn.setBackground(SystemColor.textHighlight);
		topPanel.add(longBreakBtn, c);
		
		midPanel = new JPanel();
		midPanel.setBackground(Color.WHITE);
		midPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		elapsedTime = 0;
		minute = 0;
		second = 0;
		
		Border border = BorderFactory.createEmptyBorder();
		str_minute = String.format("%02d", minute);
		str_second = String.format("%02d", second);

		time = new JLabel(str_minute + ":" + str_second);
		time.setFont(new Font("Verdana", Font.PLAIN , 40));
		time.setHorizontalAlignment(JLabel.CENTER);
		
		midPanel.add(time);
		getContentPane().add(midPanel , BorderLayout.CENTER);	
		
		start = new JButton("Play", new ImageIcon(getClass().getResource("/play.png")));
		start.setFont(new Font("Tahoma", Font.BOLD, 20));
		start.setVerticalTextPosition(SwingConstants.BOTTOM);
		start.setHorizontalTextPosition(SwingConstants.CENTER);
		start.setBackground(SystemColor.info);
		start.setFocusPainted(false);
		start.setBorder(border);
		
		midPanel.add(start);
		
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(isStarted) {
					isStarted = false;
					stop();
				} else {
					isStarted = true;
					start();
				}
			}
		});
		
		Timers timers = new Timers(0);
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				time.setText(timers.printTime());
	    		timers.Start();
			}
		});
	}
	
	public void start() {
		start.setIcon(new ImageIcon(getClass().getResource("/pause.png")));
		Runnable myRunnable =
			    new Runnable() {
			        public void run() {
			        	timer.start();
			        }
		};
		myRunnable.run();
	}
	
	public void stop() {
		start.setIcon(new ImageIcon(getClass().getResource("/play.png")));
		timer.stop();
	}
}
