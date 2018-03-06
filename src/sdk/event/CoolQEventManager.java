/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author zyp
 */
public class CoolQEventManager {

    private static final CoolQEventManager INSTANCE = new CoolQEventManager();
    private static final LinkedBlockingQueue<AsynchroEvent> events = new LinkedBlockingQueue();
    private static Thread fireAsynchroEvent;

    public void init() {
        if (fireAsynchroEvent != null) {
            return;
        }
        //异步线程广播异步事件
        fireAsynchroEvent = new Thread(new Runnable() {
            @Override
            public void run() {
                CoolQEvent event = null;
                while (true) {
                    try {
                        event = events.take();
                        callEvent(event);
                    } catch (InterruptedException ex) {
                        System.out.println("asnychro executer interrupted:" + ex.getMessage());
                        ex.printStackTrace();
                    } catch (CoolQEventException ex) {
                        System.out.println("error when fire asynchro event:" + event.getEventName());
                        ex.printStackTrace();
                    }
                }
            }
        });
        fireAsynchroEvent.start();
    }

    public static CoolQEventManager getInstance() {
        return INSTANCE;
    }

    public void registerEvents(CoolQListener listener) {
        createRegisteredListeners(listener).entrySet().forEach((entry) -> {
            getEventListeners(getRegistrationClass(entry.getKey())).registerAll(entry.getValue());
        });
    }

    private CoolQHandlerList getEventListeners(Class<? extends CoolQEvent> type) {
        try {
            Method method = getRegistrationClass(type).getDeclaredMethod("getHandlerList");
            method.setAccessible(true);
            return (CoolQHandlerList) method.invoke(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Class<? extends CoolQEvent> getRegistrationClass(Class<? extends CoolQEvent> clazz) {
        try {
            clazz.getDeclaredMethod("getHandlerList");
            return clazz;
        } catch (NoSuchMethodException e) {
            if (clazz.getSuperclass() != null && !clazz.getSuperclass().equals(CoolQEvent.class) && CoolQEvent.class.isAssignableFrom(clazz.getSuperclass())) {
                return getRegistrationClass(clazz.getSuperclass().asSubclass(CoolQEvent.class));
            } else {
                throw new RuntimeException(e);
            }
        }
    }

    private Map<Class<? extends CoolQEvent>, Set<CoolQRegisteredListener>> createRegisteredListeners(CoolQListener listener) {
        Map<Class<? extends CoolQEvent>, Set<CoolQRegisteredListener>> ret = new HashMap();
        Set<Method> methods;
        try {
            Method[] publicMethods = listener.getClass().getMethods();
            Method[] privateMethods = listener.getClass().getDeclaredMethods();
            methods = new HashSet(publicMethods.length + privateMethods.length, 1.0f);
            methods.addAll(Arrays.asList(publicMethods));
            methods.addAll(Arrays.asList(privateMethods));
        } catch (NoClassDefFoundError e) {
            return ret;
        }
        for (final Method method : methods) {
            final CoolQEventHandler eh = method.getAnnotation(CoolQEventHandler.class);
            if (eh == null) {
                continue;
            }
            if (method.isBridge() || method.isSynthetic()) {
                continue;
            }
            final Class<?> checkClass;
            if (method.getParameterTypes().length != 1 || !CoolQEvent.class.isAssignableFrom(checkClass = method.getParameterTypes()[0])) {
                continue;
            }
            final Class<? extends CoolQEvent> eventClass = checkClass.asSubclass(CoolQEvent.class);
            method.setAccessible(true);
            Set<CoolQRegisteredListener> eventSet = ret.get(eventClass);
            if (eventSet == null) {
                eventSet = new HashSet();
                ret.put(eventClass, eventSet);
            }
            CoolQEventExecutor executor = new CoolQEventExecutor() {
                public void execute(CoolQListener listener, CoolQEvent event) throws CoolQEventException {
                    try {
                        if (!eventClass.isAssignableFrom(event.getClass())) {
                            return;
                        }
                        method.invoke(listener, event);
                    } catch (InvocationTargetException ex) {
                        throw new CoolQEventException(ex.getCause());
                    } catch (Throwable t) {
                        throw new CoolQEventException(t);
                    }
                }
            };
            eventSet.add(new CoolQRegisteredListener(listener, executor, eh.priority(), eh.ignoreCancelled()));
        }
        return ret;
    }

    /**
     * 异步广播事件
     *
     * @param event
     */
    public void callAsynchroEvent(AsynchroEvent event) {
        events.offer(event);
    }

    /**
     * Calls an event with the given details.
     * <p>
     * This method only synchronizes when the event is not asynchronous.
     *
     * @param event CoolQEvent details
     */
    public void callEvent(CoolQEvent event) throws CoolQEventException {
        if (event.isAsynchronous()) {
            if (Thread.holdsLock(this)) {
                throw new IllegalStateException(event.getEventName() + " cannot be triggered asynchronously from inside synchronized code.");
            }
            fireEvent(event);
        } else {
            synchronized (this) {
                fireEvent(event);
            }
        }
    }

    private void fireEvent(CoolQEvent event) throws CoolQEventException {
        CoolQHandlerList handlers = event.getHandlers();
        CoolQRegisteredListener[] listeners = handlers.getRegisteredListeners();
        for (CoolQRegisteredListener registration : listeners) {
            registration.callEvent(event);
        }
    }
}
