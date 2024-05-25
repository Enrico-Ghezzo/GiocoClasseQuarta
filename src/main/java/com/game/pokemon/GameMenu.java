package com.game.pokemon;

import com.almasb.fxgl.app.scene.FXGLDefaultMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.Scene;
import org.jetbrains.annotations.NotNull;

public class GameMenu extends FXGLDefaultMenu {

    public GameMenu(MenuType type) {
        super(type);
    }

    @Override
    public void onExitingTo(@NotNull Scene nextState) {
        super.onExitingTo(nextState);
        FXGL.getAudioPlayer().stopAllSoundsAndMusic();
    }
}
