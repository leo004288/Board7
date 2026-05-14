package com.green.pds.service;

import java.util.HashMap;
import java.util.List;

import com.green.pds.dto.PdsDto;

public interface PdsService {

	List<PdsDto> getPdslist(HashMap<String, Object> map);

}
