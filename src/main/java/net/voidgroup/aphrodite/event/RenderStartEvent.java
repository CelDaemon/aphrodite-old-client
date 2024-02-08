package net.voidgroup.aphrodite.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface RenderStartEvent {
    Event<RenderStartEvent> EVENT = EventFactory.createArrayBacked(RenderStartEvent.class, (listeners) -> () -> {
        for(var listener : listeners) {
            listener.start();
        }
    });
    void start();
}
