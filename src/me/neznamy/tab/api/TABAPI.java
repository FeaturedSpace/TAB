package me.neznamy.tab.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.neznamy.tab.shared.Configs;
import me.neznamy.tab.shared.HeaderFooter;
import me.neznamy.tab.shared.ITabPlayer;
import me.neznamy.tab.shared.NameTag16;
import me.neznamy.tab.shared.Shared;
import me.neznamy.tab.shared.TabCommand;
import me.neznamy.tab.shared.packets.PacketPlayOutPlayerListHeaderFooter;

public class TABAPI {

	public static List<UUID> hiddenNametag = new ArrayList<UUID>();

	public static boolean isUnlimitedNameTagModeEnabled() {
		return Configs.unlimitedTags;
	}
	public static void enableUnlimitedNameTagModePermanently() {
		if (Shared.mainClass instanceof me.neznamy.tab.platforms.bukkit.Main) {
			Configs.config.set("change-nametag-prefix-suffix", true);
			Configs.config.set("unlimited-nametag-prefix-suffix-mode.enabled", true);
			Configs.config.save();
			me.neznamy.tab.platforms.bukkit.Main.instance.unload();
			me.neznamy.tab.platforms.bukkit.Main.instance.load(false, false);
		} else throw new IllegalStateException();
	}
	
	public static void setCustomTabNameTemporarily(UUID uniqueId, String value) {
		ITabPlayer t = Shared.getPlayer(uniqueId);
		if (t == null) return;
		t.properties.get("customtabname").setTemporaryValue(value);
		if (NameTag16.enable || Configs.unlimitedTags) t.updateTeam();
		t.updatePlayerListName(false);
	}
	public static void setCustomTagNameTemporarily(UUID uniqueId, String value) {
		if (!Configs.unlimitedTags) throw new IllegalStateException("Unlimited nametag mode is not enabled");
		ITabPlayer t = Shared.getPlayer(uniqueId);
		if (t == null) return;
		t.properties.get("customtagname").setTemporaryValue(value);
		if (NameTag16.enable || Configs.unlimitedTags) t.updateTeam();
		t.updatePlayerListName(false);
	}
	public static void setTabPrefixTemporarily(UUID uniqueId, String value) {
		ITabPlayer t = Shared.getPlayer(uniqueId);
		if (t == null) return;
		t.properties.get("tabprefix").setTemporaryValue(value);
		t.updatePlayerListName(false);
	}
	public static void setTabSuffixTemporarily(UUID uniqueId, String value) {
		ITabPlayer t = Shared.getPlayer(uniqueId);
		if (t == null) return;
		t.properties.get("tabsuffix").setTemporaryValue(value);
		t.updatePlayerListName(false);
	}
	public static void setTagPrefixTemporarily(UUID uniqueId, String value) {
		ITabPlayer t = Shared.getPlayer(uniqueId);
		if (t == null) return;
		t.properties.get("tagprefix").setTemporaryValue(value);
		if (NameTag16.enable || Configs.unlimitedTags) t.updateTeam();
	}
	public static void setTagSuffixTemporarily(UUID uniqueId, String value) {
		ITabPlayer t = Shared.getPlayer(uniqueId);
		if (t == null) return;
		t.properties.get("tagsuffix").setTemporaryValue(value);
		if (NameTag16.enable || Configs.unlimitedTags) t.updateTeam();
	}
	public static void setAboveNameTemporarily(UUID uniqueId, String value) {
		ITabPlayer t = Shared.getPlayer(uniqueId);
		if (!Configs.unlimitedTags) throw new IllegalStateException("Unlimited nametag mode is not enabled");
		t.properties.get("abovename").setTemporaryValue(value);
		t.restartArmorStands();
	}
	public static void setBelowNameTemporarily(UUID uniqueId, String value) {
		ITabPlayer t = Shared.getPlayer(uniqueId);
		if (!Configs.unlimitedTags) throw new IllegalStateException("Unlimited nametag mode is not enabled");
		t.properties.get("belowname").setTemporaryValue(value);
		t.restartArmorStands();
	}
	
