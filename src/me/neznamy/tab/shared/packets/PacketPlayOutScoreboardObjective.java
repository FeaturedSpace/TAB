package me.neznamy.tab.shared.packets;

import java.lang.reflect.Field;
import java.util.Map;

import me.neznamy.tab.platforms.bukkit.packets.method.MethodAPI;
import me.neznamy.tab.shared.ProtocolVersion;
import me.neznamy.tab.shared.Shared;
import net.md_5.bungee.protocol.packet.ScoreboardObjective;
import net.md_5.bungee.protocol.packet.ScoreboardObjective.HealthDisplay;

public class PacketPlayOutScoreboardObjective extends UniversalPacketPlayOut{

	private String objectiveName;
	private String title;
	private EnumScoreboardHealthDisplay displayType;
	private int action;

	public PacketPlayOutScoreboardObjective(String objectiveName, String title, EnumScoreboardHealthDisplay displayType, int action) {
		this.objectiveName = objectiveName;
		this.title = title;
		this.displayType = displayType;
		this.action = action;
	}
	public Object toNMS(ProtocolVersion clientVersion) throws Exception {
		String title = this.title;
		if (clientVersion.getMinorVersion() < 13) {
			if (title != null && title.length() > 32) title = title.substring(0, 32);
		}
		Object packet = MethodAPI.getInstance().newPacketPlayOutScoreboardObjective();
		OBJECTIVENAME.set(packet, objectiveName);
		if (ProtocolVersion.SERVER_VERSION.getMinorVersion() >= 13) {
			TITLE.set(packet, Shared.mainClass.createComponent(title));
		} else {
			TITLE.set(packet, title);
		}
		if (ProtocolVersion.SERVER_VERSION.getMinorVersion() >= 8 && displayType != null) DISPLAYTYPE.set(packet, displayType.toNMS());
		ACTION.set(packet, action);
		return packet;
	}
	public Object toBungee(ProtocolVersion clientVersion) {
		String title = this.title;
		if (clientVersion.getMinorVersion() >= 13) {
			title = (String) Shared.mainClass.createComponent(title);
		} else {
			if (title != null && title.length() > 32) title = title.substring(0, 32);
		}
		return new ScoreboardObjective(objectiveName, title, displayType.toBungee(), (byte)action);
	}
	public Object toVelocity(ProtocolVersion clientVersion) {
		return null;
	}
	public enum EnumScoreboardHealthDisplay{

		INTEGER, HEARTS;

		private Object nmsEquivalent;

		@SuppressWarnings({ "unchecked", "rawtypes" })
		private EnumScoreboardHealthDisplay() {
			if (ProtocolVersion.SERVER_VERSION.getMinorVersion() >= 8 && ProtocolVersion.SERVER_VERSION != ProtocolVersion.BUNGEE) {
				nmsEquivalent = Enum.valueOf((Class<Enum>)MethodAPI.EnumScoreboardHealthDisplay, toString());
			} else {
				nmsEquivalent = ordinal();
			}
		}
		public Object toNMS() {
			return nmsEquivalent;
		}
		public HealthDisplay toBungee() {
			return HealthDisplay.valueOf(toString());
		}
	}
	private static Map<String, Field> fields = getFields(MethodAPI.PacketPlayOutScoreboardObjective);
	private static Field OBJECTIVENAME = fields.get("a");
	private static Field TITLE = fields.get("b");
	private static Field DISPLAYTYPE = fields.get("c");
	private static Field ACTION = fields.get("d");
	
	static {
		if (ProtocolVersion.SERVER_VERSION.getMinorVersion() >= 8) {
			DISPLAYTYPE = fields.get("c");
			ACTION = fields.get("d");
		} else {
			ACTION = fields.get("c");
		}
	}
}