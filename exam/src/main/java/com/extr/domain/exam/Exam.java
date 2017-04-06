package com.extr.domain.exam;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Exam implements Serializable {

	private static final long serialVersionUID = -1595051130848974880L;
	private int examId;
	private String examName;
	private String examSubscribe;
	private int examType;
	private Date createTime;
	private Date effTime;
	private Date expTime;
	private int examPaperId;
	private String examPaperName;
	private List<Integer> groupIdList;
	private int creator;
	private String creatorId;
	//准考证号
	private String seriNo;
	//0 未审核, 1 审核通过, 2 审核不通过
	private int approved;
	public String getSeriNo() {
		return seriNo;
	}
	public void setSeriNo(String seriNo) {
		this.seriNo = seriNo;
	}
	public int getApproved() {
		return approved;
	}
	public void setApproved(int approved) {
		this.approved = approved;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getExamPaperName() {
		return examPaperName;
	}
	public void setExamPaperName(String examPaperName) {
		this.examPaperName = examPaperName;
	}
	public int getExamType() {
		return examType;
	}
	public void setExamType(int examType) {
		this.examType = examType;
	}
	public String getExamSubscribe() {
		return examSubscribe;
	}
	public void setExamSubscribe(String examSubscribe) {
		this.examSubscribe = examSubscribe;
	}
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getEffTime() {
		return effTime;
	}
	public void setEffTime(Date effTime) {
		this.effTime = effTime;
	}
	public Date getExpTime() {
		return expTime;
	}
	public void setExpTime(Date expTime) {
		this.expTime = expTime;
	}
	public int getExamPaperId() {
		return examPaperId;
	}
	public void setExamPaperId(int examPaperId) {
		this.examPaperId = examPaperId;
	}
	public List<Integer> getGroupIdList() {
		return groupIdList;
	}
	public void setGroupIdList(List<Integer> groupIdList) {
		this.groupIdList = groupIdList;
	}
	public int getCreator() {
		return creator;
	}
	public void setCreator(int creator) {
		this.creator = creator;
	}
	/*private static final long serialVersionUID = -1595051130848974880L;
	private int id;
	private String name;
	private Date start_time;
	private Date end_time;
	private boolean is_fix_location;
	private Date create_time;
	private int exam_paper_id;
	private int user_id;
	private String creator;
	
	*//**
	 * 刘攀添加location_id,group_id,exam_paper_id和get set 方法
	 * 20130705 如果不需要先注释
	 *//*
	private int location_id;
	private int group_id;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	
	public int getExam_paper_id() {
		return exam_paper_id;
	}

	public void setExam_paper_id(int exam_paper_id) {
		this.exam_paper_id = exam_paper_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public boolean isIs_fix_location() {
		return is_fix_location;
	}

	public void setIs_fix_location(boolean is_fix_location) {
		this.is_fix_location = is_fix_location;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}*/
	

}
