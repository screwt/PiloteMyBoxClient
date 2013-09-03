package com.nsx.pilotemybox.utils;

import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import com.nsx.pilotemybox.PiloteMyBox;

import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.content.Context;

public class SendMessageTask  extends Thread {
	MyMessage _m;
	PiloteMyBox _Pmb;
	
	public SendMessageTask(MyMessage m, PiloteMyBox pmb){
		_m = m;
		_Pmb = pmb;
	}

	public SendMessageTask(MyMessage m, Context context) {
		_m = m;

	}

	public void run () {
		System.out.println("Sending message: "+_m.get_Mess()+"To: "+_m.get_ServerAdress());
		try {
		      Socket socket = new Socket(_m.get_ServerAdress(), _m.get_ServerPort());
		      socket.setTcpNoDelay(true);
		      socket.setSoTimeout(500);
		      socket.getOutputStream().write(_m.get_Mess().getBytes());
		      socket.getOutputStream().flush();
		      socket.close();
		    } catch(Exception e) {
		      System.out.println( "couldn't connect ");
		      e.printStackTrace();
		      _Pmb.setScreen(PiloteMyBox.CONNECT_SCREEN,"");
		    }
	}
}
