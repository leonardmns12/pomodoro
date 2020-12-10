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
import template.FinishTemplate;
import template.LongBreakTemplate;
import template.PomodoroTemplate;
import template.ShortBreakTemplate;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemColor;

public class Pomodoro extends JFrame {
<<<<<<< Updated upstream
	private JPanel midPanel, topPanel;
	private JLabel time;
	private JButton start, pause, skip, pomodoroBtn, shortBreakBtn, longBreakBtn;
	int minute = 0, second = 0;
	String str_minute = String.format("%02d", minute);
	String str_second = String.format("%02d", second);
	boolean isStarted = false;
	Timer timer;
	private JPanel bottomPanel;
	private JLabel lblNewLabel;
=======
	public JPanel topPanel, midPanel, botPanel, statsPanel;
	private JLabel time, phase1, phase2, phase3, phase4, stats;
	public JButton start, skip;
	private int minute = 0, second = 0;
	private String str_minute = String.format("%02d", minute);
	private String str_second = String.format("%02d", second);
	private boolean isStarted = false;
	private Timer timer;
	private Record records;
>>>>>>> Stashed changes
	
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
<<<<<<< Updated upstream
=======
		//----- UI -----//
		
		//BORDER
		Border emptyBorder = BorderFactory.createEmptyBorder();
		
		//JBUTTON BUILDER
		start = new JButtonBuilder().icon("/res/play.png").background(Color.decode("#f55442")).border(emptyBorder).focus(false).visible(true).build();
		skip =  new JButtonBuilder().icon("/res/skip.png").background(Color.decode("#f55442")).border(emptyBorder).focus(false).visible(false).build();
		
		//JLABEL BUILDER
		time = new JLabelBuilder().text(str_minute + ":" + str_second).font("Verdana", 65, "PLAIN").build();
		time.setAlignmentX(Component.CENTER_ALIGNMENT);
		phase1 = new JLabelBuilder().icon("/res/outline_dot.png").border(new EmptyBorder(0, 3, 0, 3)).build();
		phase2 = new JLabelBuilder().icon("/res/outline_dot.png").border(new EmptyBorder(0, 3, 0, 3)).build();
		phase3 = new JLabelBuilder().icon("/res/outline_dot.png").border(new EmptyBorder(0, 3, 0, 3)).build();
		phase4 = new JLabelBuilder().icon("/res/outline_dot.png").border(new EmptyBorder(0, 3, 0, 3)).build();
		stats = new JLabelBuilder().text("view stats").build();
		
		//-- TOP PANEL --//
		topPanel = new JPanel();
		topPanel.setBackground(Color.decode("#f55442"));
		BoxLayout boxLayout = new BoxLayout(topPanel, BoxLayout.Y_AXIS);
		topPanel.setLayout(boxLayout);
		
		//TOP PANEL SPACER
		Component topLayoutSpacer = Box.createVerticalStrut(20);
		topPanel.add(topLayoutSpacer);
		topPanel.add(time);
		
		//-- MID PANEL --//
		midPanel = new JPanel();
		midPanel.setBackground(Color.decode("#f55442"));
		midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.X_AXIS));
		midPanel.add(start);
		midPanel.add(skip);
		topPanel.add(midPanel);
		
		//-- BOT PANEL --//
		botPanel = new JPanel();
		botPanel.setBackground(Color.decode("#f55442"));
		botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.LINE_AXIS));
		botPanel.add(phase1);
		botPanel.add(phase2);
		botPanel.add(phase3);
		botPanel.add(phase4);

		//BOT PANEL SPACER
		Component botLayoutSpacer = Box.createVerticalStrut(20);
		topPanel.add(botLayoutSpacer);
		topPanel.add(botPanel);
		
		//-- STATS PANEL --//
		statsPanel = new JPanel();
		statsPanel.setBackground(Color.decode("#f55442"));
		statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.X_AXIS));
		statsPanel.add(Box.createHorizontalGlue());
		statsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
		statsPanel.add(stats);
		statsPanel.add(Box.createVerticalGlue());
		topPanel.add(statsPanel);
	
		getContentPane().add(topPanel, BorderLayout.CENTER);	
		
		//----- TIMERS -----//
		LongBreakTemplate longBreakTemplate = new LongBreakTemplate(this);
		ShortBreakTemplate shortBreakTemplate = new ShortBreakTemplate(this);
		PomodoroTemplate pomodoroTemplate = new PomodoroTemplate(this);
		FinishTemplate finishTemplate = new FinishTemplate(this);
		
>>>>>>> Stashed changes
		Timers timers = new Timers(1500000);
		timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				time.setText(timers.printTime());
	    		timers.Start();
<<<<<<< Updated upstream
	    		System.out.println(timers.printState());
	    		if(timers.printState() == 7) {
=======
	    		setDot(timers.printState());
	    		if(timers.printState() % 2 == 0 && timers.printState() < 8) {
	    			shortBreakTemplate.setView(Color.decode("#1e8270"));
	    		} else if(timers.printState() % 2 != 0 && timers.printState() < 8) {
	    			pomodoroTemplate.setView(Color.decode("#f55442"));
	    			skip.setVisible(false);
	    		} else if(timers.printState() == 8) {
	    			longBreakTemplate.setView(Color.decode("#fff75c"));
	    		} else {
	    			finishTemplate.setView(Color.green);
>>>>>>> Stashed changes
	    			timers.resetTime();
	    			stop();
	    			time.setText("00:00");
	    			System.out.println("pomodoro finish!");
	    		}
			}
		});
		
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
		
	
		//MID PANEL
		midPanel = new JPanel();
		midPanel.setBackground(Color.WHITE);
		midPanel.setLayout(new GridLayout(0, 1, 0, 0));
		getContentPane().add(midPanel , BorderLayout.CENTER);
		
		Border border = BorderFactory.createEmptyBorder();
		
		lblNewLabel = new JLabel("Pomodoro Timer");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		midPanel.add(lblNewLabel);

		lblNewLabel = new JLabel("Pomodoro Timer");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		midPanel.add(lblNewLabel);
		
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
				start();
				start.setForeground(Color.lightGray);
				pause.setForeground(Color.BLACK);
			}
		});
