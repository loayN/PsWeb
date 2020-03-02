package LoayNaser;

import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author LoayNaser
 * recieved message format: (json) => {"type":"OS/user",
 * 							 "req":"root files/RAM/CPU/UPTIME - fun1/fun2/fun3fun4",
 * 							 "timeSMHD":"sec/min/hour/day",
 * 							 "startDateTime":"yyyy-mm-dd hh:mm:ss",
 * 							 "endDateTime":"yyyy-mm-dd hh:mm:ss",
 * 							 "id":"int"}
 * return message format: (json) => {"type":"OS/user",
 * 							 "req":" (OS) root files/RAM/CPU/UPTIME - (user) fun1/fun2/fun3fun4",
 * 							 "timeSMHD":"sec/min/hour/day",
 * 							 "startDateTime":"yyyy-mm-dd hh:mm:ss",
 * 							 "endDateTime":"yyyy-mm-dd hh:mm:ss",
 * 							 "id":"int",
 * 							 "myList":"[(the result list)]"}
 * 
 * example : recieved=> {"type":"OS","req":"root files","timeSMHD":"sec","startDateTime":"2015-01-01 23:59:00","endDateTime":"2015-01-01 23:59:00","id":"3"}
 * 			 return  => {"type":"OS","req":"root files","timeSMHD":"sec","startDateTime":"2015-01-01 23:59:00","endDateTime":"2015-01-01 23:59:00","id":"3",
 * 					     "myList":"[File system root: C:\\, Total space : 418.946 GB, Free space : 57.346 GB, Usable space : 57.346 GB, 
 * 								    File system root: D:\\, Total space : 25.0 GB, Free space : 22.215 GB, Usable space : 22.215 GB]"}
 *
 */


@ServerEndpoint(value = "/endpoint")
public class LWebSocket {

	private static final Logger logger = LogManager.getLogger(LoayNaser.LWebSocket.class);

	// objectSession => used to save for each client his cassandra session and websocket session.
/*	class objectSession {
		Session clientSession;
		com.datastax.driver.core.Session casSession;

		public objectSession(Session clS, com.datastax.driver.core.Session casSession) {
			this.clientSession = clS;
			this.casSession = casSession;
		}
	}*/

	/*// sessionsList contain a list of sessions id and private Cassandra session to each client
	public static ArrayList<objectSession> sessionsList = new ArrayList<objectSession>(); // removed => objectSession */

	com.datastax.driver.core.Session casSession = null;

	@OnOpen
	public void onOpen(Session s) {
		
		logger.trace("Entering application.");
		logger.info("Open Connection - id: " + s.getId());

		// removed => objectSession
		/*
		 * try { com.datastax.driver.core.Session casSession =
		 * CassandraConnector.openSession(); objectSession ob = new objectSession(s,
		 * casSession); sessionsList.add(ob);
		 * logger.info("cassandra connection established for connection id: " +
		 * s.getId()); } catch (Exception e) { logger.error(e);
		 * logger.error("Exception is:", e); }
		 */
		/*if (casSession.equals(null))
			casSession = CassandraConnector.openSession();*/

	}


	@OnClose
	public void onClose(Session s) {
		logger.info("Close Connection, session id : " + s.getId());
		//sessionsList.removeIf(obj -> obj.clientSession == s); // removed => objectSession
	}

	@OnError
	public void onError(Throwable e) {
		logger.error(e);
		logger.error("Exception is: ", e);
	}

	@OnMessage
	public String onMessage(String message, Session session) {

		logger.info("client id: " + session.getId() + " - Message from the client: " + message);
		String echoMsg;

		Message msgObj;
		try {
			/*if (casSession.equals(null))
				casSession = CassandraConnector.openSession();*/
			msgObj = messageToObject(message); // converting the message (string) to Message object
			msgObj = MsgTranslator.translate(msgObj, casSession);
			if (msgObj.equals(null))
				return "error; null msg";
			echoMsg = msgObjectToString(msgObj);
			return echoMsg;
		} catch (Exception e) {
			logger.error(e);
			logger.error("Exception is: ", e);
		}
		return "unknown message error";
	}

	/**
	 * messageToObject is a helper method to convert message string to Message
	 * Objects
	 * 
	 * @param msgRec : String
	 * @return msgObj : Message
	 */
	public static Message messageToObject(String msgRec) {
		ObjectMapper objectMapper = new ObjectMapper();
		Message msgObj = new Message();
		try {
			msgObj = objectMapper.readValue(msgRec, Message.class);
			return msgObj;
		} catch (IOException e) {
			logger.error(e);
			logger.error("Exception is: ", e);
		}
		return null;
	}

	/**
	 * msgObjectToString is a helper method to convert Message Objects to message
	 * string
	 * 
	 * @param msgObj
	 * @return Message object as string
	 */
	public static String msgObjectToString(Message msgObj) {
		ObjectMapper objectMapper = new ObjectMapper();
		String msgStringToSend = null;
		try {
			msgStringToSend = objectMapper.writeValueAsString(msgObj);
			return (msgStringToSend.toString());
		} catch (Exception e) {
			logger.error(e);
			logger.error("Exception is: ", e);
		}
		return null;
	}

}