	public static void setCustomTabNamePermanently(UUID uniqueId, String value) {
		ITabPlayer t = Shared.getPlayer(uniqueId);
		if (t == null) return;
		TabCommand.savePlayer(null, Shared.getPlayer(uniqueId).getName(), "customtabname", value);
		t.updatePlayerListName(false);
		if (NameTag16.enable || Configs.unlimitedTags) t.updateTeam();
	}
	public static void setCustomTagNamePermanently(UUID uniqueId, String value) {
		ITabPlayer t = Shared.getPlayer(uniqueId);
		if (t == null) return;
		if (!Configs.unlimitedTags) throw new IllegalStateException("Unlimited nametag mode is not enabled");
		TabCommand.savePlayer(null, Shared.getPlayer(uniqueId).getName(), "customtagname", value);
		t.updatePlayerListName(false);
		if (NameTag16.enable || Configs.unlimitedTags) t.updateTeam();
	}
	public static void setTabPrefixPermanently(UUID uniqueId, String value) {
		ITabPlayer t = Shared.getPlayer(uniqueId);
		if (t == null) return;
		TabCommand.savePlayer(null, Shared.getPlayer(uniqueId).getName(), "tabprefix", value);
		t.updatePlayerListName(false);
	}
	public static void setTabSuffixPermanently(UUID uniqueId, String value) {
		ITabPlayer t = Shared.getPlayer(uniqueId);
		if (t == null) return;
		TabCommand.savePlayer(null, Shared.getPlayer(uniqueId).getName(), "tabsuffix", value);
		t.updatePlayerListName(false);
	}
	public static void setTagPrefixPermanently(UUID uniqueId, String value) {
		ITabPlayer t = Shared.getPlayer(uniqueId);
		if (t == null) return;
		TabCommand.savePlayer(null, Shared.getPlayer(uniqueId).getName(), "tagprefix", value);
		if (NameTag16.enable || Configs.unlimitedTags) t.updateTeam();
	}
	public static void setTagSuffixPermanently(UUID uniqueId, String value) {
		ITabPlayer t = Shared.getPlayer(uniqueId);
		if (t == null) return;
		TabCommand.savePlayer(null, Shared.getPlayer(uniqueId).getName(), "tagsuffix", value);
		if (NameTag16.enable || Configs.unlimitedTags) t.updateTeam();
	}
	public static void seAboveNamePermanently(UUID uniqueId, String value) {
		ITabPlayer t = Shared.getPlayer(uniqueId);
		if (t == null) return;
		TabCommand.savePlayer(null, Shared.getPlayer(uniqueId).getName(), "abovename", value);
		if (Configs.unlimitedTags) t.restartArmorStands();
	}
	public static void setBelowNamePermanently(UUID uniqueId, String value) {
		ITabPlayer t = Shared.getPlayer(uniqueId);
		if (t == null) return;
		TabCommand.savePlayer(null, Shared.getPlayer(uniqueId).getName(), "belowname", value);
		if (Configs.unlimitedTags) t.restartArmorStands();
	}
	
	public static String getTemporaryCustomTabName(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("customtabname").getTemporaryValue();
	}
	public static String getTemporaryCustomTagName(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("customtagname").getTemporaryValue();
	}
	public static String getTemporaryTabPrefix(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("tabprefix").getTemporaryValue();
	}
	public static String getTemporaryTabSuffix(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("tabsuffix").getTemporaryValue();
	}
	public static String getTemporaryTagPrefix(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("tagprefix").getTemporaryValue();
	}
	public static String getTemporaryTagSuffix(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("tagsuffix").getTemporaryValue();
	}
	public static String getTemporaryAboveName(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("abovename").getTemporaryValue();
	}
	public static String getTemporaryBelowName(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("belowname").getTemporaryValue();
	}
	
