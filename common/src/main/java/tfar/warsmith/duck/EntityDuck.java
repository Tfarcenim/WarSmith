package tfar.warsmith.duck;

public interface EntityDuck {

    void setRemainingSoulFireTicks(int pRemainingFireTicks);

    int getRemainingSoulFireTicks();

    /**
     * Removes fire from entity.
     */
    void clearSoulFire();
    void setSecondsOnSoulFire(int pSeconds);

    boolean isOnSoulFire();
    void setFlagOnSoulFire(boolean pIsOnFire);
    boolean displaySoulFireAnimation();

}
