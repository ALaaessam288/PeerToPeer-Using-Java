package com.za.networking.peertopeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.Socket;
import java.io.*;

import javax.json.Json;

public class peer {

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedreader = new BufferedReader (new InputStreamReader(System.in));
		System.out.println(" enter the Username & Port # for this peer ");
	String [] setupValues = bufferedreader.readLine().split(" ") ;	
	serverthread ServerThread =new serverthread(setupValues	[1]);
	ServerThread.start();
	new peer().updatelistentopeers(bufferedreader, setupValues[0], ServerThread);
	}
public void updatelistentopeers(BufferedReader bufferedReader ,String Username ,serverthread ServerThread) throws Exception{
	System.out.println(" enter (space separated ) #hostname:Port-");
	System.out.println(" peers to recieve msgs from (S to skip ) :");
	String input = bufferedReader.readLine();
	String[] inputvalues= input.split(" ");
	if(!input.equals('s')) for(int i=0; i<inputvalues.length ;i++)
	{String[] adress=inputvalues[i].split(":");
	Socket socket= null;
	try { 
		socket =new Socket(adress[0],Integer.valueOf(adress[1]));
	new peerthread(socket).start();
	}catch (Exception e) {
		if (socket != null) socket.close();
		else System.out.println("invalid input. skipping to next step");
		// TODO: handle exception
	}
	}

  communicate( bufferedReader , Username , ServerThread);
}
private void communicate(BufferedReader bufferedReader, String username, serverthread serverThread) {
	// TODO Auto-generated method stub
	try {
		System.out.println(">u can now communicate ( e to exit , c to change)");
		boolean flag= true ;
		while(flag)
		{
			String msg=bufferedReader.readLine();
			
			if(msg.equals("e"))
			{flag=false ;
			break;
			}else if(msg.equals("c"))
			{
				updatelistentopeers(bufferedReader, username, serverThread);
			}
			else {
				StringWriter stringwriter =new StringWriter() ;
				Json.createWriter(stringwriter).writeObject(Json.createObjectBuilder().add("username",username)
						.add("msg",msg)
						.build());
				serverThread.sendMsg(stringwriter.toString());
				
			}
		}
		System.exit(0);
	}catch(Exception e) {}
	
}
}
