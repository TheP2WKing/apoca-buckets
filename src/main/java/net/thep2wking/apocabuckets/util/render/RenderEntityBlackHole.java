package net.thep2wking.apocabuckets.util.render;

import java.util.ArrayList;
import java.util.Random;

import javax.vecmath.Vector3f;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thep2wking.apocabuckets.content.entity.EntityBlackHole;

@SideOnly(Side.CLIENT)
public class RenderEntityBlackHole extends Render<EntityBlackHole> {
	private float rotX;
	private float rotY;
	private float rotZ;
	private ArrayList<Vector3f> partz;

	public RenderEntityBlackHole(RenderManager renderManager) {
		super(renderManager);
		this.partz = new ArrayList<>();
	}
	
	@Override
	public ResourceLocation getEntityTexture(EntityBlackHole entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}

	@Override
	public void doRender(EntityBlackHole entity, double x, double y, double z, float entityYaw, float partialTicks) {
		if (new Random().nextInt(15) == 0) {
			this.partz.add(new Vector3f(new Random().nextFloat() * 4.0f - 2.0f, new Random().nextFloat() * 4.0f - 2.0f,
					new Random().nextFloat() * 4.0f - 2.0f));
		}
		World world = entity.getEntityWorld();
		Block block = Block.getBlockById(new Random().nextInt(255));
		this.drawBlock(entity, block, world, x, y, z, 1.0f);
		this.rotX += new Random().nextFloat() / 530.0f;
		this.rotY += new Random().nextFloat() / 650.0f;
		this.rotZ += new Random().nextFloat() / 750.0f;
		for (Vector3f part : this.partz) {
			float speed;
			if (part.x == 0.0f || part.y == 0.0f || part.z == 0.0f)
				continue;
			block = Block.getBlockById(new Random().nextInt(255));
			this.drawBlock(entity, block, world, x + part.x, y + part.y, z + part.z, new Random().nextFloat() * 0.25f);
			if (part.x != 0.0f) {
				speed = new Random().nextFloat() / 100.0f;
				if (part.x < 0.0f) {
					speed *= -1.0f;
				}
				part.x = Math.abs(part.x) > speed ? (part.x -= speed) : 0.0f;
			}
			if (part.y != 0.0f) {
				speed = new Random().nextFloat() / 100.0f;
				if (part.y < 0.0f) {
					speed *= -1.0f;
				}
				part.y = Math.abs(part.y) > speed ? (part.y -= speed) : 0.0f;
			}
			if (part.z == 0.0f)
				continue;
			speed = new Random().nextFloat() / 100.0f;
			if (part.z < 0.0f) {
				speed *= -1.0f;
			}
			if (Math.abs(part.z) > speed) {
				part.z -= speed;
				continue;
			}
			part.z = 0.0f;
		}
	}

	public void drawBlock(EntityBlackHole entity, Block block, World world, double x, double y, double z, float scale) {
		Vector3f v = new Vector3f(this.rotX, this.rotY, this.rotZ);
		// int i = MathHelper.floor(entity.posX);
		// int j = MathHelper.floor(entity.posY);
		// int k = MathHelper.floor(entity.posZ);
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y, (float) z);
		// GlStateManager.translate((float) x - 0.5, (float) y, (float) z + 0.5);
		// GlStateManager.translate(0.5f, 0.5f, 0.5f);
		GlStateManager.rotate((float) Math.toDegrees(v.y), 1.0f, 0.0f, 0.0f);
		GlStateManager.rotate((float) Math.toDegrees(v.x), 0.0f, 1.0f, 0.0f);
		GlStateManager.rotate((float) Math.toDegrees(v.z), 0.0f, 0.0f, 1.0f);
		GlStateManager.scale(scale, scale, scale);
		this.bindEntityTexture(entity);
		GlStateManager.disableLighting();
		if (block != null) {
			Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(block.getDefaultState(), 1.0f);
		}
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}

	public static final RenderEntityBlackHole.Factory FACTORY = new RenderEntityBlackHole.Factory();

	public static class Factory implements IRenderFactory<EntityBlackHole> {
		@Override
		public Render<? super EntityBlackHole> createRenderFor(RenderManager manager) {
			return new RenderEntityBlackHole(manager);
		}
	}
}