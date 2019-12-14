package com.cos.bsymWeb.model;


public enum BoardDivision {
	BSFOOD("cook"), BSRESTAURANT("res");
	
	private String boardDivision;
	
    private BoardDivision(String boardDivision) { 
        this.boardDivision = boardDivision;
    }

	public String getName() {
		return boardDivision;
	}
    
    
}
