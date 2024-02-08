package net.voidgroup.aphrodite.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface RenderShaderEvent {
    Event<RenderShaderEvent> EVENT = EventFactory.createArrayBacked(RenderShaderEvent.class, (listeners) -> tickDelta -> {
        for(var listener : listeners) {
            listener.render(tickDelta);
        }
    });
    void render(float tickDelta);
}
