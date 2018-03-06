/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.api;

import sdk.event.AsynchroEvent;

/**
 * 群相关
 *
 * @author zyp
 * @param <E>
 */
public abstract class IGroupEvent<E extends AsynchroEvent> extends IEvent<E> {

    /**
     * number	-	群号
     */
    public long group_id;//
    /**
     * number	-	发送者 QQ 号
     */
    public long user_id;//

}
