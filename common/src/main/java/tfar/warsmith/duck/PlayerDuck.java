package tfar.warsmith.duck;

import tfar.warsmith.entity.KusarigamaEntity;
import tfar.warsmith.item.KusarigamaItem;

import javax.annotation.Nullable;

public interface PlayerDuck {

    @Nullable
    KusarigamaEntity getKusarigama();
    void setKusarigama(KusarigamaEntity kusarigama);

}
