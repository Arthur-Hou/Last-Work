package com.extr.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.extr.domain.exam.ExamPaper;
import com.extr.util.Page;

import org.apache.ibatis.annotations.Param;

import com.extr.domain.exam.ExamPaper;
import com.extr.util.Page;

public interface ExamPaperMapper {

	public List<ExamPaper> getExamPaperList(@Param("searchStr") String searchStr,@Param("paperType") String paperType, @Param("page") Page<ExamPaper> page);
	
	public void insertExamPaper(ExamPaper examPaper);
	
	public ExamPaper getExamPaperById(int examPaperId);
	
	public void updateExamPaper(ExamPaper examPaper);
	
	public void deleteExamPaper(int id);
	
	public List<ExamPaper> getEnabledExamPaperList(@Param("userName") String userName,@Param("page") Page<ExamPaper> page);
}


/*public interface ExamPaperMapper {

	public void insertExamPaper(ExamPaper examPaper);

	public ExamPaper getExamPaperById(@Param("examPaperId") int examPaperId);

	public List<ExamPaper> getExamPaperListByPaperType(
			@Param("paperType") String paperType,
			@Param("page") Page<ExamPaper> page);
	
	public void updateExamPaper(ExamPaper examPaper);

	public List<ExamPaper> getExamPaperList4Exam(@Param("paperType")int paperType);
	
	public void deleteExamPaper(int id);
}
*/