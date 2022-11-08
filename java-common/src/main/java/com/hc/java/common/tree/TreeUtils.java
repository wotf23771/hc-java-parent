package com.hc.java.common.tree;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 树结构工具类
 *
 * @author wangxiaolei
 * @since 2021/10/11 23:50
 */
public class TreeUtils {

    public static List<TreeNode> buildAndSort(List<TreeNode> nodeList) {
        // 1.建立Map缓存
        Map<String, TreeNode> mapCache = new HashMap<>();
        for (TreeNode org : nodeList) {
            mapCache.put(org.getId(), org);
        }
        // 2.设置节点Parent
        for (TreeNode org : nodeList) {
            org.setParent(mapCache.get(org.getParentId()));
        }
        // 3.设置节点 parentList
        for (TreeNode org : nodeList) {
            if (org.getParentList() == null) {
                org.setParentList(new ArrayList<>());
            }

            TreeNode parent = org.getParent();
            int count = 0;
            while (parent != null && count < 10) {
                org.getParentList().add(0, parent);
                parent = parent.getParent();
                count++;
            }
        }

        // 结果列表
        List<TreeNode> resultList = new ArrayList<>();

        // 4.计算节点 fullSn
        for (TreeNode org : nodeList) {
            StringBuffer fullSn_buffer = new StringBuffer();
            if (CollectionUtils.isNotEmpty(org.getParentList())) {
                for (TreeNode orgDTO : org.getParentList()) {
                    fullSn_buffer.append(StringUtils.leftPad(String.valueOf(orgDTO.getSn()), 10, "0"));
                }
            }
            fullSn_buffer.append(StringUtils.leftPad(String.valueOf(org.getSn()), 10, "0"));
            org.setFullSn(fullSn_buffer.toString());

            resultList.add(org);
        }

        // 进行排序
        resultList.sort(Comparator.comparing(TreeNode::getFullSn));
        return null;
    }
}
