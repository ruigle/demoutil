package cn.nong.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *首页综合链接管理
 */
public class Zhlj {
	private static final long serialVersionUID = -8402197082381702158L;
	private String id;
	private String title;//链接名
	private String status;//链接是否显示到门户。y:显示；n：不显示；默认是n
	private String type;//信息进村入户平台:ynpt  综合链接:zhlj
	private int ranking;//排序
	private String url;//链接
	private List<String> kids;
	private String[] notes;
	private Map<String, String> map = new HashMap<String,String>();
	
	
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	public String[] getNotes() {
		return notes;
	}
	public void setNotes(String[] notes) {
		this.notes = notes;
	}
	public List<String> getKids() {
		return kids;
	}
	public void setKids(List<String> kids) {
		this.kids = kids;
	}
	public static final String Zhlj_status_y = "y";//推荐
	public static final String Zhlj_status_n = "n";//不推荐
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Zhlj [id=" + id + ", title=" + title + ", status=" + status
				+ ", type=" + type + ", ranking=" + ranking + ", url=" + url
				+ "]";
	}
	
	
	
	
}
