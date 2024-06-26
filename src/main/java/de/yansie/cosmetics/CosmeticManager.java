package de.yansie.cosmetics;

public interface CosmeticManager<T> {

    void setActive(T t);

    T getCurrent();

    void tick();

    void render();
}
