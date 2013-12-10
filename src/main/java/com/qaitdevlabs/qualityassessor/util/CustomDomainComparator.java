package com.qaitdevlabs.qualityassessor.util;

import com.qaitdevlabs.qualityassessor.dto.TreeNodeDTO;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: anujchhabra
 * Date: 27/8/12
 * Time: 11:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class CustomDomainComparator implements Comparator<TreeNodeDTO> {
    @Override
    public int compare(TreeNodeDTO o1, TreeNodeDTO o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
