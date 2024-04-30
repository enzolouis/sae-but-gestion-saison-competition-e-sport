package classes;

public enum Notoriete {
    LOCAL(1f),
    REGIONAL(1.5f),
    NATIONAL(2f),
    INTERNATIONAL(2.25f),
    INTERNATIONAL_CLASSE(3f);

    private float base;

    Notoriete(float base) {
        this.base = base;
    }

    public float getBase() {
        return base;
    }

}
