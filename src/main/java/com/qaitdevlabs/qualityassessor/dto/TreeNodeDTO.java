package com.qaitdevlabs.qualityassessor.dto;

import java.util.Date;
import java.util.List;

/**
 * This class is used as data transfer object
 * 
 * @author anujchhabra
 * 
 */
public class TreeNodeDTO {
	private String key;
	private String parentKey;
	private String title; // default null, (required) Displayed name of the node
	private String weightage;
	//private String isFolder; // default false,Use a folder icon. Also the node
	// is expandable but not selectable.
	//private String isLazy; // default false, Call onLazyRead(), when the node is
	// expanded for the first time to allow for delayed
	// creation of children.
	private float score;
	private long assessmentId;
	private String type;
	private boolean root;
	//private String tooltip; // default null, Show this popup text.
	//private String href; // default null,Added to the generated <a> tag.
	//private String icon; // default null, Use a custom image (filename relative
	// to tree.options.imagePath). 'null' for default
	// icon, 'false' for no icon
	//private String addClass; // default null, Class name added to the node's
	// span tag.
	//private String noLink; // default false, Use <span> instead of <a> tag for
	// this node
	//private String activate; // default false, Initial active status.
	//private String focus; // default false, Initial focused status.
	//private boolean expand; // default false, Initial expanded status.
	//private String select; // default false, Initial selected status.
	//private String hideCheckbox; // Suppress checkbox display for this node.
	//private String unselectable; // Prevent selection.
	// The following attributes are only valid if passed to some functions:
	List<TreeNodeDTO> children; // :null Array of child nodes.
	//private String wikipediaLink;
	private String creationUserName;
	private String modificationUserName;
	private Date creationDate;
	private Date modificationDate;

	// NOTE: we can also add custom attributes here.
	// This may then also be used in the onActivate(), onSelect() or
	// onLazyTree() callbacks.

//	public String getIsFolder() {
//		return isFolder;
//	}
//
//	public void setIsFolder(String isFolder) {
//		this.isFolder = isFolder;
//	}
//
//	public String getIsLazy() {
//		return isLazy;
//	}
//
//	public void setIsLazy(String isLazy) {
//		this.isLazy = isLazy;
//	}
//
//	public String getTooltip() {
//		return tooltip;
//	}
//
//	public void setTooltip(String tooltip) {
//		this.tooltip = tooltip;
//	}

//	public String getHref() {
//		return href;
//	}
//
//	public void setHref(String href) {
//		this.href = href;
//	}
//
//	public String getIcon() {
//		return icon;
//	}
//
//	public void setIcon(String icon) {
//		this.icon = icon;
//	}
//
//	public String getAddClass() {
//		return addClass;
//	}
//
//	public void setAddClass(String addClass) {
//		this.addClass = addClass;
//	}
//
//	public String getNoLink() {
//		return noLink;
//	}
//
//	public void setNoLink(String noLink) {
//		this.noLink = noLink;
//	}
//
//	public String getActivate() {
//		return activate;
//	}
//
//	public void setActivate(String activate) {
//		this.activate = activate;
//	}
//
//	public String getFocus() {
//		return focus;
//	}
//
//	public void setFocus(String focus) {
//		this.focus = focus;
//	}
//
//	public boolean getExpand() {
//		return expand;
//	}
//
//	public void setExpand(boolean expand) {
//		this.expand = expand;
//	}
//
//	public String getSelect() {
//		return select;
//	}
//
//	public void setSelect(String select) {
//		this.select = select;
//	}
//
//	public String getHideCheckbox() {
//		return hideCheckbox;
//	}
//
//	public void setHideCheckbox(String hideCheckbox) {
//		this.hideCheckbox = hideCheckbox;
//	}
//
//	public String getUnselectable() {
//		return unselectable;
//	}
//
//	public void setUnselectable(String unselectable) {
//		this.unselectable = unselectable;
//	}

	public List<TreeNodeDTO> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNodeDTO> children) {
		this.children = children;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

//	public String getWikipediaLink() {
//		return wikipediaLink;
//	}
//
//	public void setWikipediaLink(String wikipediaLink) {
//		this.wikipediaLink = wikipediaLink;
//	}

	public String getCreationUserName() {
		return creationUserName;
	}

	public void setCreationUserName(String creationUserName) {
		this.creationUserName = creationUserName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getModificationUserName() {
		return modificationUserName;
	}

	public void setModificationUserName(String modificationUserName) {
		this.modificationUserName = modificationUserName;
	}

	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public String getWeightage() {
		return weightage;
	}

	public void setWeightage(String weightage) {
		this.weightage = weightage;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public long getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(long assessmentId) {
		this.assessmentId = assessmentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isRoot() {
		return root;
	}

	public void setRoot(boolean root) {
		this.root = root;
	}

}
