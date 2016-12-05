package net.tofweb.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.tofweb.dao.AbstractDao;
import net.tofweb.dao.WidgetDao;
import net.tofweb.model.Widget;

@Repository
@Transactional
public class WidgetDaoImpl extends AbstractDao implements WidgetDao {

	@Resource
	private SessionFactory sessionFactory;

	public Widget save(Widget widget) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(widget);

		return widget;
	}

	public Widget findById(Integer widgetId) {
		Session currentSession = sessionFactory.getCurrentSession();
		return (Widget) currentSession.get(Widget.class, widgetId);
	}

	@SuppressWarnings("unchecked")
	public Collection<Widget> findAll() {
		Session currentSession = sessionFactory.getCurrentSession();
		return (List<Widget>) currentSession.createQuery("from Widget").getResultList();
	}
}
