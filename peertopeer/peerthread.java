package com.za.networking.peertopeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.json.Json;
import javax.json.JsonObject;

public class peerthread extends Thread {
	private BufferedReader bufferedreader ;
	public peerthread (Socket socket) throws IOException{
		BufferedReader bufferedreader = new BufferedReader (new InputStreamReader(socket.getInputStream()));
	}
	public void run()
	{
		boolean flag =true ;
		while(flag)
		{try {
			JsonObject jsonobject=Json.createReader(bufferedreader).readObject();
			if (jsonobject.containsKey("Username"))
			System.out.println("["+jsonobject.getString("Username")+"]: "+jsonobject.getString("msg"));
			
			
		
			}catch	(Exception e)
			{flag=false ;
			interrupt();
			}
		}
		}
	}
		