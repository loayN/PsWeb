package LoayNaser;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Message {

	String type, req, timeSMHD, startDateTime, endDateTime;
	int id;
	
	ArrayList<String> myList = new ArrayList<>();

	/**
	 * @constructor Empty
	 */
	public Message() {
		super();
	}

	/**
	 * @constructor
	 * @param type          : user/OS
	 * @param req           : the requested data
	 * @param startDateTime : the time and date which will start collecting data
	 *                      from
	 * @param endDateTime   : the time and date which will stop collecting data at
	 * @param timeSMHD      : the data is shown relatively to mins or seconds or
	 *                      hours or days
	 */
	public Message(int id, String type, String req, String startDateTime, String endDateTime, String timeSMHD) {

		this.id = id;
		this.type = type;
		this.req = req;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.timeSMHD = timeSMHD;
		this.myList = null;
	}

	/**
	 * setters and getters for all of the params, each param separately
	 */
	//@JsonIgnore
	public void setId(int id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setReq(String req) {
		this.req = req;
	}

	public void setTimeSMHD(String timeSMHD) {
		this.timeSMHD = timeSMHD;
	}

	public void setStartDateTime(String StartDateTime) {
		this.startDateTime = StartDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public void setMyList(ArrayList<String> myList) {
		/*
		 * if(this.myList.isEmpty())this.myList = myList; else { for (String str:myList)
		 * this.myList.add( str ); }
		 */
		this.myList = myList;
	}

	public int getId() {
		return id;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public String getReq() {
		return req;
	}

	public String getStartDateTime() {
		return startDateTime;
	}

	public String getTimeSMHD() {
		return timeSMHD;
	}

	public String getType() {
		return type;
	}

	public String getMyList() {
		return String.valueOf(myList);
	}

	public void add(String str) {
		this.myList.add(str);
	}

}