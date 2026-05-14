package com.green.paging.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.board.dto.BoardDto;

@Mapper
public interface BoardPagingMapper {

	int count(BoardDto boarddto, String searchType, String keyword);
	

	List<BoardDto> getBoardPagingList(String menu_id, String searchType, String keyword, int offset, int numOfRows);


	BoardDto getBoard(BoardDto boardDto);


	void insertBoard(BoardDto boarddto);


	void incHit(BoardDto boarddto);


	void deleteBoard(BoardDto boarddto);


	void updateBoard(BoardDto boarddto);


	

}
