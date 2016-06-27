package com.java.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.entity.PageBean;
import com.java.entity.User;
import com.java.service.UserService;
import com.java.util.ResponseUtil;
import com.java.util.StringUtil;
import com.sun.mail.iap.Response;

/**
 * �û�Controller��
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	/**
	 * ɾ���û�
	 * @param ids
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids") String ids,HttpServletResponse res) throws Exception{
		String[] idStr = ids.split(",");
		JSONObject jsonObject = new JSONObject();
		for (String id : idStr) {
			userService.delete(Integer.parseInt(id));
		}
		jsonObject.put("success", true);
		ResponseUtil.write(res, jsonObject);
		return null;
	}
	
	/**
	 * ���ӻ����޸�
	 * @param user
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(User user,HttpServletResponse res) throws Exception{
		//������¼��������ʼ��Ϊ0
		int resultTotal = 0;
		if (user.getId() == null) {
			resultTotal = userService.add(user);
		}else{
			resultTotal = userService.update(user);
		}
		JSONObject jsonObject = new JSONObject();
		if(resultTotal > 0){   //˵���޸Ļ����ӳɹ�
			jsonObject.put("success", true);
		}else{
			jsonObject.put("success", false);
		}
		ResponseUtil.write(res, jsonObject);
		return null;
	}
	
	/**
	 * �û���¼
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(User user, HttpServletRequest request) throws Exception{
		User resultUser = userService.login(user);
		if (resultUser == null) {
			request.setAttribute("user", resultUser);
			request.setAttribute("errorMsg", "�û������������");
			return "login";
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", resultUser);
			return "redirect:/main.jsp";
		}
	}
	
	/**
	 * �û���ҳ��ѯ
	 * @param page
	 * @param rows
	 * @param s_user
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false) String page,@RequestParam(value="rows",required=false) String rows,User s_user,HttpServletResponse res) throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userName", StringUtil.formatLike(s_user.getUserName()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<User> userList=userService.find(map);
		Long total=userService.getTotal(map);
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(userList);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(res, result);
		return null;
	}
	
	
}