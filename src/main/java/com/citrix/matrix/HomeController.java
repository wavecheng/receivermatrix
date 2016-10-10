package com.citrix.matrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.citrix.matrix.model.Data;
import com.citrix.matrix.repository.DataRepository;

@Controller
public class HomeController {


	@Autowired
	private DataRepository dataRepository;
	
	private Logger log = LoggerFactory.getLogger("matrix");
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST,consumes = "application/json")
	@ResponseBody
	public Data save(@RequestBody String data){
		log.debug(data.toString());
		Data d = new Data();
		d.setId(1);
		d.setTableData(data);
		dataRepository.save(d);
		return d;
	}

	@RequestMapping(value="/save_config", method=RequestMethod.POST,consumes = "application/json")
	@ResponseBody
	public Data save_config(@RequestBody String cfgData){
		Data d = new Data();
		d.setId(2);
		d.setTableData(cfgData);
		dataRepository.save(d);
		return d;
	}
	
	@RequestMapping(value="/load", method=RequestMethod.GET)
	@ResponseBody
	public Data load(){
		return dataRepository.findOne(1L);
	}	
	
	@RequestMapping(value="/load_config", method=RequestMethod.GET)
	@ResponseBody
	public Data load_config(){
		return dataRepository.findOne(2L);
	}		
}
