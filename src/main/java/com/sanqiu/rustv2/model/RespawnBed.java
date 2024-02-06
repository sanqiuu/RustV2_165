package com.sanqiu.rustv2.model;

import com.jeff_media.morepersistentdatatypes.DataType;
import com.sanqiu.rustv2.RustV2;
import org.apache.commons.lang.SerializationUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.TileState;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static com.sanqiu.rustv2.model.BedsUtils.checksIfBedExists;
import static com.sanqiu.rustv2.model.BedsUtils.getMaxNumberOfBeds;
import static com.sanqiu.rustv2.model.PlayerUtils.*;

class BedsUtils{
    static RustV2 plugin = RustV2.getPlugin();
    public static void removePlayerBed(String bedUUID, Player p){
        PersistentDataContainer playerData = p.getPersistentDataContainer();
        // checks to see if player has beds
        if (playerData.has(new NamespacedKey(plugin, "beds"), new BedsDataType())) {
            PlayerBedsData playerBedsData = playerData.get(new NamespacedKey(plugin, "beds"), new BedsDataType());
            HashMap<String, BedData> beds = playerBedsData.getPlayerBedData();
            if (beds.containsKey(bedUUID)){
                BedData bedData = beds.get(bedUUID);
                playerBedsData.removeBed(bedUUID);
                playerData.set(new NamespacedKey(plugin, "beds"), new BedsDataType(), playerBedsData);

                World world = Bukkit.getWorld(bedData.getBedWorld());
                String loc[] = bedData.getBedCoords().split(":");
                Location locBed = new Location(world, Double.parseDouble(loc[0]), Double.parseDouble(loc[1]),Double.parseDouble(loc[2]));
                Block bed = world.getBlockAt(locBed);
                if (bed.getBlockData() instanceof Bed ){
                    Bed bedPart = (Bed) bed.getBlockData();
                    // since the data is in the head we need to set the Block bed to its head
                    if (bedPart.getPart().toString()=="FOOT"){
                        bed = (Block) bed.getRelative(bedPart.getFacing());
                    }
                }
                BlockState blockState = bed.getState();
                if (blockState instanceof TileState ){
                    TileState tileState  = (TileState) blockState;
                    PersistentDataContainer container = tileState.getPersistentDataContainer();
                    container.remove(new NamespacedKey(plugin, "uuid"));
                    tileState.update();
                }

            }
        }
    }
    public static boolean checksIfBedExists(Location locBed, Player p, String bedUUID, String worldString){
        World world = Bukkit.getWorld(worldString);
        Block bed = world.getBlockAt(locBed);
        boolean isBed = false;
        if (bed.getBlockData() instanceof Bed ){
            Bed bedPart = (Bed) bed.getBlockData();
            // since the data is in the head we need to set the Block bed to its head
            if (bedPart.getPart().toString()=="FOOT"){
                bed = (Block) bed.getRelative(bedPart.getFacing());
            }
            isBed = true;
        }

        if (!isBed){

            removePlayerBed(bedUUID, p);
            return false;

        }else{

            BlockState blockState = bed.getState();
            if (blockState instanceof TileState ){
                TileState tileState  = (TileState) blockState;
                PersistentDataContainer container = tileState.getPersistentDataContainer();
                String uuid = container.get(new NamespacedKey(plugin, "uuid"), PersistentDataType.STRING);

                if (container==null || uuid==null || !uuid.equalsIgnoreCase(bedUUID)){
                    removePlayerBed(bedUUID, p);
                    return false;
                }

            }

        }

        return true;
    }

    public static Block checkIfIsBed(Block block){
        if (block!= null && block.getBlockData() instanceof Bed ){
            Bed bedPart = (Bed) block.getBlockData();
            // since the data is in the head we need to set the Block bed to its head
            if (bedPart.getPart().toString()=="FOOT"){
                block = block.getRelative(bedPart.getFacing());
            }
            return block;
        }
        return null;
    }

