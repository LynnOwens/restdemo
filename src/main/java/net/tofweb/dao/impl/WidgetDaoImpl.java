package net.tofweb.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.tofweb.dao.WidgetDao;
import net.tofweb.model.Widget;

@Repository
@Transactional
public class WidgetDaoImpl implements WidgetDao {

	@Resource
	SessionFactory sessionFactory;

	public Widget save(Widget widget) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(widget);

		return widget;
	}

	public Widget findById(Integer widgetId) {
		Session currentSession = sessionFactory.getCurrentSession();
		return (Widget) currentSession.get(Widget.class, widgetId);
	}
}
