package com.sanqiu.rustv2.model;

import com.sanqiu.rustv2.RustV2;
import com.sanqiu.rustv2.data.RustData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
class Goods{
    public  ItemStack goods;
    public ItemStack money;
    public Goods(ItemStack goods,ItemStack money){
        this.goods = goods;
        this.money = money;
    }
}
class GoodsData{
    private static List<Goods> list = new ArrayList<>();
    public static List<Goods> getList(){
        Goods goods1 =  new Goods(new ItemStack(Material.RED_BED),new ItemStack(RustData.waste,10));
        Goods goods2 =  new Goods(new ItemStack(Material.BLUE_BED),new ItemStack(RustData.waste,20));
        Goods goods3 =  new Goods(new ItemStack(Material.BLACK_BED),new ItemStack(RustData.waste,30));
        Goods goods4 =  new Goods(new ItemStack(Material.YELLOW_BED),new ItemStack(RustData.waste,40));
        Goods goods5 =  new Goods(new ItemStack(Material.GRAY_BED),new ItemStack(RustData.waste,50));
        Goods goods6 =  new Goods(new ItemStack(Material.GREEN_BED),new ItemStack(RustData.waste,60));
        list.add(goods1);
        list.add(goods2);
        list.add(goods3);
        list.add(goods4);
        list.add(goods5);
        list.add(goods6);
        return list;
    }
}
public class VendingMachine {
    private static final String menuTitle = "自动贩卖机";
    private static int material_num = 54;
    private static final Material VendingMachineType = Material.GRINDSTONE;
    public static void OpenMenu(Player player){
        Inventory inventory = Bukkit.createInventory(null , material_num,menuTitle);
        List<Goods> list = GoodsData.getList();
        int goodsList_index = 0;
        for(int i=0;i<inventory.getSize();i++){
            int num = i%9;
            if(num ==1){
                inventory.setItem(i,list.get(goodsList_index).goods);
            }else if(num == 3){
                inventory.setItem(i,list.get(goodsList_index).money);
            }else if(num == 6){
                inventory.setItem(i,new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
                goodsList_index++;
            }else {
                inventory.setItem(i,new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
            }

        }
        player.openInventory(inventory);
    }
    public static  void OperateMenu(InventoryClickEvent event){
        HumanEntity entity =  event.getWhoClicked();
        if(!(entity instanceof Player)) return;
        event.setCancelled(true);
        int slot = event.getRawSlot();


    }
    public static boolean isVendingMachine(InventoryView view){

        return view.getTitle().equals(menuTitle) ;
    }
    public  static  boolean isVendingMachine(Block block){
        return block.getType() == VendingMachineType;
    }

}
