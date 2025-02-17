package com.github.teamfusion.rottencreatures.forge;

import com.github.teamfusion.rottencreatures.RottenCreatures;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(RottenCreatures.MOD_ID)
public class RottenCreaturesForge {
    public RottenCreaturesForge() {
        RottenCreatures.bootstrap();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigEntriesImpl.COMMON_SPEC);
    }
}
