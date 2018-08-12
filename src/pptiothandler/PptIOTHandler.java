package pptiothandler;

import iot.MqttPPTHandler;

public class PptIOTHandler {

	/**
	 * @param args the command line arguments
	 * @throws java.lang.Exception
	 */
	public static void main(String[] args) throws Exception {
		MqttPPTHandler handler = new MqttPPTHandler();
		handler.init();
	}
}
