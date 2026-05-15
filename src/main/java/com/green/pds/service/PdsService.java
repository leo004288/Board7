package com.green.pds.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.green.pds.dto.PdsDto;

public interface PdsService {

	List<PdsDto> getPdsList(HashMap<String, Object> map);

	void setWriter(HashMap<String, Object> map, MultipartFile[] uploadfiles);

	List<PdsDto> getPds(HashMap<String, Object> map);

}
