package org.sigaim.siie.interfaces.reportmanagement;

import javax.xml.bind.annotation.XmlEnumValue;

import org.sigaim.siie.iso13606.rm.VersionStatus;


/* *
 * This class specifies the report status both in the ISO-13606-5 norm
 * and in the specific report status for SIGAIM
 * 
 * Note both are inter-related
 * 
 * An specific mapping will be automatically used from the SIGAIM report state
 * to the normative report state
 * 
 * The state for SIGAIM is encoded using 5 boolean values.
 * 1. Dictated: the performer has finished dictating the report text (e.g by pressing the analyze button in CSIG)
 * 2. Signed: the performer has agreed to publish this report as-is, independently of possible mistakes in automated analysis. The report is now valid within the system,
 *		even if it is not accessible within the SGE. This somehow acts as an override to every other state
 * 3. Analyzed: the text of the report has been analyzed by the SAPRM, although the performer has not yet validated these concepts.
 * 4. Valid: the result of the analysis is valid wrt the current archetype model of the system.
 * 5. Confirmed: the performed has modified the analysis of the SAPRM. The analysis is consistent with the current archetype model. 
 * 
 * Analyzed and valid are under control of the SIIE and are not exposed
 * 
 * The normative status is used for version_status in audit_info
 * DRAFT("draft"): draft shall be used for any version that is not signed by the performer 
 * FINISHED("finished"): shall be used only if the report is valid, confirmed and signed. . 
 * UPDATE("update"): should not be used in this context. A new report will be added in a new composition. 
 * CORRECTION("correction"): will be used for further updates of the same report. 
 * DELETION("deletion"): will not be used. 
 * 
 * */

public class ReportStatus {
	private boolean dictated;
	private boolean confirmed;
	private boolean signed;
	
	public boolean isDictated() {
		return dictated;
	}
	public void setDictated(boolean dictaded) {
		this.dictated = dictaded;
	}
	public boolean isSigned() {
		return signed;
	}
	public void setSigned(boolean signed) {
		this.signed = signed;
	}
 
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	public VersionStatus getVersionStatus() {
		if(confirmed) return VersionStatus.FINISHED;
		return VersionStatus.DRAFT;
		
	}
	
}
