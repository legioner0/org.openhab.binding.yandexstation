/*
 * Copyright (c) 2010-2023 Contributors to the openHAB project
 *
 *  See the NOTICE file(s) distributed with this work for additional
 *  information.
 *
 * This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.openhab.binding.yandexstation.internal.actions;

import org.openhab.binding.yandexstation.internal.YandexStationHandlerFactory;
import org.openhab.core.automation.Visibility;
import org.openhab.core.automation.type.ActionType;
import org.openhab.core.automation.type.Input;
import org.openhab.core.config.core.ConfigDescriptionParameter;
import org.openhab.core.config.core.ConfigDescriptionParameterBuilder;
import org.openhab.core.config.core.FilterCriteria;

import java.util.ArrayList;
import java.util.List;

public class SayTextActionType extends ActionType {
    public static final String UID = "yandexstation.sayText";
    public static final String CONFIG_PARAM_NAME_TEXT = "sayText";
    public static final String CONFIG_PARAM_NAME_STATION = "station";
    public static final String CONFIG_TEXT = "Say Text";
    public static final String CONFIG_TEXT_DESCR = "Send text to Yandex Station for speak";
    public static final String CONFIG_STATION = "Select Station";
    public static final String CONFIG_STATION_DESCR = "Select Station";

    public static ActionType initialize() {
        // это описание конфигурационных параметров после открытия окна Add Action
        final ConfigDescriptionParameter textParam = ConfigDescriptionParameterBuilder.create(CONFIG_PARAM_NAME_TEXT, ConfigDescriptionParameter.Type.TEXT)
                .withRequired(true).withReadOnly(false).withMultiple(false).withLabel(CONFIG_TEXT)
                .withDescription(CONFIG_TEXT_DESCR).build();

        //FilterCriteria filterCriteria = new FilterCriteria("id", "yandexstation:station");
        FilterCriteria filterCriteria = new FilterCriteria("bindingId", "yandexstation");
        final ConfigDescriptionParameter stationParam = ConfigDescriptionParameterBuilder.create(CONFIG_PARAM_NAME_STATION,
                        ConfigDescriptionParameter.Type.TEXT)
                .withRequired(true).withReadOnly(false).withMultiple(false).withLabel(CONFIG_STATION)
                .withFilterCriteria(List.of(filterCriteria))
                .withContext("thing")
                .withDescription(CONFIG_STATION_DESCR).build();

        List<ConfigDescriptionParameter> config = new ArrayList<ConfigDescriptionParameter>();
        config.add(textParam);
        config.add(stationParam);

        Input textInput = new Input(SayTextActionType.CONFIG_PARAM_NAME_TEXT, String.class.getName(), CONFIG_TEXT, CONFIG_TEXT_DESCR, null, true, null, null);
        Input stationInput = new Input(SayTextActionType.CONFIG_PARAM_NAME_STATION, YandexStationHandlerFactory.class.getName(), CONFIG_STATION, CONFIG_STATION_DESCR, null, true, null, null);
        List<Input> input = new ArrayList<>();
        input.add(textInput);
        input.add(stationInput);

        return new SayTextActionType(config, input);
    }

    public SayTextActionType(List<ConfigDescriptionParameter> config, List<Input> input) {
        super(UID, config, CONFIG_TEXT, CONFIG_TEXT_DESCR, null,
                Visibility.VISIBLE, input, null);
        // отображается в окне выбора типов экшенов
    }
}
