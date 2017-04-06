package com.extr.service;

import java.util.HashMap;
import java.util.List;
/*import com.extr.controller.domain.QuestionQueryResult;
import com.extr.domain.exam.ExamHistory;
import com.extr.domain.exam.ExamPaper;
import com.extr.domain.question.Question;
import com.extr.domain.question.QuestionStruts;
import com.extr.util.Page;*/
import com.extr.domain.exam.AnswerSheet;
import com.extr.domain.exam.Exam;
import com.extr.domain.exam.ExamHistory;
import com.extr.domain.exam.UserQuestionHistory;
import com.extr.domain.user.Role;
import com.extr.util.Page;

/**
 * @author 
 * @date 
 */
public interface ExamService {


	/**
	 * 插入一个考试，如果考试目标分组已经确定，则同时给每个学员插入历史记录
	 * @param exam
	 */
	public void addExam(Exam exam);
	
	/**
	 * 往考试中添加用户
	 * @param examId
	 * @param userNameStr
	 * @param roleMap
	 */
	public void addExamUser(int examId,String userNameStr,HashMap<String,Role> roleMap);
	
	/**
	 * 获取考试清单
	 * @param page
	 * @param typeIdList 考试类型
	 * @return
	 */
	public List<Exam> getExamList(Page<Exam> page,int ... typeIdList);
	
	/**
	 * 根据考试id获取考试历史记录
	 * @param examId
	 * @param searchStr
	 * @param order
	 * @param limit
	 * @param page
	 * @return
	 */
	public List<ExamHistory> getUserExamHistListByExamId(int examId, String searchStr, String order, int limit, Page<ExamHistory> page);
	
	/**
	 * 删除一门考试
	 * @param examId
	 */
	public void deleteExamById(int examId) throws Exception;
	
	/**
	 * 更新考试的审核状态
	 * @param examId
	 * @param approved
	 */
	public void changeExamStatus(int examId, int approved);
	
	/**
	 * 审核用户考试申请
	 * @param histId
	 * @param approved
	 */
	public void changeUserExamHistStatus(int histId, int approved);
	
	/**
	 * 更新答题卡及得分
	 * @param answerSheet
	 * @param answerSheetStr
	 */
	public void updateUserExamHist(AnswerSheet answerSheet, String answerSheetStr, int approved);
	
	/**
	 * 根据考试历史id获取考试历史
	 * @param histId
	 * @return
	 */
	public ExamHistory getUserExamHistListByHistId(int histId);
	
	/**
	 * 根据用户id和考试id查询考试历史
	 * @param userId
	 * @param examId
	 * @return
	 */
	public ExamHistory getUserExamHistByUserIdAndExamId(int userId, int examId, int ... approved);
	
	/**
	 * 删除一条考试申请（历史记录），只能删除未审核的记录
	 * @param histId
	 */
	public void deleteUserExamHist(int histId);
	
	/**
	 * 根据id获取exam
	 * @param examId
	 * @return
	 */
	public Exam getExamById(int examId);
	
	/**
	 * 将用户组中的用户添加到考试中
	 * @param groupIdList
	 * @param examId
	 */
	public void addGroupUser2Exam(List<Integer> groupIdList,int examId);
	
	/**
	 * 获取用户考试历史列表（所有）
	 * @param page
	 * @param approved
	 * @return
	 */
	public List<ExamHistory> getUserExamHistList(Page<ExamHistory> page, int ... approved);
	/*List<QuestionQueryResult> getQuestionDescribeListByIdList(
			List<Integer> idList);

	List<Question> getQuestionListByIdListNew(List<Integer> idList);

	public void createExamPaper(
			HashMap<Integer, HashMap<Integer, List<QuestionStruts>>> questionMap,
			HashMap<Integer, Integer> questionTypeNum,
			HashMap<Integer, Float> questionTypePoint,
			HashMap<Integer, Float> knowledgePointRate, ExamPaper examPaper);

	public List<ExamPaper> getExamPaperListByPaperType(String paperType,
			Page<ExamPaper> page);

	public void updateExamPaper(ExamPaper examPaper);

	public ExamPaper getExamPaperById(int examPaperId);

	public void insertExamPaper(ExamPaper examPaper);

	public List<ExamPaper> getExamPaperList4Exam(int paperType);

	public void addUserExamHistory(ExamHistory examHistory);

	public ExamHistory getUserExamHistoryByUserIdAndExamPaperId(int userId,
			int examPaperId);

	public void updateExamHistory(ExamHistory examHistory);
	
	public ExamHistory getUserExamHistoryByHistId(int histId);
	
	public List<ExamHistory> getUserExamHistoryListByUserId(int userId,Page<ExamHistory> page);
	
	public void deleteExamPaper(int id);*/
}
