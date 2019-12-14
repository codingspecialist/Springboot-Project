package com.cos.IoTProject.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cos.IoTProject.Timer;
import com.cos.IoTProject.MyUtil.CoolsmsUnit;
import com.cos.IoTProject.MyUtil.MyUtil;
import com.cos.IoTProject.model.GasDetect;
import com.cos.IoTProject.model.HumanDetect;
import com.cos.IoTProject.model.Management;
import com.cos.IoTProject.model.Manager;
import com.cos.IoTProject.model.RaspiSensor;
import com.cos.IoTProject.model.TempAndHumi;
import com.cos.IoTProject.model.Users;
import com.cos.IoTProject.repository.GasDetectRepository;
import com.cos.IoTProject.repository.HumanDetectRepository;
import com.cos.IoTProject.repository.ManagementRepository;
import com.cos.IoTProject.repository.ManagerRepository;
import com.cos.IoTProject.repository.TempAndHumiRepository;
import com.cos.IoTProject.repository.UsersRepository;
import com.google.gson.Gson;

@Controller
@RequestMapping("/")
public class MainController {
	@Autowired
	public static RaspiSensor raspisensor;
	@Autowired
	private Gson gson;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private GasDetectRepository gasDetectRepository;
	@Autowired
	private HumanDetectRepository humanDetectRepository;
	@Autowired
	private ManagementRepository managementRepository;
	
	
	
	//, sort= {"createDate"}, direction = Direction.DESC
	@GetMapping("/home")
	public String home(Model model, @PageableDefault(size = 8) Pageable pageable) {
		Page<Management> list = managementRepository.findByOldmanlist(pageable);
		model.addAttribute("oldmans",list.getContent());
		
		Page<Management> gaswarninglist = managementRepository.findByGasWarniglist(pageable);
		model.addAttribute("gaswarnings",gaswarninglist.getContent());
		
		Page<Management> tempwarninglist = managementRepository.findByTempWarniglist(pageable);
		model.addAttribute("tempwarnings",tempwarninglist.getContent());
		return "home";
	}
	
	@GetMapping("/detail/{userid}")
	public String detail(@PathVariable int userid, Model model) {
		Management Management = managementRepository.findByLastmanagement(userid);
		model.addAttribute("oldman",Management);
		return "detail";
	}
	@PostMapping("/joinProc")
	public String join(@RequestParam("file") MultipartFile file, String name, int age, String gender, String tel, String address_si, String address_gu, String address_dong, String address_detail, String ipaddress) {
		String[] etc = file.getOriginalFilename().split("\\.");
		String FileName = Integer.toString((usersRepository.findMaxid()+1));
		Path filePath = Paths.get(MyUtil.getResourcePath() + FileName+"."+etc[etc.length-1]);
		System.out.println("이미지 저장 경로:"+filePath);
		try {
			Files.write(filePath, file.getBytes());
			Users user = Users.builder()
					.name(name)
					.age(age)
					.gender(gender)
					.tel(tel)
					.address_si(address_si)
					.address_gu(address_gu)
					.address_dong(address_dong)
					.address_detail(address_detail)
					.ipaddress(ipaddress)
					.img_ipaddress("http://"+ipaddress+":8000/?action=stream")
					.tah_ipaddress("http://"+ipaddress+":6000/sensor")
					.build();
			usersRepository.save(user);
			Management management = Management.builder()
					.user(user)
					.build();
			managementRepository.save(management);
			GasDetect gasDetect = GasDetect.builder()
					.gasCheck("false")
					.management(management)
					.build();
			gasDetectRepository.save(gasDetect);
		} catch (Exception e) {
		}
		return "redirect:/home/";
	}
	
	@GetMapping("/management")
	public @ResponseBody Management manageCheck() {
		Management management = managementRepository.findByLastmanagement(1);
		return management;
	}
	
	@PostMapping("/manager")
	public @ResponseBody String manager() {
		Manager manager = Manager.builder()
				.name("안아람")
				.gender("여")
				.phone("01090647358")
				.build();
		managerRepository.save(manager);
		return "ok";
	}
	
	@PostMapping("/user")
	public @ResponseBody String user() {
		Users user = Users.builder()
				.name("송민우")
				.age(80)
				.gender("남")
				.tel("051-631-2856")
				.address_si("부산시")
				.address_gu("남구")
				.address_dong("수영로 135")
				.address_detail("롯데캐슬레전드 124동 2702호")
				.ipaddress("192.168.0.50")
				.img_ipaddress("http://192.168.0.50:8000/?action=stream")
				.tah_ipaddress("http://192.168.0.50:6000/sensor")
				.build();
		usersRepository.save(user);
		Management management = Management.builder()
				.user(user)
				.build();
		managementRepository.save(management);
		GasDetect gasDetect = GasDetect.builder()
				.gasCheck("false")
				.management(management)
				.build();
		gasDetectRepository.save(gasDetect);
		return "ok";
	}
	
	@PostMapping("/senIn")
	public void sensorIn(@RequestBody String sensorData) {
		raspisensor = gson.fromJson(sensorData, RaspiSensor.class);
		Users user = usersRepository.findByIpaddress(raspisensor.getIp());
		Manager manager = managerRepository.findById(1).get();
		Management Management = managementRepository.findByLastmanagement(user.getId());
		GasDetect gasDetect = gasDetectRepository.findByManagement(Management);
		raspisensor.setManagement(Management);
		//HumanDetect humanDetect = humanDetectRepository.findByManagement(management);
		
		if(raspisensor.getGasdetect()==1 && gasDetect.getGasCheck().equals("false")) {
			gasDetect.setGasCheck("true");
			gasDetectRepository.save(gasDetect);
			String warning = user.getAddress_si()+user.getAddress_gu()+user.getAddress_dong()+user.getAddress_detail()+"에 사시는 "+user.getName()+"어르신의 집에 가스누출 위험";
			CoolsmsUnit.MessageTest(manager.getPhone(), warning);
		}
		if(raspisensor.getHumandetect()==1) {
			List<HumanDetect> humanDetect = humanDetectRepository.findByManagement(Management);
			int size = humanDetect.size();
			if(size != 0) {
				HumanDetect lastHuman = humanDetect.get(size-1);
				if(lastHuman.getOutCheck().equals("out")) {
					lastHuman.setOutCheck("come");
					humanDetectRepository.save(lastHuman);
				}else if(lastHuman.getOutCheck().equals("come")) {
					lastHuman = HumanDetect.builder()
							.management(Management)
							.outCheck("out")
							.build();
					humanDetectRepository.save(lastHuman);
				}
			}else {
				HumanDetect lastHuman = HumanDetect.builder()
						.management(Management)
						.outCheck("out")
						.build();
				humanDetectRepository.save(lastHuman);
			}
		}
		
		System.out.println("가스누출:"+raspisensor.getGasdetect());
		System.out.println("외출복귀유무:"+raspisensor.getHumandetect());
	}
}
