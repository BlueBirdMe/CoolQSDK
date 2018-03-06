package sdk.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Manages services and service providers. Services are an interface
 * specifying a list of methods that a provider must implement. Providers are
 * implementations of these services. A provider can be queried from the
 * services manager in order to use a service (if one is available). If
 * multiple plugins register a service, then the service with the highest
 * priority takes precedence.
 */
public class ServicesManager {

    public static final ServicesManager INSTANCE = new ServicesManager();
    /**
     * Map of providers.
     */
    private final Map<Class<?>, List<RegisteredServiceProvider<?>>> providers = new HashMap<Class<?>, List<RegisteredServiceProvider<?>>>();

    /**
     * Register a provider of a service.
     *
     * @param <T> Provider
     * @param service service class
     * @param provider provider to register
     * @param plugin plugin with the provider
     * @param priority priority of the provider
     */
    public <T> void register(Class<T> service, T provider, ServicePriority priority) {
        RegisteredServiceProvider<T> registeredProvider = null;
        synchronized (providers) {
            List<RegisteredServiceProvider<?>> registered = providers.get(service);
            if (registered == null) {
                registered = new ArrayList<RegisteredServiceProvider<?>>();
                providers.put(service, registered);
            }
            registeredProvider = new RegisteredServiceProvider<T>(service, provider, priority);
            // Insert the provider into the collection, much more efficient big O than sort
            int position = Collections.binarySearch(registered, registeredProvider);
            if (position < 0) {
                registered.add(-(position + 1), registeredProvider);
            } else {
                registered.add(position, registeredProvider);
            }

        }
    }

    /**
     * Unregister a particular provider for a particular service.
     *
     * @param service The service interface
     * @param provider The service provider implementation
     */
    public void unregister(Class<?> service, Object provider) {
        synchronized (providers) {
            Iterator<Map.Entry<Class<?>, List<RegisteredServiceProvider<?>>>> it = providers.entrySet().iterator();
            try {
                while (it.hasNext()) {
                    Map.Entry<Class<?>, List<RegisteredServiceProvider<?>>> entry = it.next();

                    // We want a particular service
                    if (entry.getKey() != service) {
                        continue;
                    }

                    Iterator<RegisteredServiceProvider<?>> it2 = entry.getValue().iterator();

                    try {
                        // Removed entries that are from this plugin

                        while (it2.hasNext()) {
                            RegisteredServiceProvider<?> registered = it2.next();

                            if (registered.getProvider() == provider) {
                                it2.remove();
                            }
                        }
                    } catch (NoSuchElementException e) { // Why does Java suck
                    }

                    // Get rid of the empty list
                    if (entry.getValue().size() == 0) {
                        it.remove();
                    }
                }
            } catch (NoSuchElementException e) {
            }
        }
    }

    /**
     * Unregister a particular provider.
     *
     * @param provider The service provider implementation
     */
    public void unregister(Object provider) {
        synchronized (providers) {
            Iterator<Map.Entry<Class<?>, List<RegisteredServiceProvider<?>>>> it = providers.entrySet().iterator();

            try {
                while (it.hasNext()) {
                    Map.Entry<Class<?>, List<RegisteredServiceProvider<?>>> entry = it.next();
                    Iterator<RegisteredServiceProvider<?>> it2 = entry.getValue().iterator();

                    try {
                        // Removed entries that are from this plugin

                        while (it2.hasNext()) {
                            RegisteredServiceProvider<?> registered = it2.next();

                            if (registered.getProvider().equals(provider)) {
                                it2.remove();
                            }
                        }
                    } catch (NoSuchElementException e) { // Why does Java suck
                    }

                    // Get rid of the empty list
                    if (entry.getValue().size() == 0) {
                        it.remove();
                    }
                }
            } catch (NoSuchElementException e) {
            }
        }
    }

    /**
     * Queries for a provider. This may return if no provider has been
     * registered for a service. The highest priority provider is returned.
     *
     * @param <T> The service interface
     * @param service The service interface
     *
     * @return provider or null
     */
    public <T> T load(Class<T> service) {
        synchronized (providers) {
            List<RegisteredServiceProvider<?>> registered = providers.get(service);
            if (registered == null) {
                return null;
            }
            // This should not be null!
            return service.cast(registered.get(0).getProvider());
        }
    }

    /**
     * Queries for a provider registration. This may return if no provider
     * has been registered for a service.
     *
     * @param <T> The service interface
     * @param service The service interface
     *
     * @return provider registration or null
     */
    @SuppressWarnings("unchecked")
    public <T> RegisteredServiceProvider<T> getRegistration(Class<T> service) {
        synchronized (providers) {
            List<RegisteredServiceProvider<?>> registered = providers.get(service);
            if (registered == null) {
                return null;
            }
            // This should not be null!
            return (RegisteredServiceProvider<T>) registered.get(0);
        }
    }

    /**
     * Get registrations of providers for a service. The returned list is
     * an unmodifiable copy.
     *
     * @param <T> The service interface
     * @param service The service interface
     *
     * @return a copy of the list of registrations
     */
    @SuppressWarnings("unchecked")
    public <T> List<RegisteredServiceProvider<T>> getRegistrations(Class<T> service) {
        ArrayList<RegisteredServiceProvider<T>> ret;
        synchronized (providers) {
            List<RegisteredServiceProvider<?>> registered = providers.get(service);
            if (registered == null) {
                return new ArrayList();
            }
            ret = new ArrayList();
            registered.forEach((provider) -> {
                ret.add((RegisteredServiceProvider<T>) provider);
            });
        }
        return Collections.unmodifiableList(ret);
    }

    /**
     * Get a list of known services. A service is known if it has registered
     * providers for it.
     *
     * @return a copy of the set of known services
     */
    public Set<Class<?>> getKnownServices() {
        synchronized (providers) {
            return Collections.unmodifiableSet(providers.keySet());
        }
    }

    /**
     * Returns whether a provider has been registered for a service.
     *
     * @param <T> service
     * @param service service to check
     *
     * @return true if and only if there are registered providers
     */
    public <T> boolean isProvidedFor(Class<T> service) {
        synchronized (providers) {
            return providers.containsKey(service);
        }
    }
}
