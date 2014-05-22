package app;

import contoller.WaveLetManager;

public class WaveLetDemo {

	static WaveLetManager manager = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		manager = new WaveLetManager();
		manager.init();

	}

}
