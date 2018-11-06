package com.liferay.lms.upgrade;


import java.util.List;

import com.liferay.lms.model.LmsPrefs;
import com.liferay.lms.service.LmsPrefsLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;

public class UpgradeVersion_3_7_0 extends UpgradeProcess {
	private static Log log = LogFactoryUtil.getLog(UpgradeVersion_3_7_0.class);
	private static String ADD_ACTIVITY = "ADD_ACTIVITY";
	
	public int getThreshold() {
		return 370;
	}

	protected void doUpgrade() throws Exception {
		log.info("Actualizando version a 3.7");

		
		/*********************************************************************************************/
		/*****************AÑADIMOS PERMISOS AÑADIR ACTIVIDAD AL EDITOR DE CURSOS**************/
		/*********************************************************************************************/
		
		List<Company> listCompanies = CompanyLocalServiceUtil.getCompanies();
		
		LmsPrefs lmsPrefs = null;
		long editorRoleId = 0;
		
		for(Company company: listCompanies){
			log.info("Permisos para company: " + company.getCompanyId());
			try {
				lmsPrefs = LmsPrefsLocalServiceUtil.getLmsPrefs(company.getCompanyId());
				editorRoleId = lmsPrefs.getEditorRole();
				try {
					ResourcePermissionLocalServiceUtil.addResourcePermission(company.getCompanyId(), "com.liferay.lms.learningactivity.ResourceExternalLearningActivityType", ResourceConstants.SCOPE_GROUP_TEMPLATE, "0", editorRoleId, ADD_ACTIVITY);
				} catch (PortalException | SystemException e) {
					e.printStackTrace();
				} 
				try {
					ResourcePermissionLocalServiceUtil.addResourcePermission(company.getCompanyId(), "com.liferay.lms.learningactivity.ResourceInternalLearningActivityType", ResourceConstants.SCOPE_GROUP_TEMPLATE, "0", editorRoleId, ADD_ACTIVITY);
				} catch (PortalException | SystemException e) {
					e.printStackTrace();
				} 
				try {
					ResourcePermissionLocalServiceUtil.addResourcePermission(company.getCompanyId(), "com.liferay.lms.learningactivity.SurveyLearningActivityType", ResourceConstants.SCOPE_GROUP_TEMPLATE, "0", editorRoleId, ADD_ACTIVITY);
				} catch (PortalException | SystemException e) {
					e.printStackTrace();
				}
				try {
					ResourcePermissionLocalServiceUtil.addResourcePermission(company.getCompanyId(), "com.liferay.lms.learningactivity.TaskEvaluationLearningActivityType", ResourceConstants.SCOPE_GROUP_TEMPLATE, "0", editorRoleId, ADD_ACTIVITY);
				} catch (PortalException | SystemException e) {
					e.printStackTrace();
				}
				try {
					ResourcePermissionLocalServiceUtil.addResourcePermission(company.getCompanyId(), "com.liferay.lms.learningactivity.TaskOfflineLearningActivityType", ResourceConstants.SCOPE_GROUP_TEMPLATE, "0", editorRoleId, ADD_ACTIVITY);
				} catch (PortalException | SystemException e) {
					e.printStackTrace();
				}
				try {
					ResourcePermissionLocalServiceUtil.addResourcePermission(company.getCompanyId(), "com.liferay.lms.learningactivity.TaskOnlineLearningActivityType", ResourceConstants.SCOPE_GROUP_TEMPLATE, "0", editorRoleId, ADD_ACTIVITY);
				} catch (PortalException | SystemException e) {
					e.printStackTrace();
				}
				try {
					ResourcePermissionLocalServiceUtil.addResourcePermission(company.getCompanyId(), "com.liferay.lms.learningactivity.TaskP2PLearningActivityType", ResourceConstants.SCOPE_GROUP_TEMPLATE, "0", editorRoleId, ADD_ACTIVITY);
				} catch (PortalException | SystemException e) {
					e.printStackTrace();
				}
				try {
					ResourcePermissionLocalServiceUtil.addResourcePermission(company.getCompanyId(), "com.liferay.lms.learningactivity.TestLearningActivityType", ResourceConstants.SCOPE_GROUP_TEMPLATE, "0", editorRoleId, ADD_ACTIVITY);
				} catch (PortalException | SystemException e) {
					e.printStackTrace();
				}
			} catch (PortalException | SystemException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}