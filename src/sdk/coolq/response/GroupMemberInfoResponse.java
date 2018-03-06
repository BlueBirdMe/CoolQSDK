/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.response;

/**
 *
 * @author zyp
 */
public class GroupMemberInfoResponse extends Response{
    /**
     * data 字段为 API 返回数据的内容，对于踢人、禁言等不需要返回数据的操作，这里为
     * null，对于获取群成员信息这类操作，这里为所获取的数据的对象，具体的数据内容将会在相应的 API 描述中给出。注意，异步版本的 API，data
     * 永远是 null，即使其相应的同步接口本身是有数据。
     * 需要子类覆盖这个属性
     */
    public GroupMemberInfo data;
}
