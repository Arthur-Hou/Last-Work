package com.extr.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
//import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
//import java.util.Random;
//import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*import com.extr.domain.question.QuestionFilter;
import com.extr.controller.domain.QuestionImproveResult;
import com.extr.controller.domain.QuestionQueryResult;
import com.extr.domain.question.Field;
import com.extr.domain.question.KnowledgePoint;
import com.extr.domain.question.Question;
import com.extr.domain.question.PointStatistic;
import com.extr.domain.question.QuestionContent;
import com.extr.domain.question.QuestionStruts;
import com.extr.domain.question.QuestionTag;
import com.extr.domain.question.QuestionType;
import com.extr.domain.question.Tag;
import com.extr.domain.question.UserQuestionHistory;
import com.extr.file.util.ExcelUtil;
import com.extr.persistence.QuestionMapper;
import com.extr.util.Page;
import com.extr.util.xml.Object2Xml;*/
import com.extr.domain.question.Field;
import com.extr.domain.question.KnowledgePoint;
import com.extr.domain.question.PointStatistic;
import com.extr.domain.question.Question;
import com.extr.domain.question.QuestionContent;
import com.extr.domain.question.QuestionFilter;
import com.extr.domain.question.QuestionQueryResult;
import com.extr.domain.question.QuestionStatistic;
import com.extr.domain.question.QuestionStruts;
import com.extr.domain.question.QuestionTag;
import com.extr.domain.question.QuestionType;
import com.extr.domain.question.Tag;
import com.extr.util.Page;
import com.extr.file.util.ExcelUtil;
import com.extr.persistence.QuestionMapper;
import com.google.gson.Gson;

