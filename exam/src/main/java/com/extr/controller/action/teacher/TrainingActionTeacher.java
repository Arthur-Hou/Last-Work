package com.extr.controller.action.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.extr.domain.exam.Message;
import com.extr.domain.training.Training;
import com.extr.domain.training.TrainingSection;
import com.extr.security.UserInfo;
import com.extr.service.TrainingService;

@Controller
public class TrainingActionTeacher {
	@Autowired
	private TrainingService trainingService;
	@RequestMapping(value = "teacher/training/add-training", method = RequestMethod.POST)
	public @ResponseBody Message addTraining(@RequestBody Training training) {

		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Message msg = new Message();
		try {
			training.setCreatorId(userInfo.getUserid());
			trainingService.addTraining(training);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.setResult(e.getClass().getName());
		}
		return msg;
	}
	
	@RequestMapping(value = "teacher/training/add-training-section", method = RequestMethod.POST)
	public @ResponseBody Message addTrainingSection(@RequestBody TrainingSection section){

		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Message msg = new Message();
		try {
			section.setUserId(userInfo.getUserid());
			trainingService.addTrainingSection(section);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.setResult(e.getClass().getName());
		}
		return msg;
	}
}
