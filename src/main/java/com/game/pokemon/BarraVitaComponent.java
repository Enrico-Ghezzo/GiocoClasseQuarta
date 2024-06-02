package com.game.pokemon;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.ui.ProgressBar;
import javafx.scene.paint.Color;

/**
 * Classe per creare la barra della vita alle entità
 */
public class BarraVitaComponent extends Component {
    private ProgressBar progressBar = new ProgressBar(false);
    public BarraVitaComponent() {
        progressBar.setFill(Color.rgb(255, 0, 0));
        progressBar.setWidth(30);
        progressBar.setHeight(9);
    }

    @Override
    public void onAdded() {
        progressBar.setMaxValue(entity.getComponent(VitaComponent.class).getVita());
        progressBar.setMinValue(0);
        progressBar.setCurrentValue(100);
        entity.getViewComponent().addChild(progressBar);
        progressBar.setWidth(entity.getWidth());
        progressBar.setTranslateY(6.5 - 15);
    }

    @Override
    public void onUpdate(double tpf) {
        progressBar.setCurrentValue(entity.getComponent(VitaComponent.class).getVita());
    }
}
