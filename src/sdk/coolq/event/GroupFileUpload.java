/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdk.coolq.event;

import sdk.coolq.api.GroupFileInfo;
import sdk.coolq.api.IGroupEvent;
import sdk.event.asynchro.GroupFileUploadEvent;

/**
 * 文件上传
 *
 * @author zyp
 */
public class GroupFileUpload extends IGroupEvent<GroupFileUploadEvent> {

    /**
     * object	-	文件信息
     */
    public GroupFileInfo file;//

    @Override
    public Class<GroupFileUploadEvent> getEventClass() {
        return GroupFileUploadEvent.class;
    }
}
