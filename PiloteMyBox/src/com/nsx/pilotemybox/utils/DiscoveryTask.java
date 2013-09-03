package com.nsx.pilotemybox.utils;

import java.io.IOException;
import java.util.List;

import android.os.AsyncTask;

public class DiscoveryTask extends AsyncTask<Discovery, Void, List<String> > {
	List<String> _Servers;

	public DiscoveryTask(List<String> l){
		_Servers = l;
	}
	
	@Override
	protected List<String> doInBackground(Discovery... params) {
		// TODO Auto-generated method stub

		try {
			//_Servers = 
				params[0].getServers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _Servers;
	}
	
	@Override
    protected void onPostExecute(List<String> l) {
        System.out.println("Done " + l);
    }

}
