package org.sigaim.siie.interfaces.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.dadl.OpenEHRDADLManager;
import org.sigaim.siie.db.PersistenceManager;
import org.sigaim.siie.db.sql.SQLPersistenceManager;
import org.sigaim.siie.rm.ReferenceModelManager;
import org.sigaim.siie.rm.ReflectorReferenceModelManager;

public class ResetDB {
	private DADLManager dadlManager;
	private PersistenceManager persistenceManager;
	private ReferenceModelManager referenceModelManager;
	
	@Before
	public void setUp() throws Exception {
		this.dadlManager=new OpenEHRDADLManager();
		this.referenceModelManager=new ReflectorReferenceModelManager(this.dadlManager);
		SQLPersistenceManager sqlManager=new SQLPersistenceManager();
		sqlManager.setDADLManager(this.dadlManager);
		sqlManager.setReferenceModelManager(this.referenceModelManager);
		this.persistenceManager=sqlManager;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		persistenceManager.reset();
	}

}
