package net.voidgroup.aphrodite.render;

public interface ScreenDetails {
    default boolean void_client$excludeDarken() {
        return false;
    }
    default boolean void_client$excludeBlur() {return false;}
}
