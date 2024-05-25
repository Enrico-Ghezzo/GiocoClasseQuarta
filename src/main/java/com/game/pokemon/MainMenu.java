package com.game.pokemon;

import com.almasb.fxgl.app.scene.FXGLDefaultMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.Scene;
import org.jetbrains.annotations.NotNull;

public class MainMenu extends FXGLDefaultMenu {
    private Music musicaMenu;

    public MainMenu(MenuType type) {
        super(type);
        musicaMenu = FXGL.getAssetLoader().loadMusic("musicaMenu.mp3");
    }

    @Override
    public void onEnteredFrom(@NotNull Scene prevState) {
            super.onEnteredFrom(prevState);
            // Stop any game music
            FXGL.getAudioPlayer().stopAllSoundsAndMusic();

            // Load and play the main menu music
            var menuMusic = FXGL.getAssetLoader().loadMusic("musicaMenu.mp3");
            FXGL.getAudioPlayer().loopMusic(menuMusic);

    }

    @Override
    public void onExitingTo(@NotNull Scene nextState) {
        super.onExitingTo(nextState);
        FXGL.getAudioPlayer().stopAllSoundsAndMusic();
    }
}
