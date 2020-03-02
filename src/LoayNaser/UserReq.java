package LoayNaser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.driver.core.Session;

public class UserReq {
	private static final Logger logger = LogManager.getLogger(LoayNaser.UserReq.class);

	public static Message reqAnalyze (Message msg, Session s) {
		
		String cmdSql = null;
		switch (msg.getReq()) {

		case "connectedUsers":
			
			cmdSql = "SELECT uid  FROM presence WHERE modified_date >='" + msg.getEndDateTime().toString()
					+ "' AND modified_date < '" + msg.getEndDateTime().toString() + "' allow filtering;";
			
			return CqlExecutor.exeCQL(cmdSql, msg, s);
			
		case "numOfCon":
			
			cmdSql = "SELECT did  FROM presence WHERE modified_date >='" + msg.getEndDateTime().toString()
					+ "' AND modified_date < '" + msg.getEndDateTime().toString() + "' allow filtering;";
			
			return CqlExecutor.exeCQL(cmdSql, msg, s);

		case "quantityOfMembers":
			
			cmdSql = "SELECT uid  FROM circleMember WHERE modified_date >='" + msg.getEndDateTime().toString()
					+ "' AND modified_date < '" + msg.getEndDateTime().toString() + "' allow filtering;";
			
			return CqlExecutor.exeCQL(cmdSql, msg, s);

		case "avgCons":
			
			cmdSql = "";
			
			return CqlExecutor.exeCQL(cmdSql, msg, s);
		default:
			logger.error("error in messsage request: => req:" + msg.getReq() + "Message id: " + msg.getId());
		}

		return msg;
	}

}