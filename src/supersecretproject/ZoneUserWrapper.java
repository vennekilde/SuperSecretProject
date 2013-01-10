package supersecretproject;

import info.jeppes.ZoneCore.Users.RecommendationsHolder;
import info.jeppes.ZoneCore.Users.ZoneUser;
import info.jeppes.ZoneCore.Users.ZoneUserData;
import info.jeppes.ZoneCore.Users.ZoneUserData.ServerGroup;
import info.jeppes.ZoneCore.ZoneConfig;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class ZoneUserWrapper implements ZoneUser{
    private final ZoneUserData user;
    public ZoneUserWrapper(ZoneUserData user){
        this.user = user;
    }
    
    public ZoneUserData getZoneUser(){
        return user;
    }
    
    @Override
    public void newUser() {
        user.newUser();
    }

    @Override
    public ConfigurationSection getConfig() {
        return user.getConfig();
    }

    @Override
    public ZoneConfig getUsersConfig() {
        return user.getUsersConfig();
    }

    @Override
    public ServerGroup getServerGroup() {
        return user.getServerGroup();
    }

    @Override
    public HashMap<String, Object> getTempData() {
        return user.getTempData();
    }

    @Override
    public Object getTempData(String key) {
        return user.getTempData(key);
    }

    @Override
    public void addTempData(String key, Object obj) {
        user.addTempData(key, obj);
    }

    @Override
    public void removeTempData(String key) {
        user.removeTempData(key);
    }

    @Override
    public void removeTempData(Object obj) {
        user.removeTempData(obj);
    }

    @Override
    public void setTempData(HashMap<String, Object> objList) {
        user.setTempData(objList);
    }

    @Override
    public void clearTempData() {
        user.clearTempData();
    }

    @Override
    public RecommendationsHolder getRecommendationsHolder() {
        return user.getRecommendationsHolder();
    }

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
        user.onPlayerJoin(event);
    }

    @Override
    public boolean sendMesssagesWhenOnline() {
        return user.sendMesssagesWhenOnline();
    }

    @Override
    public boolean giveItemsWhenOnline() {
        return user.giveItemsWhenOnline();
    }

    @Override
    public boolean giveLevelsWhenOnline() {
        return user.giveLevelsWhenOnline();
    }

    @Override
    public void sendMesssageWhenOnline(String message) {
        user.sendMesssageWhenOnline(message);
    }

    @Override
    public void giveItemWhenOnline(ItemStack itemStack) {
        user.giveItemWhenOnline(itemStack);
    }

    @Override
    public void giveLevelsWhenOnline(int i) {
        user.giveLevelsWhenOnline(i);
    }

    @Override
    public void saveConfig() {
        user.saveConfig();
    }

    @Override
    public void updatePlayTime() {
        user.updatePlayTime();
    }

    @Override
    public long getPlayTime() {
        return user.getPlayTime();
    }
    
    @Override
    public Player getPlayer(){
        return user.getPlayer();
    }
    
    @Override
    public void setPlayer(Player player){
        user.setPlayer(player);
    }
    
    //BUKKIT STUFF
    
    @Override
    public String getDisplayName() {
        return user.getDisplayName();
    }

    @Override
    public void setDisplayName(String string) {
        user.setDisplayName(string);
    }

    @Override
    public String getPlayerListName() {
        return user.getPlayerListName();
    }

    @Override
    public void setPlayerListName(String string) {
        user.setPlayerListName(string);
    }

    @Override
    public void setCompassTarget(Location lctn) {
        user.setCompassTarget(lctn);
    }

    @Override
    public Location getCompassTarget() {
        return user.getCompassTarget();
    }

    @Override
    public InetSocketAddress getAddress() {
        return user.getAddress();
    }

    @Override
    public void sendRawMessage(String string) {
        user.sendRawMessage(string);
    }

    @Override
    public void kickPlayer(String string) {
        user.kickPlayer(string);
    }

    @Override
    public void chat(String string) {
        user.chat(string);
    }

    @Override
    public boolean performCommand(String string) {
        return user.performCommand(string);
    }

    @Override
    public boolean isSneaking() {
        return user.isSneaking();
    }

    @Override
    public void setSneaking(boolean bln) {
        user.setSneaking(bln);
    }

    @Override
    public boolean isSprinting() {
        return user.isSprinting();
    }

    @Override
    public void setSprinting(boolean bln) {
        user.setSprinting(bln);
    }

    @Override
    public void saveData() {
        user.saveData();
    }

    @Override
    public void loadData() {
        user.loadData();
    }

    @Override
    public void setSleepingIgnored(boolean bln) {
        user.setSleepingIgnored(bln);
    }

    @Override
    public boolean isSleepingIgnored() {
        return user.isSleepingIgnored();
    }

    @Override
    public void playNote(Location lctn, byte b, byte b1) {
        user.playNote(lctn, b1, b1);
    }

    @Override
    public void playNote(Location lctn, Instrument i, Note note) {
        user.playNote(lctn, i, note);
    }

    @Override
    public void playEffect(Location lctn, Effect effect, int i) {
        user.playEffect(lctn, effect, i);
    }

    @Override
    public void sendBlockChange(Location lctn, Material mtrl, byte b) {
        user.sendBlockChange(lctn, mtrl, b);
    }

    @Override
    public boolean sendChunkChange(Location lctn, int i, int i1, int i2, byte[] bytes) {
        return user.sendChunkChange(lctn, i, i1, i2, bytes);
    }

    @Override
    public void sendBlockChange(Location lctn, int i, byte b) {
        user.sendBlockChange(lctn, i, b);
    }

    @Override
    public void sendMap(MapView mv) {
        user.sendMap(mv);
    }

    @Override
    public void updateInventory() {
        user.updateInventory();
    }

    @Override
    public void awardAchievement(Achievement a) {
        user.awardAchievement(a);
    }

    @Override
    public void incrementStatistic(Statistic ststc) {
        user.incrementStatistic(ststc);
    }

    @Override
    public void incrementStatistic(Statistic ststc, int i) {
        user.incrementStatistic(ststc, i);
    }

    @Override
    public void incrementStatistic(Statistic ststc, Material mtrl) {
        user.incrementStatistic(ststc, mtrl);
    }

    @Override
    public void incrementStatistic(Statistic ststc, Material mtrl, int i) {
        user.incrementStatistic(ststc, mtrl, i);
    }

    @Override
    public void setPlayerTime(long l, boolean bln) {
        user.setPlayerTime(l, bln);
    }

    @Override
    public long getPlayerTime() {
        return user.getPlayerTime();
    }

    @Override
    public long getPlayerTimeOffset() {
        return user.getPlayerTimeOffset();
    }

    @Override
    public boolean isPlayerTimeRelative() {
        return user.isPlayerTimeRelative();
    }

    @Override
    public void resetPlayerTime() {
        user.resetPlayerTime();
    }

    @Override
    public void giveExp(int i) {
        user.giveExp(i);
    }

    @Override
    public float getExp() {
        return user.getExp();
    }

    @Override
    public void setExp(float f) {
        user.setExp(f);
    }

    @Override
    public int getLevel() {
        return user.getLevel();
    }

    @Override
    public void setLevel(int i) {
        user.setLevel(i);
    }

    @Override
    public int getTotalExperience() {
        return user.getTotalExperience();
    }

    @Override
    public void setTotalExperience(int i) {
        user.setTotalExperience(i);
    }

    @Override
    public float getExhaustion() {
        return user.getExhaustion();
    }

    @Override
    public void setExhaustion(float f) {
        user.setExhaustion(f);
    }

    @Override
    public float getSaturation() {
        return user.getSaturation();
    }

    @Override
    public void setSaturation(float f) {
        user.setSaturation(f);
    }

    @Override
    public int getFoodLevel() {
        return user.getFoodLevel();
    }

    @Override
    public void setFoodLevel(int i) {
        user.setFoodLevel(i);
    }

    @Override
    public Location getBedSpawnLocation() {
        return user.getBedSpawnLocation();
    }

    @Override
    public String getName() {
        return user.getName();
    }

    @Override
    public PlayerInventory getInventory() {
        return user.getInventory();
    }

    @Override
    public ItemStack getItemInHand() {
        return user.getItemInHand();
    }

    @Override
    public void setItemInHand(ItemStack is) {
        user.setItemInHand(is);
    }

    @Override
    public boolean isSleeping() {
        return user.isSleeping();
    }

    @Override
    public int getSleepTicks() {
        return user.getSleepTicks();
    }

    @Override
    public GameMode getGameMode() {
        return user.getGameMode();
    }

    @Override
    public void setGameMode(GameMode gm) {
        user.setGameMode(gm);
    }

    @Override
    public int getHealth() {
        return user.getHealth();
    }

    @Override
    public void setHealth(int i) {
        user.setHealth(i);
    }

    @Override
    public int getMaxHealth() {
        return user.getMaxHealth();
    }

    @Override
    public double getEyeHeight() {
        return user.getEyeHeight();
    }

    @Override
    public double getEyeHeight(boolean bln) {
        return user.getEyeHeight(bln);
    }

    @Override
    public Location getEyeLocation() {
        return user.getEyeLocation();
    }

    @Override
    public List<Block> getLineOfSight(HashSet<Byte> hs, int i) {
        return user.getLineOfSight(hs, i);
    }

    @Override
    public Block getTargetBlock(HashSet<Byte> hs, int i) {
        return user.getTargetBlock(hs, i);
    }

    @Override
    public List<Block> getLastTwoTargetBlocks(HashSet<Byte> hs, int i) {
        return user.getLastTwoTargetBlocks(hs, i);
    }

    @Override
    public Egg throwEgg() {
        return user.throwEgg();
    }

    @Override
    public Snowball throwSnowball() {
        return user.throwSnowball();
    }

    @Override
    public Arrow shootArrow() {
        return user.shootArrow();
    }

    @Override
    public boolean isInsideVehicle() {
        return user.isInsideVehicle();
    }

    @Override
    public boolean leaveVehicle() {
        return user.leaveVehicle();
    }

    @Override
    public Entity getVehicle() {
        return user.getVehicle();
    }

    @Override
    public int getRemainingAir() {
        return user.getRemainingAir();
    }

    @Override
    public void setRemainingAir(int i) {
        user.setRemainingAir(i);
    }

    @Override
    public int getMaximumAir() {
        return user.getMaximumAir();
    }

    @Override
    public void setMaximumAir(int i) {
        user.setMaximumAir(i);
    }

    @Override
    public void damage(int i) {
        user.damage(i);
    }

    @Override
    public void damage(int i, Entity entity) {
        user.damage(i,entity);
    }

    @Override
    public int getMaximumNoDamageTicks() {
        return user.getMaximumNoDamageTicks();
    }

    @Override
    public void setMaximumNoDamageTicks(int i) {
        user.setMaximumNoDamageTicks(i);
    }

    @Override
    public int getLastDamage() {
        return user.getLastDamage();
    }

    @Override
    public void setLastDamage(int i) {
        user.setLastDamage(i);
    }

    @Override
    public int getNoDamageTicks() {
        return user.getNoDamageTicks();
    }

    @Override
    public void setNoDamageTicks(int i) {
        user.setNoDamageTicks(i);
    }

    @Override
    public Location getLocation() {
        return user.getLocation();
    }

    @Override
    public void setVelocity(Vector vector) {
        user.setVelocity(vector);
    }

    @Override
    public Vector getVelocity() {
        return user.getVelocity();
    }

    @Override
    public World getWorld() {
        return user.getWorld();
    }

    @Override
    public boolean teleport(Location lctn) {
        return user.teleport(lctn);
    }

    @Override
    public boolean teleport(Location lctn, PlayerTeleportEvent.TeleportCause tc) {
        return user.teleport(lctn, tc);
    }

    @Override
    public boolean teleport(Entity entity) {
        return user.teleport(entity);
    }

    @Override
    public boolean teleport(Entity entity, PlayerTeleportEvent.TeleportCause tc) {
        return user.teleport(entity, tc);
    }

    @Override
    public List<Entity> getNearbyEntities(double d, double d1, double d2) {
        return user.getNearbyEntities(d, d1, d2);
    }

    @Override
    public int getEntityId() {
        return user.getEntityId();
    }

    @Override
    public int getFireTicks() {
        return user.getFireTicks();
    }

    @Override
    public int getMaxFireTicks() {
        return user.getMaxFireTicks();
    }

    @Override
    public void setFireTicks(int i) {
        user.setFireTicks(i);
    }

    @Override
    public void remove() {
        user.remove();
    }

    @Override
    public boolean isDead() {
        return user.isDead();
    }

    @Override
    public Server getServer() {
        return user.getServer();
    }

    @Override
    public Entity getPassenger() {
        return user.getPassenger();
    }

    @Override
    public boolean setPassenger(Entity entity) {
        return user.setPassenger(entity);
    }

    @Override
    public boolean isEmpty() {
        return user.isEmpty();
    }

    @Override
    public boolean eject() {
        return user.eject();
    }

    @Override
    public float getFallDistance() {
        return user.getFallDistance();
    }

    @Override
    public void setFallDistance(float f) {
        user.setFallDistance(f);
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent ede) {
        user.setLastDamageCause(ede);
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        return user.getLastDamageCause();
    }

    @Override
    public UUID getUniqueId() {
        return user.getUniqueId();
    }

    @Override
    public int getTicksLived() {
        return user.getTicksLived();
    }

    @Override
    public void setTicksLived(int i) {
        user.setTicksLived(i);
    }

    @Override
    public boolean isPermissionSet(String string) {
        return user.isPermissionSet(string);
    }

    @Override
    public boolean isPermissionSet(Permission prmsn) {
        return user.isPermissionSet(prmsn);
    }

    @Override
    public boolean hasPermission(String string) {
        return user.hasPermission(string);
    }

    @Override
    public boolean hasPermission(Permission prmsn) {
        return user.hasPermission(prmsn);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String string, boolean bln) {
        return user.addAttachment(plugin,string,bln);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        return user.addAttachment(plugin);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String string, boolean bln, int i) {
        return user.addAttachment(plugin, string, bln, i);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int i) {
        return user.addAttachment(plugin, i);
    }

    @Override
    public void removeAttachment(PermissionAttachment pa) {
        user.removeAttachment(pa);
    }

    @Override
    public void recalculatePermissions() {
        user.recalculatePermissions();
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return user.getEffectivePermissions();
    }

    @Override
    public boolean isOp() {
        return user.isOp();
    }

    @Override
    public void setOp(boolean bln) {
        user.setOp(bln);
    }

    @Override
    public void sendMessage(String string) {
        user.sendMessage(string);
    }

    @Override
    public boolean isOnline() {
        return user.isOnline();
    }

    @Override
    public boolean isBanned() {
        return user.isBanned();
    }

    @Override
    public void setBanned(boolean bln) {
        user.setBanned(bln);
    }

    @Override
    public boolean isWhitelisted() {
        return user.isWhitelisted();
    }

    @Override
    public void setWhitelisted(boolean bln) {
        user.setWhitelisted(bln);
    }
    
    @Override
    public Map<String, Object> serialize() {
        return user.serialize();
    }

    @Override
    public Player getKiller() {
        return user.getKiller();
    }

    @Override
    public long getFirstPlayed() {
        return user.getFirstPlayed();
    }

    @Override
    public long getLastPlayed() {
        return user.getLastPlayed();
    }

    @Override
    public boolean hasPlayedBefore() {
        return user.hasPlayedBefore();
    }

    @Override
    public void setBedSpawnLocation(Location lctn) {
        user.setBedSpawnLocation(lctn);
    }

    @Override
    public boolean getAllowFlight() {
        return user.getAllowFlight();
    }

    @Override
    public void setAllowFlight(boolean bln) {
        user.setAllowFlight(bln);
    }

    @Override
    public void playEffect(EntityEffect ee) {
        user.playEffect(ee);
    }

    @Override
    public void sendPluginMessage(Plugin plugin, String string, byte[] bytes) {
        user.sendPluginMessage(plugin, string, bytes);
    }

    @Override
    public Set<String> getListeningPluginChannels() {
        return user.getListeningPluginChannels();
    }

    @Override
    public <T> void playEffect(Location lctn, Effect effect, T t) {
        user.playEffect(lctn, effect, t);
    }

    @Override
    public void hidePlayer(Player player) {
        player.hidePlayer(player);
    }

    @Override
    public void showPlayer(Player player) {
        player.showPlayer(player);
    }

    @Override
    public boolean canSee(Player player) {
       return player.canSee(player);
    }

    @Override
    public boolean setWindowProperty(InventoryView.Property prprt, int i) {
        return user.setWindowProperty(prprt, i);
    }

    @Override
    public InventoryView getOpenInventory() {
        return user.getOpenInventory();
    }

    @Override
    public InventoryView openInventory(Inventory invntr) {
        return user.openInventory(invntr);
    }

    @Override
    public InventoryView openWorkbench(Location lctn, boolean bln) {
        return user.openWorkbench(lctn, bln);
    }

    @Override
    public InventoryView openEnchanting(Location lctn, boolean bln) {
        return user.openEnchanting(lctn, bln);
    }

    @Override
    public void openInventory(InventoryView iv) {
        user.openInventory(iv);
    }

    @Override
    public void closeInventory() {
        user.closeInventory();
    }

    @Override
    public ItemStack getItemOnCursor() {
        return user.getItemOnCursor();
    }

    @Override
    public void setItemOnCursor(ItemStack is) {
        user.setItemOnCursor(is);
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> type) {
        return user.launchProjectile(type);
    }

    @Override
    public boolean addPotionEffect(PotionEffect pe) {
        return user.addPotionEffect(pe);
    }

    @Override
    public boolean addPotionEffect(PotionEffect pe, boolean bln) {
        return user.addPotionEffect(pe, bln);
    }

    @Override
    public boolean addPotionEffects(Collection<PotionEffect> clctn) {
        return user.addPotionEffects(clctn);
    }

    @Override
    public boolean hasPotionEffect(PotionEffectType pet) {
        return user.hasPotionEffect(pet);
    }

    @Override
    public void removePotionEffect(PotionEffectType pet) {
        user.removePotionEffect(pet);
    }

    @Override
    public Collection<PotionEffect> getActivePotionEffects() {
        return user.getActivePotionEffects();
    }

    @Override
    public EntityType getType() {
        return user.getType();
    }

    @Override
    public void setMetadata(String string, MetadataValue mv) {
        user.setMetadata(string, mv);
    }

    @Override
    public List<MetadataValue> getMetadata(String string) {
        return user.getMetadata(string);
    }

    @Override
    public boolean hasMetadata(String string) {
        return user.hasMetadata(string);
    }

    @Override
    public void removeMetadata(String string, Plugin plugin) {
        user.removeMetadata(string, plugin);
    }

    @Override
    public boolean isConversing() {
        return user.isConversing();
    }

    @Override
    public void acceptConversationInput(String string) {
        user.acceptConversationInput(string);
    }

    @Override
    public boolean beginConversation(Conversation c) {
        return user.beginConversation(c);
    }

    @Override
    public void abandonConversation(Conversation c) {
        user.abandonConversation(c);
    }

    @Override
    public void abandonConversation(Conversation c, ConversationAbandonedEvent cae) {
        user.abandonConversation(c, cae);
    }

    @Override
    public void sendMessage(String[] strings) {
        user.sendMessage(strings);
    }
    
    @Override
    public boolean isFlying() {
        return user.isFlying();
    }

    @Override
    public void setFlying(boolean bln) {
        user.setFlying(bln);
    }

    @Override
    public boolean isBlocking() {
        return user.isBlocking();
    }
    @Override
    public int getExpToLevel() {
        return user.getExpToLevel();
    }

    @Override
    public boolean hasLineOfSight(Entity entity) {
        return user.hasLineOfSight(entity);
    }

    @Override
    public boolean isValid() {
        return user.isValid();
    }
    @Override
    public void setFlySpeed(float f) throws IllegalArgumentException {
        user.setFlySpeed(f);
    }

    @Override
    public void setWalkSpeed(float f) throws IllegalArgumentException {
        user.setWalkSpeed(f);
    }

    @Override
    public float getFlySpeed() {
        return user.getFlySpeed();
    }

    @Override
    public float getWalkSpeed() {
        return user.getWalkSpeed();
    }
    
    @Override
    public void playSound(Location lctn, Sound sound, float f, float f1) {
        user.playSound(lctn, sound, f, f1);
    }

    @Override
    public void giveExpLevels(int i) {
        user.giveExpLevels(i);
    }

    @Override
    public void setBedSpawnLocation(Location lctn, boolean bln) {
        user.setBedSpawnLocation(lctn, bln);
    }

    @Override
    public Inventory getEnderChest() {
        return user.getEnderChest();
    }

    @Override
    public void setTexturePack(String string) {
        user.setTexturePack(string);
    }

    @Override
    public boolean getRemoveWhenFarAway() {
        return user.getRemoveWhenFarAway();
    }

    @Override
    public void setRemoveWhenFarAway(boolean bln) {
        user.setRemoveWhenFarAway(bln);
    }

    @Override
    public EntityEquipment getEquipment() {
        return user.getEquipment();
    }

    @Override
    public void setCanPickupItems(boolean bln) {
        user.setCanPickupItems(bln);
    }

    @Override
    public boolean getCanPickupItems() {
        return user.getCanPickupItems();
    }

    @Override
    public Location getLocation(Location lctn) {
        return user.getLocation(lctn);
    }

    @Override
    public void setMaxHealth(int i) {
        user.setMaxHealth(i);
    }

    @Override
    public void resetMaxHealth() {
        user.resetMaxHealth();
    }
}
