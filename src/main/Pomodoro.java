package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import builder.JButtonBuilder;
import builder.JLabelBuilder;
import state.Timers;
import template.FinishTemplate;
import template.LongBreakTemplate;
import template.PomodoroTemplate;
import template.ShortBreakTemplate;

import java.awt.Component;

public class Pomodoro extends JFrame {
	public JPanel topPanel, midPanel, botPanel, statsPanel;
	public JLabel time, phase1, phase2, phase3, phase4, stats;
	private JLabel monCount , tueCount , wedCount , thuCount , friCount, satCount ,sunCount;
	public JButton start, skip;
	private int minute = 0, second = 0;
	private String str_minute = String.format("%02d", minute);
	private String str_second = String.format("%02d", second);
	public boolean isStarted = false;
	private boolean isStatClicked = false;
	private Timer timer;
	private Timers timers;
	public Record records;
	
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
		
		//-- MID PANNEL --//
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
		timers = new Timers(1500000);
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				time.setText(timers.printTime());
				if(timers.printState() == 1 || timers.printState() == 3 || timers.printState() == 5) {
					skip.setVisible(false);
				} 
				else {
					skip.setVisible(true);
				}
				
	    		timers.Start();
	    		setDot(timers.printState());
	    		refreshLayout(timers.printState());
	    		if(isStatClicked) {
	    			
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
		
		//----- RECORDS -----//
		records = new Record();
		
		//----- ACTIONS -----//
		
		//SKIP ACTION 
		skip.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				timers.skipState();
				stop();
				refreshLayout(timers.printState());
				time.setText(timers.printTime());
				isStarted = false;
				start.setIcon(new ImageIcon(getClass().getResource("/res/play.png")));
			}
		});
		
		//START ACTION
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isStarted) {
					start();
					start.setIcon(new ImageIcon(getClass().getResource("/res/pause.png")));
					isStarted = true;
				}else {
					stop();
					start.setIcon(new ImageIcon(getClass().getResource("/res/play.png")));
					isStarted = false;
					skip.setVisible(false);
				}
				
			}
		});
		
		//VIEW STATS ACTION
		stats.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!isStatClicked) {
					newFrame();
					isStatClicked = true;
				}
			}
		});
	}
	
	public void start() {
		Runnable myRunnable = new Runnable() {
	        public void run() {
	        	timer.start();
	        }
		};
		myRunnable.run();
	}
	
	public void stop() {
		timer.stop();
	}
	
	public void newFrame() {
		records.read();
		JFrame logFrame = new JFrame();
		logFrame.setSize(500 , 100);
		logFrame.setVisible(true);
		logFrame.setIconImage(new ImageIcon(getClass().getResource("/res/tomato.png")).getImage());
		logFrame.setTitle("Pomodoro");
		logFrame.setLocationRelativeTo(null);
		logFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		JLabel mon, tue, wed, thu, fri, sat, sun;
		JPanel monPanel, tuePanel, wedPanel, thuPanel, friPanel, satPanel, sunPanel;

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
				
		//INIT WEEk
		initWeek();
		
		logFrame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				isStatClicked = false;
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void initWeek() {
		ArrayList<Week> week = records.getWeek();
		monCount.setText(Integer.toString(week.get(0).getWeek()));
		tueCount.setText(Integer.toString(week.get(1).getWeek()));
		wedCount.setText(Integer.toString(week.get(2).getWeek()));
		thuCount.setText(Integer.toString(week.get(3).getWeek()));
		friCount.setText(Integer.toString(week.get(4).getWeek()));
		satCount.setText(Integer.toString(week.get(5).getWeek()));
		sunCount.setText(Integer.toString(week.get(6).getWeek()));
	}
	
	public void refreshLayout(int state) {
		ShortBreakTemplate shortBreakTemplate = new ShortBreakTemplate(this);
		LongBreakTemplate longBreakTemplate = new LongBreakTemplate(this);
		FinishTemplate finishTemplate = new FinishTemplate(this);
		PomodoroTemplate pomodoroTemplate = new PomodoroTemplate(this);
		if(state % 2 == 0 && state < 8) {
			shortBreakTemplate.setView(Color.decode("#1e8270"));
		} else if(state % 2 != 0 && state < 8) {
			pomodoroTemplate.setView(Color.decode("#f55442"));
			skip.setVisible(false);
		} else if(state == 8) {
			longBreakTemplate.setView(Color.decode("#fff75c"));
		} else {
			finishTemplate.setView(Color.green);
			stop();
			timers.resetTime();
			time.setText("00:00");

			if(isStatClicked) {
				System.out.println("stat click");
				initWeek();
			}
		}
	}
}