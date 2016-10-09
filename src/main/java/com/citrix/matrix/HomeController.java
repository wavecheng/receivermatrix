package com.citrix.matrix;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.citrix.matrix.model.Cell;
import com.citrix.matrix.repository.CellRepository;

@Controller
public class HomeController {

	@Autowired
	private CellRepository cellRepository;
	
	private Logger log = LoggerFactory.getLogger("matrix");
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST,consumes = "application/json")
	@ResponseBody
	public Cell save(@RequestBody Cell cell){
		log.debug(cell.toString());
		Cell existing = cellRepository.findByRowAndColumn(cell.getRow(), cell.getColumn());
		if(existing != null){
			existing.setValue(cell.getValue());
			cell = cellRepository.saveAndFlush(existing);
		}
		else
			cell = cellRepository.save(cell);
		return cell;
	}
	
	@RequestMapping(value="/load", method=RequestMethod.GET)
	@ResponseBody
	public List<Cell> load(){
		return cellRepository.findAll();
	}	
	
}
