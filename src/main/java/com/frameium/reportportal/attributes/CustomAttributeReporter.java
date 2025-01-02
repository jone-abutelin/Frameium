package com.frameium.reportportal.attributes;

import com.epam.reportportal.cucumber.ScenarioReporter;
import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.ta.reportportal.ws.model.attribute.ItemAttributesRQ;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * Attribute Reporter to demonstrate attribute customization for different cases
 */
public class CustomAttributeReporter extends ScenarioReporter {

	/**
	 * Use this method if you need to add custom attributes on your launch before it start.
	 *
	 * @return a map of attributes
	 */
	private Map<String, String> getCustomLaunchAttributes() {
		return Collections.singletonMap("custom-launch-attribute-name", "custom-launch-attribute-value");
	}

	@Override
	protected StartLaunchRQ buildStartLaunchRq(Date startTime, ListenerParameters parameters) {
		StartLaunchRQ rq = super.buildStartLaunchRq(startTime, parameters);
		getCustomLaunchAttributes().forEach((key, value) -> rq.getAttributes().add(new ItemAttributesRQ(key, value)));
		return rq;
	}
}