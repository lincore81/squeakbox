package de.lincore.squeakbox.block;

import de.lincore.squeakbox.Consts;
import de.lincore.squeakbox.Squeakbox;
import de.lincore.squeakbox.tileentity.RgbLightTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class RgbLightBlock extends Block implements ITileEntityProvider {
	
	private static final int WHITE_COLOR_INT = 16777215;
	
	public RgbLightBlock() {
		super(Material.glass);
		setBlockName(Consts.BLOCK_RGBLIGHT_NAME);
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(0.4f);
		setStepSound(soundTypeGlass);		
		setBlockTextureName(Consts.BLOCK_RGBLIGHT_TEX);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new RgbLightTileEntity();
	}

	@Override
	public int colorMultiplier(IBlockAccess world, int x,
			int y, int z) {
		RgbLightTileEntity entity = (RgbLightTileEntity)(world.getTileEntity(x, y, z));
		return entity.getColorInt();
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		RgbLightTileEntity entity = (RgbLightTileEntity)(world.getTileEntity(x, y, z));
		return entity.getLightValue();
	}
	
	
	

}
