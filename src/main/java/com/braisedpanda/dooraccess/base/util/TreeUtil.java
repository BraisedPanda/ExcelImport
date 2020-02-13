package com.braisedpanda.dooraccess.base.util;

import com.braisedpanda.dooraccess.base.model.JwtUserModel;
import com.braisedpanda.dooraccess.base.model.TreeNode;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JiC
 * @Description:
 * @date 2019/6/20
 */
public class TreeUtil {


    /**
     * @param treeNodes
     * @param root
     * @Deprecated 递归组合树
     * @return: java.util.List<T>
     * @Author: JiC
     * @date: 2019/7/15
     */
    public static <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes, Integer root) {
        List<T> trees = Lists.newArrayList();
        for (T treeNode : treeNodes) {
            if (root.equals(treeNode.getParentId())) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    /**
     * @param treeNode
     * @param treeNodes
     * @Deprecated 递归查找子节点
     * @return: T
     * @Author: JiC
     * @date: 2019/7/16
     */
    public static <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {
        int i = 0;
        for (T it : treeNodes) {
            if (treeNode.getId().equals(it.getParentId())) {
                ++i;
                treeNode.add(findChildren(it, treeNodes));
            }
        }
        if (i <= 0) {
            treeNode.setIsParent(false);
        } else {
            treeNode.setIsParent(true);
        }
        return treeNode;
    }

    /**
     * @param cha 子集
     * @Deprecated 向上找父级，包括他自己
     * @return: java.util.List<com.ets.ecard.utils.TreeNode>
     * @Author: DengJun
     * @date: 2019/7/19
     */
    public static List<TreeNode> findParents(List<TreeNode> all, List<TreeNode> cha) {
        List<TreeNode> rue = new ArrayList<>();
        for (int a = 0; a < 4; a++) {
            rue = findPs(all, cha, rue);
            cha.addAll(rue);
        }
        return buildByRecursive(rue, -1);
    }

    public static List<TreeNode> findPs(List<TreeNode> all, List<TreeNode> cha, List<TreeNode> rue) {
        for (TreeNode treeNode : cha) {
            for (TreeNode b : all) {
                if (b.getId().equals( treeNode.getParentId()) || b.getId().equals(treeNode.getId())) {
                    if (!rue.contains(b)) {
                        rue.add(b);
                    }
                }
            }
        }
        return rue;
    }

    /**
     * @Deprecated 授权节点
     * @param treeNodes
     * @param ids
     * @param state 0:treeNodes是否在ids中  1：treeNodes选中ids
     * @return: java.util.List<com.braisedpanda.dooraccess.base.model.TreeNode>
     * @Author: JiC
     * @date: 2019/10/28
     */
    public static List<TreeNode> authNode(List<TreeNode> treeNodes, List<Integer> ids, int state) {
        List<TreeNode> tree;
        if (CollectionUtils.isEmpty(treeNodes) || CollectionUtils.isEmpty(ids)) {
            return treeNodes;
        }
        if (state == 0) {
            tree = treeNodes.stream().filter(node -> ids.contains(node.getId())).collect(Collectors.toList());
        } else {
            tree = treeNodes.stream().map(node -> {
                if (ids.contains(node.getId())) {
                    node.setChecked(true);
                }
                return node;
            }).collect(Collectors.toList());
        }
        return tree;
    }

    public static List<TreeNode> recursionAuthNode(List<TreeNode> treeNodes, List<Integer> ids) {
        if (CollectionUtils.isEmpty(treeNodes) || CollectionUtils.isEmpty(ids)) {
            return treeNodes;
        }
        for (TreeNode node : treeNodes) {
            if (ids.contains(node.getId())) {
                node.setChecked(true);
            }
            if (node.getIsParent()) {
                recursionAuthNode(node.getChildren(), ids);
            }
        }
        return treeNodes;
    }

    public static void recursionResideTree(List<TreeNode> regions, List<TreeNode> resideTree) {
        for (TreeNode node : regions) {
            if (node.getIsParent()) {
                recursionResideTree(node.getChildren(), resideTree);
            } else {
                List<TreeNode> nodes = resideTree.stream().filter(auth ->
                        node.getCityCode().equals(auth.getCityCode())).collect(Collectors.toList());
                node.setChildren(TreeUtil.buildByRecursive(nodes, -1));
            }
        }
    }

    /**
     * @Deprecated 构建顶级节点
     * @param jwtUserModel
     * @return: com.braisedpanda.dooraccess.base.model.TreeNode
     * @Author: JiC
     * @date: 2019/10/29
     */
    public static TreeNode buildTopNode(JwtUserModel jwtUserModel) {
        TreeNode treeNode = new TreeNode();
        treeNode.setOpen(true);
        treeNode.setName(jwtUserModel.getLegalPersonName());
        treeNode.setId(-1);
        return treeNode;
    }
}
