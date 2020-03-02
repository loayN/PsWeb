package LoayNaser;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

	
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class DB_try {

	/*public static void main(String[] args) {
			//t1(); // change timestamp
        	t2();
	}
	
	*/
	private static void minute(Timestamp tm1, Timestamp tm2, Session session) {
		Timestamp tm3 = null;
		tm3 = tm3.valueOf(tm1.toString());
	//	tm3= tm1;
	//	int i=0 ;
		int j=0;
		ArrayList<String> arrminutes = new ArrayList<String>(60);
		tm3.setMinutes(tm3.getMinutes()+1);
		
		
		
		for (int i = 0; i < 60; i++) {
		if (tm1.before(tm2)) {
			System.out.println(i);
			ResultSet  results = session.execute("SELECT uid  FROM presence WHERE modified_date >='"
					+ tm1.toString() + "' AND modified_date < '" + tm3.toString() + "' allow filtering;");
			j=0;
			for (Row row : results) {
				System.out.println(row);
				System.out.println(tm1.toString());
				j++;	
			}
			arrminutes.add(j+"");
			//System.out.println(j); 
		}
			tm3.setMinutes(tm3.getMinutes()+1);
			tm1.setMinutes(tm1.getMinutes()+1);
		}
			System.out.println(arrminutes);
			
		}

	@SuppressWarnings("unused")
	private static void t2() {
		

		Cluster cluster;
		Session session;
		cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		session = cluster.connect("test1");
		
		int j=0;
		char ch = 'm';	
		//ArrayList<String> arrMins = new ArrayList<String>(60);
		//ArrayList<String> arrHours = new ArrayList<String>(24);
		
		
		// year start at 1900. Ex => 1900 + 115 = 2015 
		Timestamp tm1 = new Timestamp(115,05,01, 03,05,00,0000);//new Timestamp(70, 0, 11, 10, 30, 00, 00);
		Timestamp tm2 = new Timestamp(115,05,01, 03,06,00,0000);//new Timestamp(70, 0, 11, 11, 30, 00, 00);
		// Timestamp tm3 =  new Timestamp(115,11,13, 01,12,22,0000);
		//tm3.setMinutes(tm1.getMinutes()+1);
		//System.out.println("\n" +tm1 + "  " + tm2);
		switch (ch) {
		case 'd': day(tm1,tm2,session);
		          break ;
		          
		case 'h': hour(tm1,tm2,session);
        break ;
        
		case 'm': minute(tm1,tm2,session);
        break ;
        
		case 's': seconds(tm1,tm2,session);
        break ;
		}
		
	//	for (int i = 0; i < 60; i++) {
		//	if (tm1.before(tm2)) {
			//	System.out.println(i);
			//	ResultSet  results = session.execute("SELECT did  FROM presence WHERE modified_date >='"
		//				+ tm1.toString() + "' AND modified_date < '" + tm3.toString() + "' allow filtering");
		//		j=0;
		//		for (Row row : results) {
		//			System.out.println(row);
		//			System.out.println(tm1.toString());
		//			j++;	
		//		}
		//		arrMins.add(j+"");
		//		//System.out.println(j); 
		//	}
		//	tm3.setMinutes(tm3.getMinutes()+1);
		//	tm1.setMinutes(tm1.getMinutes()+1);
		//}

		// System.out.println(j);
	//	System.out.println(arrMins);
	//	System.out.println("\n" +tm1);

	//	System.out.println(j);
	}
	

	private static void seconds(Timestamp tm1, Timestamp tm2, Session session) {
		
		Timestamp tm3 = null;
		tm3= tm3.valueOf(tm1.toString());
		//int i=0 ;
		int j=0;
		ArrayList<String> arrseconds = new ArrayList<String>();
		tm3.setSeconds(tm3.getSeconds()+1);

		//System.out.println(tm1.toString() + "\n" + tm2.toString() + "\n" + tm3.toString());
		//for (int i = 0; i < 60; i++) {
		while (tm1.before(tm2)) {
			System.out.println(tm1.toString() + "\n" + tm2.toString() + "\n" + tm3.toString());

			//System.out.println(i);
			ResultSet  results = session.execute("SELECT uid  FROM presence WHERE modified_date >='"
					+ tm1.toString() + "' AND modified_date < '" + tm3.toString() + "' allow filtering;");
			for (Row row : results) {
				System.out.println(row);
				System.out.println(tm1.toString());
				j++;	
			}
			arrseconds.add(j+"");
			System.out.println(j);
			j=0;
		
			tm3.setSeconds(tm3.getSeconds()+1);
			tm1.setSeconds(tm1.getSeconds()+1);
		}
		System.out.println(arrseconds);
		
	}

	private static void minute2(Timestamp tm1, Timestamp tm2, Session session) {
		Timestamp tm3 = null;
		tm3 = tm3.valueOf(tm1.toString());
	//	tm3= tm1;
	//	int i=0 ;
		int j=0;
		ArrayList<String> arrminutes = new ArrayList<String>(60);
		tm3.setMinutes(tm3.getMinutes()+1);
		
		for (int i = 0; i < 60; i++) {
		if (tm1.before(tm2)) {
			System.out.println(i);
			ResultSet  results = session.execute("SELECT did  FROM presence WHERE modified_date >='"
					+ tm1.toString() + "' AND modified_date < '" + tm3.toString() + "' allow filtering;");
			j=0;
			for (Row row : results) {
				System.out.println(row);
				System.out.println(tm1.toString());
				j++;	
			}
			arrminutes.add(j+"");
			//System.out.println(j); 
		}
			tm3.setMinutes(tm3.getMinutes()+1);
			tm1.setMinutes(tm1.getMinutes()+1);
		}
			System.out.println(arrminutes);
			
		}

	private static void minut3(Timestamp tm1, Timestamp tm2, Session session) {
		Timestamp tm3 = null;
		tm3 = tm3.valueOf(tm1.toString());
	//	tm3= tm1;
	//	int i=0 ;
		int j=0;
		ArrayList<String> arrminutes = new ArrayList<String>(60);
		tm3.setMinutes(tm3.getMinutes()+1);
		
		for (int i = 0; i < 60; i++) {
		if (tm1.before(tm2)) {
			System.out.println(i);
			ResultSet  results = session.execute("SELECT uid  FROM circleMember WHERE modified_date >='"
					+ tm1.toString() + "' AND modified_date < '" + tm3.toString() + "' allow filtering;");
			j=0;
			for (Row row : results) {
				System.out.println(row);
				System.out.println(tm1.toString());
				j++;	
			}
			arrminutes.add(j+"");
			//System.out.println(j); 
		}
			tm3.setMinutes(tm3.getMinutes()+1);
			tm1.setMinutes(tm1.getMinutes()+1);
		}
			System.out.println(arrminutes);
			
		}

	private static void minute4(Timestamp tm1, Timestamp tm2, Session session) {
		Timestamp tm3 = null;
		tm3 = tm3.valueOf(tm1.toString());
	//	tm3= tm1;
	//	int i=0 ;
		int j=0;
		int k=0;
		ArrayList<String> arrminutes = new ArrayList<String>(60);
		tm3.setMinutes(tm3.getMinutes()+1);
		
		for (int i = 0; i < 60; i++) {
		if (tm1.before(tm2)) {
			System.out.println(i);
			ResultSet  results1 = session.execute("SELECT uid  FROM presence WHERE modified_date >='"
					+ tm1.toString() + "' AND modified_date < '" + tm3.toString() + "' allow filtering;");
			
			ResultSet  results2 = session.execute("SELECT did  FROM presence WHERE modified_date >='"
					+ tm1.toString() + "' AND modified_date < '" + tm3.toString() + "' allow filtering;");
			
			j=0;
			k=0;
			for (Row row : results1) {
				System.out.println(row);
				System.out.println(tm1.toString());
				j++;	
			}
			
			for (Row row : results2) {
				System.out.println(row);
				System.out.println(tm1.toString());
				k++;	
			}
			
			arrminutes.add((j/k)+"");
			//System.out.println(j); 
		}
			tm3.setMinutes(tm3.getMinutes()+1);
			tm1.setMinutes(tm1.getMinutes()+1);
		}
			System.out.println(arrminutes);
			
		}
	
	private static void hour(Timestamp tm1, Timestamp tm2, Session session) {
		
		Timestamp tm3 = null;
		tm3 = tm3.valueOf(tm1.toString());// new Timestamp(115,10,01, 00,00,00,0000);
		int i=0 ;
		int j=0;
		ArrayList<String> arrhour = new ArrayList<String>(24);
		System.out.println(tm1.toString() + "\n" + tm2.toString() + "\n" + tm3.toString());
		tm3.setHours(tm3.getHours()+1);

		while (tm1.before(tm2)) {
			ResultSet  results = session.execute("SELECT uid, modified_date  FROM presence WHERE modified_date >='"
					+ tm1.toString() + "' AND modified_date < '" + tm3.toString() + "' allow filtering;");
			for (Row row : results) {
				System.out.println(row);
				System.out.println(tm1.toString());
				j++;	
			}
			
			arrhour.add(j+"");
			System.out.println(j);
			j=0;
			
			tm3.setHours(tm3.getHours()+1);
			tm1.setHours(tm1.getHours()+1);
		}
		System.out.println(arrhour);
		
	}

	private static void day(Timestamp tm1, Timestamp tm2,Session session) {
	
	
	int i=0 ;
	int j=0;
	ArrayList<String> arrday = new ArrayList<String>(30);
	
	Calendar cal1 = Calendar.getInstance();
	cal1.setTimeInMillis(tm1.getTime());
	Calendar cal3 = Calendar.getInstance();
    cal3.setTimeInMillis(tm1.getTime());
    cal3.add(Calendar.DAY_OF_MONTH, 1);
    Timestamp tm3 = new Timestamp(cal3.getTime().getTime());
    System.out.println(tm3 + "\n");
		
	
	System.out.println(tm1.toString() + "\n" + tm2.toString() + "\n" + tm3.toString() + "\n");
	while (tm1.before(tm2)) {
		System.out.println(i);
		System.out.println(tm1.toString() + "\n" + tm2.toString() + "\n" + tm3.toString());

		ResultSet  results = session.execute("SELECT uid, modified_date  FROM presence WHERE modified_date >='"
				+ tm1.toString() + "' AND modified_date < '" + tm3.toString() + "' allow filtering;");
		for (Row row : results) {
			System.out.println(row);
			System.out.println(tm1.toString());
			j++;	
		}
		arrday.add(j+"");
		j=0;

		cal3.add(Calendar.DAY_OF_MONTH, 1);
	     tm3 = new Timestamp(cal3.getTime().getTime());
	    cal1.add(Calendar.DAY_OF_MONTH, 1);
	     tm1 = new Timestamp(cal1.getTime().getTime());	
	}
		System.out.println(arrday);
	}

	@SuppressWarnings("deprecation")
	public static void t1() {

		Cluster cluster;
		Session session;
		cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		session = cluster.connect("test1");


		
		Random random = new Random();
        String cqlStatement = null;
        String uid, did, peerid;
        int onlinestatus, rowstatus;
        int min=0; int max=1;
        Timestamp modified_date;
        int length = 10;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvwxyz"
                + "0123456789";

        /**
         * 10,000 data only between 010000 - 040000
         *   long offset = Timestamp.valueOf("2015-12-13 01:00:00").getTime();
         *	 long end = Timestamp.valueOf("2015-12-13 04:00:00").getTime();
         */
        
        long offset = Timestamp.valueOf("2015-06-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2015-06-01 04:00:00").getTime();
        long diff = end - offset + 1;
        for (int i=0; i<10000; i++){
        	System.out.println("inserted : " + i+1);
            onlinestatus = random.nextInt(max + 1 - min) + min;
            rowstatus = random.nextInt(max + 1 - min) + min;
            uid = new Random().ints(length, 0, chars.length())
                    .mapToObj(z -> "" + chars.charAt(z))
                    .collect( Collectors.joining());
            did = new Random().ints(length, 0, chars.length())
                    .mapToObj(j -> "" + chars.charAt(j))
                    .collect( Collectors.joining());
            peerid = new Random().ints(length, 0, chars.length())
                    .mapToObj(x -> "" + chars.charAt(x))
                    .collect( Collectors.joining());

            modified_date = new Timestamp(offset + (long)(Math.random() * diff));

/*
            StringBuilder sb = new StringBuilder("INSERT INTO ")
                    .append("circlinpresence.presence").append("(uid, modified_date, address, did, onlinestatus, peerid, rowstatus) ")
                    .append("VALUES (" ).append( uid ).append(",").append(  modified_date).append( "," ).append( " " ).append( "," )
                    .append( did ).append( "," ).append(onlinestatus ).append(",").append(peerid).append(",")
                    .append(rowstatus).append("');");*/

            //cqlStatement = sb.toString();
            String onStat = onlinestatus + "";
            String rowStat = rowstatus + "";
            session.execute("INSERT INTO presence (uid,modified_date,did,onlinestatus,peerid,rowstatus) VALUES (" + "'"
            		 + uid + "'" + ", '" + modified_date.toString() + "' ," + "'" + did + "'" +", " + onlinestatus + " , '" + peerid + "' , " + rowstatus +")");

            //session.execute(cqlStatement);
        }
		
		
		
        
		// String[] u = {"u1", "u2", "u3", "u4", "u5", "u6", "u7", "u8", "u9", "u10",
		// "u11", "u12","u13", "u14", "u15", "u16", "u17", "u18", "u19", "u20"};
		// String[] d = {"d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9", "d10",
		// "d11", "d12","d13", "d14", "d15", "d16", "d17", "d18", "d19", "d20"};

		// Random r1 = new Random();
		// int low1 = 0;
		// int high1 = 20;
		// int resultU;
		// int resultD;

		// Random r2 = new Random();
		// int low2 = 111111111;
		// int high2 = 999999999;
		// int result2 ;


		// for(int i=0;i<100;i++)
		// {
		// resultU= r1.nextInt(high1-low1) + low1;
		// resultD= r1.nextInt(high1-low1) + low1;
		// result2 = r2.nextInt(high2-low2) + low2;

		// session.execute("INSERT INTO presence (uid,modified_date,did) VALUES (" + "'"
		// + u[resultU] + "'" + ", " + result2 + " ," + "'" + d[resultD] + "'" + ")");

		// }

		// session.execute("INSERT INTO presence (uid,modified_date,did) VALUES (" + "'"
		// + s + "'" + ",123456789," + "'" + t + "'" + ")");
		// s= "1970-01-09 00:00";

		// ResultSet results = session.execute("SELECT did FROM presence WHERE
		// modified_date >'" + tm1.toString() + "' AND modified_date < '" +
		// tm2.toString() + "' allow filtering" );
		// ResultSet results = session.execute("select did from presence where did='d5'
		// allow filtering ;");
		// for (Row row : results) {
		// System.out.println(row);
		// System.out.format("%s \n", row.getString("did"));
		// {"type":"OS","req":"root file
		// space","timeSMHD":"min","startDateTime":"'1970-01-09
		// 10:25:00'","endDateTime":"'1970-01-09 11:25:00'","id":"3"}
		// j++;
		// }
/*
		ArrayList<String> arrMins = new ArrayList<String>(60);
		ArrayList<String> arrHours = new ArrayList<String>(24);
		
		Timestamp tm1 = new Timestamp(70, 0, 11, 10, 30, 00, 00);
		Timestamp tm2 = new Timestamp(70, 0, 11, 11, 30, 00, 00);
		Timestamp tm3 =  new Timestamp(70, 0, 11, 10, 30, 00, 00);
		tm3.setMinutes(tm1.getMinutes()+1);
		for (int i = 0; i < 60; i++) {
			if (tm1.before(tm2)) {
				System.out.println(i);
				ResultSet  results = session.execute("SELECT uid  FROM presence WHERE modified_date >='"
						+ tm1.toString() + "' AND modified_date < '" + tm3.toString() + "' allow filtering");
				j=0;
				for (Row row : results) {
					System.out.println(row);
					System.out.println(tm1.toString());
					j++;	
				}
				arrMins.add(j+"");
				//System.out.println(j); 
			}
			tm3.setMinutes(tm3.getMinutes()+1);
			tm1.setMinutes(tm1.getMinutes()+1);
		}

		// System.out.println(j);
		System.out.println(arrMins);
		System.out.println("\n" +tm1 + "  " + tm2);
		
		System.out.println(j);*/
	}
}
