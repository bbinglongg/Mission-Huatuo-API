package com.hase.huatuo.healthcheck.model.response;

import java.util.HashMap;
import java.util.Map;

public class DatadictGetResponse {
	private Map<String, Map<String, String>> area;
	private Map<String, String> building;
	private Map<String, Map<String, String>> vpn;
	
	public Map<String, Map<String, String>> getArea() {
		return area;
	}

	public void setArea(Map<String, Map<String, String>> area) {
		this.area = area;
	}

	public Map<String, String> getBuilding() {
		return building;
	}

	public void setBuilding(Map<String, String> building) {
		this.building = building;
	}

	public Map<String, Map<String, String>> getVpn() {
		return vpn;
	}

	public void setVpn(Map<String, Map<String, String>> vpn) {
		this.vpn = vpn;
	}

	public DatadictGetResponse() {
		area = new HashMap<String, Map<String, String>>() {
			{
				put("GZ", new HashMap<String, String>() {
					{
						put("en-hk", "Guangzhou Office Status");
						put("zh-cn", "广州办公室情况");
					}
				});
				put("XA", new HashMap<String, String>() {
					{
						put("en-hk", "Xi'an Office Status");
						put("zh-cn", "西安办公室情况");
					}
				});
			}
		};
		
		building = new HashMap<String, String>() {
			{
				put("1", "None");
				put("2", "TKH OT1");
				put("3", "TKH OT2");
				put("4", "Jiangwan Office (Wework)");
				put("5", "Pazhou ODC");
				put("6", "Tancun ODC");
				put("7", "Renfeng ODC");
				put("8", "Xi'an Centre");
			}
		};
		
		vpn = new HashMap<String, Map<String, String>>() {
			{
				put("1", new HashMap<String, String>() {
					{
						put("en-hk", "CN VPN");
						put("zh-cn", "中国VPN");
					}
				});
				put("2", new HashMap<String, String>() {
					{
						put("en-hk", "HK VPN");
						put("zh-cn", "香港VPN");
					}
				});
			}
		};
	}
	
	
}
