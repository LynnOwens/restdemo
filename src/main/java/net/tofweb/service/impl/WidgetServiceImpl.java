package net.tofweb.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.tofweb.dao.WidgetDao;
import net.tofweb.model.Widget;
import net.tofweb.service.WidgetService;

@Service
@Transactional
public class WidgetServiceImpl implements WidgetService {

	@Resource
	private WidgetDao widgetDao;

	public Widget save(Widget widget) {
		return widgetDao.save(widget);
	}

	public Widget findById(Integer widgetId) {
		return widgetDao.findById(widgetId);
	}
}
