package sdk.service;

/**
 * A registered service provider.
 *
 * @param <T> Service
 */
public class RegisteredServiceProvider<T> implements Comparable<RegisteredServiceProvider<?>> {

    private Class<T> service;
    private T provider;
    private ServicePriority priority;

    public RegisteredServiceProvider(Class<T> service, T provider, ServicePriority priority) {
        this.service = service;
        this.provider = provider;
        this.priority = priority;
    }

    public Class<T> getService() {
        return service;
    }

    public T getProvider() {
        return provider;
    }

    public ServicePriority getPriority() {
        return priority;
    }

    @Override
    public int compareTo(RegisteredServiceProvider<?> other) {
        if (priority.ordinal() == other.getPriority().ordinal()) {
            return 0;
        } else {
            return priority.ordinal() < other.getPriority().ordinal() ? 1 : -1;
        }
    }
}
