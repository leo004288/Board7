package com.green.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.board.dto.BoardDto;
import com.green.menus.dto.MenuDTO;

@Mapper
public interface BoardMapper {

	List<BoardDto> getBoardList(MenuDTO menudto);

	void deleteBoard(BoardDto boarddto);

	BoardDto getBoard(BoardDto boarddto);

	void incHit(BoardDto boarddto);

	void insertBoard(BoardDto boarddto);

	void updateBoard(BoardDto boarddto);

	

}
