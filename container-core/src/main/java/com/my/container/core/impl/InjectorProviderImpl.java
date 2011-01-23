/*
 * Copyright 2011 Kevin Pollet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.my.container.core.impl;

import com.my.container.core.Configuration;
import com.my.container.core.InjectorProvider;

/**
 * @author Kevin Pollet
 */
public class InjectorProviderImpl implements InjectorProvider {

	public Configuration configure() {
		System.out.println("Configuration");
		return new InjectorConfiguration();
	}
}