package com.green.pds.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.green.pds.dto.FilesDto;

public class PdsFile {

	// uploadfiles에 넘어온 파일들 저장
	public static void save(HashMap<String, Object> map, MultipartFile[] uploadfiles) {
		
		// 저장될경로를 가져오기 : uploadPath
		String uploadPath = String.valueOf( map.get("uploadPath") );
		
		// 파일들을 저장하고 Files Table 에 저장할 정보를 map 에 담는다 
		List<FilesDto> fileList = new ArrayList<>();
		
		// uploadfiles 에 넘어온 파일별로 반복
		for (MultipartFile uploadfile : uploadfiles) {
			if(uploadfile.isEmpty()) {       // 전송된 파일이 없어도 다음 파일 읽음 
				continue;
			}
			
			String orgName  = uploadfile.getOriginalFilename();
			// d:\\dev\\springboot\\data\\data.abc.txt : 업로드된 파일정보
			// d:/dev/springboot/data/data.abc.txt      
			String fileName = 
					( orgName.lastIndexOf("\\") < 0)      // 못찾으면 -1 
					? orgName
					: orgName.substring( orgName.lastIndexOf("\\") + 1 )  // \\data.abc.txt		
					;
			String fileExt  = 
					( orgName.lastIndexOf(".") < 0)      // 못찾으면 -1
					? " "
					: orgName.substring( orgName.lastIndexOf("."))       // .txt		
					;
			
			// 날짜 폴더 생성
			
			// 파일 중복 방지
			// 중복되지 않는 고유한 문자열 생성
			
			// 저장할 sfilename 생성

			// 파일저장
			
			// FilesDto 에 파일 정보를 저장
			
			// fileList.add(FilesDto)
		}
		
		// map에 fileList 정보를 추가
		
	}

} //
