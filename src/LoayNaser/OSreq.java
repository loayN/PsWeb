package LoayNaser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.management.OperatingSystemMXBean;

public class OSreq /* extends Thread */ {

	private static final Logger logger = LogManager.getLogger(LoayNaser.OSreq.class);

	/**
	 * get the root file and the capacity for each one For each filesystem root,
	 * print some info
	 * 
	 * @param msg : receives a Message object
	 *
	 */
	public static Message rootFilesSpaceFunc(Message msg) {
		ArrayList<String> myList = new ArrayList<>();
		File[] roots = File.listRoots();
		for (File root : roots) {
			//myList.add("File system root: " + root.getAbsolutePath());
			//myList.add("Total space : " + (formatNum((double) root.getTotalSpace() / 1073741824) + " GB"));
			//myList.add("Free space : " + (formatNum((double) root.getFreeSpace() / 1073741824) + " GB"));
			//myList.add("Usable space : " + (formatNum((double) root.getUsableSpace() / 1073741824) + " GB"));
			String str = "{name: " +  root.getPath() + ", Total space :" + 
			(formatNum((double) root.getTotalSpace() / 1073741824)) + ", Free space : " + 
			(formatNum((double) root.getFreeSpace() / 1073741824)) + ", Usable space :" + 
			(formatNum((double) root.getUsableSpace() / 1073741824)) + "}";
			msg.add(str);
			//getAbsolutePath()
		}
		return msg;
	}

	/**
	 * get the RAM details
	 * 
	 * @param msg
	 */
	public static Message ramFunc(Message msg) {
		ArrayList<String> myList = new ArrayList<>();
		OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		long physicalMemorySize = os.getTotalPhysicalMemorySize();
		long freePhysicalMemory = os.getFreePhysicalMemorySize();
		long freeSwapSize = os.getFreeSwapSpaceSize();
		//long commitedVirtualMemorySize = os.getCommittedVirtualMemorySize();
		myList.add("physical Memory Size : " + (formatNum((double) physicalMemorySize / 1073741824)) + " GB");
		myList.add("free Physical Memory : " + formatNum((double) freePhysicalMemory / 1073741824) + " GB");
		myList.add("Total Swap Size: " +  formatNum((double) os.getTotalSwapSpaceSize() / 1073741824) + " GB");
		myList.add("free Swap Size : " + formatNum((double) freeSwapSize / 1073741824) + " GB");

		
		String str = "{totalPhysical: " +  (formatNum((double) physicalMemorySize / 1073741824)) +
				", freePhysical: " + formatNum((double) freePhysicalMemory / 1073741824) + 
				", totalSwap: " + 
				formatNum((double) os.getTotalSwapSpaceSize() / 1073741824) +
				", freeSwap: " + 
				formatNum((double) freeSwapSize / 1073741824) + "}";
				msg.add(str);
		return msg;
	}

	/**
	 * get the CPU usage
	 * 
	 * @param msg
	 * @throws InterruptedException
	 * @throws JsonProcessingException
	 */
	public static Message cpuFunc(Message msg) {

		OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
		// What % CPU load this current JVM is taking, from 0.0-1.0
		// msg.add( "What % CPU load this current JVM is taking : " + formatNum(
		// (double) osBean.getProcessCpuLoad() ) );
		// What % load the overall system is at, from 0.0-1.0
		msg.add("overall" + formatNum((double) osBean.getSystemCpuLoad()));
		return msg;
	}

	/**
	 * get the up time of the system
	 * 
	 * @param msg
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 */
	public static Message upTimeFunc(Message msg) /* throws InterruptedException, FileNotFoundException */ {
		
		
		
/*		 long uptime = -1;
		    String os = System.getProperty("os.name").toLowerCase();
		    if (os.contains("win")) {
		        Process uptimeProc = null;
				try {
					uptimeProc = Runtime.getRuntime().exec("net stats srv");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        BufferedReader in = new BufferedReader(new InputStreamReader(uptimeProc.getInputStream()));
		        String line;
		        try {
					while ((line = in.readLine()) != null) {
					    if (line.startsWith("Statistics since")) {
					        SimpleDateFormat format = new SimpleDateFormat("'Statistics since' MM/dd/yyyy hh:mm:ss a");
					        Date boottime = (Date) format.parse(line);
					        uptime = System.currentTimeMillis() - boottime.getTime();
					        break;
					    }
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    } else if (os.contains("mac") || os.contains("nix") || os.contains("nux") || os.contains("aix")) {
		        Process uptimeProc = null;
				try {
					uptimeProc = Runtime.getRuntime().exec("uptime");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        BufferedReader in = new BufferedReader(new InputStreamReader(uptimeProc.getInputStream()));
		        String line;
				try {
					line = in.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        if (line != null) {
		            Pattern parse = Pattern.compile("((\\d+) days,)? (\\d+):(\\d+)");
		            Matcher matcher = parse.matcher(line);
		            if (matcher.find()) {
		                String _days = matcher.group(2);
		                String _hours = matcher.group(3);
		                String _minutes = matcher.group(4);
		                int days = _days != null ? Integer.parseInt(_days) : 0;
		                int hours = _hours != null ? Integer.parseInt(_hours) : 0;
		                int minutes = _minutes != null ? Integer.parseInt(_minutes) : 0;
		                uptime = (minutes * 60000) + (hours * 60000 * 60) + (days * 6000 * 60 * 24);
		            }
		        }
		    }
		    msg.add( String.valueOf(uptime)); 
		    */

		
		
		
		//System.out.println("Executing Uptime command");
		ProcessBuilder crunchifyProcessBuilder = null;
		boolean waitForResult = true;
		crunchifyProcessBuilder = new ProcessBuilder("/bin/bash", "-c", "uptime");
		crunchifyProcessBuilder.redirectErrorStream(true);
		Writer crunchifyWriter = null;
		try {
			Process process = crunchifyProcessBuilder.start();
			if (waitForResult) {
				InputStream crunchifyStream = process.getInputStream();
 
				if (crunchifyStream != null) {
					crunchifyWriter = new StringWriter();
 
					char[] crunchifyBuffer = new char[2048];
					try {
						Reader crunchifyReader = new BufferedReader(new InputStreamReader(crunchifyStream, "UTF-8"));
						int count;
						while ((count = crunchifyReader.read(crunchifyBuffer)) != -1) {
							crunchifyWriter.write(crunchifyBuffer, 0, count);
						}
					} finally {
						crunchifyStream.close();
					}
					crunchifyWriter.toString();
					crunchifyStream.close();
				}
			}
		} catch (Exception e) {
			System.out.println("Error Executing tcpdump command" + e);
		}
		msg.add(crunchifyWriter.toString());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// for unix, read the uptime from the file uptime
		/*try {
			new Scanner(new FileInputStream("/proc/uptime")).next();
			msg.add(new Scanner(new FileInputStream("/proc/uptime")).next());
		
		} catch (FileNotFoundException e) {
			logger.error(e);
			logger.error("Exception is:", e);
		}*/

		// System.out.println( new Scanner(new FileInputStream("/proc/uptime")).next()
		// );
		// uptime of the Java Virtual Machine in milliseconds.
		// java.lang.management.RuntimeMXBean rb =
		// java.lang.management.ManagementFactory.getRuntimeMXBean();
		// msg.add( "Up time: " + rb.getUptime() + " ms" );
		// System.out.println( "Up time: " + rb.getUptime() + " ms" );
		// return "Up time: " + rb.getUptime() + " ms" ;
		return msg;
	}

