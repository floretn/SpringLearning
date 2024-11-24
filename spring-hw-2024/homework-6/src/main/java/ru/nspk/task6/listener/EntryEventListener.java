package ru.nspk.task6.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.nspk.task6.model.EntryId;

@Slf4j
@Service
public class EntryEventListener {

    @EventListener(EntryId.class)
    public void onEntryIdEvent(@NonNull EntryId entryId) {
        log.info("#EntryListener received. Entry event id = {}", entryId.id());
    }

    @EventListener(EntryEvent.class)
    public void onEntryIdEventFull(@NonNull EntryEvent entryEvent) {
        log.info(
                "#EntryListener received. Entry id = {}, event type: {}",
                entryEvent.id().id(),
                entryEvent.eventType());
    }
}
