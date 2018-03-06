/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.event;

import sdk.coolq.api.IGroupEvent;
import sdk.event.asynchro.GroupManagerChangeEvent;

/**
 * 管理员变动
 *
 * @author zyp
 */
public class GroupManagerChange extends IGroupEvent<GroupManagerChangeEvent> {

    /**
     * string	"set"、"unset"	事件子类型，分别表示设置和取消管理员
     */
    public String sub_type;

    @Override
    public Class<GroupManagerChangeEvent> getEventClass() {
        return GroupManagerChangeEvent.class;
    }
}
