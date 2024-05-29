package com.game.pokemon;

import com.almasb.fxgl.app.scene.FXGLDefaultMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.Scene;
import javafx.scene.layout.Background;
import org.jetbrains.annotations.NotNull;

public class MainMenu extends FXGLDefaultMenu {
    private Music musicaMenu;

    public MainMenu(MenuType type) {
        super(type);
        musicaMenu = FXGL.getAssetLoader().loadMusic("musicaMenu.mp3"); //carica in memoria la musica del menu principale
    }

    @Override
    public void onEnteredFrom(@NotNull Scene prevState) {
            super.onEnteredFrom(prevState);

            FXGL.getAudioPlayer().stopAllSoundsAndMusic();  //ferma tutti i suoni e le musiche quando si passa al menu principale
            FXGL.getAudioPlayer().loopMusic(musicaMenu);    //fa iniziare la musica del menu

    }

    @Override
    public void onExitingTo(@NotNull Scene nextState) {
        super.onExitingTo(nextState);
        FXGL.getAudioPlayer().stopAllSoundsAndMusic();  //ferma tutti i suoni e le musiche del menu principale quando si esce dal menu
    }
}
