package com.nsx.pilotemybox.utils;

import java.net.SocketAddress;

public class MyMessage {
	String _Mess;
	String _ServerAdress;
	int _ServerPort;
	
	public int get_ServerPort() {
		return _ServerPort;
	}


	public void set_ServerPort(int _ServerPort) {
		this._ServerPort = _ServerPort;
	}


	public MyMessage(String serverAdress,int port, String mess){
		_Mess = mess;
		_ServerAdress = serverAdress;
		_ServerPort = port;
	}
	
	
	public String get_Mess() {
		return _Mess;
	}

	public void set_Mess(String _Mess) {
		this._Mess = _Mess;
	}
	
	public String get_ServerAdress() {
		return (_ServerAdress);
	}

	public void set_ServerAdress(String _ServerAdress) {
		this._ServerAdress = _ServerAdress;
	}


	
	
}
