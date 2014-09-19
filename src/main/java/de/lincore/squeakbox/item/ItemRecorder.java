package de.lincore.squeakbox.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.lincore.squeakbox.Consts;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemRecorder extends Item {
	
	public static final CreativeTabs CREATIVE_TAB = CreativeTabs.tabMisc;
	
	public ItemRecorder(String textureName) {
		super();
		this.setUnlocalizedName(Consts.ITEM_RECORDER_NAME);
		this.setCreativeTab(CREATIVE_TAB);
		this.setMaxStackSize(1);
		this.setTextureName(textureName);
	}
	
	

}
