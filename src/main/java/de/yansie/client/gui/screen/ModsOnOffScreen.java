package de.yansie.client.gui.screen;

import de.yansie.client.mods.OnOffMods;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class ModsOnOffScreen extends Screen {
    private static Screen sc = null;
    public ModsOnOffScreen(Component p_96550_,Screen p_96551_) {
        super(p_96550_);
        sc = p_96551_;
    }

    protected void init() {

        int i = 0;
        for(OnOffMods mod : OnOffMods.values()) {
            this.addRenderableWidget(CycleButton.onOffBuilder(mod.getValue()).create(this.width / 2 - 155 + i % 2 * 160, this.height / 6 + 24 * (i >> 1), 150, 20,Component.literal(mod.name()),(p_12913_, p_12243_) -> {
                mod.setBoolean(p_12243_);
            }
            ));
            ++i;
        }
        ++i;
        if (i % 2 == 1) {
            ++i;
        }
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (p_280828_) -> {
            this.minecraft.setScreen(sc);
        }).bounds(this.width / 2 - 100, this.height / 6 + 24 * (i >> 1), 200, 20).build());
    }
    public boolean shouldCloseOnEsc() {
        return true;
    }
    public void render(GuiGraphics p_282063_, int p_283510_, int p_283109_, float p_283448_) {
        super.render(p_282063_, p_283510_, p_283109_, p_283448_);
        p_282063_.drawCenteredString(this.font, this.title, this.width / 2, 20, 16777215);
    }
}
