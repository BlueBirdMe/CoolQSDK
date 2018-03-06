/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.api;

/**
 *
 * @author zyp
 */
public abstract class Atable extends Repliable{
    /**
     * 是否要在回复开头 at 发送者（自动添加）
     */
    public boolean at_sender;
}
