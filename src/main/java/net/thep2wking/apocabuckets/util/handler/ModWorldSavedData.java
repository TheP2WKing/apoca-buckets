package net.thep2wking.apocabuckets.util.handler;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.thep2wking.apocabuckets.ApocaBuckets;

public class ModWorldSavedData extends WorldSavedData {
    public static final String APOCABUCKETS_DATA = ApocaBuckets.MODID;
    private boolean stopApocalypse = false;
    private boolean isBloodMoon = false;
    private int bloodMoonTicks = 0;

    public ModWorldSavedData() {
        super(APOCABUCKETS_DATA);
    }

    public ModWorldSavedData(String name) {
        super(name);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        stopApocalypse = nbt.getBoolean("stopApocalypse");
        isBloodMoon = nbt.getBoolean("isBloodMoon");
        bloodMoonTicks = nbt.getInteger("bloodMoonTicks");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setBoolean("stopApocalypse", stopApocalypse);
        compound.setBoolean("isBloodMoon", isBloodMoon);
        compound.setInteger("bloodMoonTicks", bloodMoonTicks);
        return compound;
    }

    public boolean isStopApocalypse() {
        return stopApocalypse;
    }

    public boolean isBloodMoon() {
        return isBloodMoon;
    }

    public int getBloodMoonTicks() {
        return bloodMoonTicks;
    }

    public void setStopApocalypse(boolean stopApocalypse) {
        this.stopApocalypse = stopApocalypse;
        markDirty();
    }

    public void setBloodMoon(boolean isBloodMoon) {
        this.isBloodMoon = isBloodMoon;
        markDirty();
    }

    public void setBloodMoonTicks(int bloodMoonTicks) {
        this.bloodMoonTicks = bloodMoonTicks;
        markDirty();
    }

    public void toggleStopApocalypse() {
        this.stopApocalypse = !this.stopApocalypse;
        markDirty();
    }

    public void toggleBloodMoon() {
        this.isBloodMoon = !this.isBloodMoon;
        markDirty();
    }

    public static ModWorldSavedData get(World world) {
        ModWorldSavedData apocalypseData = (ModWorldSavedData) world.loadData(ModWorldSavedData.class, APOCABUCKETS_DATA);
        if (apocalypseData == null) {
            apocalypseData = new ModWorldSavedData();
            world.setData(APOCABUCKETS_DATA, apocalypseData);
        }
        return apocalypseData;
    }

    public static boolean isApocalypseStopped(World world) {
        ModWorldSavedData data = get(world);
        return data.isStopApocalypse();
    }

    public static boolean isBloodMoon(World world) {
        ModWorldSavedData data = get(world);
        return data.isBloodMoon();
    }

    public static int getBloodMoonTicks(World world) {
        ModWorldSavedData data = get(world);
        return data.getBloodMoonTicks();
    }
}