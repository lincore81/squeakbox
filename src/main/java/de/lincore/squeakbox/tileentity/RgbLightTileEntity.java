package de.lincore.squeakbox.tileentity;

import org.apache.logging.log4j.Logger;

import de.lincore.squeakbox.Squeakbox;
import de.lincore.squeakbox.util.ColorUtils;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class RgbLightTileEntity extends TileEntity {
	private static final String NBT_RGB_ATTR = "rgb";
	public static Logger logger = Squeakbox.logger;
	
	private int red, green, blue;
	private int colorInt;
	private int lightValue; // 0 - 15	
	
	
	
	public RgbLightTileEntity() {
		this(255, 255, 255);
	}
	
	public RgbLightTileEntity(int red, int green, int blue) {
		setColor(red, green, blue);
	}
	
	public void setColor(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		
		colorInt = ColorUtils.getColorInt(red, green, blue);
		lightValue = ColorUtils.calcLightValueRGB(red, green, blue);
		logger.debug(String.format("setColor(%d, %d, %d): colorRgb=%d, lightValue=%d", 
				red, green, blue, colorInt, lightValue));
		markDirty();
	}
	
	public void setColorInt(int color) {
		// 0 <= color <= RGB_WHITE:
		color = Math.max(0, Math.min(color, ColorUtils.RGB_WHITE));
		
		int[] rgb = ColorUtils.getColorChannels(color);
		this.red = rgb[0];
		this.green = rgb[1];
		this.blue = rgb[2];
		colorInt = color;
		lightValue = ColorUtils.calcLightValueRGB(red, green, blue);
	}
	
	public int getColorInt() {
		return colorInt;
	}
	
	public int getLightValue() {
		return lightValue;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {		
		setColorInt(compound.getInteger(NBT_RGB_ATTR));
		super.readFromNBT(compound);
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		compound.setInteger(NBT_RGB_ATTR, lightValue);
		super.writeToNBT(compound);
	}
	
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound compound = new NBTTagCompound();
	    writeToNBT(compound);
	    return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, compound);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		NBTTagCompound compound = packet.func_148857_g();
        readFromNBT(compound);
	}
	
	
}
