package net.voidgroup.aphrodite.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface ResizeShaderEvent {
    Event<ResizeShaderEvent> EVENT = EventFactory.createArrayBacked(ResizeShaderEvent.class, (listeners) -> (width, height) -> {
        for(var listener : listeners) {
            listener.resize(width, height);
        }
    });
    void resize(int width, int height);
}
