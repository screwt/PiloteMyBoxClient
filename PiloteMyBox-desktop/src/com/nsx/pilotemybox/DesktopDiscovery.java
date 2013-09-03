package com.nsx.pilotemybox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.nsx.pilotemybox.utils.Discovery;

public class DesktopDiscovery implements Discovery{

	@Override
	public List<String> getServerList() {
		List<String> lst = new ArrayList<String>(); 
		lst.add("192.168.0.11");
		lst.add("192.168.0.14");
		lst.add("192.168.0.10");
		lst.add("127.0.0.1");
		return lst;
	}

	@Override
	public void getServers() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
