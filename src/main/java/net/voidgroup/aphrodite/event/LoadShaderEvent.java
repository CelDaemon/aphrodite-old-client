package net.voidgroup.aphrodite.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

import java.io.IOException;

public interface LoadShaderEvent {
    Event<LoadShaderEvent> EVENT = EventFactory.createArrayBacked(LoadShaderEvent.class, (listeners) -> () -> {
        for(var listener : listeners) {
            listener.load();
        }
    });
    void load() throws IOException;
}
