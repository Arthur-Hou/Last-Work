package com.extr.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.extr.controller.domain.QuestionFilter;
import com.extr.controller.domain.QuestionQueryResult;
import com.extr.domain.question.Field;
import com.extr.domain.question.KnowledgePoint;
import com.extr.domain.question.PointStatistic;
import com.extr.domain.question.Question;
import com.extr.domain.question.QuestionStatistic;
import com.extr.domain.question.QuestionStruts;
import com.extr.domain.question.QuestionTag;
import com.extr.domain.question.QuestionType;
import com.extr.domain.question.Tag;
import com.extr.util.Page;

/**
 * @author 
 * @date 
 */
public interface QuestionMapper {

	public List<Question> getQuestionList(
			@Param("filter") QuestionFilter filter,
			@Param("page") Page<Question> page);

	public List<Field> getAllField(@Param("page") Page<Field> page);

	public List<KnowledgePoint> getKnowledgePointByFieldId(
			@Param("fieldId") int fieldId,
			@Param("page") Page<KnowledgePoint> page);

	public List<QuestionType> getQuestionTypeList();

	/**
	 * 获取tag列表，包含所有公有的或者自己私有的
	 *
	 * @param userId
	 * @param page
	 * @return
	 */
	public List<Tag> getTagByUserId(@Param("userId") int userId,
			@Param("page") Page<Tag> page);

	/**
	 * 获取所有标签（管理员使用）
	 * @return
	 */
	public List<Tag> getTags(@Param("page") Page<Tag> page);
	/**
	 * 增加一个标签
	 *
	 * @param tag
	 */
	public void addTag(Tag tag);

	public void insertQuestion(Question question) throws Exception;

	public void addQuestionKnowledgePoint(@Param("questionId") int questionId,
			@Param("pointId") int pointId) throws Exception;

	public void addField(Field field);

	public void addKnowledgePoint(KnowledgePoint point);
	
	/**
	 * 获取试题的tag
	 * @param questionId
	 * @param userId
	 * @param page
	 * @return
	 */
	public List<QuestionTag> getQuestionTagByQuestionIdAndUserId(
			@Param("questionId") int questionId, @Param("userId") int userId,
			@Param("page") Page<QuestionTag> page);
	
	/**
	 * 给题目打标签
	 */
	public void addQuestionTag(@Param("array") List<QuestionTag> array);
	
	public void deleteQuestionTag(@Param("questionId") int questionId,@Param("userId") int userId,@Param("array") List<Integer> array);
	
	public void deleteQuestionPointByQuestionId(
			@Param("questionId") int questionId) throws Exception;
	
	public void deleteFieldByIdList(@Param("array") List<Integer> idList);
	
	public void deleteKnowledgePointByIdList(@Param("array") List<Integer> idList);
	
	public void deleteTagByIdList(@Param("array") List<Integer> idList);
	
	public Question getQuestionByQuestionId(@Param("questionId") int questionId);
	
	List<QuestionQueryResult> getQuestionAnalysisListByIdList(
			@Param("array") List<Integer> idList);
	
	public void deleteQuestionByQuestionId(@Param("questionId") int questionId);
	
	/**
	 * 获取某一题型的试题
	 * @param QuestionTypeId
	 * @param page
	 * @return
	 */
	public List<Question> getQuestionByTypeId(@Param("QuestionTypeId") int QuestionTypeId,@Param("page") Page<Question> page);
	
	/**
	 * 按知识点获取试题
	 * 
	 * @param idList
	 * @return
	 */
	List<QuestionStruts> getQuestionListByPointId(@Param("array") List<Integer> idList);
	
	/**
	 * 根据fieldId,pointId,typeId分组统计试题数量
	 * @param fieldId
	 * @return
	 */
	public List<QuestionStatistic> getTypeQuestionStaticByFieldId(int fieldId);
	
	/**
	 * 更新一道试题
	 * @param question Object为null，int＝0则不更新
	 */
	public void updateQuestion(Question question);
	
	/**
	 * 获取试题的知识点
	 * @param questionId
	 */
	public List<KnowledgePoint> getQuestionPoint(int questionId);
	
	/**
	 * 获取试题标签
	 * @param questionId
	 * @return
	 */
	public List<Tag> getQuestionTags(int questionId);
	
