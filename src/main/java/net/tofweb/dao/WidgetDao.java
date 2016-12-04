package net.tofweb.dao;

import net.tofweb.model.Widget;

public interface WidgetDao {
	Widget save(Widget widget);

	Widget findById(Integer widgetId);

	// void update(Widget widget);
	//
	// void delete(Widget widget);

}
