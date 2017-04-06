package com.extr.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.extr.domain.question.Field;
import com.extr.domain.question.KnowledgePoint;
import com.extr.domain.question.PointStatistic;
import com.extr.domain.question.Question;
import com.extr.domain.question.QuestionFilter;
import com.extr.domain.question.QuestionQueryResult;
import com.extr.domain.question.QuestionStatistic;
import com.extr.domain.question.QuestionStruts;
import com.extr.domain.question.QuestionTag;
import com.extr.domain.question.QuestionType;
import com.extr.domain.question.Tag;
import com.extr.util.Page;
/*import com.extr.controller.domain.QuestionFilter;
import com.extr.controller.domain.QuestionImproveResult;
import com.extr.controller.domain.QuestionQueryResult;
import com.extr.domain.question.Field;
import com.extr.domain.question.KnowledgePoint;
import com.extr.domain.question.Question;
import com.extr.domain.question.QuestionStruts;
import com.extr.domain.question.QuestionTag;
import com.extr.domain.question.QuestionType;
import com.extr.domain.question.Tag;
import com.extr.domain.question.UserQuestionHistory;
import com.extr.util.Page;*/

/**
 * @author 
 * @date 
 */
public interface QuestionService {
	public List<Question> getQuestionList(Page<Question> pageModel, QuestionFilter qf);

	public List<Field> getAllField(Page<Field> page);

	public List<KnowledgePoint> getKnowledgePointByFieldId(int FieldId, Page<KnowledgePoint> page);

	public Map<String, KnowledgePoint> getKnowledgePointMapByFieldId(int fieldId, Page<KnowledgePoint> page);

	public List<QuestionType> getQuestionTypeList();

	/**
	 * @param page
	 * @return
	 */
	public List<Tag> getTags(Page<Tag> page);

	/**
	 * 增加一个标签
	 *
	 * @param tag
	 */
	public void addTag(Tag tag);

	/**
	 * 添加试题，同时添加试题知识点对应关系
	 * 
	 * @param question
	 */
	public void addQuestion(Question question);

	/**
	 * 添加题库
	 * 
	 * @param field
	 */
	public void addField(Field field);

	/**
	 * 添加知识点
	 * 
	 * @param point
	 */
	public void addKnowledgePoint(KnowledgePoint point);

	/**
	 * 获取试题的tag，只包含公有tag和自己的私有tag
	 * 
	 * @param questionId
	 * @param userId
	 * @param page
	 * @return
	 */
	public List<QuestionTag> getQuestionTagByQuestionIdAndUserId(int questionId, int userId, Page<QuestionTag> page);

	/**
	 * 给题目打标签
	 * 
	 * @param questionId
	 * @param userId
	 */
	public void addQuestionTag(int questionId, int userId, List<QuestionTag> questionTagList);

	/**
	 * 重载，整合了tag的功能
	 * 
	 * @see com.extr.service.QuestionService#updateQuestionPoint(Question
	 *      question)
	 * @param question
	 * @param userId
	 * @param questionTagList
	 */
	public void updateQuestionPoint(Question question, int userId, List<QuestionTag> questionTagList);

	/**
	 * 删除专业
	 * 
	 * @param idList
	 */
	public void deleteFieldByIdList(List<Integer> idList);

	/**
	 * 删除知识分类
	 * 
	 * @param idList
	 */
	public void deleteKnowledgePointByIdList(List<Integer> idList);

	/**
	 * 删除一个标签
	 * 
	 * @param idList
	 */
	public void deleteTagByIdList(List<Integer> idList);

	public Question getQuestionByQuestionId(int questionId);

	public List<QuestionQueryResult> getQuestionDescribeListByIdList(List<Integer> idList);

	public void deleteQuestionByQuestionId(int questionId);

	public HashMap<Integer, HashMap<Integer, List<QuestionStruts>>> getQuestionStrutsMap(List<Integer> idList);

	/**
	 * 导入试题
	 * 
	 * @param filePath
	 * @param username
	 * @param fieldId
	 */
	public void uploadQuestions(String filePath, String username, int fieldId);
	
	/**
	 * 根据fieldId,pointId,typeId分组统计试题数量
	 * @param fieldId
	 * @return
	 */
	public Map<Integer,Map<Integer,QuestionStatistic>> getTypeQuestionStaticByFieldId(int fieldId);
	
	/**
	 * 获取知识点字典
	 * @param fieldId
	 * @return
	 */
	public Map<Integer,String> getKnowledgePointMap(int fieldId);
	
