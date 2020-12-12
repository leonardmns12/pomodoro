package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Record {
	
	protected File file;
	protected FileWriter fw;
	protected ArrayList<String> data = new ArrayList<String>();
	protected ArrayList<Week> week = new ArrayList<Week>();
	
	public Record() {
		file = new File(getClass().getResource("/res/log.csv").getFile());
		init();
	}

	public void write(String text) {
		try {
			file = new File(getClass().getResource("/res/log.csv").getFile());
			fw = new FileWriter(file , true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ssX");
		try {
			fw.write(sdf.format(new Date()));
			fw.write(",");
			fw.write("\n");
			fw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void read() {
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(file));
			String row;
			String[] text;
			while ((row = csvReader.readLine()) != null) {
			    text = row.split(",");
			    data.add(text[0]);
			}
			csvReader.close();
			for(int i = 0; i < data.size(); i++) {
				exceedWeek(data.get(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean exceedWeek(String date) {
		Calendar cal = Calendar.getInstance();
		Date fromDate;
		try {
			fromDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ssX").parse(date);
			cal.setTime(fromDate);
			cal.add(Calendar.DATE, 7);
			int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if(cal.getTime().compareTo(new Date()) < 0) {
				return false;
			} else {
				week.get(day-1).setWeek(week.get(day-1).getWeek() + 1);
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return false;
	}
	
	public ArrayList<Week> getWeek() {
		return this.week;
	}
	
	public void init() {
		week.add(new Week(0));
		week.add(new Week(0));
		week.add(new Week(0));
		week.add(new Week(0));
		week.add(new Week(0));
		week.add(new Week(0));
		week.add(new Week(0));
	}
}
