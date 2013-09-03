package com.nsx.pilotemybox.utils;

import java.io.IOException;
import java.util.List;

public interface Discovery {
	void getServers() throws IOException;
	public List<String> getServerList();
}
