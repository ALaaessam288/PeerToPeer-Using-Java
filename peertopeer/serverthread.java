package com.za.networking.peertopeer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Set;

public class serverthread extends Thread {
	private ServerSocket serversocket ;
	private static Set<serverthreadthread> serverthreadthreads ;
	public serverthread(String Portnumber) throws IOException {
	
	serversocket= new ServerSocket(Integer .valueOf(Portnumber)) ;
	
	}
	public void run() {
		try {
			while(true)
			{serverthreadthread Serverthreadthread=new serverthreadthread(serversocket.accept(), this);
			 
			Serverthreadthread.add(Serverthreadthread);
			Serverthreadthread.start();
			}
		}catch(Exception e) {e.printStackTrace(); }
		
		
	}
	static void sendMsg(String Msg)
	{
		try {serverthreadthreads.forEach(t-> t.getPrintWriter().println(Msg));
	}
		catch(Exception e)
		{e.printStackTrace();
		}
		}
public static Set<serverthreadthread> getserverthreadthreads(){
	return serverthreadthreads ;
}
}
