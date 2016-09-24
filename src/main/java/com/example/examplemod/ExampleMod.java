package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";
    public static Block testBlock;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        testBlock = new TestBlock();
        ResourceLocation registryName = new ResourceLocation(MODID, "testBlock");
        ItemBlock testItemBlock = new ItemBlock(testBlock);
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(MODID, "testBlock");

        GameRegistry.register(testBlock,     registryName);
        GameRegistry.register(testItemBlock, registryName);

        //レシピ追加
        //土９個でダイヤ一個
        GameRegistry.addRecipe(new ItemStack(Items.diamond),
                "###",
                "###",
                "###",
                '#',Blocks.dirt);

        //還元レシピ（不定形）
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.dirt, 9),
                new ItemStack(Items.diamond));
//        GameRegistry.addRecipe(new ItemStack(Blocks.dirt, 9),
//                "#",
//                '#',Items.diamond);

        if (event.getSide().isClient()) {
            ModelLoader.setCustomModelResourceLocation(testItemBlock,0,modelResourceLocation);
        }
    }
}

class TestBlock extends Block {
    public TestBlock() {
        super(Material.rock);
        setCreativeTab(CreativeTabs.tabBlock);
        setUnlocalizedName("testBlock");
    }
}
