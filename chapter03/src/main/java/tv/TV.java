package tv;

public class TV {
	private int channel;	// 1 ~ 255
	private int volume;		// 0 ~ 100 로테이션
	private boolean power;	
	
	public TV() {
	}
	
	public TV(int channel, int volume, boolean power) {
		this.channel = channel;
		this.volume = volume;
		this.power = power;
	}
	
	// getter 작성 
	public int getChannel() {
		return channel;
	}

	public int getVolume() {
		return volume;
	}

	public boolean isPower() {
		return power;
	}
	
	
	public void power(boolean on) {
		power = on;
	}
	
	public void channel(boolean up) {
		if(up == true) {
			if(channel >= 255) {
				channel = 1;
			} else {
				channel += 1;
			}
		} else {
			if(channel <= 1) {
				channel = 255;
			} else {
				channel -= 1;
			}
		}
		
	}
	
	public void channel(int channel) {
		if(channel <= 0) {
			channel = 255;
		}
		this.channel = channel;
	}
	
	public void volume(boolean up) {
		if(up == false) {
			if(volume <= 0) {
				volume = 100;
			} else {
				volume -= 1;
			}
		} else {
			if(volume > 100) {
				volume = 0; 
			}else {
				volume += 1;
			}
		}
		
	}
	
	public void volume(int volume) {
		if (volume > 100) {
			volume = 0;
		}
		this.volume = volume;
	}
	
	public void status() {
		System.out.println("TV[power=" + (power ? "on" : "off") + ", channel=" + channel + ", volume=" + volume + "]");
	}
	
}
