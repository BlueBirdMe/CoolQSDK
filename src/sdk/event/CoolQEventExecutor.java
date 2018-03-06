package sdk.event;

import sdk.event.CoolQEvent;
import sdk.event.CoolQEventException;
import sdk.event.CoolQListener;

/**
 * 定义了事件调用插件的类的接口。
 */
public interface CoolQEventExecutor {
    public void execute(CoolQListener listener, CoolQEvent event) throws CoolQEventException;
}