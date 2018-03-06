/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.api;

/**
 * 转义
 * @author zyp
 */
public abstract class Escapable implements IReply{
    /**
     * 消息内容是否作为纯文本发送（即不解析 CQ 码），message 数据类型为 array 时无效
     */
    public boolean auto_escape = true;
}
