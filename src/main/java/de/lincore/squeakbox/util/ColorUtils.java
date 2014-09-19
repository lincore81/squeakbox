package de.lincore.squeakbox.util;

public class ColorUtils {	
	public static final int RGB_WHITE = 16_777_215;
	
	private ColorUtils(){}

	public static int calcLightValueRGB(int red, int green, int blue) {		
		float brightness = (2*red + blue + 3*green) / (float)(6 * 255);
		
		//!DEBUG
		assert brightness >= 0f && brightness <= 1f;
		
		return (int)Math.floor(brightness * 16f);
	}	
	
	public static int getColorInt(int red, int green, int blue) {
		red   = clampColorByte(red);
		green = clampColorByte(green);
		blue  = clampColorByte(blue);
		return red + (green << 8) + (blue << 16);
	}	
	
	public static int[] getColorChannels(int color) {		
		color = clampColorInt(color);
		int red, green, blue;
		blue  = color >> 16;
		green = (color - (blue << 16)) >> 8;
		red = color - (blue << 16) - (green << 8);
		
		//!DEBUG
		assert  red   >= 0 && red   <= 255 && 
				green >= 0 && green <= 255 && 
				blue  >= 0 && blue  <= 255;
				
		return new int[]{red, green, blue};
	}	
	
	
	public static int clampColorInt(int color) {
		return (color < 0)? 0 : (color > RGB_WHITE)? RGB_WHITE : color;
	}	
	
	public static int clampColorByte(int value) {
		return (value < 0)? 0 : (value > 255)? 255 : value;
	}
		
	
	
}
