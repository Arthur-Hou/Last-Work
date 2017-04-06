package com.extr.service;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import com.extr.util.MenuItem;

@Service
public interface SystemService {

	public LinkedHashMap<String,MenuItem> getMenuItemsByAuthority(String authority);
}
