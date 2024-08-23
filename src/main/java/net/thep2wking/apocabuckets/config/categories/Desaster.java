package net.thep2wking.apocabuckets.config.categories;

import net.minecraftforge.common.config.Config;

public class Desaster {
	@Config.Name("# Enable All Desasters")
    public boolean ENABLE_ALL_DESASTERS = true;

	@Config.Name("Tsunami")
    public boolean TSUNAMI = true;

	@Config.Name("Lava Tsunami")
    public boolean LAVA_TSUNAMI = true;

	@Config.Name("Frozen")
    public boolean FROZEN = true;

	@Config.Name("Toxic")
	public boolean TOXIC = true;

	@Config.Name("White Paint")
	public boolean WHITE_PAINT = true;

	@Config.Name("Purple Paint")
	public boolean PURPLE_PAINT = true;

	@Config.Name("Void")
	public boolean VOID = true;

	@Config.Name("Fire")
	public boolean FIRE = true;

	@Config.Name("Black Hole")
	public boolean BLACK_HOLE = true;
}