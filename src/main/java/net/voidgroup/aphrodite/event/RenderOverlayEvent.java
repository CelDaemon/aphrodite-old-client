package net.voidgroup.aphrodite.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.gui.DrawContext;

public interface RenderOverlayEvent {
    Event<RenderOverlayEvent> EVENT = EventFactory.createArrayBacked(RenderOverlayEvent.class, (listeners) -> (drawContext) -> {
        for(var listener : listeners) {
            listener.render(drawContext);
        }
    });
    void render(DrawContext drawContext);
}
