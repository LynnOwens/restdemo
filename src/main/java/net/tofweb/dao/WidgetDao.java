package net.tofweb.dao;

import java.util.Collection;

import net.tofweb.model.Widget;

public interface WidgetDao {
	Widget save(Widget widget);

	Widget findById(Integer widgetId);

	Collection<Widget> findAll();

	void delete(Widget widget);

}
