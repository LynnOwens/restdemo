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

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.tofweb.service.WidgetService#save(net.tofweb.model.Widget)
	 */
	public Widget save(Widget widget) {
		return widgetDao.save(widget);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.tofweb.service.WidgetService#findById(java.lang.Integer)
	 */
	public Widget findById(Integer widgetId) {
		return widgetDao.findById(widgetId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.tofweb.service.WidgetService#findAll()
	 */
	public Collection<Widget> findAll() {
		return widgetDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.tofweb.service.WidgetService#delete(java.lang.Integer)
	 */
	public void delete(Integer widgetId) {
		Widget widget = new Widget();
		widget.setId(widgetId);
		widgetDao.delete(widget);
	}
}