    public static int getMaxNumberOfBeds(Player player){
        int maxBeds = RespawnBed.max_beds;
        int maxBedsByPerms = 0;
        if (player.hasPermission("multiplebedspawn.maxcount")){
            for (PermissionAttachmentInfo perm : player.getEffectivePermissions()){
                String permName = perm.getPermission();
                if (permName.contains("multiplebedspawn.maxcount.") && perm.getValue()){
                    String maxCount = (permName.split("multiplebedspawn.maxcount."))[1].trim();
                    try{
                        int max = Integer.parseInt(maxCount);
                        if (max>53) {
                            plugin.getLogger().warning("Permission "+permName+" is invalid! Should be lower than 53. Value defaulted to 53, please remove this permission. Warning triggered by player "+player.getName());
                            max = 53;
                        }
                        if (max>maxBedsByPerms){
                            maxBedsByPerms = max;
                        }
                    }catch (Exception err){
                        plugin.getLogger().warning("Permission "+permName+" is invalid! Should be a number after 'maxcount.'. Warning triggered by player "+player.getName());
                    }
                }
            }
        }
        if (maxBedsByPerms>0){
            maxBeds = maxBedsByPerms;
        }
        return maxBeds;
    }
}

class PlayerUtils {

    static RustV2 plugin = RustV2.getPlugin();

    public static String locationToString(Location loc){
        return loc.getX()+":"+loc.getY()+":"+loc.getZ();
    }

    public static void setPropPlayer(Player p){

        PersistentDataContainer playerData = p.getPersistentDataContainer();
        if (!playerData.has(new NamespacedKey(plugin, "hasProp"), DataType.BOOLEAN)) {
            p.setInvulnerable(true);

            playerData.set(new NamespacedKey(plugin, "isInvisible"), DataType.BOOLEAN, p.isInvisible());
            p.setInvisible(true);

            playerData.set(new NamespacedKey(plugin, "canPickupItems"), DataType.BOOLEAN, p.getCanPickupItems());
            p.setCanPickupItems(false);

            if (RespawnBed.spawn_on_sky) {
                playerData.set(new NamespacedKey(plugin, "allowFly"), DataType.BOOLEAN, p.getAllowFlight());
                playerData.set(new NamespacedKey(plugin, "isFlying"), DataType.BOOLEAN, p.isFlying());
                p.setAllowFlight(true);
                p.setFlying(true);
            }
            playerData.set(new NamespacedKey(plugin, "lastWalkspeed"), PersistentDataType.FLOAT, p.getWalkSpeed());
            p.setWalkSpeed(0);

            playerData.set(new NamespacedKey(plugin, "hasProp"), DataType.BOOLEAN, true);
        }
    }

    public static void undoPropPlayer(Player p){

        PersistentDataContainer playerData = p.getPersistentDataContainer();
        if (playerData.has(new NamespacedKey(plugin, "hasProp"), DataType.BOOLEAN)) {

            p.setInvulnerable(false);
            p.setInvisible(playerData.get(new NamespacedKey(plugin, "isInvisible"), DataType.BOOLEAN));
            p.setCanPickupItems(playerData.get(new NamespacedKey(plugin, "canPickupItems"), DataType.BOOLEAN));

            playerData.remove(new NamespacedKey(plugin, "isInvisible"));
            playerData.remove(new NamespacedKey(plugin, "canPickupItems"));

            p.setWalkSpeed(playerData.get(new NamespacedKey(plugin, "lastWalkspeed"), PersistentDataType.FLOAT));
            playerData.remove(new NamespacedKey(plugin, "lastWalkspeed"));


            if (RespawnBed.spawn_on_sky) {
                p.setAllowFlight(playerData.get(new NamespacedKey(plugin, "allowFly"), DataType.BOOLEAN));
                p.setFlying(playerData.get(new NamespacedKey(plugin, "isFlying"), DataType.BOOLEAN));

                playerData.remove(new NamespacedKey(plugin, "allowFly"));
                playerData.remove(new NamespacedKey(plugin, "isFlying"));
            }

            playerData.remove(new NamespacedKey(plugin, "hasProp"));

            p.closeInventory();
        }

    }

