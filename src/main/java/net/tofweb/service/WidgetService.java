package net.tofweb.service;

import net.tofweb.model.Widget;

public interface WidgetService {

	Widget save(Widget widget);

	Widget findById(Integer widgetId);
}
