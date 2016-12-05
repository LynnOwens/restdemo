package net.tofweb.controller;

import java.net.URI;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.tofweb.model.Widget;
import net.tofweb.service.WidgetService;

@RestController
@RequestMapping("/widget")
public class WidgetController {

	@Resource
	WidgetService widgetService;

	@RequestMapping(method = RequestMethod.GET)
	Collection<Widget> get() {
		return widgetService.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{widgetId}")
	Widget getById(@PathVariable Integer widgetId) {
		return widgetService.findById(widgetId);
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add(@RequestBody Widget widget) {
		Widget result = widgetService.save(widget);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
}
