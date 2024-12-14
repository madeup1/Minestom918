package net.minestom.events;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.Consumer;

public class EventManager
{
    private HashMap<Class<? extends IEvent>, LinkedList<Hook>> map;
    private String owner;
    public EventManager(String name)
    {
        this.owner = name;
        this.map = new HashMap<>();
    }

    public <T extends IEvent> void register(Class<T> clazz, Consumer<T> consumer)
    {
        if (!map.containsKey(clazz))
        {
            map.put(clazz, new LinkedList<>());
        }

        map.get(clazz).addLast(new Hook<>(consumer));
    }

    public boolean post(IEvent event)
    {
        if (!map.containsKey(event.getClass()))
            return false;

        map.get(event.getClass()).forEach(c -> {
            c.call(event);
        });

        if (event instanceof CancellableEvent cancellableEvent)
        {
            return cancellableEvent.cancelled();
        }

        return false;
    }

    private record Hook<T>(Consumer<T> consumer)
    {

        public void call(T event)
        {
            consumer.accept((T) event);
        }
    }
}
