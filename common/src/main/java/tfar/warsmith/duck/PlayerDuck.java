package tfar.warsmith.duck;

import tfar.warsmith.entity.KusarigamaEntity;

import javax.annotation.Nullable;

public interface PlayerDuck {

    @Nullable
    KusarigamaEntity getKusarigama();
    void setKusarigama(KusarigamaEntity kusarigama);
    boolean hasOpportunisticStrike();
    void setOpportunisticStrike(boolean opportunity);

    boolean isChargedBaseballBat();
    void setChargedBaseballBat(boolean value);

}
