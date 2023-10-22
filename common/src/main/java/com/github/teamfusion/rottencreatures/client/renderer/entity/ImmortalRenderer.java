package com.github.teamfusion.rottencreatures.client.renderer.entity;

import com.github.teamfusion.rottencreatures.RottenCreatures;
import com.github.teamfusion.rottencreatures.client.model.ImmortalModel;
import com.github.teamfusion.rottencreatures.client.model.LayerBuilder;
import com.github.teamfusion.rottencreatures.client.renderer.entity.layers.DashAttackLayer;
import com.github.teamfusion.rottencreatures.client.renderer.entity.layers.ImmortalOverlayLayer;
import com.github.teamfusion.rottencreatures.client.renderer.entity.layers.ImpaledLayer;
import com.github.teamfusion.rottencreatures.common.entities.Immortal;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

public class ImmortalRenderer<T extends Immortal> extends HumanoidMobRenderer<T, ImmortalModel<T>> {
    public static final LayerBuilder LAYER = LayerBuilder.of("immortal");

    public ImmortalRenderer(EntityRendererProvider.Context context) {
        super(context, new ImmortalModel<>(context.bakeLayer(LAYER.getMain())), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
        this.addLayer(new ImpaledLayer<>(this, context.getModelSet()));
        this.addLayer(new DashAttackLayer<>(this, context.getModelSet()));
        this.addLayer(new ImmortalOverlayLayer<>(this, context.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(Immortal mob) {
        return new ResourceLocation(RottenCreatures.MOD_ID, "textures/entity/immortal/immortal.png");
    }

    @Override
    protected void setupRotations(T immortal, PoseStack matrices, float animationProgress, float bodyYaw, float tickDelta) {
        super.setupRotations(immortal, matrices, animationProgress, bodyYaw, tickDelta);
        if (!((double)immortal.walkAnimation.speed() < 0.01D)) {
            float timestamp = immortal.walkAnimation.position() - immortal.walkAnimation.speed() * (1.0F - tickDelta) + 6.0F;
            float degreeModifier = (Math.abs(timestamp % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrices.mulPose(Axis.ZP.rotationDegrees(6.5F * degreeModifier));
        }
    }
}