<<<<<<< Updated upstream
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
=======
		
		//VIEW STATS ACTION
		stats.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				newFrame();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
>>>>>>> Stashed changes
				// TODO Auto-generated method stub
				stop();
				pause.setForeground(Color.lightGray);
				start.setForeground(Color.BLACK);
			}
<<<<<<< Updated upstream
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
		skip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
					timers.skipState();
=======

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
	
	public void emptyDot() {
		phase1.setIcon(new ImageIcon(getClass().getResource("/res/outline_dot.png")));
		phase2.setIcon(new ImageIcon(getClass().getResource("/res/outline_dot.png")));
		phase3.setIcon(new ImageIcon(getClass().getResource("/res/outline_dot.png")));
		phase4.setIcon(new ImageIcon(getClass().getResource("/res/outline_dot.png")));
	}
	
	public void newFrame() {
		JFrame logFrame = new JFrame();
		logFrame.setVisible(true);
		logFrame.setSize(500 , 100);
		logFrame.setLocationRelativeTo(null);
		logFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JLabel mon,tue,wed,thu,fri,sat,sun;
		JLabel monCount , tueCount , wedCount , thuCount , friCount, satCount ,sunCount;
		JPanel monPanel, tuePanel , wedPanel, thuPanel, friPanel , satPanel , sunPanel;
		monPanel = new JPanel();
		tuePanel = new JPanel();
		wedPanel = new JPanel();
		thuPanel = new JPanel();
		friPanel = new JPanel();
		satPanel = new JPanel();
		sunPanel = new JPanel();
		BoxLayout monLayout = new BoxLayout(monPanel , BoxLayout.Y_AXIS);
		BoxLayout tueLayout = new BoxLayout(tuePanel , BoxLayout.Y_AXIS);
		BoxLayout wedLayout = new BoxLayout(wedPanel , BoxLayout.Y_AXIS);
		BoxLayout thuLayout = new BoxLayout(thuPanel , BoxLayout.Y_AXIS);
		BoxLayout friLayout = new BoxLayout(friPanel , BoxLayout.Y_AXIS);
		BoxLayout satLayout = new BoxLayout(satPanel , BoxLayout.Y_AXIS);
		BoxLayout sunLayout = new BoxLayout(sunPanel , BoxLayout.Y_AXIS);
		monPanel.setLayout(monLayout);
		
		//JLABEL BUILDER
		
		//MONDAY
		mon = new JLabelBuilder().text("Mon").build();
		monCount = new JLabelBuilder().text("0").build();
		monPanel.add(mon);
		monPanel.add(monCount);
		
		//TUESDAY 
		tue = new JLabelBuilder().text("Tue").build();
		tueCount = new JLabelBuilder().text("0").build();
		tuePanel.setLayout(tueLayout);
		tuePanel.add(tue);
		tuePanel.add(tueCount);
		
		//WEDNESDAY
		wed = new JLabelBuilder().text("Wed").build();
		wedCount = new JLabelBuilder().text("0").build();
		wedPanel.setLayout(wedLayout);
		wedPanel.add(wed);
		wedPanel.add(wedCount);
		
		//THURSDAY
		thu = new JLabelBuilder().text("Thu").build();
		thuCount = new JLabelBuilder().text("0").build();
		thuPanel.setLayout(thuLayout);
		thuPanel.add(thu);
		thuPanel.add(thuCount);
		
		//FRIDAY
		fri = new JLabelBuilder().text("Fri").build();
		friCount = new JLabelBuilder().text("0").build();
		friPanel.setLayout(friLayout);
		friPanel.add(fri);
		friPanel.add(friCount);
		
		//SATURDAY
		sat = new JLabelBuilder().text("Sat").build();
		satCount = new JLabelBuilder().text("0").build();
		satPanel.setLayout(satLayout);
		satPanel.add(sat);
		satPanel.add(satCount);
		
		//SUNDAY
		sun = new JLabelBuilder().text("Sun").build();
		sunCount = new JLabelBuilder().text("0").build();
		sunPanel.setLayout(sunLayout);
		sunPanel.add(sun);
		sunPanel.add(sunCount);
		
		
		JPanel logPanel = new JPanel();
		logPanel.add(monPanel);
		logPanel.add(tuePanel);
		logPanel.add(wedPanel);
		logPanel.add(thuPanel);
		logPanel.add(friPanel);
		logPanel.add(satPanel);
		logPanel.add(sunPanel);
		logFrame.add(logPanel);
		
		records.read();
		
		//INIT WEEK
		ArrayList<Week> week = records.getWeek();
		monCount.setText(Integer.toString(week.get(0).getWeek()));
		tueCount.setText(Integer.toString(week.get(1).getWeek()));
		wedCount.setText(Integer.toString(week.get(2).getWeek()));
		thuCount.setText(Integer.toString(week.get(3).getWeek()));
		friCount.setText(Integer.toString(week.get(4).getWeek()));
		satCount.setText(Integer.toString(week.get(5).getWeek()));
		sunCount.setText(Integer.toString(week.get(6).getWeek()));
	}
>>>>>>> Stashed changes
}