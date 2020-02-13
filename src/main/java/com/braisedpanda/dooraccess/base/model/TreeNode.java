package com.braisedpanda.dooraccess.base.model;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 节点
 * @author JiC
 * @date 2019-07-15 16:38
 */
@Data
@Accessors(chain = true)
public class TreeNode implements Serializable {

    private static final long serialVersionUID = 1679966350746070368L;

    private Integer id;

    private Integer parentId;

    private String name;

    private Integer flag;

    private String code;

    private String pCode;

    private String icon;

    private Boolean open;

    private Boolean isParent;

    private String cityCode;//城市编码

    private Integer regionId;//区域id

    private Integer levelIndex;//区域层级

    /**
     * 节点是否被选中
     * 2019/9/18
     */
    private Boolean checked;

    private String url;

    private Integer enable;

    private Integer sort;

    private List<TreeNode> children;

    public void add(TreeNode node) {
        if (children == null) {
            children = Lists.newArrayList();
        }
        children.add(node);
    }
}
