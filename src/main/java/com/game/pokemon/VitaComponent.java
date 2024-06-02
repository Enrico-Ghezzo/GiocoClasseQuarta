package com.game.pokemon;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

/**
 * Classe per creare il componente della vita nelle entità
 */
public class VitaComponent extends Component {
    private int vita;
    private boolean isDead = false;

    @Override
    public void onUpdate(double tpf) {
        if(vita<=0){
            isDead = true;
        }
    }

    public VitaComponent(int vita) {
        this.vita = vita;
    }

    public int getVita() {
        return vita;
    }

    public void prendiDanno(int danno){
        vita = vita - danno;
    }
    public void muori(AnimatedTexture t, AnimationChannel a){
        if(t == null || a == null){
            entity.removeFromWorld();
        }
        else{
            t.playAnimationChannel(a);
            t.setOnCycleFinished(() -> {
                entity.removeFromWorld();
            });
        }
    }

    public boolean isDead() {
        return isDead;
    }
}
