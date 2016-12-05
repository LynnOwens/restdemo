package net.tofweb.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.tofweb.dao.WidgetDao;
import net.tofweb.model.Widget;

/**
 * @author Lynn Owens
 *
 *         Widget data access.
 */
@Repository
@Transactional
public class WidgetDaoImpl implements WidgetDao {

	@Resource
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.tofweb.dao.WidgetDao#save(net.tofweb.model.Widget)
	 */
	public Widget save(Widget widget) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(widget);

		return widget;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.tofweb.dao.WidgetDao#findById(java.lang.Integer)
	 */
	public Widget findById(Integer widgetId) {
		Session currentSession = sessionFactory.getCurrentSession();
		return (Widget) currentSession.get(Widget.class, widgetId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.tofweb.dao.WidgetDao#findAll()
	 */
	@SuppressWarnings("unchecked")
	public Collection<Widget> findAll() {
		Session currentSession = sessionFactory.getCurrentSession();
		return (List<Widget>) currentSession.createQuery("from Widget").getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.tofweb.dao.WidgetDao#delete(net.tofweb.model.Widget)
	 */
	public void delete(Widget widget) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.delete(widget);
	}
}
