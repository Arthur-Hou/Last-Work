package com.extr.file.util;

/*import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.extr.domain.question.KnowledgePoint;
import com.extr.domain.question.Question;
import com.extr.domain.question.QuestionContent;
import com.extr.service.QuestionService;
import com.extr.util.xml.Object2Xml;*/
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


public class FileUploadTest {

	private static Log log = LogFactory.getLog(FileUploadUtil.class);
	public static List<String> uploadFile(HttpServletRequest request, 
			HttpServletResponse response, String username) throws FileNotFoundException{
		List<String> filePathList = new ArrayList<String>();
		
		String strPath = ",webapps,files,training," + username;
		
		String filepath = System.getProperty("catalina.base") + strPath.replace(',', File.separatorChar);
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String fileName = null;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {

			MultipartFile mf = entity.getValue();
			fileName = mf.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf('.'));
			try {
				String newFileName = MD5FileUtil.getMD5String(mf.getBytes());
				String newfilepath;
				newfilepath = filepath + File.separatorChar + newFileName + fileType;
				String filepathUrl = "files" + File.separatorChar + "training" + File.separatorChar + username + File.separatorChar + newFileName + fileType;
				
				System.out.println("newfilepath=" + newfilepath);
				File dest = new File(filepath);
				if(!dest.exists()){
					dest.mkdirs();
				}
				File uploadFile = new File(newfilepath);
				if(uploadFile.exists()){
					uploadFile.delete();
				}
				log.info("start upload file: " + fileName);
				FileCopyUtils.copy(mf.getBytes(), uploadFile);
				filePathList.add(filepathUrl);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info("upload failed. filename: " + fileName + e.getMessage());
				return null;
			}
			
			
		}
		
		return filePathList;
	}
	
	public static List<String> uploadImg(HttpServletRequest request, 
			HttpServletResponse response, String username) throws Exception{
		List<String> filePathList = new ArrayList<String>();

		String strPath = ",webapps,files,question," + username;
		
		String filepath = System.getProperty("catalina.base") + strPath.replace(',', File.separatorChar);
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String fileName = null;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {

			MultipartFile mf = entity.getValue();
			fileName = mf.getOriginalFilename();
			String file_type = fileName.substring(fileName.indexOf('.'));
			if(!".jpg".equals(file_type.toLowerCase()) && !".png".equals(file_type.toLowerCase()))
				throw new Exception("文件类型错误");
			fileName = String.valueOf(new Date().getTime()) + file_type;
			String newfilepath;
			newfilepath = filepath + File.separatorChar + fileName;
			String filepathUrl = "files" + File.separatorChar + "question" + File.separatorChar + username + File.separatorChar + fileName;

			System.out.println("newfilepath=" + newfilepath);
			File dest = new File(filepath);
			if(!dest.exists()){
				dest.mkdirs();
			}
			File uploadFile = new File(newfilepath);
			if(uploadFile.exists()){
				uploadFile.delete();
			}
			try {

				log.info("start upload file: " + fileName);
				FileCopyUtils.copy(mf.getBytes(), uploadFile);
			} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
				log.info("upload failed. filename: " + fileName + e.getMessage());
				return null;
			}
			filePathList.add(filepathUrl);
			
		}
		
		return filePathList;
	}
	
	public static void copyFile(String oldPath, String newPath) { 
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { //文件存在时 
				InputStream inStream = new FileInputStream(oldPath); //读入原文件 
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[2000];
				while ( (byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; //字节数 文件大小 
					fs.write(buffer, 0, byteread);
				}
				fs.close();
				inStream.close();
			}
		}catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
			return;
		}
		File oldfile = new File(oldPath);
		oldfile.delete();
	}
	/*@Test
	public void uploadExcelTest() {

		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"src/main/webapp/WEB-INF/spring/root-context.xml");

		QuestionService questionService = (QuestionService) ctx
				.getBean("questionService");

		String filePath = "C:\\Users\\mars\\Desktop\\国网试题录入终稿3-hsh-20140825\\国网试题录入完成版4-hsh-20140825.xlsx";
		List<Question> questionList = new ArrayList<Question>();
		try {
			List<Map<String, String>> questionMapList = ExcelUtil
					.ExcelToList(filePath);
			
			for (Map<String, String> map : questionMapList) {
				try{
					System.out.println(map);
					Question question = new Question();
					question.setName(map.get("题目").length() > 10 ? map.get("题目")
							.substring(0, 10) + "..." : map.get("题目"));
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
					if(question.getQuestion_type_id() == 3){
						if(map.get("答案").equals("对"))
							question.setAnswer("T");
						if(map.get("答案").equals("错"))
							question.setAnswer("F");
					}
					
					String z = map.get("知识类");
					String s = map.get("所属专业");
					KnowledgePoint kp = questionService.getKnowledgePointByName(
							map.get("知识类"), map.get("所属专业"));
					List<Integer> pointList = new ArrayList<Integer>();
					pointList.add(kp.getPointId());
					question.setReferenceName(map.get("出处"));
					question.setExamingPoint(map.get("知识点"));
					question.setKeyword(map.get("知识关键点"));
					question.setPoints(map.get("分值").equals("") ? 0 :Float.parseFloat(map.get("分值")));
					QuestionContent qc = new QuestionContent();
					
					Iterator<String> it = map.keySet().iterator();
					List<String> keyStr = new ArrayList<String>();
					while(it.hasNext()){
						String key = it.next();
						if(key.contains("选项"))
							keyStr.add(key.replace("选项", ""));
					}
					
					Collections.sort(keyStr);
					LinkedHashMap<String, String> choiceList = new LinkedHashMap<String, String>();
					for(int i=0;i<keyStr.size();i++){
						if(!map.get("选项" + keyStr.get(i)).trim().equals(""))
							choiceList.put(keyStr.get(i), map.get("选项" + keyStr.get(i)));
					}
					if(question.getQuestion_type_id() == 1 || question.getQuestion_type_id() == 2)
						qc.setChoiceList(choiceList);
					qc.setTitle(map.get("题目"));
					String xmlStr = Object2Xml.toXml(qc);
					question.setContent(xmlStr);
					question.setCreator("admin");
					question.setPointList(pointList);
					questionService.addQuestion(question);
				}catch(Exception e){
					e.printStackTrace();
					continue;
				}
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/
}
