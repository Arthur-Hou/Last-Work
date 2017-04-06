package com.extr.persistence;

import org.apache.ibatis.annotations.Param;

import com.extr.domain.exam.PracticePaper;

/**
 * @author 
 * @date 
 */
public interface PracticeMapper {

	PracticePaper getPracticePaperByUserID(@Param("userId")int userId);

	void deletePracticePaperByUserId(@Param("userId")int userId);

	void insertPracticePaper(PracticePaper practicePaper);

}
