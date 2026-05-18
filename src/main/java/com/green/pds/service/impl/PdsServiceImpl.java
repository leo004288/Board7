package com.green.pds.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.green.pds.dto.FilesDto;
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
		
		// menu_id=MENU01, nowpage=1, title=aaa, writer=aaa, content=aaa, uploadPath = "d:/dev/springpoot/data"
		System.out.println("이전 map:" + map);
		
		// 별도 클래스를 생성해서 처리한다 : pdsFile
		// 넘어온 정보로 파일을 저장
		PdsFile.save(map, uploadfiles);
		
		// menu_id=MENU01, nowpage=1, title=fffhf, writer=aaa, content=fhchhghgh ghg,
        // uploadPath=D:/dev/springboot/data/,
        // fileList=[FilesDto(file_num=0, idx=0, filename=토큰.txt, fileext=.txt, sfilename=2026\05\18\9d1dba1b-6dca-49c0-8690-2a0cdfc1ebfc.토큰.txt),
        //			 FilesDto(file_num=0, idx=0, filename=용어.txt, fileext=.txt, sfilename=2026\05\18\1d7a492a-4833-4eff-bd7d-021b216d47f7.용어.txt)
        //			]}
		System.out.println("이후 map:" + map);
		
		// 2. db저장 : 자료실 글 쓰기 <- map
		// Board table에 저장
		pdsMapper.setWrite(map); // insertBoard
		
		// 2-1. Files 에 저장
		List<FilesDto> fileList = (List<FilesDto>) map.get("fileList");
		if( fileList.size() > 0 )
			pdsMapper.setFileWriter(map);
		
	}

	@Override
	public List<PdsDto> getPds(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
