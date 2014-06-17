package org.sigaim.siie.seql.parser.model;

import java.util.ArrayList;
import java.util.List;

import org.openehr.am.parser.ContentObject;
import org.sigaim.siie.seql.engine.exceptions.SEQLException;

public class SEQLResultSet {
	private List<SEQLSelectCondition> selectConditions;
	private List<SEQLResultSetRow> rows;
	private SEQLResultSetRow currentRow;
	private int currentRowIndex=-1;
	
	public SEQLResultSet(List<SEQLSelectCondition> condition) {
		this.selectConditions=condition;
		this.rows=new ArrayList<SEQLResultSetRow>();
		this.currentRow=null;
	}
	public void addRow() {
		if(currentRow!=null) {
			this.rows.add(currentRow);
		}
		currentRow=new SEQLResultSetRow();
	}
	public void compile() {
		this.addRow();
		this.currentRow=null;
		this.currentRowIndex=-1;
	}
	public void appendToRow(ContentObject object) throws SEQLException{
		if(currentRow==null) {
			throw new SEQLException("Invalid cursor position when building result set");
		} else {
			currentRow.appendColumnValue(object);
		}
	}
	public boolean nextRow() throws SEQLException {
		if(currentRowIndex>=rows.size()-1) {
			return false;
		} else {
			currentRow=this.rows.get(++currentRowIndex);
			return true;
		}
	}
	public ContentObject getColumn(int index) throws SEQLException {
		if(currentRow==null) throw new SEQLException("Invalid cursor position");
		if(index>=selectConditions.size()) throw new SEQLException("Column "+index+" out of bounds");
		else return this.currentRow.getColumnAtIndex(index);
	}
	public int getNumberOfColumns() {
		return selectConditions.size();
	}
}
