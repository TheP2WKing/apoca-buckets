package net.thep2wking.apocabuckets.config.categories;

import net.minecraftforge.common.config.Config;

public class Content {
	@Config.Name("Disaster Recovery Remote Controls Apocalypses")
    public boolean DISASTER_RECOVERY_REMOTE_CONTROLS_APOCALYPSES = true;

	@Config.Name("Disaster Recovery Remote Useable In Survival")
	public boolean DISASTER_RECOVERY_REMOTE_USEABLE_IN_SURVIVAL = true;

	@Config.Name("Apocalyptic Bucket Helmet Effects")
	public boolean APOCALYPTIC_BUCKET_HELMET_EFFECTS = true;

	@Config.Name("Register Forge Buckets For Fluids")
	public boolean REGISTER_FORGE_BUCKETS_FOR_FLUIDS = false;
}