    public static void teleportPlayer(Player p, PersistentDataContainer data, PersistentDataContainer playerData, PlayerBedsData playerBedsData, String uuid){
        boolean isOkayToTP = true;

        if (data.has(new NamespacedKey(plugin, "cooldown"), PersistentDataType.LONG) && data.has(new NamespacedKey(plugin, "uuid"), PersistentDataType.STRING)){

            long cooldown = data.get(new NamespacedKey(plugin, "cooldown"), PersistentDataType.LONG);
            if (cooldown>System.currentTimeMillis()){
                isOkayToTP = false;
            }

        }

        if (isOkayToTP) {
            HashMap<String, BedData> beds = playerBedsData.getPlayerBedData();
            undoPropPlayer(p);
            String loc[] = beds.get(uuid).getBedSpawnCoords().split(":");
            World world = Bukkit.getWorld(beds.get(uuid).getBedWorld());
            Location locSpawn = new Location(world, Double.parseDouble(loc[0]), Double.parseDouble(loc[1]),Double.parseDouble(loc[2]));
            if (!p.hasPermission("multiplebedspawn.skipcooldown")) {
                beds.get(uuid).setBedCooldown(System.currentTimeMillis() + (RespawnBed.bed_cooldown * 1000L));
            }
            playerData.set(new NamespacedKey(plugin, "beds"), new BedsDataType(), playerBedsData);
            playerData.remove(new NamespacedKey(plugin, "spawnLoc"));
            p.teleport(locSpawn);
        }
    }

    public static Integer getPlayerBedsCount(Player p){
        PersistentDataContainer playerData = p.getPersistentDataContainer();
        PlayerBedsData playerBedsData = null;
        AtomicInteger playerBedsCount = new AtomicInteger();
        playerBedsCount.set(0);
        if (playerData.has(new NamespacedKey(plugin, "beds"), new BedsDataType())) {
            playerBedsData = playerData.get(new NamespacedKey(plugin, "beds"), new BedsDataType());
            if (playerBedsData != null && playerBedsData.getPlayerBedData() != null) {
                HashMap<String, BedData> beds = playerBedsData.getPlayerBedData();
                if (!RespawnBed.link_worlds) {
                    HashMap<String, BedData> bedsT = (HashMap<String, BedData>) beds.clone();
                    beds.forEach((uuid, bedData) -> {
                        // clear lists so beds are only from the world that player is in
                        if (!bedData.getBedWorld().equalsIgnoreCase(p.getWorld().getName())) {
                            bedsT.remove(uuid);
                        }
                    });
                    beds = bedsT;
                }
                playerBedsCount.set(beds.size());
                beds.forEach((uuid, bedData) -> {
                    String[] location = bedData.getBedCoords().split(":");
                    Location bedLoc = new Location(p.getWorld(), Double.parseDouble(location[0]), Double.parseDouble(location[1]), Double.parseDouble(location[2]));
                    if(!checksIfBedExists(bedLoc, p, uuid, bedData.getBedWorld())){
                        playerBedsCount.addAndGet(-1);
                    }
                });

            }
        }
        return playerBedsCount.get();
    }

}

class BedData implements Serializable {

    private String bedName;
    private Material bedMaterial;
    private String bedCoords;
    private String bedSpawnCoords;
    private String bedWorld;
    private long bedCooldown = 0;

    public BedData(Block bed, Player p){
        this.bedMaterial = bed.getType();
        this.bedCoords = locationToString(bed.getLocation());
        this.bedSpawnCoords = locationToString(p.getLocation());
        this.bedWorld = bed.getWorld().getName();
    }

    public String getBedName() {
        return bedName;
    }

    public void setBedName(String bedName) {
        this.bedName = bedName;
    }

    private String locationToString(Location loc){
        return loc.getX()+":"+loc.getY()+":"+loc.getZ();
    }

    public Material getBedMaterial() {
        return bedMaterial;
    }

    public String getBedCoords() {
        return bedCoords;
    }

    public String getBedSpawnCoords() {
        return bedSpawnCoords;
    }

    public String getBedWorld() {
        return bedWorld;
    }

    public void setBedWorld(String bedWorld) {
        this.bedWorld = bedWorld;
    }

    public long getBedCooldown(){
        return bedCooldown;
    }

