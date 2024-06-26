package de.yansie.client.gui.screen;

import de.yansie.Client;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class WelcomeScreen extends Screen {
    public WelcomeScreen(Component p_96550_) {
        super(p_96550_);
    }
    private MultiLineLabel message = MultiLineLabel.EMPTY;

    protected void init() {
        this.addRenderableWidget(Button.builder(Component.literal("Fortfahren"), (p_280810_) -> {
            this.minecraft.setScreen(new TitleScreen());
        }).bounds(this.width / 2 - 155, this.height / 4 + 120 + 12, 300, 20).build());
        this.message = MultiLineLabel.create(this.font, Component.literal("Herzlich willkommen im Y-Client!"), 295);

    }

    public boolean shouldCloseOnEsc() {
        return true;
    }

    public void render(GuiGraphics p_283359_, int p_96296_, int p_96297_, float p_96298_) {
        super.render(p_283359_, p_96296_, p_96297_, p_96298_);
        p_283359_.drawCenteredString(this.font, this.title, this.width / 2, this.height / 4 - 60 + 20, 16777215);
        this.message.renderLeftAligned(p_283359_, this.width / 2 - 145, this.height / 4, 9, 10526880);

    }
}
