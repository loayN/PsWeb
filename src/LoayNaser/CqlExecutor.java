package LoayNaser;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class CqlExecutor {

	private static final Logger logger = LogManager.getLogger(LoayNaser.CqlExecutor.class);

	/**
	 * 
	 * @param cqlStatement => query to execute : String
	 * @param msg => Object of the recieved message : Message
	 * @param s => Cassandra session : datastax.driver.core.Session
	 * @return
	 */
	public static Message exeCQL(String cqlStatement, Message msg, Session s) {

		Timestamp tmstmpStart = null, tmstmpEnd = null, tmstmpTmp1 = null, tmstmpTmp2 = null;
		tmstmpStart = tmstmpStart.valueOf(msg.getStartDateTime().toString()); // the timestamp of the start point - used in sec/min/hour
		tmstmpEnd = tmstmpEnd.valueOf(msg.getEndDateTime().toString()); // the timestamp of the end point - used in sec/min/hour
		tmstmpTmp1 = tmstmpTmp1.valueOf(tmstmpStart.toString()); // temporary timestamp for moving between the start/end point - used in sec/min/hour
		
		int j = 0;

		ArrayList<String> resultArrLst = new ArrayList<String>(); //used to save the results of the query execution  
		
		// same as tmstmpStart/tmstmpEnd/tmstmpTmp1 only used for days manipulation
		Calendar cal1 = Calendar.getInstance();
		cal1.setTimeInMillis(tmstmpStart.getTime());
		Calendar cal3 = Calendar.getInstance();
	    cal3.setTimeInMillis(tmstmpStart.getTime());
	    
	    String TimeSMHD = msg.getTimeSMHD(); // TimeSMHD => contain the stamp sec/min/hour/day from the message 
		
		//switch(msg.getTimeSMHD()) {
		switch (TimeSMHD) {
		
		case "sec":
			tmstmpTmp1.setSeconds(tmstmpTmp1.getSeconds()+1);
		case "min":
			tmstmpTmp1.setMinutes(tmstmpTmp1.getMinutes()+1);
		case "hour":
			tmstmpTmp1.setHours(tmstmpTmp1.getHours()+1);
		case "day":
			cal3.add(Calendar.DAY_OF_MONTH, 1);
		    tmstmpTmp2 = new Timestamp(cal3.getTime().getTime());
		default: 
			logger.error("error in messsage TimeSMHD: => TimeSMHD:" + msg.getTimeSMHD() + "Message id: " + msg.getId());
		}
		
		
		while (tmstmpStart.before(tmstmpEnd)) {

			for (Row row : s.execute(cqlStatement)) {
				//msg.add(row.toString());
				System.out.println(row.toString());
				j++;
			}
			if (TimeSMHD.equals("day")) {
				resultArrLst.add(j+""+ tmstmpTmp2.toString());

			}
			else {
			resultArrLst.add(j+""+ tmstmpTmp1.toString());
			}
			j=0;
			switch(TimeSMHD) { // increasing the right time stamp
			
			case "sec":
				tmstmpTmp1.setSeconds(tmstmpTmp1.getSeconds()+1);
				tmstmpStart.setSeconds(tmstmpStart.getSeconds()+1);
			case "min":
				tmstmpTmp1.setMinutes(tmstmpTmp1.getMinutes()+1);
				tmstmpStart.setMinutes(tmstmpStart.getMinutes()+1);
			case "hour":
				tmstmpTmp1.setHours(tmstmpTmp1.getHours()+1);
				tmstmpStart.setHours(tmstmpStart.getHours()+1);
			case "day":
				cal3.add(Calendar.DAY_OF_MONTH, 1);
			     tmstmpTmp2 = new Timestamp(cal3.getTime().getTime());
			    cal1.add(Calendar.DAY_OF_MONTH, 1);
			     tmstmpStart = new Timestamp(cal1.getTime().getTime());		
			default: 
				logger.error("error in messsage TimeSMHD: => TimeSMHD:" + msg.getTimeSMHD() + "Message id: " + msg.getId());
			}
			
		}
		 
		msg.setMyList(resultArrLst); // adding the result array to the Message object for returning it.
		return msg;
		
	}

}
