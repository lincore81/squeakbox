package de.lincore.squeakbox;

import org.apache.logging.log4j.Logger;

import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import de.lincore.squeakbox.block.RgbLightBlock;
import de.lincore.squeakbox.item.ItemRecorder;
import de.lincore.squeakbox.proxy.CommonProxy;
import de.lincore.squeakbox.tileentity.RgbLightTileEntity;

@Mod(modid=Consts.MOD_ID, name=Consts.MOD_NAME, version=Consts.MOD_VERSION)
public class Squeakbox {	
	public static Logger logger;
	
	@Mod.Instance
	static Squeakbox instance;
	
	@SidedProxy(clientSide = Consts.CLIENT_PROXY, serverSide = Consts.SERVER_PROXY)
	static CommonProxy proxy;
	
	public ItemRecorder itemRecorder;
	public RgbLightBlock rgblight;

	@Mod.EventHandler()
	public boolean onPreInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		logger.info("pre init.");
		MinecraftForge.EVENT_BUS.register(this);
		
		return false;
	}
	
	
	@Mod.EventHandler()
	public boolean onInit(FMLInitializationEvent event) {
		logger.info("init");
		
		itemRecorder = new ItemRecorder(Consts.ITEM_RECORDER_TEX);
		rgblight = new RgbLightBlock();				
		
		GameRegistry.registerItem(itemRecorder, Consts.ITEM_RECORDER_ID);
		GameRegistry.registerBlock(rgblight, Consts.BLOCK_RGBLIGHT_ID);
		GameRegistry.registerTileEntity(RgbLightTileEntity.class, Consts.TILE_ENTITY_RGBLIGHT_ID);
		return false;
	}
	
	@SubscribeEvent
	public void onSoundPlaying(PlaySoundEvent17 event) {		
		logger.info("Playing sound: " + event.name);
	}

}
