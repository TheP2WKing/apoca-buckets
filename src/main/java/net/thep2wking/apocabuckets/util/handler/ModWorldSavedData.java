package net.thep2wking.apocabuckets.util.handler;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;
import net.thep2wking.apocabuckets.ApocaBuckets;

public class ModWorldSavedData extends WorldSavedData {
    public static final String APOCABUCKETS_DATA = ApocaBuckets.MODID;
    private boolean stopApocalypse = false;

    public ModWorldSavedData() {
        super(APOCABUCKETS_DATA);
    }

    public ModWorldSavedData(String name) {
        super(name);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        stopApocalypse = nbt.getBoolean("stopApocalypse");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setBoolean("stopApocalypse", stopApocalypse);
        return compound;
    }

    public boolean isStopApocalypse() {
        return stopApocalypse;
    }

    public void setStopApocalypse(boolean stopApocalypse) {
        this.stopApocalypse = stopApocalypse;
        markDirty();
    }

    public void toggleStopApocalypse() {
        this.stopApocalypse = !this.stopApocalypse;
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
}