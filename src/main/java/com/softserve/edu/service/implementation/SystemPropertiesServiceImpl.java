package com.softserve.edu.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.dao.SystemPropertiesDao;
import com.softserve.edu.service.SystemPropertiesService;

@Service
public class SystemPropertiesServiceImpl implements SystemPropertiesService {

	@Autowired
	SystemPropertiesDao systemPropertiesDao;

	@Override
	@Transactional
	public String getImagePath() {
		return systemPropertiesDao.getPropeties("IMAGE_PATH").get(0).getValue();
	}
}