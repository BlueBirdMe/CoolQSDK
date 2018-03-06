package sdk.event;

import java.util.*;
import java.util.Map.Entry;

/**
 * 一个处理事件的类, 存储每个事件。基于 lahwran's fevents.
 */
public class CoolQHandlerList {

    /**
     * 包含了所有HandlerList的数组.此数组字段是这个系统速度的关键.
     */
    private volatile CoolQRegisteredListener[] handlers = null;

    /**
     * 动态HandlerList.使用register()和unreguster()进行改动，当有任何改动的时候自动bake(合并)CoolQHandlerList.
     * <p>
     * 译注:“bake”的意思是“烤”，但意思根本对不上，根据源代码来分析大概是“合并”，或者“拷贝”
     * 下方出现的“bake”同.一般情况下这个字段的意义我们是不需要理解的.如果你知道其真正意义请告知我们.
     * <p>
     * 原文：Dynamic handler lists. These are changed using register() and
     * unregister() and are automatically baked to the handlers array any time
     * they have changed.
     */
    private final EnumMap<CoolQEventPriority, ArrayList<CoolQRegisteredListener>> handlerslots;

    /**
     * 所有已经创建的HandlerList,用于bakeAll().
     */
    private static final ArrayList<CoolQHandlerList> allLists = new ArrayList<CoolQHandlerList>();

    /**
     * 合并(bake)所有处理器列表.最好用在所有正常的事件注册完毕后,即所有插件都加载完了,如果你使用fevents插件系统.
     * <p>
     * 原文：Bake all handler lists. Best used just after all normal event
     * registration is complete, ie just after all plugins are loaded if
     * you're using fevents in a plugin system.
     */
    public static void bakeAll() {
        synchronized (allLists) {
            for (CoolQHandlerList h : allLists) {
                h.bake();
            }
        }
    }

    /**
     * 从所有处理器列表中注销所有监听器.
     * <p>
     * 原文：Unregister all listeners from all handler lists.
     */
    public static void unregisterAll() {
        synchronized (allLists) {
            for (CoolQHandlerList h : allLists) {
                synchronized (h) {
                    for (List<CoolQRegisteredListener> list : h.handlerslots.values()) {
                        list.clear();
                    }
                    h.handlers = null;
                }
            }
        }
    }

    /**
     * 从处理器列表中注销一个指定的监听器.
     * <p>
     * 原文：Unregister a specific listener from all handler lists.
     *
     * @param listener 要注销的监听器
     */
    public static void unregisterAll(CoolQListener listener) {
        synchronized (allLists) {
            for (CoolQHandlerList h : allLists) {
                h.unregister(listener);
            }
        }
    }

    /**
     * 用EventPriority来创建和初始化一个新的HandlerList.
     * <p>
     * HandlerList将会被添加到元列表，为了bakeAll()方法.
     * <p>
 原文：Create a new handler list and initialize using CoolQEventPriority.
 <p>
     * The HandlerList is then added to meta-list for use in bakeAll()
     */
    public CoolQHandlerList() {
        handlerslots = new EnumMap<CoolQEventPriority, ArrayList<CoolQRegisteredListener>>(CoolQEventPriority.class);
        for (CoolQEventPriority o : CoolQEventPriority.values()) {
            handlerslots.put(o, new ArrayList<CoolQRegisteredListener>());
        }
        synchronized (allLists) {
            allLists.add(this);
        }
    }

    /**
     * 在处理器列表中注册一个监听器.
     * <p>
     * 原文：Register a new listener in this handler list
     *
     * @param listener 要注册的监听器
     */
    public synchronized void register(CoolQRegisteredListener listener) {
        if (handlerslots.get(listener.getPriority()).contains(listener)) {
            throw new IllegalStateException("This listener is already registered to priority " + listener.getPriority().toString());
        }
        handlers = null;
        handlerslots.get(listener.getPriority()).add(listener);
    }

    /**
     * 在处理列表中注册一个监听器集合(批量注册监听器).
     * <p>
     * 原文：Register a collection of new listeners in this handler list
     *
     * @param listeners 要注册的监听器
     */
    public void registerAll(Collection<CoolQRegisteredListener> listeners) {
        for (CoolQRegisteredListener listener : listeners) {
            register(listener);
        }
    }

    /**
     * 从一个指定的排序的插槽删除一个监听器.
     * <p>
     * 译注：“插槽”的意思见本类的handlerslots变量.
     * <p>
     * 原文：Remove a listener from a specific order slot
     *
     * @param listener 要移除的监听器
     */
    public synchronized void unregister(CoolQRegisteredListener listener) {
        if (handlerslots.get(listener.getPriority()).remove(listener)) {
            handlers = null;
        }
    }

    /**
     * 从这个处理列表移除一个指定的监听器。
     * <p>
     * 原文：Remove a specific listener from this handler
     *
     * @param listener 要移除的监听器
     */
    public synchronized void unregister(CoolQListener listener) {
        boolean changed = false;
        for (List<CoolQRegisteredListener> list : handlerslots.values()) {
            for (ListIterator<CoolQRegisteredListener> i = list.listIterator(); i.hasNext();) {
                if (i.next().getListener().equals(listener)) {
                    i.remove();
                    changed = true;
                }
            }
        }
        if (changed) {
            handlers = null;
        }
    }

    /**
     * 合并(bake)一个HashMap和ArrayLists到二维数组 - 如果不必要，什么也不会做.
     * <p>
     * 原文：Bake HashMap and ArrayLists to 2d arrays - does nothing if not
     * necessary
     */
    public synchronized void bake() {
        if (handlers != null) {
            return; // don't re-bake when still valid
        }
        List<CoolQRegisteredListener> entries = new ArrayList<CoolQRegisteredListener>();
        for (Entry<CoolQEventPriority, ArrayList<CoolQRegisteredListener>> entry : handlerslots.entrySet()) {
            entries.addAll(entry.getValue());
        }
        handlers = entries.toArray(new CoolQRegisteredListener[entries.size()]);
    }

    /**
     * 获取与这个处理器列表相关的已注册的监听器.
     * <p>
     * 原文：Get the baked registered listeners associated with this handler list
     *
     * @return 注册过的监听器的数组
     */
    public CoolQRegisteredListener[] getRegisteredListeners() {
        CoolQRegisteredListener[] handlers;
        while ((handlers = this.handlers) == null) {
            bake(); // This prevents fringe cases of returning null
        }
        return handlers;
    }

    /**
     * 获取每个事件类型的所有处理器的列表.
     * <p>
     * 原文：Get a list of all handler lists for every event type
     *
     * @return 所有处理器的列表
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<CoolQHandlerList> getHandlerLists() {
        synchronized (allLists) {
            return (ArrayList<CoolQHandlerList>) allLists.clone();
        }
    }
}
