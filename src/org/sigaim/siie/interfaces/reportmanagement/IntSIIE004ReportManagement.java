package org.sigaim.siie.interfaces.reportmanagement;

import org.sigaim.siie.iso13606.rm.CDCV;
import org.sigaim.siie.iso13606.rm.FunctionalRole;
import org.sigaim.siie.iso13606.rm.II;
import org.sigaim.siie.iso13606.rm.VersionStatus;
import org.sigaim.siie.rm.exceptions.RejectException;

public interface IntSIIE004ReportManagement {
	public ReturnValueCreateHealthcareFacility createHealthcareFacility(String requestId) throws RejectException;
	public ReturnValueCreateSubjectOfCare createSubjectOfCare(String requestId) throws RejectException;
	public ReturnValueCreatePerformer createPerformer(String requestId) throws RejectException;
	public ReturnValueCreateReport createReport(String requestId,
			II ehrId, FunctionalRole composerId, 
			String textTranscription, boolean dictated, II rootArchetypeId)
			throws RejectException;
	public ReturnValueUpdateReport updateReport(
			String requestId,
			II ehrId, //the id of the previous version
			II previousVersionId, 
			FunctionalRole composerId,
			String textTranscription,
			ReportStatus rstatus,
			II rootArchetypeId,
			String encodedConcepts
			) throws RejectException;
}
