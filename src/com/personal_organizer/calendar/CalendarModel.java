//    This file is part of jcalendar.
//
//    jcalendar is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    jcalendar is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with jcalendar.  If not, see <http://www.gnu.org/licenses/>.

//	  Copyright Simone Pellegrini 2004-2008
//    Created on Apr 24, 2004

package com.personal_organizer.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class CalendarModel extends Observable {

	private MonthComboBoxModel _monthModel = null;
	private DayTableModel _dayModel = null;

	private Calendar _calendar = null;
	
	private List listeners_ = new ArrayList();

	public CalendarModel() {
		init(Calendar.getInstance());
	}

	public CalendarModel(int day_of_week, int month, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, day_of_week);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		init(calendar);
	}

	public CalendarModel(Calendar c) {
		init(c);
	}

	private void init(Calendar c) {
		_calendar = c;
		_monthModel = new MonthComboBoxModel();
		this.addObserver(_monthModel);
		_dayModel = new DayTableModel();
		this.addObserver(_dayModel);
		setMonth(_calendar.get(Calendar.MONTH));
	}
	
	public void addDateListener(DateListener dl){
		if(!listeners_.contains(dl))
			listeners_.add(dl);
	}
	
	public void removeDateListener(DateListener dl){
		if(listeners_.contains(dl))
			listeners_.remove(dl);
	}

	public Calendar getCalendar() {
		return this._calendar;
	}

	public int getMonth() {
		return this._calendar.get(Calendar.MONTH);
	}

	public int getYear() {
		return this._calendar.get(Calendar.YEAR);
	}

	public void setMonth(int month) {
		_calendar.set(Calendar.MONTH, month);
		this.setChanged();
		this.notifyObservers(this._calendar);
		dateChangedEvent();
	}

	public void setYear(int year) {
		_calendar.set(Calendar.YEAR, year);
		this.setChanged();
		this.notifyObservers(this._calendar);
		dateChangedEvent();
	}

	public DefaultComboBoxModel getComboBoxModel() {
		return this._monthModel;
	}

	public DefaultTableModel getTableModel() {
		return this._dayModel;
	}

	public void setDay(int day) {
		_calendar.set(Calendar.DAY_OF_MONTH, day);
		this.setChanged();
		this.notifyObservers(this._calendar);
		dateChangedEvent();
	}
	
	private void dateChangedEvent(){
		for(int i=0; i<listeners_.size(); i++)
			((DateListener)listeners_.get(i)).dateChanged(this._calendar);
	}

}

class MonthComboBoxModel extends DefaultComboBoxModel implements Observer {

	public MonthComboBoxModel() {
		super(Constants.MONTHS);
	}

	public void update(Observable o, Object arg) {
		Calendar c = (Calendar) arg;
		this.setSelectedItem(Constants.MONTHS[c.get(Calendar.MONTH)]);
	}
}

class DayTableModel extends DefaultTableModel implements Observer {
	
	private int _currentDayRow;
	private int _currentDayCol;
	
	public DayTableModel() {
		super(6, 7);
		this.setColumnIdentifiers(Constants.TABLE_HEADER_DAY_NAME);
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	public int getCurrentDayCol(){
		return this._currentDayCol;
	}
	
	public int getCurrentDayRow(){
		return this._currentDayRow;
	}

	public void update(Observable o, Object arg) {
		clearTable();
		Calendar c = (Calendar) arg;
		Calendar calendar = (Calendar)c.clone();
		int day = c.get(Calendar.DAY_OF_MONTH);
		int row = 0;
		for (int i = 1;
			i
				< dayOfMonth(
					calendar.get(Calendar.MONTH),
					((GregorianCalendar) calendar).isLeapYear(
						calendar.get(Calendar.YEAR)))
					+ 1;
			i++) {
			calendar.set(Calendar.DAY_OF_MONTH, i);
			int col = convert(calendar.get(Calendar.DAY_OF_WEEK));
			this.setValueAt(new Integer(i), row, col - 1);
			if(i==day){
				this._currentDayRow = row;
				this._currentDayCol = col-1;
			}
			if (col == 7)
				row++;
		}
	}

	private void clearTable() {
		for (int i = 0; i < 6; i++) {
			for (int k = 0; k < 7; k++)
				this.setValueAt(null, i, k);
		}
	}

	private int convert(int day_of_week) {
		return (day_of_week == 1 ? 7 : day_of_week - 1);
	}

	private int dayOfMonth(int month, boolean leapYear) {
		switch (month) {
			case 1 :
				return leapYear ? 29 : 28;
			case 10 :
			case 3 :
			case 5 :
			case 8 :
				return 30;
			default :
				return 31;
		}
	}

}
