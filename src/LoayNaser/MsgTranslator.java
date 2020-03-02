package LoayNaser;

import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.driver.core.Session;

public class MsgTranslator {

	private static final Logger logger = LogManager.getLogger(LoayNaser.MsgTranslator.class);

	/**
	 * check the message if its asks about the user or OS
	 * 
	 * @param msg : is the message received from the client that represented as
	 *            Message object
	 * @return Message msg : that contain a new list of the requested data
	 */
	public static Message translate(Message msg, Session s) {

		switch (msg.getType()) {
		case "user": {

			/**
			 * check the requests that relative to the users
			 * 
			 * @param msg : is the message received from the client that represented as
			 *            Message object
			 * @param s   : Cassandra session
			 * @return Message msg
			 */
			
			
			Timestamp tmstmpStart = null;
			tmstmpStart = tmstmpStart.valueOf(msg.getStartDateTime().toString());
			for(int i=0; i<30; i++) {
				msg.add((i*10)+" " + tmstmpStart.toString());
				tmstmpStart.setMinutes(tmstmpStart.getMinutes()+1);
				
			}
			return msg;//UserReq.reqAnalyze(msg, s);
		}
		case "OS": {
			return osFunctions(msg);
			}
		}
		logger.error("error in message type: => type: " + msg.getType());
		return null;
	}

	/**
	 * check the requests that relative to the OS
	 * 
	 * @param msg : is the message received from the client that represented as
	 *            Message object
	 */
	private static Message osFunctions(Message msg) {

		switch (msg.getReq()) {
		case "general":
			// need some work on setting the list "maybe"
			OSreq.generaFunc(msg);
			break;
		case "root files":
			return OSreq.rootFilesSpaceFunc(msg);
		case "RAM":
			return OSreq.ramFunc(msg);
		case "CPU":
			return OSreq.cpuFunc(msg);
		case "UPTIME":
			return OSreq.upTimeFunc(msg);
		}
		logger.error("error in messsage request: => req:" + msg.getReq());

		return null;
	}

}
