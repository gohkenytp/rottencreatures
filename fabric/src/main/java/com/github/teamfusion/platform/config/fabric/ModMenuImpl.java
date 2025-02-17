package com.github.teamfusion.platform.config.fabric;

import com.github.teamfusion.rottencreatures.fabric.ConfigEntriesImpl;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;

public class ModMenuImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(ConfigEntriesImpl.class, parent).get();
    }
}