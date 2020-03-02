package LoayNaser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.PlainTextAuthProvider;
import com.datastax.driver.core.Session;


public class CassandraConnector {
	
	private static final Logger logger = LogManager.getLogger(LoayNaser.CassandraConnector.class);	

	
	public static Session openSession() {
		String n1PubIP = GetCoordinate.getCassandraConnectionData("n1PubIP");
	    String dcName = GetCoordinate.getCassandraConnectionData("dcName"); // this is the DC name you used when created
	    String user = GetCoordinate.getCassandraConnectionData("user");
	    String password = GetCoordinate.getCassandraConnectionData("password");

	    
	    Cluster.Builder clusterBuilder = Cluster.builder()
	    		.addContactPoint(n1PubIP)
	    		.withPort(9042)
	    		.withAuthProvider(new PlainTextAuthProvider(user,password));
	    Cluster cluster = null;
	    Session session = null;
	    
	    try {
	    	cluster = clusterBuilder.build();
	    	//Metadata meta = cluster.getMetadata();
	    	//System.out.printf( "Connected to cluster: %s\n", meta.getClusterName() );
	    	//for (Host host : meta.getAllHosts()) {
	             //System.out.printf( "Datacenter: %s; Host: %s; Rack: %s\n", host.getDatacenter(), host.getAddress(), host.getRack() );
	    	//}
	        logger.debug("Cassandra host: " + n1PubIP + ":  9042 , keyspace: " + dcName);
	        session = cluster.connect();
	        logger.info("new Cassandra session established, logged in keyspace: " + session.getLoggedKeyspace());
	    }catch(Exception e) {
			logger.error(e);
			logger.error("Exception is:",e);
	    }
	    return session;
	}
}
