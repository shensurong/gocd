/*
 * Copyright 2018 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import * as m from "mithril";
import {Stream} from "mithril/stream";
import {PluginInfo} from "models/shared/plugin_infos_new/plugin_info";

export interface SaveOperation {
  onSuccessfulSave: (msg: m.Children) => void;
  onError: (msg: m.Children) => void;
}

export interface EditOperation<T> {
  onEdit: (obj: T, e: MouseEvent) => void;
}

export interface DeleteOperation<T> {
  onDelete: (obj: T, e: MouseEvent) => void;
}

export interface RefreshOperation<T> {
  onRefresh: (obj: T, e: MouseEvent) => void;
}

export interface CloneOperation<T> {
  onClone: (obj: T, e: MouseEvent) => void;
}

export interface AddOperation<T> {
  onAdd: (e: MouseEvent) => void;
}

export interface RequiresPluginInfos {
  pluginInfos: Stream<Array<PluginInfo<any>>>;
}

export interface EnableOperation<T> {
  onEnable: (obj: T, e: MouseEvent) => void;
}

export interface DisableOperation<T> {
  onDisable: (obj: T, e: MouseEvent) => void;
}
