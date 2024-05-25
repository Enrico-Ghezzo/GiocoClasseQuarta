package com.game.pokemon;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;

public class FabbricaScene extends SceneFactory {
    public FXGLMenu newMainMenu(){
        return new MainMenu(MenuType.MAIN_MENU);
    }

    @Override
    public FXGLMenu newGameMenu() {
        return super.newGameMenu();
    }
}
