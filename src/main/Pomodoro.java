package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import builder.JButtonBuilder;
import builder.JLabelBuilder;
import state.Timers;
import java.awt.Component;

public class Pomodoro extends JFrame {
	private JPanel topPanel, midPanel, botPanel, statsPanel;
	private JLabel time, phase1, phase2, phase3, phase4, stats;
	private JButton start, skip;
	private int minute = 0, second = 0;
	private String str_minute = String.format("%02d", minute);
	private String str_second = String.format("%02d", second);
	private boolean isStarted = false;
	private Timer timer;
	private Record records;
	
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
		
		Timers timers = new Timers(1500000);
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				time.setText(timers.printTime());
	    		timers.Start();
	    		setDot(timers.printState());
	    		if(timers.printState() % 2 == 0 && timers.printState() < 8) {
	    			topPanel.setBackground(Color.decode("#1e8270"));
	    			midPanel.setBackground(Color.decode("#1e8270"));
	    			botPanel.setBackground(Color.decode("#1e8270"));
	    			statsPanel.setBackground(Color.decode("#1e8270"));
	    			start.setBackground(Color.decode("#1e8270"));
	    			skip.setBackground(Color.decode("#1e8270"));
	    		} else if(timers.printState() % 2 != 0 && timers.printState() < 8) {
	    			topPanel.setBackground(Color.decode("#f55442"));
	    			start.setBackground(Color.decode("#f55442"));
	    			skip.setBackground(Color.decode("#f55442"));
	    			midPanel.setBackground(Color.decode("#f55442"));
	    			botPanel.setBackground(Color.decode("#f55442"));
	    			statsPanel.setBackground(Color.decode("#f55442"));
	    		} else if(timers.printState() == 8) {
	    			topPanel.setBackground(Color.decode("#fff75c"));
	    			start.setBackground(Color.decode("#fff75c"));
	    			skip.setBackground(Color.decode("#fff75c"));
	    			midPanel.setBackground(Color.decode("#fff75c"));
	    			botPanel.setBackground(Color.decode("#fff75c"));
	    			statsPanel.setBackground(Color.decode("#fff75c"));
	    		} else {
	    			topPanel.setBackground(Color.green);
	    			start.setBackground(Color.green);
	    			skip.setBackground(Color.green);
	    			midPanel.setBackground(Color.green);
	    			botPanel.setBackground(Color.green);
	    			statsPanel.setBackground(Color.green);
	    			timers.resetTime();
	    			stop();
	    			time.setText("00:00");
	    			emptyDot();
	    			start.setIcon(new ImageIcon(getClass().getResource("/res/play.png")));
	    			isStarted = false;
	    			skip.setVisible(false);
	    			System.out.println("pomodoro finish!");
	    			records.write("finish");
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
				// TODO Auto-generated method stub
				timers.skipState();
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
					skip.setVisible(true);
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
				newFrame();
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
		
		//INIT WEEk
		ArrayList<Week> week = records.getWeek();
		monCount.setText(Integer.toString(week.get(0).getWeek()));
		tueCount.setText(Integer.toString(week.get(1).getWeek()));
		wedCount.setText(Integer.toString(week.get(2).getWeek()));
		thuCount.setText(Integer.toString(week.get(3).getWeek()));
		friCount.setText(Integer.toString(week.get(4).getWeek()));
		satCount.setText(Integer.toString(week.get(5).getWeek()));
		sunCount.setText(Integer.toString(week.get(6).getWeek()));
	}
}