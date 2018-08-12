package iot;

import automatedUI.KeyEvents;
import java.awt.event.KeyEvent;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import pptiothandler.DefConfig;

public class MqttPPTHandler implements MqttCallback {

	MemoryPersistence persistence = new MemoryPersistence();
	MqttClient sampleClient;
	MqttConnectOptions connOpts;

	// Automated UI
	KeyEvents events;

	public MqttPPTHandler() throws Exception {
		events = new KeyEvents();
		sampleClient = new MqttClient(DefConfig.MQTT_BROKER,
			DefConfig.MQTT_CLIENT_ID, persistence);
		connOpts = new MqttConnectOptions();

		connOpts.setCleanSession(true);
	}

	public void init() throws Exception {
		sampleClient.setCallback(this);
		sampleClient.connect(connOpts);
		sampleClient.subscribe(DefConfig.MQTT_TOPIC);
		this.sendMessage("connected");
	}

	public void sendMessage(String msg) throws MqttException {
		MqttMessage message = new MqttMessage(msg.getBytes());

		sampleClient.publish(DefConfig.MQTT_TOPIC, message);
	}

	public void disconnect() throws MqttException {
		sampleClient.disconnect();
	}

	@Override
	public void connectionLost(Throwable cause) {
		System.out.println(cause.getMessage());
	}

	@Override
	public void messageArrived(String topic, MqttMessage message)
		throws Exception {
		String rec = message.toString();

		System.out.println(rec);

		switch (rec) {
			case "next":
				events.raiseKeyEvent(KeyEvent.VK_PAGE_DOWN);
				break;
			case "preview":
				events.raiseKeyEvent(KeyEvent.VK_PAGE_UP);
				break;
			default:
				System.err.println("Unkown command :: " + rec);
				break;
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("delivery");
	}
}
