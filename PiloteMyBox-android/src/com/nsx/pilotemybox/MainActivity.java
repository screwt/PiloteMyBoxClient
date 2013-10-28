package com.nsx.pilotemybox;

import java.io.IOException;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        
        final AndroidDiscovery ad = new AndroidDiscovery(this.getApplicationContext());
        
        Runnable n= (new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					ad.getServers();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        	
        });
        
        new Thread(n).start();
        
        initialize(new PiloteMyBox(ad), cfg);
    }
}