    public void setBedCooldown(long cooldown){
        this.bedCooldown = cooldown;
    }

}
class BedDataType implements PersistentDataType<byte[], BedData> {

    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<BedData> getComplexType() {
        return BedData.class;
    }

    @Override
    public byte[] toPrimitive(BedData complex, PersistentDataAdapterContext context) {
        return SerializationUtils.serialize(complex);
    }

    @Override
    public BedData fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        try {
            InputStream is = new ByteArrayInputStream(primitive);
            ObjectInputStream o = new ObjectInputStream(is);
            return (BedData) o.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

}
class BedsDataType implements PersistentDataType<byte[], PlayerBedsData> {

    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<PlayerBedsData> getComplexType() {
        return PlayerBedsData.class;
    }

    @Override
    public byte[] toPrimitive(PlayerBedsData complex, PersistentDataAdapterContext context) {
        return SerializationUtils.serialize(complex);
    }

    @Override
    public PlayerBedsData fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        try {
            InputStream is = new ByteArrayInputStream(primitive);
            ObjectInputStream o = new ObjectInputStream(is);
            return (PlayerBedsData) o.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

}
class PlayerBedsData implements Serializable {

    private HashMap<String, BedData> bedData = new HashMap<String, BedData>();;

    public PlayerBedsData(Player p, Block bed, String bedUUID){
        BedData tempBedData = new BedData(bed, p);
        this.bedData.put(bedUUID, tempBedData);
    }

    public void setNewBed(Player p, Block bed, String bedUUID){
        BedData tempBedData = new BedData(bed, p);
        this.bedData.put(bedUUID, tempBedData);
    }

    public void removeBed(String bedUUID){
        bedData.remove(bedUUID);
    }

    public boolean hasBed(String bedUUID){
        return bedData.containsKey(bedUUID);
    }

    public HashMap<String, BedData> getPlayerBedData(){
        return bedData;
    }

}

public class RespawnBed {
    public static boolean link_worlds = true;
    public static boolean remove_beds_gui = false;
    public static boolean disable_bed_world_desc = true;
    public static boolean disable_bed_coords_desc = false;
    public static boolean spawn_on_sky = true;
    public static boolean exclusive_bed = false;
    public static boolean bed_sharing_bed = false;

    public static int max_beds= 5;
    public static boolean disable_sleeping= true;
    public static int bed_cooldown= 30;

