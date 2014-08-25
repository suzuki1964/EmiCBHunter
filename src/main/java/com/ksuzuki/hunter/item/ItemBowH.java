package com.ksuzuki.hunter.item;

import com.ksuzuki.hunter.creativetab.CreativeTabH;
import com.ksuzuki.hunter.entity.EntityPoisonArrow;
import com.ksuzuki.hunter.init.ModItems;
import com.ksuzuki.hunter.reference.Reference;
import com.ksuzuki.hunter.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;


import static com.ksuzuki.hunter.init.ModItems.poisonArrow;

/**
 * Created by suzuki on 8/23/14.
 */
public class ItemBowH extends ItemBow
{
    public static final String[] bowPullIconNameArray = new String[] {"pulling_0", "pulling_1", "pulling_2"};

    @SideOnly(Side.CLIENT)
    protected IIcon[] iconArray;

    public ItemBowH()
    {
        this.maxStackSize = 1;
        this.setMaxDamage(384);
        this.setUnlocalizedName("customBow");
        this.setCreativeTab(CreativeTabH.H_tab);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     * changed normal arrow to poison arrow
     */
    @Override
    public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
    {
        int j = this.getMaxItemUseDuration(p_77615_1_) - p_77615_4_;

        ArrowLooseEvent event = new ArrowLooseEvent(p_77615_3_, p_77615_1_, j);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return;
        }
        j = event.charge;

        boolean flag = p_77615_3_.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, p_77615_1_) > 0;

        if (flag || p_77615_3_.inventory.hasItem(poisonArrow))
        {
            float f = (float)j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;

            if ((double)f < 0.1D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            EntityPoisonArrow entityPoisonArrow = new EntityPoisonArrow(p_77615_2_, p_77615_3_, f * 2.0F);

            if (f == 1.0F)
            {
                entityPoisonArrow.setIsCritical(true);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, p_77615_1_);

            if (k > 0)
            {
                entityPoisonArrow.setDamage(entityPoisonArrow.getDamage() + (double)k * 0.5D + 0.5D);
            }

            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, p_77615_1_);

            if (l > 0)
            {
                entityPoisonArrow.setKnockbackStrength(l);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, p_77615_1_) > 0)
            {
                entityPoisonArrow.setFire(100);
            }

            p_77615_1_.damageItem(1, p_77615_3_);
            p_77615_2_.playSoundAtEntity(p_77615_3_, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (flag)
            {
                entityPoisonArrow.canBePickedUp = 2;
            }
            else
            {
                p_77615_3_.inventory.consumeInventoryItem(ModItems.poisonArrow);
            }

            if (!p_77615_2_.isRemote)
            {
                p_77615_2_.spawnEntityInWorld(entityPoisonArrow);
            }
        }
    }

    /*
    public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_)
    {
        return p_77654_1_;
    }
*/

    /**
     * How long it takes to use or consume an item
     */
    /*
    public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return 72000;
    }
*/

    /**
     * returns the action that specifies what animation to play when the items is being used
     */

    /*
    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.bow;
    }
*/

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     * Now checks to see if player has poison arrow
     */

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        ArrowNockEvent event = new ArrowNockEvent(entityPlayer, itemStack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return event.result;
        }

        if (entityPlayer.capabilities.isCreativeMode || entityPlayer.inventory.hasItem(ModItems.poisonArrow))
        {
            entityPlayer.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack
            ));
        }

        return itemStack;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    /*public int getItemEnchantability()
    {
        return 1;
    }*/

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_standby");
        this.iconArray = new IIcon[bowPullIconNameArray.length];

        for (int i = 0; i < this.iconArray.length; ++i) {
            this.iconArray[i] = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_" + bowPullIconNameArray[i]);
        }

    }

    /**
     * used to cycle through icons based on their used duration, i.e. for the bow
     */

    /*
    @SideOnly(Side.CLIENT)
    public IIcon getItemIconForUseDuration(int p_94599_1_)
    {
        return this.iconArray[p_94599_1_];
    }
*/

    /*
     *runs the bow pulling animation
     */

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (usingItem == null) { return itemIcon; }
        int ticksInUse = stack.getMaxItemUseDuration() - useRemaining;
        if (ticksInUse > 17) {
            return iconArray[2];
        } else if (ticksInUse > 13) {
            return iconArray[1];
        } else if (ticksInUse > 0) {
            return iconArray[0];
        } else {
            return itemIcon;
        }
    }


}
