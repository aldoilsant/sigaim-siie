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
	public ReturnValueCreateReport createReport(
			String requestId,
			II ehrId,
			FunctionalRole composerId,
			String audioData,
			String textTranscription,
			CDCV reportStatus,
			II rootArchetypeId
			) throws RejectException;
	public ReturnValueUpdateReport updateReport(
			String requestId,
			II reportId,
			String audioData,
			String textTranscription,
			CDCV reportStatus,
			II rootArchetypeId,
			VersionStatus reason
			) throws RejectException;
}