	/**
	 * get general data about the system host name , Current IP address , OS name
	 * and version , OS architecture , JVM details ...
	 * 
	 * @param msg
	 */
	public static Message generaFunc(Message msg) {
		ArrayList<String> myList = new ArrayList<>();
		InetAddress ip;
		try {

			ip = InetAddress.getLocalHost();
			// System.out.println( "Current host name : " + ip.getHostName() );
			myList.add("Current host name : " + ip.getHostName());

			// System.out.println( "Current IP address : " + ip.getHostAddress() );
			myList.add("Current IP address : " + ip.getHostAddress());

			String nameOS = System.getProperty("os.name");

			// System.out.println( "Operating system Name=>" + nameOS );
			myList.add("Operating system Name=>" + nameOS);

			String osType = System.getProperty("os.arch");

			// System.out.println( "Operating system type =>" + osType );
			myList.add("Operating system type =>" + osType);

			String osVersion = System.getProperty("os.version");

			// System.out.println( "Operating system version =>" + osVersion + "\n" );
			myList.add("Operating system version =>" + osVersion);

			// System.out.println( System.getenv( "PROCESSOR_IDENTIFIER" ) );
			myList.add(System.getenv("PROCESSOR_IDENTIFIER"));

			// System.out.println( System.getenv( "PROCESSOR_ARCHITECTURE" ) );
			myList.add(System.getenv("PROCESSOR_ARCHITECTURE"));

			// System.out.println( System.getenv( "PROCESSOR_ARCHITEW6432" ) );
			myList.add(System.getenv("PROCESSOR_ARCHITEW6432"));

			// System.out.println( System.getenv( "NUMBER_OF_PROCESSORS" ) );
			myList.add(System.getenv("NUMBER_OF_PROCESSORS"));

			/* Total number of processors or cores available to the JVM */
			// System.out.println( "Available processors (cores): " +
			// Runtime.getRuntime().availableProcessors() );
			// myList.add("Available processors (cores): " +
			// Runtime.getRuntime().availableProcessors());

			/* Total amount of free memory available to the JVM */
			// System.out.println( "Free memory (bytes): " +
			// Runtime.getRuntime().freeMemory() );
			// myList.add("Free memory (bytes): " +
			// Runtime.getRuntime().freeMemory() );

			/*
			 * This will return Long.MAX_VALUE if there is no preset limit long maxMemory =
			 * Runtime.getRuntime().maxMemory(); Maximum amount of memory the JVM will
			 * attempt to use System.out.println( "Maximum memory (bytes): " + (maxMemory ==
			 * Long.MAX_VALUE ? "no limit" : maxMemory) );
			 * 
			 * Total memory currently in use by the JVM System.out.println(
			 * "Total memory (bytes): " + Runtime.getRuntime().totalMemory() );
			 */

			/*
			 * NetworkInterface network = NetworkInterface.getByInetAddress( ip );
			 * 
			 * byte[] mac = network.getHardwareAddress();
			 * 
			 * System.out.print( "Current MAC address : " );
			 * 
			 * StringBuilder sb = new StringBuilder(); for (int i = 0; i < mac.length; i++)
			 * { sb.append( String.format( "%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""
			 * ) ); } System.out.println( sb.toString() );
			 */

		} catch (UnknownHostException e) {
			logger.error(e);
			logger.error("Exception is:", e);
		} catch (Exception e) {
			logger.error(e);
			logger.error("Exception is:", e);
		}
		msg.setMyList(myList);
		return msg;
	}

	/**
	 * helper method : to limit the decimal number
	 * 
	 * @param num
	 * @return num
	 */
	private static double formatNum(double num) {
		DecimalFormat df = new DecimalFormat(".##");
		num = Double.valueOf(df.format(num));
		return num;
	}
}