/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.yourcompany.struts.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.cj.bean.UserBean;
import com.cj.dao.textDao;
import com.cj.dto.UserDto;
import com.cj.dto.textDto;
import com.yourcompany.struts.form.TextActionForm;

/** 
 * MyEclipse Struts
 * Creation date: 03-18-2009
 * 
 * XDoclet definition:
 * @struts.action path="/textAction" name="textActionForm" input="/form/textAction.jsp" scope="request" validate="true"
 */
public class TextAction extends DispatchAction{
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward textDescript(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TextActionForm textActionForm = (TextActionForm) form;// TODO Auto-generated method stub
		textDto textdto = new textDto();
		//ContentDto cd=new ContentDto();
		PrintWriter pw=null;
		try {
			pw=response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		textdto.setTextDescript(textActionForm.getTextDescript());
		textdto.setContentName(textActionForm.getContentName());
		textdto.setCategory(textActionForm.getCategory());
		textdto.setUserName(textActionForm.getUserName());
		
		textDao ud=new textDao();
		boolean b=ud.textDescript(textdto);
		if(b){
				pw.print("success");
		}else
			pw.print("failure");
		return null;
	}
}