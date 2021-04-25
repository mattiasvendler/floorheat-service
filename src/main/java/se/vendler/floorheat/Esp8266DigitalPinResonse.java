package se.vendler.floorheat;

public class Esp8266DigitalPinResonse {
	private int return_value;
	private int id;
	private String name;
	private String hardware;
	private boolean connected;
	
	public Esp8266DigitalPinResonse() {
	}
	
	public Esp8266DigitalPinResonse(int return_value, int id, String name, String hardware, boolean connected) {
		this.return_value = return_value;
		this.id = id;
		this.name = name;
		this.hardware = hardware;
		this.connected = connected;
	}
	
	public int getReturn_value() {
		return return_value;
	}
	
	public void setReturn_value(int return_value) {
		this.return_value = return_value;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getHardware() {
		return hardware;
	}
	
	public void setHardware(String hardware) {
		this.hardware = hardware;
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
}
