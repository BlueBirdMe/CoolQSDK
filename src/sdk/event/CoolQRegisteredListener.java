package sdk.event;

/**
 * Stores relevant information for plugin listeners
 */
public class CoolQRegisteredListener {

    private final CoolQListener listener;
    private final CoolQEventPriority priority;
    private final CoolQEventExecutor executor;
    private final boolean ignoreCancelled;

    public CoolQRegisteredListener(final CoolQListener listener, final CoolQEventExecutor executor, final CoolQEventPriority priority, final boolean ignoreCancelled) {
        this.listener = listener;
        this.priority = priority;
        this.executor = executor;
        this.ignoreCancelled = ignoreCancelled;
    }

    /**
     * Gets the listener for this registration
     *
     * @return Registered CoolQListener
     */
    public CoolQListener getListener() {
        return listener;
    }

    /**
     * Gets the priority for this registration
     *
     * @return Registered Priority
     */
    public CoolQEventPriority getPriority() {
        return priority;
    }

    /**
     * Calls the event executor
     *
     * @param event The event
     *
     * @throws CoolQEventException If an event handler throws an exception.
     */
    public void callEvent(final CoolQEvent event) throws CoolQEventException {
        if (event instanceof CoolQCancellable) {
            if (((CoolQCancellable) event).isCancelled() && isIgnoringCancelled()) {
                return;
            }
        }
        executor.execute(listener, event);
    }

    /**
     * Whether this listener accepts cancelled events
     *
     * @return True when ignoring cancelled events
     */
    public boolean isIgnoringCancelled() {
        return ignoreCancelled;
    }
}