    public static void updateItens(Inventory gui, Player p){

        if (gui.getViewers().toString().length()>2){

            ItemStack itens[] = gui.getContents();
            boolean hasActiveCooldown = false;
            for (ItemStack item : itens){

                if (item!=null && item.hasItemMeta()){

                    ItemMeta item_meta = item.getItemMeta();
                    PersistentDataContainer data = item_meta.getPersistentDataContainer();

                    if (data.has(new NamespacedKey(plugin, "cooldown"), PersistentDataType.LONG) && data.has(new NamespacedKey(plugin, "uuid"), PersistentDataType.STRING)){

                        long cooldown = data.get(new NamespacedKey(plugin, "cooldown"), PersistentDataType.LONG);
                        String uuid = data.get(new NamespacedKey(plugin, "uuid"), PersistentDataType.STRING);
                        List<String> lore = item_meta.getLore();

                        int optionsCount = 2;
                        if (RespawnBed.disable_bed_world_desc) {
                            optionsCount--;
                        }
                        if (RespawnBed.disable_bed_coords_desc) {
                            optionsCount--;
                        }
                        if (cooldown>System.currentTimeMillis()){
                            hasActiveCooldown = true;
                            long sec = ( cooldown - System.currentTimeMillis() ) / 1000;
                            String seconds = Long.toString(sec);
                            if (lore == null){
                                lore = new ArrayList<>();
                            }
                            if (lore.size()>optionsCount) {
                                lore.set(
                                        optionsCount,
                                        ChatColor.GOLD+""+ChatColor.BOLD+"冷却: {1}".replace("{1}", seconds)
                                );
                            }else {
                                lore.add(
                                        ChatColor.GOLD+""+ChatColor.BOLD+"冷却: {1}".replace("{1}", seconds)
                                );
                            }
                        }else{
                            if (lore.size()>optionsCount) {
                                lore.remove(optionsCount);
                            }
                        }

                        item_meta.setLore(lore);
                        item.setItemMeta(item_meta);
                    }
                }
            }

            if (hasActiveCooldown){
                Bukkit.getScheduler().runTaskLater(plugin, () -> {updateItens(gui, p);}, 10L);
            }

        }

    }
    public static void openRespawnMenu(Player p){
        RustV2 plugin = RustV2.getPlugin();
        // gets how much beds player has to use on for loop and for the if check
        PersistentDataContainer playerData = p.getPersistentDataContainer();
        PlayerBedsData playerBedsData = null;

        int playerBedsCount = getPlayerBedsCount(p);

        if (playerData.has(new NamespacedKey(plugin, "beds"), new BedsDataType())) {
            playerBedsData = playerData.get(new NamespacedKey(plugin, "beds"), new BedsDataType());
        }

        // if the player doesnt have any beds than dont open menu
        if (playerBedsCount>0){

            // sets stuff to player be invul and invis on spawn
            setPropPlayer(p);

            // create inventory
            int bedCount = playerBedsCount+1;
            Inventory gui = Bukkit.createInventory(p, 9 * ( (int) Math.ceil( bedCount / (Double) 9.0 ) ), ChatColor.translateAlternateColorCodes('&', "床"));

            HashMap<String, BedData> beds = playerBedsData.getPlayerBedData();
            if (!RespawnBed.link_worlds) {
                HashMap<String, BedData> bedsT = (HashMap<String, BedData>) beds.clone();
                beds.forEach((uuid, bed) -> {
                    // clear lists so beds are only from the world that player is in
                    if (!bed.getBedWorld().equalsIgnoreCase(p.getWorld().getName())) {
                        bedsT.remove(uuid);
                    }
                });
                beds = bedsT;
            }
            AtomicBoolean hasCooldown = new AtomicBoolean(false);
            AtomicInteger cont= new AtomicInteger(1);
            beds.forEach((uuid, bed) -> {
                ItemStack item = new ItemStack(bed.getBedMaterial(),1);
                ItemMeta item_meta = item.getItemMeta();
                String bedName = "床 {1}".replace("{1}", cont.toString());
                item_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', bedName));
                if (bed.getBedName()!=null) {
                    item_meta.setDisplayName(bed.getBedName());
                }
                PersistentDataContainer data = item_meta.getPersistentDataContainer();

                List<String> lore = new ArrayList<>();
                if (!RespawnBed.disable_bed_world_desc) {
                    lore.add(ChatColor.DARK_PURPLE + bed.getBedWorld().toUpperCase());
                }
                if (!RespawnBed.disable_bed_coords_desc) {
                    String[] location = bed.getBedCoords().split(":");
                    String locText = "X: " + location[0].substring(0, location[0].length() - 2) +
                            " Y: " + location[1].substring(0, location[1].length() - 2) +
                            " Z: " + location[2].substring(0, location[2].length() - 2);
                    lore.add(ChatColor.GRAY + locText);
                }
                // checks if has any cooldowns
                if (bed.getBedCooldown()>0L){

                    long cooldown = bed.getBedCooldown();
                    if (cooldown>System.currentTimeMillis()){ // if cooldown isnt expired
                        hasCooldown.set(true);
                        data.set(new NamespacedKey(plugin, "cooldown"), PersistentDataType.LONG, cooldown);
                    }else{
                        bed.setBedCooldown(0L);
                    }

                }

                data.set(new NamespacedKey(plugin, "uuid"), PersistentDataType.STRING, uuid);
                data.set(new NamespacedKey(plugin, "location"), PersistentDataType.STRING, bed.getBedCoords());
                data.set(new NamespacedKey(plugin, "world"), PersistentDataType.STRING, bed.getBedWorld());

                item_meta.setLore(lore);
                item.setItemMeta(item_meta);
                gui.addItem(item);
                cont.getAndIncrement();
            });

            if (hasCooldown.get()) {
                Bukkit.getScheduler().runTaskLater(plugin, () -> {updateItens(gui, p);}, 10L);
            }

            ItemStack item = new ItemStack(Material.GRASS_BLOCK,1);
            ItemMeta item_meta = item.getItemMeta();
            item_meta.setDisplayName(ChatColor.YELLOW+"SPAWN");
            item.setItemMeta(item_meta);
            gui.setItem(9 * ( (int) Math.ceil( bedCount / (Double) 9.0 )) -1, item);

            // I dont know why but if openInventory is not on a scheduler is does not open
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                p.openInventory(gui);
            }, 0L);

        }else{

            String spawnCoords[] = playerData.get(new NamespacedKey(plugin, "spawnLoc"), PersistentDataType.STRING).split(":");
            Location location = new Location(p.getWorld(), Double.parseDouble(spawnCoords[0]), Double.parseDouble(spawnCoords[1]), Double.parseDouble(spawnCoords[2]));
            playerData.remove(new NamespacedKey(plugin, "spawnLoc"));
            undoPropPlayer(p);
            boolean test = p.teleport(location);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                p.teleport(location);
            }, 1L);

        }

    }

    public static void onPlayerRespawn(Player p,Location respawnLoc) {
        RustV2 plugin = RustV2.getPlugin();
        String world = p.getWorld().getName();
        PersistentDataContainer playerData = p.getPersistentDataContainer();
        PlayerBedsData playerBedsData;
        HashMap<String, BedData> beds;
        if (playerData.has(new NamespacedKey(plugin, "beds"), new BedsDataType())) {
            playerBedsData = playerData.get(new NamespacedKey(plugin, "beds"), new BedsDataType());
            if (playerBedsData != null && playerBedsData.getPlayerBedData() != null) {
                beds = playerBedsData.getPlayerBedData();
                if (!RespawnBed.link_worlds) {
                    HashMap<String, BedData> bedsT = (HashMap<String, BedData>) beds.clone();
                    beds.forEach((uuid, bed) -> {
                        if (!bed.getBedWorld().equalsIgnoreCase(world)) {
                            bedsT.remove(uuid);
                        }
                    });
                    beds = bedsT;
                }
                beds.forEach((uuid, bed) -> { // loops all beds to check if they still exist
                    String loc[] = bed.getBedCoords().split(":");
                    Location locBed = new Location(Bukkit.getWorld(bed.getBedWorld()), Double.parseDouble(loc[0]), Double.parseDouble(loc[1]),Double.parseDouble(loc[2]));
                    checksIfBedExists(locBed, p, uuid, bed.getBedWorld());
                });

            }
        }
        if (RespawnBed.spawn_on_sky) {
            playerData.set(new NamespacedKey(plugin, "spawnLoc"), PersistentDataType.STRING, locationToString(respawnLoc));
        }
        RespawnBed.openRespawnMenu(p);
    }

    public static void onPlayerJoinEvent(Player p) {
        RustV2 plugin = RustV2.getPlugin();
        PersistentDataContainer playerData = p.getPersistentDataContainer();
        NamespacedKey spawnLocName = new NamespacedKey(plugin, "spawnLoc");
        if (RespawnBed.spawn_on_sky && playerData.has(spawnLocName, PersistentDataType.STRING)) {
            String spawnCoords[] = playerData.get(spawnLocName, PersistentDataType.STRING).split(":");
            Location location = new Location(p.getWorld(), Double.parseDouble(spawnCoords[0]), Double.parseDouble(spawnCoords[1]), Double.parseDouble(spawnCoords[2]));
            playerData.remove(spawnLocName);
            p.teleport(location);
        }
        undoPropPlayer(p);
    }

    public static void onPlayerGetOnBed(Player player ,Block bed) {
        String world = player.getWorld().getName();
        PersistentDataContainer playerData = player.getPersistentDataContainer();

        int maxBeds = getMaxNumberOfBeds(player);
        PlayerBedsData playerBedsData = null;

        int playerBedsCount = getPlayerBedsCount(player);

        if (playerData.has(new NamespacedKey(plugin, "beds"), new BedsDataType())) {
            playerBedsData = playerData.get(new NamespacedKey(plugin, "beds"), new BedsDataType());
        }

        if (playerBedsCount < maxBeds) {

            UUID randomUUID = UUID.randomUUID();
            BlockState blockState = bed.getState();

            if (blockState instanceof TileState ){
                TileState tileState  = (TileState) blockState; // sets a randomUUID to the bed if the bed doesnt have it or get the bed uuid
                PersistentDataContainer container = tileState.getPersistentDataContainer();

                if (!container.has(new NamespacedKey(plugin, "uuid"), PersistentDataType.STRING)) {
                    container.set(new NamespacedKey(plugin, "uuid"), PersistentDataType.STRING, "" + randomUUID);
                } else {
                    randomUUID = UUID.fromString(container.get(new NamespacedKey(plugin, "uuid"), PersistentDataType.STRING));
                    if ((playerBedsData==null || (playerBedsData!=null && !playerBedsData.hasBed(randomUUID.toString()))) && RespawnBed.exclusive_bed){
                        player.sendMessage(ChatColor.RED + "床已经被其他玩家拥有");
                        return;
                    }
                }

                tileState.update();

            }

            boolean registerBed = false;
            if (playerBedsData == null) { // if the player doesnt have any bed

                playerBedsData = new PlayerBedsData(player, bed, randomUUID.toString());
                registerBed = true;

            } else if (!playerBedsData.hasBed(randomUUID.toString())) {

                playerBedsData.setNewBed(player, bed, randomUUID.toString());
                registerBed = true;

            }

            if (registerBed) {
                playerData.set(new NamespacedKey(plugin, "beds"), new BedsDataType(), playerBedsData);
                //player.sendMessage(ChatColor.translateAlternateColorCodes('&', "床注册成功"));
            }


        } else {
            player.sendMessage(ChatColor.RED + "你放置的床已达最大值");
        }

    }
    public static void operateRespawnMenu(InventoryClickEvent e){
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        if (e.getCurrentItem() != null){
            PersistentDataContainer playerData = p.getPersistentDataContainer();
            int playerBedsCount = 0;
            PlayerBedsData playerBedsData = null;
            if (playerData.has(new NamespacedKey(plugin, "beds"), new BedsDataType())) {
                playerBedsData = playerData.get(new NamespacedKey(plugin, "beds"), new BedsDataType());
                if (playerBedsData!=null && playerBedsData.getPlayerBedData()!=null){
                    playerBedsCount = playerBedsData.getPlayerBedData().size();
                }
            }
            double bedCount = playerBedsCount + 1;
            int index = e.getSlot();
            if (e.getCurrentItem().getType().toString().toLowerCase().contains("bed")){

                ItemMeta item_meta = e.getCurrentItem().getItemMeta();
                PersistentDataContainer data = item_meta.getPersistentDataContainer();

                String bedCoord[] = data.get(new NamespacedKey(plugin, "location"), PersistentDataType.STRING).split(":");
                String world = data.get(new NamespacedKey(plugin, "world"), PersistentDataType.STRING);
                Location location = new Location(p.getWorld(), Double.parseDouble(bedCoord[0]), Double.parseDouble(bedCoord[1]), Double.parseDouble(bedCoord[2]));
                String uuid = data.get(new NamespacedKey(plugin, "uuid"), PersistentDataType.STRING);

                if (checksIfBedExists(location , p, uuid, world)){

                    teleportPlayer(p, data, playerData, playerBedsData, uuid);

                }else{
                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        p.closeInventory();
                    }, 0L);
                }


            }else if(index==9 * ( (int) Math.ceil( bedCount / (Double) 9.0 ) )-1){
                if (RespawnBed.spawn_on_sky && playerData.has(new NamespacedKey(plugin, "spawnLoc"), PersistentDataType.STRING)) {
                    String spawnCoords[] = playerData.get(new NamespacedKey(plugin, "spawnLoc"), PersistentDataType.STRING).split(":");
                    Location location = new Location(p.getWorld(), Double.parseDouble(spawnCoords[0]), Double.parseDouble(spawnCoords[1]), Double.parseDouble(spawnCoords[2]));
                    playerData.remove(new NamespacedKey(plugin, "spawnLoc"));
                    undoPropPlayer(p);
                    p.teleport(location);
                }else{
                    undoPropPlayer(p);
                }
            }
        }
    }
}