/**
 * @author 
 * @date 
 */
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionMapper questionMapper;

	@Override
	public List<Question> getQuestionList(Page<Question> pageModel, QuestionFilter qf) {
		// TODO Auto-generated method stub
		return questionMapper.getQuestionList(qf, pageModel);
	}

	@Override
	public List<Field> getAllField(Page<Field> page) {
		// TODO Auto-generated method stub
		return questionMapper.getAllField(page);
	}

	@Override
	public List<KnowledgePoint> getKnowledgePointByFieldId(int fieldId, Page<KnowledgePoint> page) {
		// TODO Auto-generated method stub
		return questionMapper.getKnowledgePointByFieldId(fieldId, page);
	}

	@Override
	public List<QuestionType> getQuestionTypeList() {
		// TODO Auto-generated method stub
		return questionMapper.getQuestionTypeList();
	}

	@Override
	public List<Tag> getTags(Page<Tag> page) {
		// TODO Auto-generated method stub
		return questionMapper.getTags(page);
	}

	@Override
	public void addTag(Tag tag) {
		// TODO Auto-generated method stub
		questionMapper.addTag(tag);
	}

	@Override
	@Transactional
	public void addQuestion(Question question) {
		// TODO Auto-generated method stub
		try {
			questionMapper.insertQuestion(question);
			for (Integer i : question.getPointList()) {
				questionMapper.addQuestionKnowledgePoint(question.getId(), i);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void addField(Field field) {
		// TODO Auto-generated method stub
		questionMapper.addField(field);
	}

	@Override
	public void addKnowledgePoint(KnowledgePoint point) {
		// TODO Auto-generated method stub
		questionMapper.addKnowledgePoint(point);
	}

	@Override
	public List<QuestionTag> getQuestionTagByQuestionIdAndUserId(int questionId, int userId, Page<QuestionTag> page) {
		// TODO Auto-generated method stub
		return questionMapper.getQuestionTagByQuestionIdAndUserId(questionId, userId, page);
	}

	@Override
	@Transactional
	public void addQuestionTag(int questionId, int userId, List<QuestionTag> questionTagList) {
		// TODO Auto-generated method stub
		try {
			List<Integer> idList = new ArrayList<Integer>();
			for (QuestionTag t : questionTagList) {
				idList.add(t.getTagId());
			}
			questionMapper.deleteQuestionTag(questionId, userId, idList.size() == 0 ? null : idList);
			questionMapper.addQuestionTag(questionTagList);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 重载，整合了tag的功能
	 * 
	 * @see com.extr.service.QuestionServiceImpl#updateQuestionPoint(Question
	 *      question)
	 * @param question
	 * @param userId
	 * @param questionTagList
	 */
	@Override
	@Transactional
	public void updateQuestionPoint(Question question, int userId, List<QuestionTag> questionTagList) {
		// TODO Auto-generated method stub
		try {
			questionMapper.deleteQuestionPointByQuestionId(question.getId());
			for (int id : question.getPointList()) {
				questionMapper.addQuestionKnowledgePoint(question.getId(), id);
			}
			List<Integer> idList = new ArrayList<Integer>();
			for (QuestionTag t : questionTagList) {
				idList.add(t.getTagId());
			}

			if (questionTagList != null && questionTagList.size() != 0) {
				questionMapper.deleteQuestionTag(question.getId(), userId, idList.size() == 0 ? null : idList);
				questionMapper.addQuestionTag(questionTagList);
			} else {
				questionMapper.deleteQuestionTag(question.getId(), userId, idList.size() == 0 ? null : idList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getClass().getName());
		}
	}

	@Override
	public void deleteFieldByIdList(List<Integer> idList) {
		// TODO Auto-generated method stub
		questionMapper.deleteFieldByIdList(idList);
	}

	@Override
	public void deleteKnowledgePointByIdList(List<Integer> idList) {
		// TODO Auto-generated method stub
		questionMapper.deleteKnowledgePointByIdList(idList);
	}

	@Override
	public void deleteTagByIdList(List<Integer> idList) {
		// TODO Auto-generated method stub
		questionMapper.deleteTagByIdList(idList);
	}

	@Override
	public Question getQuestionByQuestionId(int questionId) {
		// TODO Auto-generated method stub
		return questionMapper.getQuestionByQuestionId(questionId);
	}

	@Override
	public List<QuestionQueryResult> getQuestionDescribeListByIdList(List<Integer> idList) {
		List<QuestionQueryResult> questionList = questionMapper.getQuestionAnalysisListByIdList(idList);
		return questionList;
	}

	@Override
	public void deleteQuestionByQuestionId(int questionId) {
		// TODO Auto-generated method stub
		questionMapper.deleteQuestionByQuestionId(questionId);
	}

	@Override
	public HashMap<Integer, HashMap<Integer, List<QuestionStruts>>> getQuestionStrutsMap(List<Integer> idList) {
		// TODO Auto-generated method stub
		HashMap<Integer, HashMap<Integer, List<QuestionStruts>>> hm = new HashMap<Integer, HashMap<Integer, List<QuestionStruts>>>();
		List<QuestionStruts> questionList = questionMapper.getQuestionListByPointId(idList);
		for (QuestionStruts q : questionList) {
			HashMap<Integer, List<QuestionStruts>> hashmap = new HashMap<Integer, List<QuestionStruts>>();
			List<QuestionStruts> ql = new ArrayList<QuestionStruts>();
			if (hm.containsKey(q.getPointId()))
				hashmap = hm.get(q.getPointId());
			if (hashmap.containsKey(q.getQuestionTypeId()))
				ql = hashmap.get(q.getQuestionTypeId());
			ql.add(q);
			hashmap.put(q.getQuestionTypeId(), ql);
			hm.put(q.getPointId(), hashmap);
		}
		return hm;
	}

	@Transactional
	@Override
	public void uploadQuestions(String filePath, String username, int fieldId) {
		// TODO Auto-generated method stub
		String strPath = ",webapps,files,question," + username + ",tmp";

		filePath = System.getProperty("catalina.base") + strPath.replace(',', File.separatorChar) + File.separatorChar
				+ filePath;
		Map<String, KnowledgePoint> pointMap = this.getKnowledgePointMapByFieldId(fieldId, null);
		int index = 2;
		try {
			List<Map<String, String>> questionMapList = ExcelUtil.ExcelToList(filePath);
			for (Map<String, String> map : questionMapList) {

				System.out.println(map);
				Question question = new Question();
				question.setName(map.get("题目").length() > 10 ? map.get("题目").substring(0, 10) + "..." : map.get("题目"));
				if (map.get("类型").equals("单选题") || map.get("类型").equals("单项选择题"))
					question.setQuestion_type_id(1);
				else if (map.get("类型").equals("多选题") || map.get("类型").equals("多项选择题"))
					question.setQuestion_type_id(2);
				else if (map.get("类型").equals("判断题"))
					question.setQuestion_type_id(3);
				else if (map.get("类型").equals("填空题"))
					question.setQuestion_type_id(4);
				else if (map.get("类型").equals("简答题"))
					question.setQuestion_type_id(5);
				else if (map.get("类型").equals("论述题"))
					question.setQuestion_type_id(6);
				else if (map.get("类型").equals("分析题"))
					question.setQuestion_type_id(7);

				question.setAnalysis(map.get("解析"));
				question.setAnswer(map.get("答案"));
				if (question.getQuestion_type_id() == 3) {
					if (map.get("答案").equals("对"))
						question.setAnswer("T");
					if (map.get("答案").equals("错"))
						question.setAnswer("F");
				}

				KnowledgePoint kp = pointMap.get(map.get("知识类"));
				if(kp == null)
					throw new Exception("知识类不存在：" + map.get("知识类"));
				List<Integer> pointList = new ArrayList<Integer>();
				pointList.add(kp.getPointId());
				question.setReferenceName(map.get("出处"));
				question.setExamingPoint(map.get("知识点"));
				question.setKeyword(map.get("知识关键点"));
				question.setPoints(map.get("分值").equals("") ? 0 : Float.parseFloat(map.get("分值")));
				QuestionContent qc = new QuestionContent();

				Iterator<String> it = map.keySet().iterator();
				List<String> keyStr = new ArrayList<String>();
				while (it.hasNext()) {
					String key = it.next();
					if (key.contains("选项"))
						keyStr.add(key.replace("选项", ""));
				}

				Collections.sort(keyStr);
				LinkedHashMap<String, String> choiceList = new LinkedHashMap<String, String>();
				for (int i = 0; i < keyStr.size(); i++) {
					if (!map.get("选项" + keyStr.get(i)).trim().equals(""))
						choiceList.put(keyStr.get(i), map.get("选项" + keyStr.get(i)));
				}
				if (question.getQuestion_type_id() == 1 || question.getQuestion_type_id() == 2)
					qc.setChoiceList(choiceList);
				qc.setTitle(map.get("题目"));
				Gson gson = new Gson();
				String content = gson.toJson(qc);
				question.setContent(content);
				question.setCreator(username);
				question.setPointList(pointList);
				this.addQuestion(question);
				index++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			throw new RuntimeException("第" + index + "行有错误，请检查！" + e.getMessage());
		}
	}

	@Override
	public Map<String, KnowledgePoint> getKnowledgePointMapByFieldId(int fieldId, Page<KnowledgePoint> page) {
		// TODO Auto-generated method stub
		Map<String, KnowledgePoint> map = new HashMap<String, KnowledgePoint>();
		List<KnowledgePoint> pointList = questionMapper.getKnowledgePointByFieldId(fieldId, page);
		for (KnowledgePoint point : pointList) {
			map.put(point.getPointName(), point);
		}
		return map;
	}
	
	@Override
	public Map<Integer, Map<Integer, QuestionStatistic>> getTypeQuestionStaticByFieldId(int fieldId) {
		// TODO Auto-generated method stub
		List<QuestionStatistic> statisticList = questionMapper.getTypeQuestionStaticByFieldId(fieldId);
		Map<Integer, Map<Integer, QuestionStatistic>> map = new HashMap<Integer, Map<Integer, QuestionStatistic>>();
		for(QuestionStatistic statistic : statisticList){
			Map<Integer, QuestionStatistic> tmp = map.get(statistic.getPointId());
			if(tmp == null){
				tmp = new HashMap<Integer, QuestionStatistic>();
			}
			tmp.put(statistic.getQuestionTypeId(), statistic);
			map.put(statistic.getPointId(), tmp);
		}
		return map;
	}

	@Override
	public Map<Integer, String> getKnowledgePointMap(int fieldId) {
		// TODO Auto-generated method stub
		List<KnowledgePoint> knowledgeList = null;
		if(fieldId == 0)
			knowledgeList = questionMapper.getKnowledgePointByFieldId(0, null);
		else 
			knowledgeList = questionMapper.getKnowledgePointByFieldId(fieldId, null);
		Map<Integer, String> knowledgeMap = new HashMap<Integer, String>();
		for(KnowledgePoint kp : knowledgeList){
			knowledgeMap.put(kp.getPointId(), kp.getPointName());
		}
		return knowledgeMap;
	}

	@Override
	public Map<Integer, String> getQuestionTypeMap() {
		// TODO Auto-generated method stub
		List<QuestionType> typeList = this.getQuestionTypeList();
		Map<Integer, String> typeMap = new HashMap<Integer, String>();
		for(QuestionType tp : typeList){
			typeMap.put(tp.getId(), tp.getName());
		}
		return typeMap;
	}

	@Transactional
	@Override
	public void updateQuestion(Question question, List<QuestionTag> questionTagList) {
		// TODO Auto-generated method stub
		try {
			questionMapper.updateQuestion(question);
			questionMapper.deleteQuestionPointByQuestionId(question.getId());
			for (int id : question.getPointList()) {
				questionMapper.addQuestionKnowledgePoint(question.getId(), id);
			}
			List<Integer> idList = new ArrayList<Integer>();
			for (QuestionTag t : questionTagList) {
				idList.add(t.getTagId());
			}

			if (questionTagList != null && questionTagList.size() != 0) {
				questionMapper.deleteQuestionTag(question.getId(), 0, null);
				questionMapper.addQuestionTag(questionTagList);
			} else {
				questionMapper.deleteQuestionTag(question.getId(), 0, null);
			}

		} catch (Exception e) {
			//e.printStackTrace();
			throw new RuntimeException(e.getClass().getName());
		}
		
	}

	@Transactional
	@Override
	public Question getQuestionDetail(int questionId,int userId) {
		// TODO Auto-generated method stub
		try {
			Question question = questionMapper.getQuestionByQuestionId(questionId);
			List<QuestionTag> tagList = questionMapper.getQuestionTagByQuestionIdAndUserId(questionId, userId, null);
			List<KnowledgePoint> pointList = questionMapper.getQuestionPoint(questionId);
			question.setTagList(tagList);
			question.setKnowledgePoint(pointList);
			return question;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<PointStatistic> getPointCount(int fieldId, Page<PointStatistic> page) {
		// TODO Auto-generated method stub
		return questionMapper.getPointCount(fieldId, page);
	}
}

/*@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionMapper questionMapper;

	@Override
	public List<Field> getAllField(Page<Field> page) {
		return questionMapper.getAllField(page);
	}

	@Override
	public List<KnowledgePoint> getKnowledgePointByFieldId(int FieldId,
			Page<KnowledgePoint> page) {
		return questionMapper.getKnowledgePointByFieldId(FieldId, page);
	}

	@Override
	public List<Question> getQuestionList(Page<Question> pageModel,
			QuestionFilter qf) {
		return questionMapper.getQuestionList(qf, pageModel);
	}

	@Override
	public List<QuestionType> getQuestionTypeList() {
		return questionMapper.getQuestionTypeList();
	}

	@Override
	public Question getQuestionByQuestionId(int questionId) {
		// TODO Auto-generated method stub
		return questionMapper.getQuestionByQuestionId(questionId);
	}

	@Override
	public List<KnowledgePoint> getQuestionKnowledgePointListByQuestionId(
			int questionId) {
		// TODO Auto-generated method stub
		return questionMapper
				.getQuestionKnowledgePointListByQuestionId(questionId);
	}

	@Override
	@Transactional
	public void addQuestion(Question question) {
		// TODO Auto-generated method stub
		try {
			questionMapper.insertQuestion(question);
			for (Integer i : question.getPointList()) {
				questionMapper.addQuestionKnowledgePoint(question.getId(), i);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void deleteQuestionByQuestionId(int questionId) {
		// TODO Auto-generated method stub
		try {
			questionMapper.deleteQuestionPointByQuestionId(questionId);
			questionMapper.deleteQuestionByQuestionId(questionId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public HashMap<Integer, HashMap<Integer, List<QuestionStruts>>> getQuestionStrutsMap(
			List<Integer> idList) {
		// TODO Auto-generated method stub
		HashMap<Integer, HashMap<Integer, List<QuestionStruts>>> hm = new HashMap<Integer, HashMap<Integer, List<QuestionStruts>>>();
		List<QuestionStruts> questionList = questionMapper
				.getQuestionListByPointId(idList);
		for (QuestionStruts q : questionList) {
			HashMap<Integer, List<QuestionStruts>> hashmap = new HashMap<Integer, List<QuestionStruts>>();
			List<QuestionStruts> ql = new ArrayList<QuestionStruts>();
			if (hm.containsKey(q.getPointId()))
				hashmap = hm.get(q.getPointId());
			if (hashmap.containsKey(q.getQuestionTypeId()))
				ql = hashmap.get(q.getQuestionTypeId());
			ql.add(q);
			hashmap.put(q.getQuestionTypeId(), ql);
			hm.put(q.getPointId(), hashmap);
		}
		return hm;
	}

	@Override
	public KnowledgePoint getKnowledgePointByName(String pointName,
			String fieldName) {
		// TODO Auto-generated method stub
		return questionMapper.getKnowledgePointByName(pointName, fieldName);
	}

	@Override
	public void addUserQuestionHistory(UserQuestionHistory userQuestionHistory)
			throws Exception {
		// TODO Auto-generated method stub
		if (userQuestionHistory.getHistory() == null)
			throw new Exception("不能插入空的历史记录");

		String histStr = Object2Xml.toXml(userQuestionHistory.getHistory());
		userQuestionHistory.setHistoryStr(histStr);
		questionMapper.addUserQuestionHistory(userQuestionHistory);
	}

	@Override
	public UserQuestionHistory getUserQuestionHistoryByUserId(int userId) {
		// TODO Auto-generated method stub
		UserQuestionHistory uh = questionMapper
				.getUserQuestionHistoryByUserId(userId);
		if (uh != null)
			uh.setHistory(Object2Xml.toBean(uh.getHistoryStr(), Map.class));
		return uh;
	}

	@Override
	public void updateUserQuestionHistory(
			UserQuestionHistory userQuestionHistory) throws Exception {
		// TODO Auto-generated method stub
		if (userQuestionHistory.getHistory() == null)
			throw new Exception("不能插入空的历史记录");

		String histStr = Object2Xml.toXml(userQuestionHistory.getHistory());
		userQuestionHistory.setHistoryStr(histStr);
		questionMapper.updateUserQuestionHistory(userQuestionHistory);
	}

	@Override
	public List<QuestionQueryResult> getQuestionAnalysisListByPointIdAndTypeId(
			int typeId, int pointId) {
		// TODO Auto-generated method stub
		return questionMapper.getQuestionAnalysisListByPointIdAndTypeId(typeId,
				pointId);
	}

	@Override
	public List<QuestionImproveResult> getQuestionImproveResultByQuestionPointIdList(
			List<Integer> questionPointIdList) {
		// TODO Auto-generated method stub
		return questionMapper
				.getQuestionImproveResultByQuestionPointIdList(questionPointIdList);
	}

	@Override
	public List<QuestionQueryResult> getQuestionQueryResultListByFieldIdList(
			List<Integer> fieldIdList, List<Integer> typeIdList, int limit) {
		// TODO Auto-generated method stub
		List<QuestionQueryResult> tmp = questionMapper
				.getQuestionAnalysisListByFieldIdList(fieldIdList, typeIdList);
		List<QuestionQueryResult> result = new ArrayList<QuestionQueryResult>();
		if (limit >= tmp.size())
			return tmp;
		Random random = new Random();
		Set<Integer> idSet = new HashSet<Integer>();
		while (idSet.size() < 20) {
			idSet.add(random.nextInt(tmp.size()));
		}
		Iterator<Integer> it = idSet.iterator();
		while (it.hasNext()) {
			result.add(tmp.get(it.next()));
		}
		return result;
	}

	@Transactional
	@Override
	public void updateQuestionPoint(Question question) {
		// TODO Auto-generated method stub
		try {
			questionMapper.deleteQuestionPointByQuestionId(question.getId());
			for (int id : question.getPointList()) {
				questionMapper.addQuestionKnowledgePoint(question.getId(), id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getClass().getName());
		}

	}

	@Override
	public void addField(Field field) {
		// TODO Auto-generated method stub
		questionMapper.addField(field);
	}

	@Override
	public void addKnowledgePoint(KnowledgePoint point) {
		// TODO Auto-generated method stub
		questionMapper.addKnowledgePoint(point);
	}

	@Override
	public void deleteFieldByIdList(List<Integer> idList) {
		// TODO Auto-generated method stub
		questionMapper.deleteFieldByIdList(idList);
	}

	@Override
	public void deleteKnowledgePointByIdList(List<Integer> idList) {
		// TODO Auto-generated method stub
		questionMapper.deleteKnowledgePointByIdList(idList);
	}
	
	@Override
	@Transactional
	public void uploadQuestions(String filePath,String username,int fieldId) {
		// TODO Auto-generated method stub
		
		String strPath = ",webapps,files,question," + username + ",tmp";
		
		filePath = System.getProperty("catalina.base") + strPath.replace(',', File.separatorChar) + File.separatorChar + filePath;
		int index = 2;
		try {
			List<Map<String, String>> questionMapList = ExcelUtil
					.ExcelToList(filePath);
			for (Map<String, String> map : questionMapList) {

				System.out.println(map);
				Question question = new Question();
				question.setName(map.get("题目").length() > 10 ? map.get("题目")
						.substring(0, 10) + "..." : map.get("题目"));
				if (map.get("类型").equals("单选题")
						|| map.get("类型").equals("单项选择题"))
					question.setQuestion_type_id(1);
				else if (map.get("类型").equals("多选题")
						|| map.get("类型").equals("多项选择题"))
					question.setQuestion_type_id(2);
				else if (map.get("类型").equals("判断题"))
					question.setQuestion_type_id(3);
				else if (map.get("类型").equals("填空题"))
					question.setQuestion_type_id(4);
				else if (map.get("类型").equals("简答题"))
					question.setQuestion_type_id(5);
				else if (map.get("类型").equals("论述题"))
					question.setQuestion_type_id(6);
				else if (map.get("类型").equals("分析题"))
					question.setQuestion_type_id(7);

				question.setAnalysis(map.get("解析"));
				question.setAnswer(map.get("答案"));
				if (question.getQuestion_type_id() == 3) {
					if (map.get("答案").equals("对"))
						question.setAnswer("T");
					if (map.get("答案").equals("错"))
						question.setAnswer("F");
				}

				KnowledgePoint kp = this.getKnowledgePointByName(
						map.get("知识类"), map.get("所属专业"));
				KnowledgePoint kp = questionMapper.getKnowledgePointByPointNameAndFieldId(map.get("知识类"), fieldId);
				List<Integer> pointList = new ArrayList<Integer>();
				pointList.add(kp.getPointId());
				question.setReferenceName(map.get("出处"));
				question.setExamingPoint(map.get("知识点"));
				question.setKeyword(map.get("知识关键点"));
				question.setPoints(map.get("分值").equals("") ? 0 : Float
						.parseFloat(map.get("分值")));
				QuestionContent qc = new QuestionContent();

				Iterator<String> it = map.keySet().iterator();
				List<String> keyStr = new ArrayList<String>();
				while (it.hasNext()) {
					String key = it.next();
					if (key.contains("选项"))
						keyStr.add(key.replace("选项", ""));
				}

				Collections.sort(keyStr);
				LinkedHashMap<String, String> choiceList = new LinkedHashMap<String, String>();
				for (int i = 0; i < keyStr.size(); i++) {
					if (!map.get("选项" + keyStr.get(i)).trim().equals(""))
						choiceList.put(keyStr.get(i),
								map.get("选项" + keyStr.get(i)));
				}
				if (question.getQuestion_type_id() == 1
						|| question.getQuestion_type_id() == 2)
					qc.setChoiceList(choiceList);
				qc.setTitle(map.get("题目"));
				String xmlStr = Object2Xml.toXml(qc);
				question.setContent(xmlStr);
				question.setCreator(username);
				question.setPointList(pointList);
				this.addQuestion(question);
				index ++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			throw new RuntimeException("第" + index + "行有错误，请检查！");
		}
	}

	@Override
	public Integer getMinFieldId() {
		// TODO Auto-generated method stub
		return questionMapper.getMinFieldId();
	}

	@Override
	public List<Tag> getTagByUserId(int userId, Page<Tag> page) {
		// TODO Auto-generated method stub
		return questionMapper.getTagByUserId(userId, page);
	}

	@Override
	public void addTag(Tag tag) {
		// TODO Auto-generated method stub
		questionMapper.addTag(tag);
	}

	@Override
	public List<QuestionTag> getQuestionTagByQuestionIdAndUserId(int questionId,
			int userId, Page<QuestionTag> page) {
		// TODO Auto-generated method stub
		return questionMapper.getQuestionTagByQuestionIdAndUserId(questionId, userId, page);
	}

	@Override
	@Transactional
	public void addQuestionTag(int questionId, int userId, List<QuestionTag> questionTagList) {
		// TODO Auto-generated method stub
		try{
			questionMapper.deleteQuestionTag(questionId, userId, questionTagList);
			questionMapper.addQuestionTag(questionTagList);
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
	}

	*//**
	 * 重载，整合了tag的功能
	 * @see com.extr.service.QuestionServiceImpl#updateQuestionPoint(Question question)
	 * @param question
	 * @param userId
	 * @param questionTagList
	 *//*
	@Override
	@Transactional
	public void updateQuestionPoint(Question question, int userId,
			List<QuestionTag> questionTagList) {
		// TODO Auto-generated method stub
		try {
			questionMapper.deleteQuestionPointByQuestionId(question.getId());
			for (int id : question.getPointList()) {
				questionMapper.addQuestionKnowledgePoint(question.getId(), id);
			}
			
			if(questionTagList != null && questionTagList.size() != 0){
				questionMapper.deleteQuestionTag(question.getId(), userId, questionTagList);
				questionMapper.addQuestionTag(questionTagList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getClass().getName());
		}
	}
}
*/