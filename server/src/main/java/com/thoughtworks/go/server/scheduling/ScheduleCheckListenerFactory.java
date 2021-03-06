/*************************GO-LICENSE-START*********************************
 * Copyright 2014 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *************************GO-LICENSE-END***********************************/

package com.thoughtworks.go.server.scheduling;

import com.thoughtworks.go.server.service.support.DaemonThreadStatsCollector;
import com.thoughtworks.go.server.perf.SchedulingPerformanceLogger;
import com.thoughtworks.go.util.SystemEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleCheckListenerFactory {
    private ScheduleCheckCompletedTopic topic;
    private BuildCauseProducerService producerService;
    private ScheduleCheckQueue queue;
    private SystemEnvironment systemEnvironment;
    private SchedulingPerformanceLogger schedulingPerformanceLogger;
    private final DaemonThreadStatsCollector daemonThreadStatsCollector;

    @Autowired
    public ScheduleCheckListenerFactory(ScheduleCheckCompletedTopic topic,
                                        BuildCauseProducerService producerService,
                                        ScheduleCheckQueue queue,
                                        SystemEnvironment systemEnvironment,
                                        SchedulingPerformanceLogger schedulingPerformanceLogger, DaemonThreadStatsCollector daemonThreadStatsCollector) {
        this.topic = topic;
        this.producerService = producerService;
        this.queue = queue;
        this.systemEnvironment = systemEnvironment;
        this.schedulingPerformanceLogger = schedulingPerformanceLogger;
        this.daemonThreadStatsCollector = daemonThreadStatsCollector;
    }

    public void init(){
        int numberOfListeners = systemEnvironment.getNumberOfMaterialCheckListener();

        for (int i = 0; i < numberOfListeners; i++) {
            queue.addListener(new ScheduleCheckListener(topic, producerService, schedulingPerformanceLogger));
        }
    }
}
