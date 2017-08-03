package com.github.pleier.controller.system.user;

import com.github.pleier.controller.system.base.BaseController;
import com.github.pleier.entity.Page;
import com.github.pleier.entity.system.Role;
import com.github.pleier.service.system.menu.MenuManager;
import com.github.pleier.service.system.role.RoleManager;
import com.github.pleier.service.system.user.UserManager;
import com.github.pleier.util.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by PLEI on 8/3/2017.
 */
@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController {

	String menuUrl = "user/listUsers.do"; //�˵���ַ(Ȩ����)
	@Resource(name="userService")
	private UserManager userService;
	@Resource(name="roleService")
	private RoleManager roleService;
	@Resource(name="menuService")
	private MenuManager menuService;

	/**��ʾ�û��б�
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/listUsers")
	public ModelAndView listUsers(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//�ؼ��ʼ�������
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String lastLoginStart = pd.getString("lastLoginStart");	//��ʼʱ��
		String lastLoginEnd = pd.getString("lastLoginEnd");		//����ʱ��
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			pd.put("lastLoginStart", lastLoginStart+" 00:00:00");
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			pd.put("lastLoginEnd", lastLoginEnd+" 00:00:00");
		}
		page.setPageData(pd);
		List<PageData>	userList = userService.listUsers(page);	//�г��û��б�
		pd.put("ROLE_ID", "1");
		List<Role> roleList = roleService.listAllRolesByPId(pd);//�г�����ϵͳ�û���ɫ
		mv.setViewName("system/user/user_list");
		mv.addObject("userList", userList);
		mv.addObject("roleList", roleList);
		mv.addObject("pd", pd);
		mv.addObject("QX", Jurisdiction.getHC());	//��ťȨ��
		return mv;
	}

	/**ɾ���û�
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteU")
	public void deleteU(PrintWriter out) throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //У��Ȩ��
		logBefore(logger, Jurisdiction.getUsername()+"ɾ��user");
		PageData pd = new PageData();
		pd = this.getPageData();
		userService.deleteU(pd);
		out.write("success");
		out.close();
	}

	/**ȥ�����û�ҳ��
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goAddU")
	public ModelAndView goAddU()throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //У��Ȩ��
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("ROLE_ID", "1");
		List<Role> roleList = roleService.listAllRolesByPId(pd);//�г�����ϵͳ�û���ɫ
		mv.setViewName("system/user/user_edit");
		mv.addObject("msg", "saveU");
		mv.addObject("pd", pd);
		mv.addObject("roleList", roleList);
		return mv;
	}

	/**�����û�
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveU")
	public ModelAndView saveU() throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //У��Ȩ��
		logBefore(logger, Jurisdiction.getUsername()+"����user");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("USER_ID", this.get32UUID());	//ID ����
		pd.put("LAST_LOGIN", "");				//����¼ʱ��
		pd.put("IP", "");						//IP
		pd.put("STATUS", "0");					//״̬
		pd.put("SKIN", "default");
		pd.put("RIGHTS", "");
		pd.put("PASSWORD", new SimpleHash("SHA-1", pd.getString("USERNAME"), pd.getString("PASSWORD")).toString());	//�������
		if(null == userService.findByUsername(pd)){	//�ж��û����Ƿ����
			userService.saveU(pd); 					//ִ�б���
			mv.addObject("msg","success");
		}else{
			mv.addObject("msg","failed");
		}
		mv.setViewName("save_result");
		return mv;
	}

	/**�ж��û����Ƿ����
	 * @return
	 */
	@RequestMapping(value="/hasU")
	@ResponseBody
	public Object hasU(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(userService.findByUsername(pd) != null){
				errInfo = "error";
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//���ؽ��
		return AppUtil.returnObject(new PageData(), map);
	}

	/**�ж������Ƿ����
	 * @return
	 */
	@RequestMapping(value="/hasE")
	@ResponseBody
	public Object hasE(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(userService.findByUE(pd) != null){
				errInfo = "error";
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//���ؽ��
		return AppUtil.returnObject(new PageData(), map);
	}

	/**�жϱ����Ƿ����
	 * @return
	 */
	@RequestMapping(value="/hasN")
	@ResponseBody
	public Object hasN(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(userService.findByUN(pd) != null){
				errInfo = "error";
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//���ؽ��
		return AppUtil.returnObject(new PageData(), map);
	}

	/**ȥ�޸��û�ҳ��(ϵͳ�û��б��޸�)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goEditU")
	public ModelAndView goEditU() throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //У��Ȩ��
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		if("1".equals(pd.getString("USER_ID"))){return null;}		//�����޸�admin�û�
		pd.put("ROLE_ID", "1");
		List<Role> roleList = roleService.listAllRolesByPId(pd);	//�г�����ϵͳ�û���ɫ
		mv.addObject("fx", "user");
		pd = userService.findById(pd);								//����ID��ȡ
		mv.setViewName("system/user/user_edit");
		mv.addObject("msg", "editU");
		mv.addObject("pd", pd);
		mv.addObject("roleList", roleList);
		return mv;
	}

	/**ȥ�޸��û�ҳ��(�����޸�)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goEditMyU")
	public ModelAndView goEditMyU() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		mv.addObject("fx", "head");
		pd.put("ROLE_ID", "1");
		List<Role> roleList = roleService.listAllRolesByPId(pd);	//�г�����ϵͳ�û���ɫ
		pd.put("USERNAME", Jurisdiction.getUsername());
		pd = userService.findByUsername(pd);						//�����û�����ȡ
		mv.setViewName("system/user/user_edit");
		mv.addObject("msg", "editU");
		mv.addObject("pd", pd);
		mv.addObject("roleList", roleList);
		return mv;
	}

	/**�鿴�û�
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/view")
	public ModelAndView view() throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //У��Ȩ��
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		if("admin".equals(pd.getString("USERNAME"))){return null;}	//���ܲ鿴admin�û�
		pd.put("ROLE_ID", "1");
		List<Role> roleList = roleService.listAllRolesByPId(pd);	//�г�����ϵͳ�û���ɫ
		pd = userService.findByUsername(pd);						//����ID��ȡ
		mv.setViewName("system/user/user_view");
		mv.addObject("msg", "editU");
		mv.addObject("pd", pd);
		mv.addObject("roleList", roleList);
		return mv;
	}

	/**ȥ�޸��û�ҳ��(���߹���ҳ���)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goEditUfromOnline")
	public ModelAndView goEditUfromOnline() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		if("admin".equals(pd.getString("USERNAME"))){return null;}	//���ܲ鿴admin�û�
		pd.put("ROLE_ID", "1");
		List<Role> roleList = roleService.listAllRolesByPId(pd);	//�г�����ϵͳ�û���ɫ
		pd = userService.findByUsername(pd);						//����ID��ȡ
		mv.setViewName("system/user/user_edit");
		mv.addObject("msg", "editU");
		mv.addObject("pd", pd);
		mv.addObject("roleList", roleList);
		return mv;
	}

	/**
	 * �޸��û�
	 */
	@RequestMapping(value="/editU")
	public ModelAndView editU() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"�޸�ser");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		if(!Jurisdiction.getUsername().equals(pd.getString("USERNAME"))){		//�����ǰ��¼�û��޸��û������ύ���û����Ǳ���
			if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}  //У��Ȩ�� �жϵ�ǰ�����������û�����鿴Ȩ��
			if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //У��Ȩ���жϵ�ǰ�����������û������޸�Ȩ��
			if("admin".equals(pd.getString("USERNAME")) && !"admin".equals(Jurisdiction.getUsername())){return null;}	//��admin�û������޸�admin
		}else{	//�����ǰ��¼�û��޸��û������ύ���û����Ǳ��ˣ������޸ı��˵Ľ�ɫID
			pd.put("ROLE_ID", userService.findByUsername(pd).getString("ROLE_ID")); //�Խ�ɫID��ԭ���˽�ɫID
		}
		if(pd.getString("PASSWORD") != null && !"".equals(pd.getString("PASSWORD"))){
			pd.put("PASSWORD", new SimpleHash("SHA-1", pd.getString("USERNAME"), pd.getString("PASSWORD")).toString());
		}
		userService.editU(pd);	//ִ���޸�
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * ����ɾ��
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAllU")
	@ResponseBody
	public Object deleteAllU() throws Exception {
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //У��Ȩ��
		logBefore(logger, Jurisdiction.getUsername()+"����ɾ��user");
		PageData pd = new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String USER_IDS = pd.getString("USER_IDS");
		if(null != USER_IDS && !"".equals(USER_IDS)){
			String ArrayUSER_IDS[] = USER_IDS.split(",");
			userService.deleteAllU(ArrayUSER_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}

	/**�����û���Ϣ��EXCEL
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			if(Jurisdiction.buttonJurisdiction(menuUrl, "cha")){
				String keywords = pd.getString("keywords");				//�ؼ��ʼ�������
				if(null != keywords && !"".equals(keywords)){
					pd.put("keywords", keywords.trim());
				}
				String lastLoginStart = pd.getString("lastLoginStart");	//��ʼʱ��
				String lastLoginEnd = pd.getString("lastLoginEnd");		//����ʱ��
				if(lastLoginStart != null && !"".equals(lastLoginStart)){
					pd.put("lastLoginStart", lastLoginStart+" 00:00:00");
				}
				if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
					pd.put("lastLoginEnd", lastLoginEnd+" 00:00:00");
				}
				Map<String,Object> dataMap = new HashMap<String,Object>();
				List<String> titles = new ArrayList<String>();
				titles.add("�û���"); 		//1
				titles.add("���");  		//2
				titles.add("����");			//3
				titles.add("ְλ");			//4
				titles.add("�ֻ�");			//5
				titles.add("����");			//6
				titles.add("�����¼");		//7
				titles.add("�ϴε�¼IP");	//8
				dataMap.put("titles", titles);
				List<PageData> userList = userService.listAllUser(pd);
				List<PageData> varList = new ArrayList<PageData>();
				for(int i=0;i<userList.size();i++){
					PageData vpd = new PageData();
					vpd.put("var1", userList.get(i).getString("USERNAME"));		//1
					vpd.put("var2", userList.get(i).getString("NUMBER"));		//2
					vpd.put("var3", userList.get(i).getString("NAME"));			//3
					vpd.put("var4", userList.get(i).getString("ROLE_NAME"));	//4
					vpd.put("var5", userList.get(i).getString("PHONE"));		//5
					vpd.put("var6", userList.get(i).getString("EMAIL"));		//6
					vpd.put("var7", userList.get(i).getString("LAST_LOGIN"));	//7
					vpd.put("var8", userList.get(i).getString("IP"));			//8
					varList.add(vpd);
				}
				dataMap.put("varList", varList);
				ObjectExcelView erv = new ObjectExcelView();					//ִ��excel����
				mv = new ModelAndView(erv,dataMap);
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**���ϴ�EXCELҳ��
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goUploadExcel")
	public ModelAndView goUploadExcel()throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/user/uploadexcel");
		return mv;
	}

	/**����ģ��
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/downExcel")
	public void downExcel(HttpServletResponse response)throws Exception{
		FileDownload.fileDownload(response, PathUtil.getClasspath() + Const.FILEPATHFILE + "Users.xls", "Users.xls");
	}

	/**��EXCEL���뵽���ݿ�
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/readExcel")
	public ModelAndView readExcel(
			@RequestParam(value="excel",required=false) MultipartFile file
			) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;}
		if (null != file && !file.isEmpty()) {
			String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE;								//�ļ��ϴ�·��
			String fileName =  FileUpload.fileUp(file, filePath, "userexcel");							//ִ���ϴ�
			List<PageData> listPd = (List)ObjectExcelRead.readExcel(filePath, fileName, 2, 0, 0);		//ִ�ж�EXCEL����,���������ݵ���List 2:�ӵ�3�п�ʼ��0:�ӵ�A�п�ʼ��0:��0��sheet
			/*�������ݿ����======================================*/
			pd.put("RIGHTS", "");					//Ȩ��
			pd.put("LAST_LOGIN", "");				//����¼ʱ��
			pd.put("IP", "");						//IP
			pd.put("STATUS", "0");					//״̬
			pd.put("SKIN", "default");				//Ĭ��Ƥ��
			pd.put("ROLE_ID", "1");
			List<Role> roleList = roleService.listAllRolesByPId(pd);//�г�����ϵͳ�û���ɫ
			pd.put("ROLE_ID", roleList.get(0).getROLE_ID());		//���ý�ɫIDΪ����һ��
			/**
			 * var0 :���
			 * var1 :����
			 * var2 :�ֻ�
			 * var3 :����
			 * var4 :��ע
			 */
			for(int i=0;i<listPd.size();i++){
				pd.put("USER_ID", this.get32UUID());										//ID
				pd.put("NAME", listPd.get(i).getString("var1"));							//����

				String USERNAME = GetPinyin.getPingYin(listPd.get(i).getString("var1"));	//����������������ȫƴ
				pd.put("USERNAME", USERNAME);
				if(userService.findByUsername(pd) != null){									//�ж��û����Ƿ��ظ�
					USERNAME = GetPinyin.getPingYin(listPd.get(i).getString("var1"))+Tools.getRandomNum();
					pd.put("USERNAME", USERNAME);
				}
				pd.put("BZ", listPd.get(i).getString("var4"));								//��ע
				if(Tools.checkEmail(listPd.get(i).getString("var3"))){						//�����ʽ���Ծ�����
					pd.put("EMAIL", listPd.get(i).getString("var3"));
					if(userService.findByUE(pd) != null){									//�����Ѵ��ھ�����
						continue;
					}
				}else{
					continue;
				}
				pd.put("NUMBER", listPd.get(i).getString("var0"));							//����Ѵ��ھ�����
				pd.put("PHONE", listPd.get(i).getString("var2"));							//�ֻ���

				pd.put("PASSWORD", new SimpleHash("SHA-1", USERNAME, "123").toString());	//Ĭ������123
				if(userService.findByUN(pd) != null){
					continue;
				}
				userService.saveU(pd);
			}
			/*�������ݿ����======================================*/
			mv.addObject("msg","success");
		}
		mv.setViewName("save_result");
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}

}
