package com.nsx.pilotemybox;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;

import com.nsx.pilotemybox.utils.Discovery;
 
public class AndroidDiscovery implements Discovery{
	Context _Context;
	static int DISCOVERY_PORT = 8888;
	List<String> _list;
	boolean _listen;
	
	public AndroidDiscovery(Context context) {
		_Context = context;
		_list = new ArrayList<String>();
		_listen = true;
	}
	
	public List<String> getServerList(){
		return _list;
	}
	
	@Override
	public void getServers() throws IOException {
		System.out.println("LAUching discovery");
		DatagramSocket socket = new DatagramSocket(DISCOVERY_PORT);
		socket.setBroadcast(true);
		String data = "hellow!";
		DatagramPacket packet = new DatagramPacket(data.getBytes(), data.length(),
		    getBroadcastAddress(), DISCOVERY_PORT);
		socket.send(packet);

		byte[] buf = new byte[1024];
		DatagramPacket packet11 = new DatagramPacket(buf, buf.length);
		socket.setSoTimeout(3000);
		System.out.println("receiving packets");
		while(_listen){
			socket.receive(packet11);
			System.out.println("PAcket received: "+packet11.getData()+" from:"+packet11.getAddress());
			_list.add(""+packet11.getAddress().getHostAddress());
		}
	}
	
	InetAddress getBroadcastAddress() throws IOException {
	    WifiManager wifi = (WifiManager) _Context.getSystemService(Context.WIFI_SERVICE);
	    DhcpInfo dhcp = wifi.getDhcpInfo();
	    // handle null somehow

	    int broadcast = (dhcp.ipAddress & dhcp.netmask) | ~dhcp.netmask;
	    byte[] quads = new byte[4];
	    for (int k = 0; k < 4; k++)
	      quads[k] = (byte) ((broadcast >> k * 8) & 0xFF);
	    return InetAddress.getByAddress(quads);
	}

}
