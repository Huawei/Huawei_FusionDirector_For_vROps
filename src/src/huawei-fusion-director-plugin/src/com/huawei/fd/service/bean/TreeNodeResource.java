package com.huawei.fd.service.bean;

import com.integrien.alive.common.adapter3.MetricData;
import com.integrien.alive.common.adapter3.ResourceKey;

import java.util.List;
import java.util.Map;

/**
 * Interface for tree node objects, such as node, processor, drive and so on.
 * @author harbor
 *
 */
public interface TreeNodeResource {
    
   /**
    * 
    * @param identifierPrefix
    * @param adapterKind
    * @param metricsByResource
    * @return
    */
    public ResourceKey convert2Resource(String identifierPrefix, String adapterKind, 
            Map<ResourceKey, List<MetricData>> metricsByResource);
    
    /**
     * 
     * @return
     */
    public String getResourceName();
    
    /**
     * 
     * @return
     */
    public String getResourceLabel();
    
    /**
     * 
     * @return
     */
    public String getResourceIdentifier();
    
    /**
     * 
     * @param child
     */
    public void addChild(TreeNodeResource child);
    
    /**
     * 
     * @return
     */
    public List<TreeNodeResource> getChildren();
    
    /**
     * 
     * @param children
     */
    public void addChildren(List<TreeNodeResource> children);
    
    public boolean allowRename();
    
}
