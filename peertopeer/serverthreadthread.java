package com.za.networking.peertopeer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class serverthreadthread extends Thread{
	private ServerSocket serversocket ;
	private Socket socket ;
private PrintWriter printWriter ;
private serverthread serverThread;
public  serverthreadthread(Socket socket ,serverthread serverThread) {
	// TODO Auto-generated constructor stub
	this.serverThread=serverThread ;
	this.socket=socket;
	
}
public void run () {
	try { BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(this.socket.getInputStream())) ;
	this.printWriter =new PrintWriter(socket.getOutputStream(),true);
	while(true) serverthread.sendMsg(bufferedReader.readLine());
	}catch(Exception e) {	serverthread.getserverthreadthreads().remove(this);
	
	}
	
	
	
}
public void add(serverthreadthread Serverthreadthread) {
	// TODO Auto-generated method stub
	
}
public PrintWriter getPrintWriter() {
	// TODO Auto-generated method stub
	return printWriter;}

}
