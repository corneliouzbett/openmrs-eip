package org.openmrs.eip.mysql.watcher.route;

import org.openmrs.eip.BaseDbBackedCamelTest;
import org.openmrs.eip.mysql.watcher.Event;
import org.openmrs.eip.mysql.watcher.WatcherTestConfig;
import org.openmrs.eip.mysql.watcher.config.WatcherConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Base class for tests for routes that wish to be notified of DB events in the backing OpenMRS
 * database, .
 */
@Import({ WatcherConfig.class, WatcherTestConfig.class })
public abstract class BaseWatcherRouteTest extends BaseDbBackedCamelTest {
	
	protected static final String PROP_EVENT = "event";
	
	protected static final String PROP_RETRY_MAP = "route-retry-count-map";
	
	@Autowired
	protected ConfigurableEnvironment env;
	
	protected Event createEvent(String table, String pkId, String identifier, String operation) {
		Event event = new Event();
		event.setTableName(table);
		event.setPrimaryKeyId(pkId);
		event.setIdentifier(identifier);
		event.setOperation(operation);
		event.setSnapshot(false);
		return event;
	}
	
	/**
	 * Subclasses that need the actual debezium events can override this method and return true
	 * 
	 * @return true if debezium engine should be started otherwise false
	 */
	protected boolean startDebezium() {
		return false;
	}
	
}
