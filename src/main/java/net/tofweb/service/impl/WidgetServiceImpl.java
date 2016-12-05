package net.tofweb.service.impl;

import java.util.Collection;

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

	public Collection<Widget> findAll() {
		return widgetDao.findAll();
	}

	public void delete(Integer widgetId) {
		Widget widget = new Widget();
		widget.setId(widgetId);
		widgetDao.delete(widget);
	}
}
