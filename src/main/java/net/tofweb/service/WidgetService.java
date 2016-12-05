package net.tofweb.service;

import java.util.Collection;

import net.tofweb.model.Widget;

public interface WidgetService {

	Widget save(Widget widget);

	Widget findById(Integer widgetId);

	Collection<Widget> findAll();

	void delete(Integer widgetId);
}
