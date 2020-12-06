package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class Pomodoro extends JFrame{
	JPanel panel;
	JLabel time;
	JButton start , pause;
	int minute , second;
	boolean isStarted = false;
	public Pomodoro() {
		init();
		setSize(400 , 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("Pomodoro");
	}
	
	public void init() {
		panel = new JPanel(new GridLayout(2,1));
		panel.setBackground(Color.red);
		minute = 0;
		second = 0;
		start = new JButton(new ImageIcon(getClass().getResource("/res/play.png")));
		start.setBackground(Color.red);
		start.setFocusPainted(false);
		Border border = BorderFactory.createEmptyBorder();
		start.setBorder(border);
		String str_minute = String.format("%02d", minute);
		String str_second = String.format("%02d" , second);
		time = new JLabel(str_minute + ":" + str_second);
		time.setFont(new Font("Verdana" , Font.PLAIN , 40));
		time.setHorizontalAlignment(JLabel.CENTER);
		panel.add(time); 
		panel.add(start);
		add(panel , BorderLayout.CENTER);	
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
	}
	
	public void start() {
		start.setIcon(new ImageIcon(getClass().getResource("/res/pause.png")));
	}
	
	public void stop() {
		start.setIcon(new ImageIcon(getClass().getResource("/res/play.png")));
	}
}
