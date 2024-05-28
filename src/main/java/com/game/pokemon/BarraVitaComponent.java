package com.game.pokemon;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.ui.ProgressBar;
import javafx.scene.paint.Color;

import java.awt.*;

public class BarraVitaComponent extends Component {
    private ProgressBar progressBar = new ProgressBar(false);
    public BarraVitaComponent() {
        progressBar.setMaxValue(100);
        progressBar.setMinValue(0);
        progressBar.setCurrentValue(100);

        progressBar.setFill(Color.rgb(255, 0, 0));
        progressBar.setWidth(50);
        progressBar.setHeight(10);
    }

    @Override
    public void onAdded() {
        FXGL.getGameScene().addUINode(progressBar);
    }

    @Override
    public void onUpdate(double tpf) {
        progressBar.setCurrentValue(entity.getComponent(PlayerControl.class).getVita());
    }

    public void setPosition(double x, double y, double paddingFromBottom) {
        progressBar.setTranslateX(x - 20);
        progressBar.setTranslateY(y - paddingFromBottom);
    }
}
