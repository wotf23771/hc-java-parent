package com.hc.java.common.tree;

import java.util.List;

/**
 * 树节点
 *
 * @author wangxiaolei
 * @since 2021/10/11 23:44
 */
public interface TreeNode {

    String getId();

    String getParentId();

    TreeNode getParent();

    void setParent(TreeNode parent);

    List<TreeNode> getParentList();

    void setParentList(List<TreeNode> parentList);

    void setSn(int sn);

    int getSn();

    void setFullSn(String fullSn);

    String getFullSn();
}