	/**
	 * 获取试题类型字典
	 * @return
	 */
	public Map<Integer,String> getQuestionTypeMap();
	
	/**
	 * 更新试题
	 * @param question
	 * @param questionTagList
	 */
	public void updateQuestion(Question question,List<QuestionTag> questionTagList);
	
	/**
	 * 获取试题信息（详细）
	 * @param questionId
	 */
	public Question getQuestionDetail(int questionId, int userId);
	
	/**
	 * 获取知识点统计信息
	 * @param fieldId
	 * @return
	 */
	public List<PointStatistic> getPointCount(int fieldId, Page<PointStatistic> page);
	/**
	 * 获取题目类型
	 * 
	 * @return
	 *//*
	List<Field> getAllField(Page<Field> page);

	List<KnowledgePoint> getKnowledgePointByFieldId(int FieldId,Page<KnowledgePoint> page);

	List<Question> getQuestionList(Page<Question> pageModel, QuestionFilter qf);

	List<QuestionType> getQuestionTypeList();

	Question getQuestionByQuestionId(int questionId);

	*//**
	 * 获取题目的知识点，知识点名由专业名fieldname和知识点pointname名拼接
	 * 
	 * @param questionId
	 * @return
	 *//*
	List<KnowledgePoint> getQuestionKnowledgePointListByQuestionId(
			int questionId);

	*//**
	 * 添加试题，同时添加试题知识点对应关系
	 * 
	 * @param question
	 *//*
	public void addQuestion(Question question);

	public void deleteQuestionByQuestionId(int questionId);

	public HashMap<Integer, HashMap<Integer, List<QuestionStruts>>> getQuestionStrutsMap(
			List<Integer> idList);

	public KnowledgePoint getKnowledgePointByName(String pointName,
			String fieldName);

	*//**
	 * 添加学员练习试题的记录
	 * 
	 * @param userQuestionHistory
	 *//*
	public void addUserQuestionHistory(UserQuestionHistory userQuestionHistory)
			throws Exception;

	*//**
	 * 获取学员练习试题的记录
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
			UserQuestionHistory userQuestionHistory) throws Exception;

	public List<QuestionQueryResult> getQuestionAnalysisListByPointIdAndTypeId(
			int typeId, int pointId);

	*//**
	 * 强化练习获取分类信息
	 * 
	 * @return
	 *//*
	public List<QuestionImproveResult> getQuestionImproveResultByQuestionPointIdList(
			List<Integer> questionPointIdList);
	
	public List<QuestionQueryResult> getQuestionQueryResultListByFieldIdList(
			List<Integer> fieldIdList,List<Integer> typeIdList,int limit);
	
	public void updateQuestionPoint(Question question);
	
	*//**
	 * 添加题库
	 * @param field
	 *//*
	public void addField(Field field);
	
	*//**
	 * 添加知识点
	 * @param point
	 *//*
	public void addKnowledgePoint(KnowledgePoint point);
	
	*//**
	 * 删除题库
	 * @param idList
	 *//*
	public void deleteFieldByIdList(List<Integer> idList);
	
	*//**
	 * 删除知识点
	 * @param idList
	 *//*
	public void deleteKnowledgePointByIdList( List<Integer> idList);
	
	*//**
	 * 上传试题
	 * @param filePath
	 * @param username
	 *//*
	public void uploadQuestions(String filePath,String username,int fieldId);
	
	*//**
	 * 获取一个最小的，具有point的fieldid，用于首页取默认field
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
    public List<Tag> getTagByUserId(int userId, Page<Tag> page);

    *//**
     * 增加一个标签
     *
     * @param tag
     *//*
    public void addTag(Tag tag);
    
    *//**
	 * 获取试题的tag，只包含公有tag和自己的私有tag
	 * @param questionId
	 * @param userId
	 * @param page
	 * @return
	 *//*
	public List<QuestionTag> getQuestionTagByQuestionIdAndUserId(
			int questionId, int userId, Page<QuestionTag> page);
	
	*//**
	 * 给题目打标签
	 * @param questionId
	 * @param userId
	 *//*
	public void addQuestionTag(int questionId, int userId, List<QuestionTag> questionTagList);
	
	*//**
	 * 重载，整合了tag的功能
	 * @see com.extr.service.QuestionService#updateQuestionPoint(Question question)
	 * @param question
	 * @param userId
	 * @param questionTagList
	 *//*
	public void updateQuestionPoint(Question question,int userId, List<QuestionTag> questionTagList);*/
}