	public static boolean hasTemporaryCustomTabName(UUID uniqueId) {
		return getTemporaryCustomTabName(uniqueId) != null;
	}
	public static boolean hasTemporaryCustomTagName(UUID uniqueId) {
		return getTemporaryCustomTagName(uniqueId) != null;
	}
	public static boolean hasTemporaryTabPrefix(UUID uniqueId) {
		return getTemporaryTabPrefix(uniqueId) != null;
	}
	public static boolean hasTemporaryTabSuffix(UUID uniqueId) {
		return getTemporaryTabSuffix(uniqueId) != null;
	}
	public static boolean hasTemporaryTagPrefix(UUID uniqueId) {
		return getTemporaryTagPrefix(uniqueId) != null;
	}
	public static boolean hasTemporaryTagSuffix(UUID uniqueId) {
		return getTemporaryTagSuffix(uniqueId) != null;
	}
	public static boolean hasTemporaryAboveName(UUID uniqueId) {
		return getTemporaryAboveName(uniqueId) != null;
	}
	public static boolean hasTemporaryBelowName(UUID uniqueId) {
		return getTemporaryBelowName(uniqueId) != null;
	}
	
	public static void removeTemporaryCustomTabName(UUID uniqueId) {
		setCustomTabNameTemporarily(uniqueId, null);
	}
	public static void removeTemporaryCustomTagName(UUID uniqueId) {
		setCustomTagNameTemporarily(uniqueId, null);
	}
	public static void removeTemporaryTabPrefix(UUID uniqueId) {
		setTabPrefixTemporarily(uniqueId, null);
	}
	public static void removeTemporaryTabSuffix(UUID uniqueId) {
		setTabSuffixTemporarily(uniqueId, null);
	}
	public static void removeTemporaryTagPrefix(UUID uniqueId) {
		setTagPrefixTemporarily(uniqueId, null);
	}
	public static void removeTemporaryTagSuffix(UUID uniqueId) {
		setTagSuffixTemporarily(uniqueId, null);
	}
	public static void removeTemporaryAboveName(UUID uniqueId) {
		setAboveNameTemporarily(uniqueId, null);
	}
	public static void removeTemporaryBelowName(UUID uniqueId) {
		setBelowNameTemporarily(uniqueId, null);
	}
	
	public static String getOriginalCustomTabName(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("customtabname").getOriginalRawValue();
	}
	public static String getOriginalCustomTagName(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("customtagname").getOriginalRawValue();
	}
	public static String getOriginalTabPrefix(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("tabprefix").getOriginalRawValue();
	}
	public static String getOriginalTagPrefix(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("tagprefix").getOriginalRawValue();
	}
	public static String getOriginalTabSuffix(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("tabsuffix").getOriginalRawValue();
	}
	public static String getOriginalTagSuffix(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("tagsuffix").getOriginalRawValue();
	}
	public static String getOriginalAboveName(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("abovename").getOriginalRawValue();
	}
	public static String getOriginalBelowName(UUID uniqueId) {
		return Shared.getPlayer(uniqueId).properties.get("belowname").getOriginalRawValue();
	}
	
	public static void sendHeaderFooter(UUID uniqueId, String header, String footer) {
		Shared.getPlayer(uniqueId).sendCustomPacket(new PacketPlayOutPlayerListHeaderFooter(header, footer));
	}
	public static void refreshHeaderFooter(UUID uniqueId) {
		HeaderFooter.refreshHeaderFooter(Shared.getPlayer(uniqueId));
	}
	public static void clearHeaderFooter(UUID uniqueId) {
		Shared.getPlayer(uniqueId).sendCustomPacket(new PacketPlayOutPlayerListHeaderFooter("",""));
	}
	
	public static void hideNametag(UUID uniqueId) {
		hiddenNametag.add(uniqueId);
		Shared.getPlayer(uniqueId).updateTeamPrefixSuffix();
	}
	public static void showNametag(UUID uniqueId) {
		hiddenNametag.remove(uniqueId);
		Shared.getPlayer(uniqueId).updateTeamPrefixSuffix();
	}
	public static boolean hasHiddenNametag(UUID uniqueId) {
		return hiddenNametag.contains(uniqueId);
	}
}