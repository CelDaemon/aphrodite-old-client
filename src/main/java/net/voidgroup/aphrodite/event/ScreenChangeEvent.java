package net.voidgroup.aphrodite.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface ScreenChangeEvent {
    Event<ScreenChangeEvent> EVENT = EventFactory.createArrayBacked(ScreenChangeEvent.class, (listeners) -> () -> {
        for(var listener : listeners) {
            listener.change();
        }
    });
    void change();
}
