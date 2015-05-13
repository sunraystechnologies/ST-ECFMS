package in.co.sunrays.util;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import in.co.sunrays.ocha.bean.UserBean;
import in.co.sunrays.ocha.controller.ORSView;
import in.co.sunrays.ocha.model.AppRoles;

public class MenuBuilder {

	public static final int HORIZONTAL = 1;
	public static final int VERTICAL = 2;
	public static final String separator = " | ";

	public static String getLink(String text, String url) {
		return "<a href='" + url + "'>" + text + "</a>";
	}

	public static String getHorizontalLink(HashMap<String, String> hmap) {
		StringBuffer sb = new StringBuffer(separator + "");
		Iterator<String> keys = hmap.keySet().iterator();

		String key = null;
		String value = null;
		while (keys.hasNext()) {
			key = keys.next();
			value = hmap.get(key);
			sb.append(getLink(key, value) + separator);
		}
		return sb.toString();
	}

	public static String getVericalLink(HashMap<String, String> hmap) {
		/*
		 * <UL> <LI> </LI> </UL>
		 */
		StringBuffer sb = new StringBuffer("<UL>");

		Iterator<String> keys = hmap.keySet().iterator();
		String key = null;
		String value = null;
		while (keys.hasNext()) {
			key = keys.next();
			value = hmap.get(key);
			sb.append("<LI>" + getLink(key, value) + "</LI>");
		}
		sb.append("</UL>");
		return sb.toString();
	}

	public static String getMenu(long roleId) {
		return getMenu(roleId, HORIZONTAL);
	}
	public static String getMenu(long roleId, int i) {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("Home", ORSView.LOGIN_CTL);

		if (roleId == AppRoles.COMMISSIONER) {
			map.put("Hot News", ORSView.HOT_NEWS_CTL);
			map.put("Hot News List", ORSView.HOT_NEWS_LIST_CTL);
			map.put("Crime Report", ORSView.CRIMEREPORT_CTL);
			map.put("Crime Report List", ORSView.CRIMEREPORT_LIST_CTL);
			map.put("Complaint", ORSView.COMPLAINT_CTL);
			map.put("Complaint List", ORSView.COMPLAINT_LIST_CTL);
			map.put("Feedback", ORSView.FEEDBACK_CTL);
			map.put("Feedback List", ORSView.FEEDBACK_LIST_CTL);
			map.put("Mail", ORSView.MAIL_CTL);
			map.put("Mail List", ORSView.MAIL_LIST_CTL);
			map.put("Most Wanted", ORSView.MOSTWANTED_CTL);
			map.put("Most Wanted List", ORSView.MOSTWANTED_LIST_CTL);
			map.put("Missing Person", ORSView.MISSINGPERSON_CTL);
			map.put("Missing Person List", ORSView.MISSINGPERSON_LIST_CTL);
			map.put("Police Station", ORSView.POLICESTATION_CTL);
			map.put("Police Station List", ORSView.POLICESTATION_LIST_CTL);
			map.put("Inbox", ORSView.INBOX_LIST_CTL);
			map.put("Send Items", ORSView.SENDBOX_LIST_CTL);
			map.put("My Profile", ORSView.MY_PROFILE_CTL);
		
		} else if (roleId == AppRoles.USER) {
			map.put("Complaint", ORSView.COMPLAINT_CTL);
			map.put("Crime Report", ORSView.CRIMEREPORT_CTL);
			map.put("Missing Person", ORSView.MISSINGPERSON_CTL);
			map.put("Feedback", ORSView.FEEDBACK_CTL);
			map.put("Mail", ORSView.MAIL_CTL);
			map.put("My Profile", ORSView.MY_PROFILE_CTL);
		}
		 else if (roleId == AppRoles.INSPECTOR) {
			 map.put("Hot News List", ORSView.HOT_NEWS_LIST_CTL);
			 map.put("Missing Person List", ORSView.MISSINGPERSON_LIST_CTL);
			 
		 }
		
		if (i == HORIZONTAL) {
			return getHorizontalLink(map);
		} else {
			return getVericalLink(map);
		}
	}
}