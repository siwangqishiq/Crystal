package com.xinlan.crystal;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import android.os.Bundle;

public class MainActivity extends AndroidApplication {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useWakelock = true;
		config.useAccelerometer = false;
		config.useCompass = false;
		//config.useGL20 = false;
		initialize(new Crystal(), config);
	}
}// end class
