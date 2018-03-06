/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.message.reply;

import sdk.coolq.api.Atable;

/**
 *
 * @author zyp
 */
public class GroupMessageReply extends Atable{
    /**
     * 撤回该条消息
     */
    public boolean delete;
    /**
     * 把发送者踢出群组（需要登录号权限足够），不拒绝此人后续加群请求，发送者是匿名用户时无效
     */
    public boolean kick;
    /**
     * 把发送者禁言 30 分钟（需要登录号权限足够），对匿名用户也有效，不支持指定禁言时长（如需指定，请调用相应 API）
     */
    public boolean ban;
}
