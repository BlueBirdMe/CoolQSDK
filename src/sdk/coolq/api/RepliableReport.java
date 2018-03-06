/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.api;

import java.lang.reflect.InvocationTargetException;
import sdk.event.CoolQEvent;
import sdk.event.CoolQEventException;
import sdk.event.CoolQEventManager;
import sdk.event.RepliableEvent;
import sdk.util.bean.Ignore;

/**
 *
 * @author zyp
 * @param <E>
 * @param <R>
 */
public abstract class RepliableReport<E extends RepliableEvent, R extends IReply> extends IReport<E> {

    @Ignore
    protected R reply;

    @Override
    public R getReply() {
        return reply;
    }

    @Override
    public void fire() throws  CoolQEventException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        E e = (E) CoolQEvent.getConstructor(getEventClass(), getClass()).newInstance(this);
        CoolQEventManager.getInstance().callEvent(e);
        reply = (R) e.getReply();
    }
}
