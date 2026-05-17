package com.green.pds.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.green.pds.dto.PdsDto;
import com.green.pds.mapper.PdsMapper;
import com.green.pds.service.PdsService;

@Service
public class PdsServiceImpl implements PdsService {
	
	// @Value가 application.properties에 있는 변수 가지고옴 
	@Value("${part1.upload-path}")
	private String uploadPath;
	
	@Autowired
	private PdsMapper pdsMapper;
	
	@Override
	public List<PdsDto> getPdsList(HashMap<String, Object> map) {
		List<PdsDto> pdsList = pdsMapper.getPdsList(map); 
		return pdsList;
	}

	@Override
	public void setWriter(HashMap<String, Object> map, MultipartFile[] uploadfiles) {
		// 파일저장 + db저장
		// 1. 파일저장 : uploadfiles [] -> uploadPath -> d:/dev/springpoot/data
//		String uploadPath = "d:/dev/springpoot/data";
		map.put("uploadPath", uploadPath);
		
		System.out.println("이전 map:" + map);
		
		// 별도 클래스를 생성해서 처리한다
		PdsFile.save(map, uploadfiles);
		
		System.out.println("이후 map:" + map);
		
		// 2. db저장 : 자료실 글 쓰기 <- map
		
		
	}

	@Override
	public List<PdsDto> getPds(HashMap<String, Object> map) {
		return null;
	}

}
