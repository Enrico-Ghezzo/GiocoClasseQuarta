package com.game.pokemon;

import com.almasb.fxgl.entity.component.Component;

public class VitaComponent extends Component {
    private int vita;

    @Override
    public void onUpdate(double tpf) {
        if(vita<=0){
            muori();
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
    public void muori(){
        entity.removeFromWorld();
    }
}
