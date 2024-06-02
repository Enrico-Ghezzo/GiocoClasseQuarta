package com.game.pokemon;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.IntroScene;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Classe per modificare i menu
 */
public class FabbricaScene extends SceneFactory {
    //CREA IL NUOVO MENU PRINCIPALE
    public FXGLMenu newMainMenu(){
        return new MainMenu(MenuType.MAIN_MENU);
    }

    //CREA IL NUOVO MENU DEL GIOCO
    @Override
    public FXGLMenu newGameMenu() {
        return super.newGameMenu();
    }
}