	/**
	 * 获取知识点统计信息
	 * @param fieldId
	 * @return
	 */
	public List<PointStatistic> getPointCount(@Param("fieldId") int fieldId, @Param("page") Page<PointStatistic> page);
}

	/*List<Field> getAllField(@Param("page") Page<Field> page);

	List<KnowledgePoint> getKnowledgePointByFieldId(
			@Param("fieldId") int fieldId,
			@Param("page") Page<KnowledgePoint> page);

	List<QuestionQueryResult> getQuestionAnalysisListByIdList(
			@Param("array") List<Integer> idList);

	List<QuestionQueryResult> getQuestionAnalysisListByPointIdAndTypeId(
			@Param("typeId") int typeId, @Param("pointId") int pointId);

	List<Question> getQuestionListByIdListNew(
			@Param("array") List<Integer> idList);

	List<Question> getQuestionListByQuestionTypeIdAndReferenceId(
			@Param("questionTypeId") int questionTypeId,
			@Param("fieldId") int fieldId, @Param("limitNum") int limitNum);

	List<QuestionType> getQuestionTypeList();

	*//**
	 * 按知识点获取试题
	 * 
	 * @param idList
	 * @return
	 *//*
	List<QuestionStruts> getQuestionListByPointId(
			@Param("array") List<Integer> idList);

	List<Question> getQuestionList(@Param("filter") QuestionFilter filter,
			@Param("page") Page<Question> page);

	Question getQuestionByQuestionId(@Param("questionId") int questionId);

	*//**
	 * 获取题目的知识点，知识点名由专业名fieldname和知识点pointname名拼接
	 * 
	 * @param questionId
	 * @return
	 *//*
	List<KnowledgePoint> getQuestionKnowledgePointListByQuestionId(
			@Param("questionId") int questionId);

	public void addQuestionKnowledgePoint(@Param("questionId") int questionId,
			@Param("pointId") int pointId) throws Exception;

	public void insertQuestion(Question question) throws Exception;

	public void deleteQuestionByQuestionId(@Param("questionId") int questionId)
			throws Exception;

	public void deleteQuestionPointByQuestionId(
			@Param("questionId") int questionId) throws Exception;

	public KnowledgePoint getKnowledgePointByName(
			@Param("pointName") String pointName,
			@Param("fieldName") String fieldName);

	public KnowledgePoint getKnowledgePointByPointNameAndFieldId(
			@Param("pointName") String pointName, @Param("fieldId") int fieldId);

	*//**
	 * 添加学员练习试题的记录
	 * 
	 * @param userQuestionHistory
	 *//*
	public void addUserQuestionHistory(UserQuestionHistory userQuestionHistory);

	*//**
	 * 获取学院练习试题的记录
	 * 
	 * @param userId
	 * @return
	 *//*
	public UserQuestionHistory getUserQuestionHistoryByUserId(int userId);

	*//**
	 * 更新学员练习试题记录
	 * 
	 * @param userQuestionHistory
	 *//*
	public void updateUserQuestionHistory(
			UserQuestionHistory userQuestionHistory);

	*//**
	 * 强化练习获取分类信息
	 * 
	 * @return
	 *//*
	public List<QuestionImproveResult> getQuestionImproveResultByQuestionPointIdList(
			@Param("array") List<Integer> questionPointIdList);

	public List<QuestionQueryResult> getQuestionAnalysisListByFieldIdList(
			@Param("array") List<Integer> fieldIdList,
			@Param("typeIdList") List<Integer> questionTypeIdList);

	public void addField(Field field);

	public void addKnowledgePoint(KnowledgePoint point);

	public void deleteFieldByIdList(@Param("array") List<Integer> idList);

	public void deleteKnowledgePointByIdList(
			@Param("array") List<Integer> idList);

	*//**
	 * 获取一个最小的，具有point的fieldid，用于首页取默认field
	 * 
	 * @return
	 *//*
	public Integer getMinFieldId();

	*//**
	 * 获取tag列表，包含所有公有的或者自己私有的
	 * 
	 * @param userId
	 * @param page
	 * @return
	 *//*
	public List<Tag> getTagByUserId(@Param("userId") int userId,
			@Param("page") Page<Tag> page);

	*//**
	 * 增加一个标签
	 * 
	 * @param tag
	 *//*
	public void addTag(Tag tag);

	*//**
	 * 获取试题的tag
	 * @param questionId
	 * @param userId
	 * @param page
	 * @return
	 *//*
	public List<QuestionTag> getQuestionTagByQuestionIdAndUserId(
			@Param("questionId") int questionId, @Param("userId") int userId,
			@Param("page") Page<QuestionTag> page);
	
	*//**
	 * 给题目打标签
	 *//*
	public void addQuestionTag(@Param("array") List<QuestionTag> array);
	
	public void deleteQuestionTag(@Param("questionId") int questionId,@Param("userId") int userId,@Param("array") List<QuestionTag> array);
}
*/